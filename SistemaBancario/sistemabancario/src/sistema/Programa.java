package sistema;
//Esse Sistema é de inteira interação com o funcionário do banco.
//Para qualquer operação no sistema que o cliente queira fazer, será necessário utilização do sistema por um atendente.
import persistencia.PersistenciaArquivo;
import sistema.Cliente;
import sistema.Conta;
import java.io.Serializable;
import java.util.Scanner;

public class Programa {
	
	public static void main(String[] args) {
		PersistenciaArquivo p = new PersistenciaArquivo();
		Scanner entrada = new Scanner(System.in);
		boolean condicao = true;

		System.out.println("-------------------\n BANCO SYSTEM - BS \n-------------------");
		System.out.println("Bem-vindo(a) ao Sistema do Banco System");
		while (condicao == true){
			menu();
			int opcao = entrada.nextInt();
			
			switch (opcao) {
				//1 - Cadastrar Cliente. - Ok
				case 1:
					Cliente cl1 = new Cliente();
					Conta c1 = new Conta();
					System.out.println("Informe o nome do cliente a ser cadastrado:");
					cl1.setNome(entrada.next());
					System.out.println("Informe o cpf:");
					cl1.setCpf(entrada.next());
					cl1.cadastrarConta(c1);
					p.adicionarCliente(cl1);
					break;
				//2- Adicionar conta e relacionar a cliente. - OK +-
				case 2:
					System.out.println("Informe o cpf do cliente:");
					String cpf2 = entrada.next();
					Cliente cl2 = p.localizarClienteCPF(cpf2);
					Conta c2 = new Conta();
					cl2.cadastrarConta(c2);
					System.out.println(cl2);
					break;
				//3 -Consultar cliente por cpf. - OK +-
				case 3:
					System.out.println("Informe o cpf do cliente:");
					String cpf3 = entrada.next();
					Cliente cl3 = p.localizarClienteCPF(cpf3);
					System.out.println(cl3);
					break;
				//4 - Remover cliente. - OK
				case 4:
					try {
						System.out.println("Informe o cpf do cliente a ser removido:");
						String cpf4 = entrada.next();
						p.removerCliente(cpf4);
					} catch(Exception ex) {
						System.out.printf("Erro! Não foi possivel remover o cliente." + ex.getMessage());
					}
					break;
				//Listar clientes cadastrados. - OK
				case 5:
					System.out.println("\nClientes cadastrados no Banco:");
					p.listarClientes();
					break;
				//Remover conta de um cliente.
				case 6:
					System.out.println("Informe o cpf do cliente:");
					String cpf7 = entrada.next();
					Cliente cl7 = p.localizarClienteCPF(cpf7);
					System.out.println(cl7);
					System.out.println("Informe o número da conta a ser removida:");
					int numeroConta7 = entrada.nextInt();
					cl7.localizarConta(numeroConta7);
					Conta rem7 = new Conta(numeroConta7);
					cl7.removerConta(rem7);
					break;
				//Consultar Saldo.
				case 7:
					try {
						System.out.println("Informe o número da Conta:");
						int numeroConta8 = entrada.nextInt();
						Conta c8 = new Conta(numeroConta8);
						System.out.println("Saldo da conta é:");
						c8.consultarSaldo();
					} catch (Exception ex) {
						System.out.println("Número da conta não corresponde a nenhuma existente!");
					}
					break;
				//Realizar depósito. 
				case 8:
					System.out.println("\nInforme o número da Conta a ser depositada:");
					int numeroConta9 = entrada.nextInt();
					Conta tempC9 = new Conta(numeroConta9);
					System.out.println("\nInforme a quantia a ser depositada:");
					float valordeposito = entrada.nextFloat();
					tempC9.efetuarDeposito(numeroConta9, valordeposito);
					System.out.println(tempC9);
					break;
				//Realizar saque.
				case 9:
					System.out.println("\nInforme o número da Conta a ser sacada:");
					int numeroConta10 = entrada.nextInt();
					Conta tempC10 = new Conta(numeroConta10);
					System.out.println("\nInforme a quantia a ser sacada:");
					float valorsaque = entrada.nextFloat();
					tempC10.efetuarDeposito(numeroConta10, valorsaque);
					System.out.println(tempC10);
					break;
				//Transferir quantia.
				case 10:
					System.out.println("\nInforme o seu número da Conta:");
					int numeroConta11 = entrada.nextInt();
					System.out.println("\nInforme o número da Conta a receber a transferência:");
					int numerocontaDestino11 = entrada.nextInt();
					Conta contaDestino11 = new Conta(numerocontaDestino11);
					System.out.println("\nInforme a quantia a ser transferida:");
					float quantia11 = entrada.nextFloat();
					Conta c11 = new Conta(numeroConta11);
					c11.realizarTransferencia(numeroConta11, quantia11, contaDestino11);
					break;
				//Sair do sistema.
				case 11:
					System.out.println("Obrigado por usar o sistema do Banco System! Volte sempre!");
					condicao = false;
					break;
				//Opção digitada incorretamente.
				default:
					System.err.println("\nErro! O número informado não correspondente as opções.");
					
					break;
				
			}
		}
	}
	
	public static void menu() {
		System.out.println("------------------------------------------------");
		System.out.println("De acordo com as opções, escolha a operação: \n");
	    System.out.println("1 - Cadastrar Cliente");
	    System.out.println("2 - Criar uma nova Conta");
	    System.out.println("3 - Consultar Cliente por CPF");
	    System.out.println("4 - Remover Cliente");
	    System.out.println("5 - Listar Clientes Cadastrados");
	    System.out.println("6 - Remover uma Conta do Cliente");
	    System.out.println("7 - Consultar Saldo");
	    System.out.println("8 - Realizar Depósito");
	    System.out.println("9 - Realizar Saque");
	    System.out.println("10 - Realizar Transferência");
	    System.out.println("11 - Sair\n");
	    System.out.print("Informe sua opção: ");
	}
}
