public class Cordenada implements Cloneable{
	
    private int x;
    private int y;    
    
    /** 
     * Constroi as cordenadas 
     * @param x valor da cordenada no eixo X
     * @param y valor da cordenada no eixo Y
     */
    public Cordenada(int x, int y) {
        this.x = x;
        this.y = y;                      
    }    
    
    /** 
     * Metodo para retornar o valor do eixo X
     * @return int, retorna a cordenada do eixo X
     */
    int getX() {
        return this.x;
    }
    
    /** 
     * Metodo para retornar o valor do eixo Y
     * @return int, retorna a cordenada do eixo Y
     */
    int getY() {
        return this.y;     
        
    }
    
    /**
	 * Metodo toString implementado para a classe Cordenada 
	 * @return String, retorna uma string da cordenada
	 */		
    @Override
    public String toString(){
		return ("(" + this.x + "," + this.y + ")");
	
	}
	
    /**
	 * Metodo equals implementado para a classe Cordenada 
	 * @return boolean, retorna verdadeiro se for igual e falso se for diferente
	 */	
    @Override
    public boolean equals (Object obj)
    {
        if (this==obj) return true;
        
        if (obj==null) return false;        
      
        if (obj.getClass()!= Cordenada.class) return false;
        
        Cordenada aux = (Cordenada)obj;
        
        if (this.x != aux.x) return false;
        if (this.y != aux.y) return false;       
        
        return true;
    }
    
    
    
    /**
	 * Metodo hashcode implementado para a classe Cordenada 
	 * @return int, retorna um numero hashcode
	 */
    @Override
    public int hashCode ()
    {
      
        int ret=666; 
        
        ret = (13*ret) + new Integer(this.x + this.y).hashCode(); 
      
        
        if (ret<0) ret = -ret;
        
        return ret;
    }
    
    /**
	 * Construtor de clone da classe cordenada
	 * @param Cordenada cordenada que deseja clonar
	 */
    public Cordenada (Cordenada modelo) throws Exception
    {
        if (modelo==null)
            throw new Exception ("Modelo ausente");
            
        this.x = modelo.x;
        this.y = modelo.y;   
  
      
    }
    
    
    /**
	 * Metodo de clone da classe cordenada
	 * @return Object, retorna um objeto clone da classe cordenada
	 */
    @Override
    public Object clone ()
    {
        Cordenada ret=null;
        
        try
        {
            ret = new Cordenada (this);
        }
        catch (Exception erro)
        {}
        
        return ret;
    }
    
    /**
	 * Metodo compareTo para o cordenada
	 * @param Labirinto labirinto
	 * @return int, retorna 0 se for igual
	 */	
    //@Override
    public int compareTo (Cordenada lab)
    {
        
        if (this.getX() < lab.getX())      return -1;
        if (this.getX() > lab.getX())      return  1;
        if (this.getY() < lab.getY())      return -2;
        if (this.getY() > lab.getY())      return  2;
        return 0;
    }	

    
}





