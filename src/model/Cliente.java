package model;

public class Cliente {
	private String nome;
	private String CPF;
	private int idade;
	private char sexo;
	private double saldo;
	
	public Cliente(String nome, String cPF, int idade, char sexo, double saldo) {
		this.nome = nome;
		CPF = cPF;
		this.idade = idade;
		this.sexo = sexo;
		this.saldo = saldo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	@Override
	public String toString() {
		return "\nNome: " + nome + "\n"
				+ "CPF: " + CPF + "\n"
				+ "Idade: " + idade + "\n"
				+ "Sexo: " + sexo + "\n"
				+ "Saldo: " + saldo;
	}
}
