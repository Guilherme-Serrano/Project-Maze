import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Labirinto <X> implements Cloneable {
    
    private String[][] Lab;
    private String texto;
    private boolean[][] Visitado; //n√£o usei ainda
    private static Cordenada entrada;
    private static Cordenada saida;
      
    
    
    /**
     * 
     * Criar o labirinto em uma matriz
     * 
     * @param arquivo do labirinto
     */
    
    public Labirinto(String lab)throws Exception{
		
		
		
		texto = lab; //transforma o arquivo txt em uma string	
		
		
		int row, col;
                  
        String[] str = texto.split("[\r]?\n"); //passa a linha da string para uma pos do vetor
        
        int linha = Integer.parseInt(str[0]); //pega a quantidade de linhas pelo numero
        
        int auxLinha = str.length -1; //pega a quantidade de linhas da string para verificar o tamanho
        
        int coluna = str[1].length(); //pega a quantidade de colunas             
       
                
        //define o tamanho da matriz
        Lab = new String [linha][coluna];         
         
        //faz todas as valida√ß√µes
        validaLabirinto(str, auxLinha);
		  
          
        //preenche a matriz
		for(row = 0; row < getLinha(); row++){
			
			
			
			for (col = 0; col < getColuna(); col++) {
                
                if(str[row+1].charAt(col) == 'E'){
				
					entrada = new Cordenada(row, col); //pega a cordenada da entrada
					
				}else if(str[row+1].charAt(col) == 'S'){
				
					saida = new Cordenada(col, row); //pega a cordenada da saida
				
				}
                
                //if(verificaTamanho(row, col));
                
                Lab[row][col] = String.valueOf(str[row+1].charAt(col)); //preenche a matriz
               
            }
		}	
	
	
}


//---------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * 
     * Faz todas as validaÁıes do labirinto
     * 
     * @param str[] labirinto
     * @param auxLinha quantidade de linhas do labirinto
     * 
     */
    
    
    public void validaLabirinto(String str[], int auxLinha)throws Exception{
		
		//Valida as dimensoes do labirinto
        if(!validaTamanho(str, auxLinha)){
			throw new Exception("Tamanho de linhas errado!");		
		};
		
		
		//Valida qnt de entradas
        if(!validaEntrada(str)){
			throw new Exception("Error: Quantida de entradas invalidas");
		};
		
		//Valida qnt de saidas
        if(!validaSaida(str)){
			throw new Exception("Error: Quantida de saidas invalidas");
		};
		
		//Valida se tem paredes na extremidade
        if(!validaParedes(str)){
			throw new Exception("Error: Faltando paredes nas extremidades");
		};
		
	}
   
    
    /**
     * 
     * valida se o labirinto possui alguma parede faltando nas extremidades
     * 
     * @param str[] labirinto
     * @return boolean, retorna falso se estiver faltando alguma parede
     * 
     */
    
    public boolean validaParedes(String str[])
    {
		for(int i = 0; i < getLinha(); i++){
		
			if(str[1].charAt(i) == ' '){
				
				return false;
					
			}
			
			if(str[getLinha()].charAt(i) == ' '){
				
				return false;
					
			}
			
			if(str[i+1].charAt(0) == ' '){
				
				return false;
					
			}
			
			if(str[i+1].charAt((getColuna()-1)) == ' '){
				
				return false;
					
			}
			
		}
		
		return true;
			
	}


//---------------------------------------------------------------------------------------------------------------------------------------------------------
   
    
    /**
     * 
     * valida se o labirinto escolhido tem a dimensao correta
     * 
     * @param str[] labirinto
     * @param linha quantidade de linhas do labirinto
     * @return boolean, retorna falso se as dimensoes do labirinto estiverem erradas
     * 
     */
    
    public boolean validaTamanho(String str[], int linha)
    {
		for(int row = 0; row < getLinha(); row++){
		if (str[row+1].length() != getColuna() || linha != getLinha()) {
                return false;
			}
		}
		return true;
	}

//---------------------------------------------------------------------------------------------------------------------------------------------------------
	
    /**
	 * 
	 * Verifica se tem uma entrada no labirinto
	 * 
	 * @param string str
	 * @return boolean, retorna falso se estiver faltado entrada ou se houver mais de uma no labirinto
	 */
    
    
    
    
    public boolean validaEntrada(String str[])
    {
		boolean entrada = false;
		
		for(int row = 0; row < getLinha(); row++){
			for(int col = 0; col < getColuna(); col++){
				
				if (str[row+1].charAt(col) == 'E') {
                
					if(entrada == true){
					
						return false;
						
					}else{
					
						entrada = true;
					
					}
			}
			
			}
		}
		
		if(entrada == false)
		{
		
			return false;
			
		}
		return true;
		
	}

//---------------------------------------------------------------------------------------------------------------------------------------------------------
	
    /**
	 * 
	 * Verifica se tem uma saida no labirinto
	 * 
	 * @param string str
	 * @return boolean, retorna falso se estiver faltado saida ou se houver mais de uma no labirinto
	 */
	
	
	public boolean validaSaida(String str[])
    {
		boolean saida = false;
		
		for(int row = 0; row < getLinha(); row++){
			for(int col = 0; col < getColuna(); col++){
				
				if (str[row+1].charAt(col) == 'S') {
                
					if(saida == true){
					
						return false;
						
					}else{
					
						saida = true;
					
					}
			}
			
			}
		}
		
		if(saida == false)
		{
		
			return false;
			
		}
		return true;
		
	}
//---------------------------------------------------------------------------------------------------------------------------------------------------------
   
	/**
	 * 
	 * Pega a quantidade de colunas
	 * 
	 * 
	 * @return boolean, retorna int com a quantidade de colunas do labirinto
	 */	
    public int getColuna() 
    {
		return Lab[0].length;
			
	}
    
    
    /**
	 * 
	 * Pega a quantidade de linhas do labirinto
	 * 
	 * 
	 * @return boolean, retorna int com a quantidade de linhas do labirinto
	 */
	
	public int getLinha()
    {
		return Lab.length;
						
	}
	
//---------------------------------------------------------------------------------------------------------------------------------------------------------
	
	
	/**
	 * 
	 * Pega o conteudo da matriz a partir da cordenada
	 * 
	 * @param x int da cordenada do eixo X
	 * @param y int da cordenada do eixo Y
	 * @return String, retorna o conteudo da matriz na posicao (x,y)
	 */	
	public String getConteudo(int x, int y)
	{
		return Lab[x][y];
	}
	
	/**
	 * Altera o conteudo da matriz do labirinto
	 * 
	 * @param x int da cordenada do eixo X
	 * @param y int da cordenada do eixo Y
	 * 
	 */	
	public void setConteudo(int x, int y, String z){
		
		this.Lab[x][y] = z;
	
	}
	
//---------------------------------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * Pega a quantidade de posicoes da matriz labirinto
	 * 
	 * @return int, retorna a quantidade de posiÁoes da matriz labirinto
	 * 
	 */	
	
	public int getTamanho()
	{
		return (getLinha() * getColuna());
	}
	
	/**
	 * Pega a cordenada da posicao da entrada
	 * 
	 * @return Cordenada, retorna a cordenada da entrada
	 * 
	 */	
	public Cordenada getEntrada()
	{
		return this.entrada;	
	}
	
	public Cordenada getSaida()
	{
		return this.saida;	
	} 	 	
	
//---------------------------------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * Metodo toString implementado para a classe labirinto
	 * 
	 * @return String, retorna uma string do labirinto
	 * 
	 */	
	@Override
	public String toString() {
        
        String str="";
        
        for(int row = 0; row < getLinha(); row++){
						
			for (int col = 0; col < getColuna(); col++) {
                
                str += this.Lab[row][col];
               
            }
            
            str += "\n";
		}
		
        return str;
    }
    
	/**
	 * Metodo equals implementado para a classe labirinto
	 * 
	 * @return boolean, retorna verdadeiro se for igual e falso se for diferente
	 * 
	 */	
    @Override
    public boolean equals (Object obj)
    {
        if (this==obj) return true;
        
        if (obj==null) return false;        
      
        if (obj.getClass()!= Labirinto.class) return false;
        
        Labirinto lab = (Labirinto)obj;
        
        if (this != lab) return false;       
        
        return true;
    }
    
    /**
	 * Metodo hashcode implementado para a classe labirinto
	 * 
	 * @return int, retorna um numero hashcode
	 * 
	 */	
    @Override
    public int hashCode ()
    {
      
        int ret=666; 
        
        ret = (13*ret) + new Integer(this.getTamanho()).hashCode(); 
      
        
        if (ret<0) ret = -ret;
        
        return ret;
    }
    
    /**
	 * Construtor de copia para labirinto
	 * 
	 * @param labirinto, labirinto a ser clonado
	 * 
	 */	
    public Labirinto (Labirinto modelo) throws Exception
    {
        if (modelo==null)
            throw new Exception ("Modelo ausente");
            
        this.Lab = modelo.Lab;
		this.texto = modelo.texto;
		this.entrada = modelo.entrada;
		this.saida = modelo.saida;		
		
        
        String[] str = modelo.texto.split("[\r]?\n");
       
        this.Lab     = new String [getLinha()][getColuna()];
        
        for(int row = 0; row < getLinha(); row++){		
			
			for (int col = 0; col < getColuna(); col++) {
                
                if(str[row+1].charAt(col) == 'E'){
				
					this.entrada = new Cordenada(row, col); //pega a cordenada da entrada
					
				}else if(str[row+1].charAt(col) == 'S'){
				
					this.saida = new Cordenada(col, row); //pega a cordenada da saida
				
				}
                
                //if(verificaTamanho(row, col));
                
                this.Lab[row][col] = modelo.Lab[row][col]; //Copia a matriz
                              
            }
		}
    }
    
    
    /**
	 * Metodo clone para o labirinto
	 * 
	 * @return Object, retorna o clone do labirinto
	 * 
	 */	
    @Override
    public Object clone ()
    {
        Labirinto<X> ret=null;
        
        try
        {
            ret = new Labirinto<X> (this);
        }
        catch (Exception erro)
        {}
        
        return ret;
    }
    
    /**
	 * Metodo compareTo para o labirinto
	 * @param Labirinto labirinto
	 * @return int, retorna 0 se for igual
	 */	
    //@Override
    public int compareTo (Labirinto lab)
    {
        if (this.getTamanho()<lab.getTamanho())      return -1;
        if (this.getTamanho()>lab.getTamanho())      return  1;
        if (this.entrada.getX()<lab.entrada.getX())  return -2;
        if (this.entrada.getX()>lab.entrada.getX())  return  2;
        if (this.entrada.getY()<lab.entrada.getY())  return -2;
        if (this.entrada.getY()>lab.entrada.getY())  return  2;
        if (this.saida.getX()<lab.saida.getX())      return -3;
        if (this.saida.getX()>lab.saida.getX())      return  3;
        if (this.saida.getY()<lab.saida.getY())      return -4;
        if (this.saida.getY()>lab.saida.getY())      return  4;
        return 0;
    }					
		
			
}


	
	
    
    
   
  


