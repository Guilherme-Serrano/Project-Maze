import java.util.*;
import java.io.*;

public class LabEscolhido extends Comunicado implements Serializable 
{
    private String nome;
    private String labirinto;
    private String dataCriacao;
    private String dataUltimaAtualizacao;
    private String idCliente;
    
	/**
	 * Construtor para classe LabEscolhido
	 * 
	 * @return String nome, String labirinto, String data criacao, String data update, String Id do cliente
	 * 
	 */
    public LabEscolhido (String nom, String lab, String dtCri, String dtUltAtu, String Id)
    {
        this.nome = nom;
        this.labirinto = lab;
        this.dataCriacao = dtCri;
        this.dataUltimaAtualizacao = dtUltAtu;
        this.idCliente = Id;
               
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
	 * get para recuperar o nome do labirinto
	 * 
	 * @return string nomeLabirinto
	 * 
	 */
    public String getNome ()
    {
        return this.nome;
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
	 * get para recuperar a data da criacao do labirinto
	 * 
	 * @return string dataCriacao
	 * 
	 */
    public String getDataCriacao ()
    {
        return this.dataCriacao.toString();
    }
    
    /**
	 * get para recuperar a data do update do labirinto
	 * 
	 * @return string dataUpdate
	 * 
	 */
    public String getDataUltimaAtualizacao ()
    {
        return this.dataUltimaAtualizacao.toString();
    }
    
    /**
	 * Construtor do LabEscolhido apenas para o nome do labirinto
	 * 
	 * @param string nomeLab
	 * 
	 */	
    public LabEscolhido (String nome) 
    {
    	this.nome = nome;
    }
    
    /**
	 * Set para o labirinto
	 * 
	 * @param string labirinto
	 * 
	 */	
    public void setLabirinto (String lab) 
    {
    	this.labirinto = lab;
    }
    
    /**
	 * Metodo toString implementado para a classe LabEscolhido
	 * 
	 * @return String, retorna uma string do LabEscolhido
	 * 
	 */	
	@Override
	public String toString() {
        
        String str = "Nome: "            + this.nome + "\n" +
					 "ID: "	             + this.idCliente     + "\n" +
					 "Data Criacao: "    + this.dataCriacao   + "\n" +
					 "Data Update: "     + this.dataUltimaAtualizacao    + "\n" +
					 "Labirinto: "	     + this.labirinto;       
		
        return str;
    }
    
	/**
	 * Metodo equals implementado para a classe LabEscolhido
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
        
        LabEscolhido lab = (LabEscolhido)obj;
        
        if (this != lab) return false;       
        
        return true;
    }
    
    /**
	 * Metodo hashcode implementado para a classe LabEscolhido
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
