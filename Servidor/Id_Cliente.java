import java.util.Vector;

public class Id_Cliente extends Comunicado
{
    private Vector<String> vetorLabirintos;
	private String idCliente; // vai ser o ip
	
	/**
	 * Construtor do Id_Cliente
	 * 
	 * @param string idCliente
	 * 
	 */
    public Id_Cliente(String idCliente)
    {
        this.idCliente = idCliente;
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
	 * set para passar os nomes de um vetor para o vetor do objeto do Id_Cliente
	 * 
	 * @param Vetor do tipo strings com os nomes dos labirintos
	 * 
	 */
    public void setStringsBD(Vector<String> vetorLabirintos) //define 
    {
    	this.vetorLabirintos = vetorLabirintos;
    }
    
    /**
	 * get para recuperar o vetor com os nomes
	 * 
	 * @return Vector labirintos
	 * 
	 */
    public Vector<String> getStringsBD() //recebe
    {
    	return this.vetorLabirintos;
    }
    
    /**
	 * Metodo toString implementado para a classe Id_Cliente
	 * 
	 * @return String, retorna uma string do LabEscolhido
	 * 
	 */	
	@Override
	public String toString() {
        
        String str = "ID: " + this.idCliente     + "\n" +
					 "Labs: " + this.vetorLabirintos;
        
		
        return str;
    }
    
	/**
	 * Metodo equals implementado para a classe Id_Cliente
	 * 
	 * @return boolean, retorna verdadeiro se for igual e falso se for diferente
	 * 
	 */	
    @Override
    public boolean equals (Object obj)
    {
        if (this==obj) return true;
        
        if (obj==null) return false;        
      
        if (obj.getClass()!= LabEscolhido.class) return false;
        
        Id_Cliente lab = (Id_Cliente)obj;
        
        if (this != lab) return false;       
        
        return true;
    }
    
    /**
	 * Metodo hashcode implementado para a classe Id_Cliente
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
