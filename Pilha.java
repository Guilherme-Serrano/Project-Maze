public class Pilha <X> implements Cloneable
{
    private Object[] elemento; // private X[] elemento;
    private int      tamanhoInicial;
    private int      ultimo = -1; // vazia
    
    /** 
     * Construtor padrao, pois nao tem parametros 
     */    
    public Pilha ()
    {
        this.elemento       = new Object [10]; // this.elemento = new X [10];
        this.tamanhoInicial = 10;
    }
    
    /** 
     * Construtor de pilha
     * @param int Tamanho da pilha
     */  
    public Pilha (int tamanho) throws Exception
    {
        if (tamanho<=0)
            throw new Exception ("Tamanho invalido");
            
        this.elemento       = new Object [tamanho]; // this.elemento = new X [tamanho];
        this.tamanhoInicial = tamanho;
    }
    
    /** 
     * Metodo para pegar a quantidade de itens na pilha
     * @return int, retorna a quantidade de itens da pilha
     */ 
    public int getQuantidade ()
    {
        return this.ultimo+1;
    }
    
    /** 
     * Metodo para redimensionar a pilha
     * @param float, fator desejado para redimensionar a pilha
     */ 
    private void redimensioneSe (float fator)
    {
        // X[] novo = new X [Math.round(this.elemento.length*fator)];
        Object[] novo = new Object [Math.round(this.elemento.length*fator)];
        
        for(int i=0; i<=this.ultimo; i++)
            novo[i] = this.elemento[i];

        this.elemento = novo;
    }
    
    /** 
     * Metodo para inserir um item na pilha
     * @param x, item para inserir na pilha
     */ 
    public void guardeUmItem (X x) throws Exception
    {
        if (x==null)
            throw new Exception ("Valor ausente");
        
        if (this.ultimo+1==this.elemento.length) // cheia
            this.redimensioneSe (2.0F);
            
        this.ultimo++;
        this.elemento[this.ultimo]=x;
    }

    /** 
     * Metodo para recuperar um item na pilha
     * @return x, retorna o ultimo item da pilha
     */ 
    public X recupereUmItem () throws Exception
    {
        if (this.ultimo==-1)
            throw new Exception ("Pilha vazia");
            
        return (X)this.elemento[this.ultimo];
    }

    /** 
     * Metodo para remover um item na pilha
    */ 
    public void removaUmItem () throws Exception
    {
        if (this.ultimo==-1) // vazia
            throw new Exception ("Nada a remover");

        this.elemento[this.ultimo] = null;
        this.ultimo--;

        if (this.elemento.length>this.tamanhoInicial &&
            this.ultimo+1<=Math.round(this.elemento.length*0.25F))
            this.redimensioneSe (0.5F);
    }    
    
    /** 
     * Metodo para verificar se a pilha esta lotada
     * @return boolean, verdadeiro para cheia e falso caso contrario
     */ 
    public boolean isCheia ()
    {
        return this.ultimo+1==this.elemento.length;
    }
    
    /** 
     * Metodo para verificar se a pilha esta vazia
     * @return boolean, verdadeiro para vazia e falso caso contrario
     */ 
    public boolean isVazia ()
    {
        return this.ultimo==-1;
    }
    
    /**
	 * Metodo toString implementado para a classe Pilha 
	 * @return String, retorna uma string da Pilha
	 */	
    @Override
    public String toString ()
    {
        String ret;
        
        if (this.ultimo==0)
            ret="1 elemento";
        else
            ret=(this.ultimo+1)+" elementos";
            
        if (this.ultimo!=-1)
            ret += ", sendo o ultimo "+this.elemento[this.ultimo];
        
        return ret;
    }
    
    /**
	 * Metodo equals implementado para a classe Pilha 
	 * @return boolean, retorna verdadeiro se for igual e falso se for diferente
	 */	
    @Override
    public boolean equals (Object obj)
    {
        if(this==obj)
            return true;

        if(obj==null) // só estou testando o obj, porque sei que o this NUNCA é null
            return false;

        if(this.getClass()!=obj.getClass())
            return false;

        Pilha<X> pil = (Pilha<X>) obj;

        if(this.ultimo!=pil.ultimo)
            return false;

        if(this.tamanhoInicial!=pil.tamanhoInicial)
            return false;

        for(int i=0 ; i<=this.ultimo; i++)
            if(!this.elemento[i].equals(pil.elemento[i]))
                return false;

        return true;
    }

    
    /**
	 * Metodo hashcode implementado para a classe Pilha 
	 * @return int, retorna um numero hashcode
	 */
    @Override
    public int hashCode ()
    {
        int ret=666/*qualquer positivo*/;

        ret = ret*7/*primo*/ + new Integer(this.ultimo        ).hashCode();
        ret = ret*7/*primo*/ + new Integer(this.tamanhoInicial).hashCode();

        for (int i=0; i<=this.ultimo; i++)
            ret = ret*7/*primo*/ + this.elemento[i].hashCode();

        if (ret<0)
            ret=-ret;

        return ret;
    }
    
    /**
	 * Construtor de clone da classe Pilha
	 * @param Pilha Pilha que deseja clonar
	 */
    public Pilha (Pilha modelo) throws Exception
    {
        if (modelo==null)
            throw new Exception ("Modelo ausente");
            
        
        this.elemento       = modelo.elemento;
        this.tamanhoInicial = modelo.tamanhoInicial;
        this.ultimo         = modelo.ultimo;        
        
        //private Object[] elemento; // private X[] elemento;
		//private int      tamanhoInicial;
		//private int      ultimo = -1; // vazia
        
      //this.elem       =       new X      [modelo.elem.length];
        this.elemento       = (X[]) new Object [modelo.elemento.length];
        
        			
		
		for (int i = 0; i < modelo.getQuantidade(); i++)
		{
			
			this.elemento[this.ultimo-i] = modelo.elemento[this.ultimo-i];
			
		}
		
        
    }
    
    /**
	 * Metodo de clone da classe Pilha
	 * @return Object, retorna o objeto pilha clonado
	 */
    @Override
    public Object clone ()
    {
        Pilha <X> ret=null;
        
        try
        {
            ret = new Pilha<X> (this);
        }
        catch (Exception erro)
        {}
        
        return ret;
    }
    
    //fazer o metodo clone
}
