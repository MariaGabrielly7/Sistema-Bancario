package sistema;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Random;
import sistema.Cliente;

public class Conta implements Serializable {

	Random numero = new Random();
	private int numeroConta;
	private float saldo;
	private boolean status = true;
	private Date datadeAbertura;    
	
	public Conta() {
		this.numeroConta = 1 + numero.nextInt(9999);
		this.saldo = 0.0f;
		this.status = true;
		this.datadeAbertura = new Date();
	}
	
	public Conta(int numeroConta, float saldo) {
		this.numeroConta = numeroConta;
		this.saldo = saldo;
		this.status = true;
		this.datadeAbertura = new Date();
	}
	
	public Conta(int numeroConta) {
		this.numeroConta = numeroConta;
	}
	
	public int getNumeroConta() {
		return numeroConta;
	}
	public void setNumeroConta(int numeroConta) {
		this.numeroConta = numeroConta;
	}
	public float getSaldo() {
		return saldo;
	}
	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Date getDatadeAbertura() {
		return datadeAbertura;
	}
	public void setDatadeAbertura(Date datadeAbertura) {
		this.datadeAbertura = datadeAbertura;
	}
	
	public String toString() {
		return "Conta [numeroConta=" + getNumeroConta() + ", saldo=" + getSaldo() + ", status=" + getStatus() + ", datadeAbertura="
				+ getDatadeAbertura() + "]";
	}

	public int hashCode() {
		return Objects.hash(numeroConta);
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conta other = (Conta) obj;
		return numeroConta == other.numeroConta;
	}
	
	
	//Verificar esse metódo.
	public void consultarSaldo() {
		try {
			System.out.println(getSaldo());
		} catch(Exception ex){
			System.out.println("Número da conta informado não existente!");
		}
	}
	
	public void efetuarSaque(int numeroConta, float quantia) {
		Cliente clientetemp = null;
		Conta contatemp = clientetemp.localizarConta(numeroConta);
		try {
			if(quantia <= getSaldo() && getStatus() == true) {
				setSaldo(getSaldo() - quantia);
				System.out.println("Saque realizado com sucesso!");
			}else if(quantia > getSaldo() || quantia < 0) {
				System.err.println("Impossível sacar valor informado!");
			}else
				System.err.println("A Conta está desativada!");
		} catch(Exception ex){
			System.err.println("Operação não pode ser realizada!" + ex.getMessage());
		}
	}
	
	public Conta efetuarDeposito(int numeroConta, float quantia) {
		Cliente clientetemp = new Cliente();
		Conta contatemp = clientetemp.localizarConta(numeroConta);
			if(getStatus() == true && quantia > 0) {
				setSaldo(getSaldo() + quantia);
				System.out.println("Depósito realizado com sucesso!");
				
			}else{
				System.err.println("A Conta está desativada!");
			}
		return contatemp;
	}
	
	public void realizarTransferencia(int numeroConta, float quantia, Conta contadestino) {
		Cliente clientetemp = new Cliente();
		Conta contatemp = clientetemp.localizarConta(numeroConta);
		try {
			if(quantia <= getSaldo() && quantia > 0 && getStatus() == true && contadestino.status == true) {
				setSaldo(getSaldo() - quantia);
				contadestino.saldo += quantia;
				System.out.println("Transferência realizada com sucesso!");
			}
			else if(getStatus() == false) {
				System.err.println("A Conta de origem da transferência está desativada!");
			}else if(quantia > getSaldo() || quantia < 0) {
				System.err.println("Impossível transferir valor informado!");
			}else
				System.err.println("A conta destino para transferência está desativada!");
		} catch(Exception ex){
			System.err.println("Operação não pode ser realizada!"+ex.getMessage());
		}
	}
	
	
	
}
