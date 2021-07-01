package bd.daos;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import bd.BDOracle;
import bd.core.MeuResultSet;
import bd.dbos.Labirintos;

public class BDLabirinto
{
	/**
	 * Especifica��es do Metodo
	 * 
	 * M�todo que � respons�vel por incluir um labirinto no Banco de dados 
	 * Ele recebe um objeto labirinto verifica se este n�o � nulo e particiona esse objeto 
	 * mandando cada uma das informa��es por um comando SQL para o banco de dados.
	 * 
	 * 
	 */
    public static void incluir (Labirintos lab) throws Exception
    {
        if (lab==null)
            throw new Exception ("Labirinto nao fornecido");

        try
        {
            String sql;

            sql = "INSERT INTO LABIRINTOS " +
                  "(IP,NOME,LABIRINTO,DATACRIACAO,DATAUPDATE) " +
                  "VALUES " +
                  "(?,?,?,?,?)";

            BDOracle.COMANDO.prepareStatement (sql);

            BDOracle.COMANDO.setString (1, lab.getIP());
            BDOracle.COMANDO.setString (2, lab.getNome());
            BDOracle.COMANDO.setString (3, lab.getLab());
            BDOracle.COMANDO.setString (4, lab.getDataCriacao());
            BDOracle.COMANDO.setString (5, lab.getDataUpdate());
            
            BDOracle.COMANDO.executeUpdate ();
            BDOracle.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
        	BDOracle.COMANDO.rollback();
            throw new Exception ("Erro ao inserir Labirinto");
        }
    }

	/**
	 * Especifica��es do Metodo
	 * 
	 * M�todo que � respons�vel por alterar um labirinto no Banco de dados 
	 * Ele recebe as informa��es que ser�o alteradas, no caso o labirinto. Alem disso 
	 * o metodo recebe o IP do Cliente e o Nome para poder utilizar no comando de SQL
	 * Alem disso dentro do metodo ele busca uma data para poder alterar no campo data de
	 * Update do Banco de Dados.
	 * 
	 * Campos Alterados: Labirinto, DataUpdate
	 * Campos Utilizados para Buscar: IP, NOME
	 * 
	 */
	public static void alterar (String ip,String nome,String lab) throws Exception
    {
		SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy HH:mm:ss");

        try
        {
            String sql;

            sql = "UPDATE LABIRINTOS " +
                  "SET LABIRINTO=? , DATAUPDATE = ?" +
                  "WHERE IP = ?"+
                  "AND NOME=?";

            BDOracle.COMANDO.prepareStatement (sql);

            BDOracle.COMANDO.setString (1, lab);
            BDOracle.COMANDO.setString (2, sdf.format(new java.util.Date()));
			BDOracle.COMANDO.setString (3, ip);
			BDOracle.COMANDO.setString (4, nome);
		
            BDOracle.COMANDO.executeUpdate ();
            BDOracle.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
			BDOracle.COMANDO.rollback();
            throw new Exception ("Erro ao atualizar dados de Labirinto");
        }
    }
    
	
	/**
	 * Especifica��es do Metodo
	 * 
	 * M�todo que � respons�vel por retornar uma Lista de labirintos no Banco de Dados. 
	 * Ele recebe o IP do Cliente que faz a solicita��o de Abir os labirintos, assim que
	 * ele � acionado ele cria dentro dele mesmo uma ArrayList de labirintos, essa sera
	 * responsalvel por receber todos os labirintos que serao retornados na busca feita
	 * pelo IP dele. Ele usa um comando SQL para buscar todos os labirintos daquele IP
	 * depois dentro de um while ele sepera esses na ArrayList.
	 * 
	 */
    public static List<Labirintos> getLabirintosByIP (String ip) throws Exception
    {
   	
    	List<Labirintos> labirintos = new ArrayList<Labirintos>();
    	
        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM LABIRINTOS " +
                  "WHERE IP = ?";

            BDOracle.COMANDO.prepareStatement (sql);

            BDOracle.COMANDO.setString (1, ip);
			
            MeuResultSet resultado = (MeuResultSet)BDOracle.COMANDO.executeQuery ();
            
            while(resultado.next()){
            	
            	Labirintos lab = new Labirintos(resultado.getString ("IP"),
            									resultado.getString("NOME"),
            									resultado.getString("LABIRINTO"),
            									resultado.getString("DATACRIACAO"),
            									resultado.getString("DATAUPDATE"));	
            	
            	labirintos.add(lab);
            }
            
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar/criar a Lista de Labirintos");
        }

        return labirintos;
    }
    
	/**
	 * Especifica��es do Metodo
	 * 
	 * M�todo que � respons�vel por retornar o Labirinto que foi escolhido pelo Cliente
	 * quando o Cliente na Janela seleciona a op��o Abrir e dentro das possibilidades
	 * seleciona um dos labirintos essa funcao recebe o nome do labirinto que ele escolheu
	 * e tambem o IP desse cliente para poder buscar o labirinto desse cliente pelo IP dele
	 * e o nome do labirinto que o cliente deseja. Assim � enviado um comando com essas
	 * informa��es ao Banco de Dados, o Banco envia um objeto � separado somente as informa�oes
	 * uteis dele e com isso � criado um objeto Labirinto que � retornado pela fun��o. 
	 * 
	 */
    public static Labirintos getLabirintoEscolhido (String ip,String nome) throws Exception
    {
    	Labirintos lab = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM LABIRINTOS " +
                  "WHERE IP = ?" +
                  "AND NOME=?";

            BDOracle.COMANDO.prepareStatement (sql);

            BDOracle.COMANDO.setString (1, ip);
            BDOracle.COMANDO.setString (2, nome);
            
            MeuResultSet resultado = (MeuResultSet)BDOracle.COMANDO.executeQuery ();

            lab = new Labirintos (resultado.getString ("IP"),
            					resultado.getString("NOME"),
            					resultado.getString("LABIRINTO"),
            					resultado.getString("DATACRIACAO"),
            					resultado.getString("DATAUPDATE"));
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao Retornar Labirinto Escolhido");
        }

        return lab;
    }
}