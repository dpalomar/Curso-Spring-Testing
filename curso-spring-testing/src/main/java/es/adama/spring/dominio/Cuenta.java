package es.adama.spring.dominio;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.AUTO;

import javax.persistence.Column;
import javax.validation.constraints.Min;

@Entity
@Table(name = "cuenta_t")
public class Cuenta {

	@Id
	@GeneratedValue(strategy = AUTO)
	@Column(name = "numero_cuenta")
	private Long numeroCuenta;
	
	@Column(name = "balance")
	@Min(message="el saldo debe ser >=0",value=0)
	private long balance;

	
	
	
	
	public Cuenta(Long numeroCuenta, long balance) {
		super();
		this.numeroCuenta = numeroCuenta;
		this.balance = balance;
	}

	public Cuenta() {
		// TODO Auto-generated constructor stub
	}

	public Long getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(Long numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}
	
    public void depositar(long cantidad) {
        balance += cantidad;
    }


    public void retirar(long cantidad) {
        balance -= cantidad;
    }
}
