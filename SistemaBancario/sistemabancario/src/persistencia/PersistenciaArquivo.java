package persistencia;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import sistema.Cliente;

public class PersistenciaArquivo implements Serializable{

	private static final long serialVersionUID = 1L;
	private List<Cliente> clientesCadastrados = new ArrayList<>();

	
	public List<Cliente> getClientesCadastrados() {
		return clientesCadastrados;
	}

	public void setClientesCadastrados(List<Cliente> clientesCadastrados) {
		this.clientesCadastrados = clientesCadastrados;
	}

	public PersistenciaArquivo() {
		carregarDados();
	}
	
	public String toString() {
		return "[clientesCadastrados]" + clientesCadastrados + "\n";
	}

	public void adicionarCliente(Cliente cl) {
		carregarDados();
		if (!clientesCadastrados.contains(cl)) {
			clientesCadastrados.add(cl);
			salvarDados();
			System.out.println("Cliente cadastrado com Sucesso!");
		} else
			System.err.println("Cliente já cadastrado");
	}
	
	public Cliente localizarClienteCPF(String cpf) {
		carregarDados();
		Cliente temp = new Cliente(cpf);
		if(clientesCadastrados.contains(temp)) {
			int index = clientesCadastrados.indexOf(temp);
			temp = clientesCadastrados.get(index);
			return temp;
		} else
			return null;
	}

	private void salvarDados() {
		try {
			FileOutputStream fos = new FileOutputStream("dados.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(clientesCadastrados);
			oos.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	private void carregarDados() {
		try {
			FileInputStream fis = new FileInputStream("dados.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			clientesCadastrados = (ArrayList<Cliente>) ois.readObject();
			ois.close();
		} catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	
	public void listarClientes() {
		carregarDados();
		for (Cliente cliente : getClientesCadastrados() ) {
            System.out.println(cliente);
        }
	}
	
	public void removerCliente(String cpf) {
		carregarDados();
		Cliente remcliente = new Cliente();
		remcliente.setCpf(cpf);
		clientesCadastrados.remove(remcliente);
		salvarDados();
	}
	
}
