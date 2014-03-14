package es.adama.spring.servicios;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import es.adama.spring.dominio.Cuenta;

public interface CuentaServicio {

	public abstract Cuenta get(Long numeroCuenta) throws CuentaDesconocidaException;

	public abstract void depositar(Long numeroCuenta, long cantidad) throws CuentaDesconocidaException;

	public abstract Cuenta retirar(Long numeroCuenta, long cantidad)throws CuentaDesconocidaException;

	public abstract void transferir(Long deNumeroDeCuenta,
			Long aNumeroDeCuenta, long cantidad) throws CuentaDesconocidaException;

	public abstract Long crearCuenta() throws CuentaDesconocidaException;

	public abstract void borrarCuenta(Long numeroCuenta)
			throws CuentaDesconocidaException;

	public abstract List<Long> getTodosNumerosCuentas() throws CuentaDesconocidaException;

}