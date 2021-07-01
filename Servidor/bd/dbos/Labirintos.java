package bd.dbos;

public class Labirintos implements Cloneable
{
    private String ip;
    private String nome;
    private String labirinto;
    private String datacriacao;
    private String dataupdate;
 
	/**
	 * Especifica��es do Metodo
	 * 
	 * M�todo que � respons�vel por setar o IP de um Cliente no Objeto Labirinto
	 * Ele verifica se este IP � NULO ou igual a um espa�o;
	 * 
	 */
    public void setIP (String ip) throws Exception
    {
        if (ip==null || ip.equals(""))
            throw new Exception ("IP nao fornecido");
        this.ip = ip;
    }     

    
	/**
	 * Especifica��es do Metodo
	 * 
	 * M�todo que � respons�vel por setar o NOME do Labirinto no Objeto Labirinto
	 * Ele verifica se este NOME � NULO ou igual a um espa�o;
	 * 
	 */
    public void setNome (String nome) throws Exception
    {
        if (nome==null || nome.equals(""))
            throw new Exception ("Nome nao fornecido");
        this.nome = nome;
    }

    
	/**
	 * Especifica��es do Metodo
	 * 
	 * M�todo que � respons�vel por setar o Labirinto no Objeto Labirinto
	 * Ele verifica se este NOME � NULO ou igual a um espa�o;
	 * 
	 */
    public void setLab (String labirinto) throws Exception
    {
        if (labirinto==null || labirinto.equals(""))
            throw new Exception ("Labirinto nao fornecido");
        this.labirinto = labirinto;
    }

    
	/**
	 * Especifica��es do Metodo
	 * 
	 * M�todo que � respons�vel por setar o Data de Cria��o do Labirinto no Objeto Labirinto.
	 * Ele verifica se este Data Cri��o � NULO ou igual a um espa�o;
	 * 
	 */
    public void setDataCriacao (String datacriacao) throws Exception
    {
        if (datacriacao==null || datacriacao.equals(""))
            throw new Exception ("Data Criacao nao fornecido");
        this.datacriacao = datacriacao;
    }
    
    
	/**
	 * Especifica��es do Metodo
	 * 
	 * M�todo que � respons�vel por setar o Data da Ultima Atualiza��o do Labirinto 
	 * no Objeto Labirinto.
	 * Ele verifica se este Data Update � NULO ou igual a um espa�o;
	 * 
	 */
    public void setDataUpdate (String dataupdate) throws Exception
    {
        if (dataupdate==null || dataupdate.equals(""))
            throw new Exception ("Data Update nao fornecido");
        this.dataupdate = dataupdate;
    }
    
	/**
	 * Especifica��es do Metodo
	 * 
	 * M�todo que � respons�vel por retornar o IP do Clinte que esta dentro do
	 * Objeto labirinto
	 * 
	 */
    public String getIP ()
    {
        return this.ip;
    }

	/**
	 * Especifica��es do Metodo
	 * 
	 * M�todo que � respons�vel por retornar o Nome do Labirnto que esta dentro do
	 * Objeto labirinto
	 * 
	 */
    public String getNome ()
    {
        return this.nome;
    }
    
	/**
	 * Especifica��es do Metodo
	 * 
	 * M�todo que � respons�vel por retornar o Labirnto que esta dentro do
	 * Objeto labirinto
	 * 
	 */
    public String getLab ()
    {
        return this.labirinto;
    }

    
	/**
	 * Especifica��es do Metodo
	 * 
	 * M�todo que � respons�vel por retornar a Data de Cria��o do Labirinto 
	 * que esta dentro do Objeto labirinto
	 * 
	 */
    public String getDataCriacao ()
    {
        return this.datacriacao;
    }
    
    
	/**
	 * Especifica��es do Metodo
	 * 
	 * M�todo que � respons�vel por retornar a Data da Ultima Atualiza��o do Labirinto 
	 * que esta dentro do Objeto labirinto
	 * 
	 */
    public String getDataUpdate ()
    {    

        return this.dataupdate;
    }
    
    
	/**
	 * Especifica��es do Metodo
	 * 
	 * Construtor de um labirinto recebendo por parametro as variaveis separadamente
	 * ou seja ele recebe o ip,nome,lab,datacriacao,dataupdate e cria um objeto labirinto.
	 * 
	 */
    public Labirintos (String ip,String nome, String lab, String datacriacao, String dataupdate) throws Exception
    {
		this.setIP (ip);
        this.setNome (nome);
        this.setLab (lab);
        this.setDataCriacao (datacriacao);
        this.setDataUpdate (dataupdate);
    }
    
    
	/**
	 * Especifica��es do Metodo
	 * 
	 * Metodo Obrigatorio que retorna uma String com as informa��es do Objeto Labirinto
	 * 
	 */
    public String toString ()
    {
        String ret="";

        ret+="IP: "+this.ip +"\n";
        ret+="Nome  : "+this.nome  +"\n";
        ret+="Lab: "+this.labirinto +"\n";
        ret+="Data Cria��o: "+this.datacriacao +"\n";
        ret+="Data Update: "+this.dataupdate;

        return ret;
    }

	/**
	 * Especifica��es do Metodo
	 * 
	 * Metodo Obrigatorio que retorna que faz compara��es de informa��es do labirinto
	 * 
	 */
    public boolean equals (Object obj)
    {
        if (this==obj)
            return true;

        if (obj==null)
            return false;

        if (!(obj instanceof Labirintos))
            return false;

        Labirintos lab = (Labirintos)obj;

        if (this.ip.equals(lab.ip))
            return false;

        if (this.labirinto.equals(lab.labirinto))
            return false;

        return true;
    }

	/**
	 * Especifica��es do Metodo
	 * 
	 * Metodo Obrigatorio hashCode
	 * 
	 */
    public int hashCode ()
    {
        int ret=666;

        ret = 7*ret + this.ip.hashCode();
        ret = 7*ret + this.nome.hashCode();
        ret = 7*ret + this.labirinto.hashCode();
        ret = 7*ret + this.datacriacao.hashCode();
        ret = 7*ret + this.dataupdate.hashCode();
        
        return ret;
    }


	/**
	 * Especifica��es do Metodo
	 * 
	 * Construtor de Labirinto recebendo por paremetro um labirinto
	 * 
	 */
    public Labirintos (Labirintos modelo) throws Exception
    {
        this.ip = modelo.ip; 
        this.nome   = modelo.nome;
        this.labirinto  = modelo.labirinto;
        this.datacriacao  = modelo.datacriacao;
        this.dataupdate  = modelo.dataupdate;
    }

	/**
	 * Especifica��es do Metodo
	 * 
	 * Metodo Obrigatorio que Clona o Labirinto
	 * 
	 */
    public Object clone ()
    {
    	Labirintos ret=null;

        try
        {
            ret = new Labirintos (this);
        }
        catch (Exception erro)
        {}          
        return ret;
    }
}
