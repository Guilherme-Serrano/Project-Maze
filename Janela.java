import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Formatter;
import java.util.Scanner;
import java.io.*;
import java.io.File;  // Import the FileWriter class
import java.io.FileWriter; 
import java.io.IOException;

public class Janela
{
	private static Formatter Arquivo;
	
	/**
     * 
     * Classe tratadora de mouse
     * 
     * 
     */
	private class TratadoraDeMouse implements ActionListener
    {
		private Labirinto Lab;
		private boolean controller = false;
		
		//abre o arquivo
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
	     * Abre o labirinto após o clique no botão "Abrir labirinto"
	     * 
	     * 
	     */
        private void abrirLabirinto (){
			
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
				btn[3].setEnabled(false);
				areaNotificacao.setText(error.getMessage());						
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
				//verifica se algum labirinto foi escolhido
				if(Lab == null)
					throw new Exception("Error: Nenhum Labirinto Escolhido");
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
            areaNotificacao.setText("Crie seu Novo Labirinto");
        }
        
        /**
	     * 
	     * Salva o labirinto localmente após o clique no botão "Salvar labirinto"
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
				areaNotificacao.setText(error.getMessage());							
				this.criarLabirinto();						
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
			
			if (comando == "Novo labirinto"){
				areaNotificacao.setText("");
				this.criarLabirinto ();
            } else if (comando == "Abrir labirinto"){
				areaNotificacao.setText("");
                this.abrirLabirinto ();
                
            } else if(comando == "Executar labirinto"){
				areaNotificacao.setText("");
				this.executarLabirinto();
				
			} else {
				areaNotificacao.setText("");
				this.salvarLabirinto();
			}			
        }
	}
    
	private JTextArea  areaEdicao = new JTextArea  (16, 58);
	private JTextArea  areaNotificacao   = new JTextArea  (5,20);
	private JButton btn []   = new JButton [4];	
	
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
		botoes.setLayout (new GridLayout(1,4)); //faz o layout do painel
		String texto [] = {"Novo labirinto", "Abrir labirinto", "Salvar arquivo de labirinto", "Executar labirinto"};
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
}
