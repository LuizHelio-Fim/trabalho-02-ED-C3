package structure;

import model.Cliente;
import model.No;

public class ArvoreBinariaPesquisa {

    private No raiz;
    private int quantNos;

    public ArvoreBinariaPesquisa() {
        this.raiz = null;
        this.quantNos = 0;
    }

    public boolean eVazia() {
        return this.raiz == null;
    }

    public No getRaiz() {
        return raiz;
    }

    public int getQuantNos() {
        return quantNos;
    }

    public boolean inserir(Cliente cliente) {

        if (pesquisarNome(cliente.getNome()) != null) {
            return false;
        }

        if (pesquisarCPF(cliente.getCPF())) {
            return false;
        }

        raiz = inserir(cliente, raiz);
        quantNos++;

        return true;
    }
    private No inserir(Cliente cliente, No no) {

        if (no == null) {
            return new No(cliente);
        }

        if (cliente.getNome().compareToIgnoreCase(no.getInfo().getNome()) < 0) {
            no.setEsq(inserir(cliente, no.getEsq()));
        } else {
            no.setDir(inserir(cliente, no.getDir()));
        }

        return no;
    }

    public Cliente pesquisarNome(String nome) {
        No resultado = pesquisarNome(nome, raiz);

        if (resultado != null) {
            return resultado.getInfo();
        }

        return null;
    }
    private No pesquisarNome(String nome, No no) {

        if (no == null) {
            return null;
        }

        int comparacao = nome.compareToIgnoreCase(no.getInfo().getNome());

        if (comparacao == 0) {
            return no;
        }

        if (comparacao < 0) {
            return pesquisarNome(nome, no.getEsq());
        }

        return pesquisarNome(nome, no.getDir());
    }

    public boolean pesquisarCPF(String cpf) {
        return pesquisarCPF(cpf, raiz);
    }
    private boolean pesquisarCPF(String cpf, No no) {

        if (no == null) {
            return false;
        }

        if (no.getInfo().getCPF().equals(cpf)) {
            return true;
        }

        return pesquisarCPF(cpf, no.getEsq())
                || pesquisarCPF(cpf, no.getDir());
    }

    public boolean remover(String nome) {

        if (pesquisarNome(nome) == null) {
            return false;
        }

        raiz = remover(nome, raiz);
        quantNos--;

        return true;
    }
    private No remover(String nome, No no) {

        if (no == null) {
            return null;
        }

        int comparacao = nome.compareToIgnoreCase(no.getInfo().getNome());

        if (comparacao < 0) {

            no.setEsq(remover(nome, no.getEsq()));

        } else if (comparacao > 0) {

            no.setDir(remover(nome, no.getDir()));

        } else {

            // nó folha
            if (no.getEsq() == null && no.getDir() == null) {
                return null;
            }

            // um filho
            if (no.getEsq() == null) {
                return no.getDir();
            }

            if (no.getDir() == null) {
                return no.getEsq();
            }

            // dois filhos
            No maiorEsquerda = maiorEsquerda(no.getEsq());

            no.setInfo(maiorEsquerda.getInfo());

            no.setEsq(remover(maiorEsquerda.getInfo().getNome(), no.getEsq()));
        }

        return no;
    }

    private No maiorEsquerda(No no) {

        while (no.getDir() != null) {
            no = no.getDir();
        }

        return no;
    }


    public int contarMasculino() {
        return contarMasculino(raiz);
    }
    private int contarMasculino(No no) {

        if (no == null) {
            return 0;
        }

        int contador = 0;

        if (Character.toUpperCase(no.getInfo().getSexo()) == 'M') {
            contador++;
        }

        contador += contarMasculino(no.getEsq());
        contador += contarMasculino(no.getDir());

        return contador;
    }

    public int contarFeminino() {
        return contarFeminino(raiz);
    }

    private int contarFeminino(No no) {

        if (no == null) {
            return 0;
        }

        int contador = 0;

        if (Character.toUpperCase(no.getInfo().getSexo()) == 'F') {
            contador++;
        }

        contador += contarFeminino(no.getEsq());
        contador += contarFeminino(no.getDir());

        return contador;
    }

    public Cliente clienteMaisNovo() {

        if (raiz == null) {
            return null;
        }

        return clienteMaisNovo(raiz, raiz.getInfo());
    }
    private Cliente clienteMaisNovo(No no, Cliente menor) {

        if (no == null) {
            return menor;
        }

        if (no.getInfo().getIdade() < menor.getIdade()) {
            menor = no.getInfo();
        }

        menor = clienteMaisNovo(no.getEsq(), menor);
        menor = clienteMaisNovo(no.getDir(), menor);

        return menor;
    }

    public Cliente clienteMaisVelho() {

        if (raiz == null) {
            return null;
        }

        return clienteMaisVelho(raiz, raiz.getInfo());
    }
    private Cliente clienteMaisVelho(No no, Cliente maior) {

        if (no == null) {
            return maior;
        }

        if (no.getInfo().getIdade() > maior.getIdade()) {
            maior = no.getInfo();
        }

        maior = clienteMaisVelho(no.getEsq(), maior);
        maior = clienteMaisVelho(no.getDir(), maior);

        return maior;
    }

    public void listarFaixaEtaria(int min, int max) {
        listarFaixaEtaria(raiz, min, max);
    }
    private void listarFaixaEtaria(No no, int min, int max) {

        if (no != null) {

            listarFaixaEtaria(no.getEsq(), min, max);

            if (no.getInfo().getIdade() >= min
                    && no.getInfo().getIdade() <= max) {

                System.out.println(no.getInfo());
            }

            listarFaixaEtaria(no.getDir(), min, max);
        }
    }

    public boolean atualizarCliente(String nome,
                                    int idade,
                                    char sexo,
                                    double saldo) {

        No cliente = pesquisarNome(nome, raiz);

        if (cliente == null) {
            return false;
        }

        cliente.getInfo().setIdade(idade);
        cliente.getInfo().setSexo(sexo);
        cliente.getInfo().setSaldo(saldo);

        return true;
    }

    public void camCentral() {
        camCentral(raiz);
    }
    private void camCentral(No no) {

        if (no != null) {

            camCentral(no.getEsq());

            System.out.println(no.getInfo());

            camCentral(no.getDir());
        }
    }
}
