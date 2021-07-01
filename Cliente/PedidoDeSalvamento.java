import java.io.*;

public class PedidoDeSalvamento extends Comunicado implements Serializable
{
	private String idCliente; // vai ser o ip
	private String labirinto;	
	private String nomeLabirinto;
	private String dataCriacao;
	private String dataUpdate;
	
	/**
	 * Construtor do PedidoDeSalvamento
	 * 
	 * @param string idCliente, string nomeLab, string lab, string dateCri, string dateUp
	 * 
	 */	
	public PedidoDeSalvamento (String idCliente, String nomeLab, String lab, String dateCri, String dateUp)
	{
		this.idCliente = idCliente;
		this.labirinto = lab;
		this.nomeLabirinto = nomeLab;
		this.dataCriacao = dateCri;
		this.dataUpdate = dateUp;		
	}
	
	/**
	 * get para recuperar o id do cliente
	 * 
	 * @return string idCliente
	 * 
	 */	
	public String getIdCliente ()
	{
		return this.idCliente;
	}
	
	/**
	 * get para recuperar o nome do labirinto
	 * 
	 * @return string nomeLabirinto
	 * 
	 */	
	public String getNomeLabirinto ()
	{
		return this.nomeLabirinto;
	}
	
	/**
	 * get para recuperar o conteudo do labirinto
	 * 
	 * @return string labirinto
	 * 
	 */	
	public String getLabirinto ()
	{
		return this.labirinto;
	}
	
	/**
	 * get para recuperar a data da criacao do labirinto
	 * 
	 * @return string dataCriacao
	 * 
	 */	
	public String getDataCriacao()
	{
		return this.dataCriacao;
		
	}
	
	/**
	 * get para recuperar a data do update do labirinto
	 * 
	 * @return string dataUpdate
	 * 
	 */
	public String getDataUpdate()
	{
		return this.dataUpdate;
		
	}
		
	/**
	 * Metodo toString implementado para a classe PedidoDeSalvamento
	 * 
	 * @return String, retorna uma string do PedidoDeSalvamento
	 * 
	 */	
	@Override
	public String toString() {
        
        String str = "Nome: "            + this.nomeLabirinto + "\n" +
					 "ID: "	             + this.idCliente     + "\n" +
					 "Data Criacao: "    + this.dataCriacao   + "\n" +
					 "Data Criacao: "    + this.dataUpdate    + "\n" +
					 "Labirinto: "	     + this.labirinto;       
		
        return str;
    }
    
	/**
	 * Metodo equals implementado para a classe PedidoDeSalvamento
	 * 
	 * @return boolean, retorna verdadeiro se for igual e falso se for diferente
	 * 
	 */	
    @Override
    public boolean equals (Object obj)
    {
        if (this==obj) return true;
        
        if (obj==null) return false;        
      
        if (obj.getClass()!= PedidoDeSalvamento.class) return false;
        
        PedidoDeSalvamento lab = (PedidoDeSalvamento)obj;
        
        if (this != lab) return false;       
        
        return true;
    }
    
    /**
	 * Metodo hashcode implementado para a classe PedidoDeSalvamento
	 * 
	 * @return int, retorna um numero hashcode
	 * 
	 */	
    @Override
    public int hashCode ()
    {
      
        int ret=666; 
        
        ret = (13*ret) + new Integer(this.idCliente).hashCode(); 
      
        
        if (ret<0) ret = -ret;
        
        return ret;
    }
}
