package es.adama.spring.servicios;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CuentaDesconocidaException extends RuntimeException {

	public CuentaDesconocidaException(Long numeroCuenta) {
		super("número de cuenta desconocida " + numeroCuenta);

	}

	public CuentaDesconocidaException(Long numeroCuenta, Exception causa) {

		super("número de cuenta desconocida " + numeroCuenta, causa);
	}
}
