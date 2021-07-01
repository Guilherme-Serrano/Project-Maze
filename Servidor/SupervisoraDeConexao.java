import java.io.*;
import java.net.*;
import java.util.*;

import bd.core.MeuResultSet;
import bd.daos.BDLabirinto;
import bd.dbos.Labirintos;


public class SupervisoraDeConexao extends Thread 
{
    private double              valor=0;
    private Parceiro            usuario;
    private Socket              conexao;
    private ArrayList<Parceiro> usuarios;

	/**
	 * Especificações do Metodo
	 * 
	 * Esse metodo é responsavel por verificar se a conexao esta Ativa e se a
	 * Usuarios conectados nela.
	 * 
	 */
    public SupervisoraDeConexao
    (Socket conexao, ArrayList<Parceiro> usuarios)
    throws Exception
    {
        if (conexao==null)
            throw new Exception ("Conexao ausente");

        if (usuarios==null)
            throw new Exception ("Usuarios ausentes");

        this.conexao  = conexao;
        this.usuarios = usuarios;
    }

    
    
	/**
	 * Especificações do Metodo
	 * 
	 * Este metodo contem varias funcionalidades mas as principais funcionalidades dele são
	 * basicamente ele fica recebendo comunicados do servidor e verificando o conteudo desse
	 * comunicado basicamente ele fica entrando em um lopping de if e else para veficar
	 * qual ação sera efetuda com base no comunicado que ele esta recebendo, entao existe um
	 * comunicado que é responsavel por Salvar um objeto Labirinto no Banco de Dados, outro
	 * que é responsavel por Retornar para a Janela do Cliente uma lista que contem o nome de
	 * todos os labirinto que aquele IP em especifico já salvou no Banco existe tambem um
	 * comunicado que pede para retornar ao Cliente o labirinto que este escolheu. Essas sao
	 * as 3 principais funcionalidades da Supervisora de Conexão, alem disso existem outra
	 * funcionalidades que estao aqui somente para manter a conexao ativa e ficar recebendo
	 * e enviando os comunicados. 
	 * 
	 */
    public void run ()
    {

    	
        ObjectOutputStream transmissor;
        try
        {
            transmissor =
            new ObjectOutputStream(
            this.conexao.getOutputStream());
        }
        catch (Exception erro)
        {
            return;
        }
        
        ObjectInputStream receptor=null;
        try
        {
            receptor=
            new ObjectInputStream(
            this.conexao.getInputStream());
        }
        catch (Exception err0)
        {
            try
            {
                transmissor.close();
            }
            catch (Exception falha)
            {}
            return;
        }

        try
        {
            this.usuario =
            new Parceiro (this.conexao,
                          receptor,
                          transmissor);
        }
        catch (Exception erro)
        {}

        try
        {
            synchronized (this.usuarios)
            {
                this.usuarios.add (this.usuario);
            }


            for(;;)
            {
                Comunicado comunicado = this.usuario.envie ();

                if (comunicado==null)
                    return;
                else if (comunicado instanceof PedidoDeSalvamento)
                {
                    PedidoDeSalvamento novoPedido = (PedidoDeSalvamento)comunicado;
                    String Nome = novoPedido.getNomeLabirinto();
                    String Lab = novoPedido.getLabirinto();
                    String IP = novoPedido.getIdCliente();
                    String DataCriacao = novoPedido.getDataCriacao();
                    String DataUpdate = novoPedido.getDataUpdate();
                    try {
                    	BDLabirinto.incluir (new Labirintos (IP,Nome,Lab,DataCriacao,DataUpdate));
                    }catch(Exception erro) {
                    	BDLabirinto.alterar(IP, Nome, Lab);
                    }  
		        }
                
		        else if (comunicado instanceof Id_Cliente)
                {
                	Id_Cliente novoPedido = (Id_Cliente)comunicado;
                   	List<Labirintos> labirintosArray = BDLabirinto.getLabirintosByIP(novoPedido.getIdCliente());
                	Vector<String> labirintos = new Vector<String>();
                	for(Labirintos lab : labirintosArray) {
                		
                		labirintos.add(lab.getNome());
                	}
                	novoPedido.setStringsBD(labirintos); 
                	usuario.receba(novoPedido);
                }
                
                else if (comunicado instanceof LabEscolhido)
                {
                	LabEscolhido labEscolhido = (LabEscolhido)comunicado;               	
                	String nome = labEscolhido.getNome(); 
                	String IP = labEscolhido.getidCliente(); 
                	Labirintos lab = new Labirintos(BDLabirinto.getLabirintoEscolhido(IP,nome));
                	labEscolhido.setLabirinto(lab.getLab());
					usuario.receba(labEscolhido);
				}

            }
        }
        catch (Exception erro)
        {
            try
            {
                transmissor.close ();
                receptor   .close ();
            }
            catch (Exception falha)
            {}

            return;
        }
    }
}
