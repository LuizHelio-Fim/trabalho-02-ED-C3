package model;

public class No {
	private Cliente info; 
	private No esq, dir;
	
	public No(Cliente elem){
		this.info = elem;
		this.esq = null;
		this.dir = null;
	}
	
	public No getEsq(){
		return this.esq;
	}
	
	public No getDir(){
		return this.dir;
	}
	
	public Cliente getInfo(){
		return this.info;
	}
	
	public void setEsq(No no){
		this.esq = no;
	}
	
	public void setDir(No no){
		this.dir = no;
	}
	
	public void setInfo(Cliente elem){
		this.info = elem;
	}
}
