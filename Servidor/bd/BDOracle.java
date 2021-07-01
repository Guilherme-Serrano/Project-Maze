package bd;

import bd.core.*;
import bd.daos.*;

public class BDOracle
{
	/**
	 * Especificações do Metodo
	 * 
	 * Este metodo é responsavel por realizar a conexao do programa com o Banco de Dados
	 * da Oracle ele contem o usuario e a senha do Banco de Dados alem do servidor que
	 * ele vai se conectar com o banco de Dados
	 * 
	 */
    public static final MeuPreparedStatement COMANDO;

    static
    {

    	MeuPreparedStatement comando = null;

    	try
        {
            comando = new MeuPreparedStatement (
                      "oracle.jdbc.driver.OracleDriver",
                      "jdbc:oracle:thin:@localhost:1521:xe",
                      "system", "Felipe123Orcl");
        }
        catch (Exception erro)
        {
            System.err.println ("Problemas de conexao com o BD");
            System.exit(0);
        }
        
        COMANDO = comando;
    }
}