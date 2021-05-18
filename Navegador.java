public class Navegador {
	
	private Pilha<Cordenada> pilhaCaminho;
    private Pilha<Pilha> pilhaPossibilidades; 
    private Pilha<Cordenada> pilhaAdjacente;
    private Cordenada vetor[];
		
    /**
     * 
     * Faz a navegacao pelo labirinto
     * 
     * @param Labirinto
     */
	public Navegador(Labirinto Lab) throws Exception {
        
        //Valoriza as Pilhas
			pilhaCaminho = new Pilha(Lab.getTamanho());
			pilhaPossibilidades = new Pilha(Lab.getTamanho());
 		
		//Inicia a Entrada na Pilha Caminho 
			pilhaCaminho.guardeUmItem (Lab.getEntrada());
			
		//Retorno da Procurar saida (Variavel Gatilho do Looping)
			int retornoProcurarSaida = 0; 
		
		//LOOP Inicio
			do{	
				//Inicia a Busca
					pilhaAdjacente = new Pilha(3);								//Valoriza a Pilha Adjacente
					retornoProcurarSaida = procurarSaida(Lab);					//Chama a Procurar Saida e Espera o Retorno		
					
				//Verifica se Encontrou a Saida 
				if(retornoProcurarSaida == 1) 									
				{
					break;														
				}
				
				//Coloca o Item da Adjacente na Atual e Remove esse Item da Adjacente
				if(pilhaAdjacente.isVazia())
				{
					throw new Exception("Labirinto sem Caminhno!");
				}

				pilhaCaminho.guardeUmItem(pilhaAdjacente.recupereUmItem());
				Lab.setConteudo(pilhaCaminho.recupereUmItem().getX(),pilhaCaminho.recupereUmItem().getY(),"*");
				pilhaAdjacente.removaUmItem();
				pilhaPossibilidades.guardeUmItem(pilhaAdjacente);
				
			}while(retornoProcurarSaida == 0);
		//LOOP Fim
			
			int auxPularLinha = 0;
			Pilha <Cordenada> copia1 = (Pilha)pilhaCaminho.clone();			
			vetor = new Cordenada[Lab.getTamanho()];
			passaVetor(Lab);
			limpaMatriz(Lab);
			
			
	}
	
//--------------------------------------------------------------------------------------------------------------------------------------------
	
	/** 
     * Procura pela saida do labirinto     
     * @param Labirinto      
     * @return int, retorna 1 se localizar a saida e 0 caso nao tenha achado
     */	
	public int procurarSaida(Labirinto Lab)throws Exception{
		
		//Olha para todos os Lados e Atribui os Valores dos Returns
			int cima = olharParaCima(Lab);
			int baixo = olharParaBaixo(Lab);
			int esquerda = olharParaEsquerda(Lab);
			int direita = olharParaDireita(Lab);

		// 0 = achou espaço em branco
		// 1 = achou saida
		// 2 = parede

		//Logica Caso Normal
		
			if(cima == 1 || baixo == 1 || esquerda == 1 || direita == 1)//Verifica se achou a Saida
			{
				return 1; 			
			}
			else
			{
				if(cima == 0 || baixo == 0 || esquerda == 0 || direita == 0 )//Verifica se achou Espaço em Branco
				{
					return 0;	
				}		
			}	

		//Logica Caso Sem Saida	
			
			//Varre a Pilha Possibilidades
			while(!pilhaPossibilidades.isVazia())
			{			
				if(pilhaPossibilidades.recupereUmItem().isVazia()){
					//Se vazia Somente Remove da Possibilidades e da Caminho
					
					Lab.setConteudo(pilhaCaminho.recupereUmItem().getX(),pilhaCaminho.recupereUmItem().getY(),"X");	//Limpa o Conteudo Dela
					pilhaCaminho.removaUmItem();																	//Remove da Pilha Caminho
					pilhaPossibilidades.removaUmItem();																//Remove da Possibilidade																
				}else{
					//Se Tiver Conteudo 
									
					Lab.setConteudo(pilhaCaminho.recupereUmItem().getX(),pilhaCaminho.recupereUmItem().getY(),"X");	//Limpa o Conteudo Dela
					pilhaCaminho.removaUmItem();																	//Remove da Pilha Caminho
					pilhaAdjacente = pilhaPossibilidades.recupereUmItem();											//Coloca na Pilha Adjacente
					pilhaPossibilidades.removaUmItem();																//Remove da Possibilidade
					break;
				}
			}	
		
		return 0;

	}

//--------------------------------------------------------------------------------------------------------------------------------------------
	
	 
	/** 
     * Verifica a posicao acima da posicao atual     
     * @param Labirinto      
     * @return int, retorna 0 para espaco livre, 1 se localizar a saida e 2 se for parede
     */	
	public int olharParaCima(Labirinto Lab)throws Exception{

		//Busca a Cordenada Atual e Valoriza X e Y
			Cordenada Atual;
			Atual = pilhaCaminho.recupereUmItem();
			int x = Atual.getX();
			int y = Atual.getY();
		
			if(x == 0)													//Verifica se a cordenada de Y é a Ultima Linha
			{
				x = 0; 
			}
			else{
				x --;
			}
		
		//Cria a cordenada que vai ser Verificada
			Cordenada Proxima = new Cordenada(x,y);	
		
		//Verifica se a Cordenada Proxima é um Caminho(Vazio) ou é uma Parede
		
			if(Lab.getConteudo(x, y).charAt(0) == ' ')
			{
					pilhaAdjacente.guardeUmItem(Proxima);
					return 0;
			}
			else
			{ 
				if(Lab.getConteudo(x, y).charAt(0) == 'S')
				{
					return 1;
				}
				else
				{
					return 2;
				}
			}
		
    }

//--------------------------------------------------------------------------------------------------------------------------------------------
	/** 
     * Verifica a posicao abaixo da posicao atual     
     * @param Labirinto      
     * @return int, retorna 0 para espaco livre, 1 se localizar a saida e 2 se for parede
     */	
	public int olharParaBaixo(Labirinto Lab)throws Exception{

		//Busca a Cordenada Atual e Valoriza X e Y
			Cordenada Atual; 
			Atual = pilhaCaminho.recupereUmItem();
			int x = Atual.getX();
			int y = Atual.getY();	
				
        //Verifica se a cordenada de Y é a Ultima Linha
			if(x == (Lab.getLinha()-1))
			{
				x=Lab.getLinha()-1;		
			}
			else
			{
				x ++;
			}		
			
		//Cria a cordenada que vai ser Verificada
			Cordenada Proxima = new Cordenada(x,y);			
		
		//Verifica se a Cordenada Proxima é um Caminho(Vazio) ou é uma Parede
			if(Lab.getConteudo(x, y).charAt(0) == ' ')
			{
				pilhaAdjacente.guardeUmItem(Proxima);
				return 0;
			}
			else
			{ 
				if(Lab.getConteudo(x, y).charAt(0) == 'S')
				{
					return 1;
				}
				else
				{
					return 2;
				}
			}
    }
    
//--------------------------------------------------------------------------------------------------------------------------------------------
	/** 
     * Verifica a posicao a esquerda da posicao atual     
     * @param Labirinto      
     * @return int, retorna 0 para espaco livre, 1 se localizar a saida e 2 se for parede
     */	
	public int olharParaEsquerda(Labirinto Lab)throws Exception{

		//Busca a Cordenada Atual e Valoriza X e Y
			Cordenada Atual; 
			Atual = pilhaCaminho.recupereUmItem();
			int x = Atual.getX();
			int y = Atual.getY();
		
		//Verifica se a cordenada de Y é 0 (Primeira Coluna)	
			if(y == 0)
			{
				y = 0;
			}
			else
			{
				y --;
			}
		
		//Cria a cordenada que vai ser Verificada
			Cordenada Proxima = new Cordenada(x,y);
		
		//Verifica se a Cordenada Proxima é um Caminho(Vazio) ou é uma Parede
			if(Lab.getConteudo(x, y).charAt(0) == ' ')
			{
				pilhaAdjacente.guardeUmItem(Proxima);
				return 0;
			}
			else
			{ 
				if(Lab.getConteudo(x, y).charAt(0) == 'S')
				{
					return 1;
				}
				else
				{
					return 2;
				}
			}
    }
    
//--------------------------------------------------------------------------------------------------------------------------------------------
	/** 
     * Verifica a posicao a direita da posicao atual     
     * @param Labirinto      
     * @return int, retorna 0 para espaco livre, 1 se localizar a saida e 2 se for parede
     */
	public int olharParaDireita(Labirinto Lab)throws Exception{

		//Busca a Cordenada Atual e Valoriza X e Y
			Cordenada Atual; 
			Atual = pilhaCaminho.recupereUmItem();
			int x = Atual.getX();
			int y = Atual.getY();

		//Verifica se a cordenada de Y é a Ultima Coluna		
			if(y == Lab.getColuna())
			{
				y = Lab.getColuna();
			}
			else
			{
				y ++;
			}
		
		//Cria a cordenada que vai ser Verificada
			Cordenada Proxima = new Cordenada(x,y);

		
		//Verifica se a Cordenada Proxima é um Caminho(Vazio) ou é uma Parede
			if(Lab.getConteudo(x, y).charAt(0) == ' ')
			{
				pilhaAdjacente.guardeUmItem(Proxima);
				return 0;
            }
			else 
			{
				if(Lab.getConteudo(x, y).charAt(0) == 'S')
				{
					return 1;
				}
				else
				{
					return 2;
				}
			}	
	}
	
//--------------------------------------------------------------------------------------------------------------------------------------------	
	/** 
     * Passa as cordenadas do caminho correto para um vetor     
     * @param Labirinto 
     */
	public void passaVetor(Labirinto Lab)throws Exception{
			
		//Cria variavel para Looping
			int i=0;
		
		//Cria uma pilha auxiliar para inverter a ordem das cordenadas
		Pilha <Cordenada> aux = new Pilha(Lab.getTamanho()); 		
			
		//Enquanto pilha nao for Vazia, pega item da Pilha e para outra pilha para inverter a ordem
			while(!pilhaCaminho.isVazia())
			{
				aux.guardeUmItem(pilhaCaminho.recupereUmItem());			
				pilhaCaminho.removaUmItem();
				i++;				
			}
			
			//zera o contador
			i = 0;
			
			//passa o conteudo da pilha para um vetor
			while(!aux.isVazia())
			{
				vetor[i] = aux.recupereUmItem();
				aux.removaUmItem();
				i++;
			}		
		}

//--------------------------------------------------------------------------------------------------------------------------------------------
	/** 
     * Preenche o labirinto com o caminho correto   
     * @param Labirinto 
     */
	public void limpaMatriz (Labirinto Lab){
		
		//For par linha e for para coluna para Limpar as Coisas Inseridas na Matriz
			for(int row = 0; row < Lab.getLinha(); row++)
			{
				for (int col = 0; col < Lab.getColuna(); col++) 
				{
					if(Lab.getConteudo(row, col).charAt(0) != '#' && Lab.getConteudo(row, col).charAt(0) != 'E' && Lab.getConteudo(row, col).charAt(0) != 'S')
					{
						Lab.setConteudo(row,col," ");
					}             
				}
			}
			
		//Cria variavel para Looping
			int i = 0;
				
		//Enquanto Vetor nao for Vazio, Coloca o * no Caminho Certo MENOS na Entrada
				while(vetor[i] != null)
				{
					if(!vetor[i].equals(Lab.getEntrada()))
					{
						Lab.setConteudo(vetor[i].getX(), vetor[i].getY(), "*");
					}
					i++;
				}
	}
	
//--------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Metodo toString implementado para a classe Navegador 
	 * @return String, retorna uma string do caminho correto 
	 */	
	@Override
	public String toString() {
        
			String str ="\n";
			int i = 0;
			
			str += "Caminho em Cordenadas \nInicio: ";
					
			while(vetor[i] != null)
			{
				if(i%10 == 0){
					str += "\n";
				}
				
				str += vetor[i] + "	";
				i++;
			}       
			
			str += " :Final\n";
			
			return str;
    }
    
	/**
	 * Metodo equals implementado para a classe Navegador 
	 * @return boolean, retorna verdadeiro se for igual e falso se for diferente
	 */	
    @Override
    public boolean equals (Object obj)
    {
			if (this==obj) return true;
        
			if (obj==null) return false;        
      
			if (obj.getClass()!= Navegador.class) return false;
        
			Navegador lab = (Navegador)obj;
        
			if (this != lab) return false;       
        
			return true;
    }
    
    
    /**
	 * Metodo hashcode implementado para a classe Navegador 
	 * @return int, retorna um numero hashcode
	 */	
    @Override
    public int hashCode ()
    {
      
			int ret=666; 
        
			ret = (13*ret) + new Integer(this.vetor.length).hashCode(); 
      
        
			if (ret<0) ret = -ret;
        
			return ret;
    }
    	 
}

