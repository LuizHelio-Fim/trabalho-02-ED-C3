package service;

import model.Cliente;
import structure.ArvoreBinariaPesquisa;

public class ClienteService {
	private ArvoreBinariaPesquisa arvore;
	
	public ClienteService() {
		arvore = new ArvoreBinariaPesquisa();
	}
	
	public boolean login(int numero, String senha) {
		return numero == 123456 && senha.equals("admin");
	}
	
	public boolean cadastrarCliente(String nome, String cpf, int idade, char sexo, double saldo) {
		if (!validarNome(nome)) return false;
		
		if (!validarCPF(cpf)) return false;
		
		if (!validarIdade(idade)) return false;
		
		if (!validarSexo(sexo)) return false;
		
		if (!validarSaldo(saldo)) return false;
		
		Cliente cliente = new Cliente(nome, cpf, idade, sexo, saldo);
		
		return arvore.inserir(cliente);
	}
	
	public boolean removerCliente(String nome) {
		return arvore.remover(nome);
	}
	
	public boolean atualizarCliente(String nome, int idade, char sexo, double saldo) { 
		if (!validarIdade(idade)) return false; 
		
		if (!validarSexo(sexo)) return false; 
		
		if (!validarSaldo(saldo)) return false;
		
		return arvore.atualizarCliente(nome, idade, sexo, saldo);
	}
	
	public Cliente buscarCliente(String nome) {
		return arvore.pesquisarNome(nome);
	}
	
	public boolean clienteExiste(String nome) {
		return buscarCliente(nome) != null;
	}
	
	public int contarMasculino() {
		return arvore.contarMasculino();
	}
	
	public int contarFeminino() {
		return arvore.contarFeminino();
	}
	
	public Cliente clienteMaisNovo() {
		return arvore.clienteMaisNovo();
	}
	
	public Cliente clienteMaisVelho() {
		return arvore.clienteMaisVelho();
	}
	
	public String listarFaixaEtaria(int min, int max) {
		return arvore.listarFaixaEtaria(min, max);
	}
	
	public void listarClientes() {
		arvore.camCentral();
	}
	
	// VALIDACOES
	
	private boolean validarNome(String nome) {
		return nome != null && !nome.isBlank();
	}
	
	private boolean validarCPF(String cpf) {
		if (cpf == null || cpf.isBlank()) {
			return false;
		}
		
		if (cpf.length() != 11) {
			return false;
		}
		
		for (int i=0; i < cpf.length(); i++) {
			if (!Character.isDigit(cpf.charAt(i))) {
				return false;
			}
		}
		
		return true;
	}
	
	private boolean validarIdade(int idade) {
		return idade > 0 && idade <= 120;
	}
	
	private boolean validarSexo(char sexo) {
		sexo = Character.toUpperCase(sexo);
		
		return sexo == 'M' || sexo == 'F';
	}
	
	private boolean validarSaldo(double saldo) {
		return saldo >= 0;
	}
}
