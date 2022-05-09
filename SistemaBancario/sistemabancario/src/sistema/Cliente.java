package sistema;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import sistema.Conta;

public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;
	private String cpf;
	private String nome;
	private List<Conta> contas = new ArrayList<>();
	private Conta conta;
	
	public Cliente() {
		
	}
	
	public Cliente(String cpf) {
		this.cpf = cpf;
	}
	
	public Cliente(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
		this.contas = contas;
	}
	
	public Cliente(String nome, String cpf, List<Conta> contas) {
		this.nome = nome;
		this.cpf = cpf;
		this.contas = contas;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Conta> getContas() {
		return contas;
	}
	
	//Verificar esse set.
	public void setContas(List<Conta> contas) {
		contas.addAll(contas);
	}

	public int hashCode() {
		return Objects.hash(cpf);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(cpf, other.cpf);
	}

	public String toString() {
		return "Cliente [cpf=" + cpf + ", nome=" + nome + ", contas=" + contas + "]";
	}

	public void cadastrarConta(Conta c) {
		if(!contas.contains(c))
			contas.add(c);
		else
			System.err.println("Conta já cadastrada!");
	}
	
	public void removerConta(Conta c) {
		if(contas.contains(c))
			contas.remove(c);
		else
			System.err.println("Conta não cadastrada!");
	}
	
	public Conta localizarConta(int numeroConta) {
		Conta temp = new Conta(numeroConta);
		if(contas.contains(temp))
		{
			int index = contas.indexOf(temp);
			temp = contas.get(index);
			return temp;
		}
		else
			return null;
	}
	
	public void conta(Conta conta) {
		this.conta = conta;
	}
	
	public boolean containsConta(Conta c) {
		if(contas.contains(c))
			return true;
		else
			return false;
	}
	
}
