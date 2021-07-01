import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Formatter;
import java.util.Scanner;
import java.io.*;
import java.io.File;  // Import the FileWriter class
import java.io.FileWriter; 
import java.io.IOException;
import java.net.*;
import java.util.Date;
import java.util.Vector;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
  

public class Janela extends JFrame
{
	private static Formatter Arquivo;
	public static final String HOST_PADRAO  = "localhost";
	public static final int    PORTA_PADRAO = 3000;
	private JComboBox<String> opcoesLabirintos;
	private Parceiro servidor;
	private Labirinto Lab;
	
	/**
     * 
     * Classe tratadora de mouse que vai realizar o tratamento dos clicks nos botões
     * 
     * 
     */
	private class TratadoraDeMouse implements ActionListener
    {
		
		private boolean controller = false;
		private boolean servidorOn = false;
		
		
		
		//abre o arquivo localmente
		private String abrirArquivo (){
            JFileChooser chooser = new JFileChooser();
			int option = chooser.showOpenDialog(null);
			File file = chooser.getSelectedFile();
			String texto = "";
			try (Scanner input = new Scanner(file)) {
				while (input.hasNextLine()) {
					texto += input.nextLine() + "\n";
				}
			} catch(Exception error){
				
			}
			return texto;
        }       
		
		/**
	     * 
	     * Abre o labirinto localmente ou pelo servidor (dependendo se o servidor está ON ou não) após o clique no botão "Abrir labirinto"
	     * 
	     * 
	     */
        private void abrirLabirinto (){
			
            
            if(servidorOn == false){
				
				try{
					areaEdicao.setEditable(true);
					//Limpa a area de ediÃ§Ã£o
					areaEdicao.setText ("");
					
					String file = abrirArquivo ();
					areaEdicao.setText (file);
					Lab =  new Labirinto(file);
					Labirinto copia1 = new Labirinto(Lab);
					Navegador navLab = new Navegador(copia1);				
					//printa o labirinto escolhido na area de edicao
					areaEdicao.setText (""+Lab);
					areaNotificacao.setText ("");
					btn[3].setEnabled(true);		
				
				} catch (Exception error){
					//printa o erro na area de erro
					areaEdicao.setText ("");
					btn[3].setEnabled(false);
					areaNotificacao.setText(error.getMessage());						
				}
				
			} else {
				
				
				String ip;
				try
				{
					areaEdicao.setEditable(true);
					areaEdicao.setText ("");
					
					ip = InetAddress.getLocalHost().getHostAddress(); // pega o ip
					
					Id_Cliente confirmaIP = new Id_Cliente(ip);
					
					servidor.receba(confirmaIP);//envia o ip para o servidor
					
					
					Id_Cliente nomesLabirintos = (Id_Cliente)servidor.envie(); //recebe do servidor
					
					
					
					
					new JanelaComboBox(nomesLabirintos.getStringsBD());
		
				}
				catch(Exception erroIP)
				{
					erroIP.printStackTrace();
					ip = "invalido";

				}
				
			}         
            
            
            
        }
        
        /**
	     * 
	     * Executa o labirinto após o clique no botão "Executar labirinto"
	     * 
	     * 
	     */
        private void executarLabirinto (){
            try{
				
				System.out.println("1");
				//verifica se algum labirinto foi escolhido
				if(Lab == null)
					throw new Exception("Error: Nenhum Labirinto Escolhido");
				
				System.out.println(Lab);	
				
				Navegador navLab = new Navegador(Lab);
				areaEdicao.setText(""+Lab);
				controller = true;		
				btn[3].setEnabled(false);			
			} catch (Exception error){
				//printa o erro na area de erro
				areaNotificacao.setText(error.getMessage());
			}
        }
        
        /**
	     * 
	     * Permite o usuario criar o labirinto após o clique no botão "Criar labirinto"
	     * 
	     * 
	     */
        private void criarLabirinto (){
			
			if(controller ==  true){
				
				areaEdicao.setText("");
				controller = false;
			}
			
            areaEdicao.setEditable(true);
            btn[3].setEnabled(false);
            
        }
        
        /**
	     * 
	     * Salva o labirinto localmente ou no banco de dados pelo servidor (dependendo se o servidor está ON ou não) após o clique no botão "Salvar labirinto"
	     * 
	     * 
	     */
        private void salvarLabirinto (){

			areaEdicao.setEditable(false);
			String aux = areaEdicao.getText();		
			String[] str = aux.split("[\r]?\n");
			int auxLinha = str.length;			
			String tentativaLabirinto = "" + auxLinha + "\n";
			tentativaLabirinto += aux;	
	
			if(servidorOn == false){
			
				try{
					//Limpa a area de ediÃ§Ã£o		
					Lab =  new Labirinto(tentativaLabirinto);				
					Navegador navLab = new Navegador(Lab);
					JFileChooser file = new JFileChooser();
					file.setFileSelectionMode(JFileChooser.FILES_ONLY);
					int i = file.showSaveDialog(null);
					if (i==1){
						areaEdicao.setText("");
					} else {
						try {					
							File novoArquivo = new File(file.getSelectedFile().getPath()+".txt");
							FileWriter conteudoArquivo = new FileWriter(file.getSelectedFile().getPath()+".txt");
							conteudoArquivo.write(tentativaLabirinto);
							conteudoArquivo.close();
							areaEdicao.setText("");
							areaNotificacao.setText("");
							areaNotificacao.setText("Labirinto Salvo com sucesso!");
							controller = true;	
							btn[3].setEnabled(true);
						} catch (IOException error) {
							//printa o erro na area de erro
							areaNotificacao.setText(error.getMessage());
						}
					}				
				} catch (Exception error){
					//printa o erro na area de erro
					btn[3].setEnabled(false);
					areaNotificacao.setText(error.getMessage());							
					this.criarLabirinto();						
				}		
			
			} else {
				
				String nomeLabirinto = null, ip;
				Date dataCriacao = new Date();
				Date dataUltimaAtualizacao = new Date();				
				
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				String dataCriacaoS = dateFormat.format(dataCriacao);
				String dataAtualizacao = dateFormat.format(dataUltimaAtualizacao);
 
				
				
				try
				{
					Lab =  new Labirinto(tentativaLabirinto);				
					Navegador navLab = new Navegador(Lab);
					
					try
					{
						ip = InetAddress.getLocalHost().getHostAddress(); // pega o ip
					}
					catch(Exception erroIP)
					{
						ip = "invalido";
					}

					
					
					nomeLabirinto = JOptionPane.showInputDialog("Insira o nome do labirinto");				
					
					PedidoDeSalvamento pedirSalvar = new PedidoDeSalvamento(ip, nomeLabirinto, tentativaLabirinto, dataCriacaoS, dataAtualizacao);
					try
					{						
						
						servidor.receba(pedirSalvar); // envia para o servidor as informações a serem salvas (ip, nome, data de criação, data de edição)
						btn[3].setEnabled(true);
						
					}catch(Exception erroSalvarServidor)
					{
						System.out.println("Deu erro no pedido de salvamento");
					}
					
				} catch(Exception error)
				{
					//printa o erro na area de erro
					btn[3].setEnabled(false);
					System.out.println(error);
					areaNotificacao.setText(error.getMessage());							
					this.criarLabirinto();	
				}

				
				
				
				
					
					
			
			}	
		}
		
		/**
	     * 
	     *
	     *Metodo que faz a conexão com o servidor quando clicado no botão servidor
	     * 
	     * 
	     */
		private void servidor(){
				
			if (servidorOn == false)
			{

				Socket conexao=null;
				try
				{
					String host = Janela.HOST_PADRAO;
					int    porta= Janela.PORTA_PADRAO;	
					conexao = new Socket (host, porta);
				}
				catch (Exception erro)
				{
					System.err.println ("Indique o servidor e a porta corretos!\n");
					return;
				}

				ObjectOutputStream transmissor=null;
				try
				{
					transmissor =
					new ObjectOutputStream(
					conexao.getOutputStream());
				}
				catch (Exception erro)
				{
					System.err.println ("Indique o servidor e a porta corretos!\n");
					return;
				}
				
				ObjectInputStream receptor=null;
				
				try
				{
					receptor =
					new ObjectInputStream(
					conexao.getInputStream());
				}
				catch (Exception erro)
				{
					System.err.println ("Indique o servidor e a porta corretos!\n");
					return;
				}

				servidor=null;
				
				try
				{
					servidor = new Parceiro (conexao, receptor, transmissor);
				}
				catch (Exception erro)
				{
					System.err.println ("Indique o servidor e a porta corretos!\n");
					return;
				}
				
				servidorOn = true;
				btn[4].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.green));
				btn[4].setForeground(Color.green);
				areaNotificacao.setForeground(Color.green);
				areaNotificacao.setText("Mensagem: servidor conectado");
			} 
			else 
			{
				servidorOn = false;
				btn[4].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.gray));
				btn[4].setForeground(Color.black);
				areaNotificacao.setText("Mensagem: servidor desconectado");
				try
				{
					servidor.receba (new PedidoParaSair ()); // o servidor deve detectar esse comunicado para desconectar o cliente do servidor
				}
				catch (Exception erro){}
				
			}
			
		}
        /**
	     * 
	     *
	     *Metodo que faz as ações click do mouse e verifica onde foi clicado
	     * 
	     * 
	     */
        @Override
        public void actionPerformed (ActionEvent e)
        {
            String comando = e.getActionCommand();
            
            areaNotificacao.setForeground(Color.red);
			
			if (comando == "Novo labirinto"){
				areaNotificacao.setText("");
				areaNotificacao.setForeground(Color.blue);
				areaNotificacao.setText("Mensagem: Crie seu Novo Labirinto");
				this.criarLabirinto ();
            } else if (comando == "Abrir labirinto"){
				areaNotificacao.setText("");
                this.abrirLabirinto ();
                
            } else if(comando == "Executar labirinto"){
				areaNotificacao.setText("");
				this.executarLabirinto();
				
			} else if (comando == "Servidor"){
				
				areaNotificacao.setText("");
				this.servidor();
				
			} else {
				areaNotificacao.setText("");
				this.salvarLabirinto();
			}			
        }
	}
    
	private JTextArea  areaEdicao = new JTextArea  (16, 58);
	private JTextArea  areaNotificacao   = new JTextArea  (5,20);
	private JButton btn []   = new JButton [5];	
	
	/**
     * 
     *
     * Responsavel pela interface do programa
     * 
     * 
     */
	public Janela (){
		
		//Area Editavel		
		JScrollPane scroll = new JScrollPane ( areaEdicao );
		scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED );		
		areaEdicao.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK)); //Coloca bordas na areaEdicao
		areaEdicao.setFont(new Font("Courier", Font.PLAIN, 20));
		areaEdicao.setEditable(false);
		
		//Area Erro
		areaNotificacao.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));   //Coloca bordas na areaNotificacao
		areaNotificacao.setFont(new Font("Courier", Font.PLAIN, 20));
		areaNotificacao.setForeground(Color.RED);
		areaNotificacao.setEditable(false);
		
		//Botoes
		JPanel botoes = new JPanel(); //cria um painel para os botÃµes
		botoes.setLayout (new GridLayout(1,5)); //faz o layout do painel
		String texto [] = {"Novo labirinto", "Abrir labirinto", "Salvar arquivo de labirinto", "Executar labirinto", "Servidor"};
		TratadoraDeMouse tratadorDeMouse = new TratadoraDeMouse ();
		
		//adiciona as strings no vetor de botÃµes
		for (int i=0; i<this.btn.length; i++){
			this.btn [i] = new JButton (texto [i]);
			
			this.btn [i].setActionCommand  (texto [i]);
			this.btn [i].addActionListener (tratadorDeMouse);				
			
			botoes.add (this.btn [i]);
		}
		
		//Janela
		JFrame janela = new JFrame  ("Labirinto");
		janela.setSize (1200,700);
		janela.getContentPane().setLayout(new BorderLayout());
		janela.add(scroll, BorderLayout.CENTER);
		janela.add(this.areaNotificacao,BorderLayout.SOUTH);
		janela.add(botoes,BorderLayout.NORTH);
		janela.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		janela.setVisible(true);
	}
	
	
	/**
     * 
     *
     * Responsavel pela interface do Combo Box, onde o cliente podera escolher o labirinto que deseja abrir
     * 
     * 
     * 
     */
	private class JanelaComboBox extends JFrame //cria uma janela para o combobox
    {
    	protected JanelaComboBox(Vector<String> janelaComboBoxVector) 
    	{
    		this.setSize(300, 300);//define o tamanho da janela
    		this.setLayout(new GridLayout(3, 1));
    		JLabel textoComboBox = new JLabel("Escolha o Labirinto que deseja abrir:");//texto label da janela
    		this.add(textoComboBox);//adiciona o label na janela
    		opcoesLabirintos = new JComboBox<String>(janelaComboBoxVector);//instancia o combobox
    		this.add(opcoesLabirintos);//adiciona o combobox
    		JButton confirmaEscolha = new JButton("Confirmar");//cria um botao    		
    		confirmaEscolha.addActionListener(new VerificaComboBox());//chama a classe que vai fazer a verificacao do botao
    		confirmaEscolha.addActionListener(e -> this.dispose());    		
    		this.add(confirmaEscolha);//adciona o botao na janela
			this.setVisible(true);//torna a janela visivel

    	}
    	
    }
    
    /**
     * 
     *
     * Classe que realiza as comparacoes e selects para abrir o labirinto,
     * e ao escolher, esse labirinto será enviado ao servidor.
     * 
     * 
     */
    private class VerificaComboBox implements ActionListener 
    {
    	public void actionPerformed(ActionEvent e) 
    	{
    		String itemSelecionado, ip, line;
    		
    		
    		itemSelecionado = opcoesLabirintos.getSelectedItem().toString();
    		
    		LabEscolhido labEscolhido = new LabEscolhido (itemSelecionado); 		
 		
    		try{
				
				servidor.receba(labEscolhido);
				
				labEscolhido = (LabEscolhido)servidor.envie();				
				
				Lab =  new Labirinto(labEscolhido.getLabirinto());				
								
				//printa o labirinto escolhido na area de edicao
				areaEdicao.setText (""+Lab);
				areaNotificacao.setText ("");
				System.out.println("2");
				btn[3].setEnabled(true);		
					
			} catch (Exception error) {
				
				areaNotificacao.setText(error.getMessage());
				
			}		

    	}
    	
    	
    }
    
}
