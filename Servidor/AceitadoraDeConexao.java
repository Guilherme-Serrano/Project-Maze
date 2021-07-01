import java.net.*;
import java.util.*;


	/**
     * 
     * Pega as novas conex�es por meio de um server socket
     * e cria uma supervisora de conex�o em uma thread 
     * separada para cada cliente que se conectar
     * 
     */
public class AceitadoraDeConexao extends Thread//
{
    private ServerSocket        pedido;
    private ArrayList<Parceiro> usuarios;

    public AceitadoraDeConexao 
    (String porta, ArrayList<Parceiro> usuarios)
    throws Exception
    {
        if (porta==null)
            throw new Exception ("Porta ausente");

        try
        {
            this.pedido =
            new ServerSocket (Integer.parseInt(porta));
        }
        catch (Exception  erro)
        {
            throw new Exception ("Porta invalida");
        }

        if (usuarios==null)
            throw new Exception ("Usuarios ausentes");

        this.usuarios = usuarios;
    }
	
	/**
     * 
     * Metodo para aceitar continuamente novas conex�es
     * 
     */
    public void run ()
    {
        for(;;)
        {
            Socket conexao=null;
            try
            {
                conexao = this.pedido.accept();
            }
            catch (Exception erro)
            {
                continue;
            }

            SupervisoraDeConexao supervisoraDeConexao=null;
            try
            {
                supervisoraDeConexao =
                new SupervisoraDeConexao (conexao, usuarios);
            }
            catch (Exception erro)
            {} // sei que passei parametros corretos para o construtor
            supervisoraDeConexao.start();
        }
    }
}
