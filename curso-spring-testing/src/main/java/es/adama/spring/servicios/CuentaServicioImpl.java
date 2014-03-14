package es.adama.spring.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.adama.spring.dominio.Cuenta;
import es.adama.spring.repositorios.CuentaRepository;


@Transactional
@Service
public class CuentaServicioImpl implements CuentaServicio {

    @Autowired
    private final CuentaRepository cuentaRepository;

    

    CuentaServicioImpl(CuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }


    /* (non-Javadoc)
	 * @see es.adama.spring.servicios.CuentaServicio#get(java.lang.Long)
	 */

	@Transactional(readOnly = true)
    @Override
    public Cuenta get(Long numeroCuenta) {
        Cuenta cuenta = getEntidadCuenta(numeroCuenta);
        return new Cuenta(cuenta.getNumeroCuenta(),cuenta.getBalance());
    }


    /* (non-Javadoc)
	 * @see es.adama.spring.servicios.CuentaServicio#depositar(java.lang.Long, long)
	 */

	@Override
    public void depositar(Long numeroCuenta, long cantidad) {
        Cuenta cuenta = getEntidadCuenta(numeroCuenta);
        cuenta.depositar(cantidad);
        cuentaRepository.save(cuenta);
    }


    /* (non-Javadoc)
	 * @see es.adama.spring.servicios.CuentaServicio#retirar(java.lang.Long, long)
	 */

	@Override
    public Cuenta retirar(Long numeroCuenta, long cantidad) {
        Cuenta cuenta = getEntidadCuenta(numeroCuenta);
        cuenta.retirar(cantidad);
        Cuenta cuentaGuardada = cuentaRepository.save(cuenta);
        return new Cuenta(cuentaGuardada.getNumeroCuenta(),cuentaGuardada.getBalance());
    }


    /* (non-Javadoc)
	 * @see es.adama.spring.servicios.CuentaServicio#transferir(java.lang.Long, java.lang.Long, long)
	 */

	@Override
    public void transferir(Long deNumeroDeCuenta, Long aNumeroDeCuenta, long cantidad) {
        Cuenta deCuenta = getEntidadCuenta(deNumeroDeCuenta);
        Cuenta aCuenta = getEntidadCuenta(aNumeroDeCuenta);
        deCuenta.retirar(cantidad);
        aCuenta.depositar(cantidad);
        cuentaRepository.save(deCuenta);
        cuentaRepository.save(aCuenta);
    }


    /* (non-Javadoc)
	 * @see es.adama.spring.servicios.CuentaServicio#crearCuenta()
	 */

	@Override
    public Long crearCuenta() {
        Cuenta cuenta = new Cuenta();
        Cuenta cuentaGuardada = cuentaRepository.save(cuenta);
        return cuentaGuardada.getNumeroCuenta();
    }


    /* (non-Javadoc)
	 * @see es.adama.spring.servicios.CuentaServicio#borrarCuenta(java.lang.Long)
	 */

	@Override
    public void borrarCuenta(Long numeroCuenta) throws CuentaDesconocidaException {
        try {
            cuentaRepository.delete(numeroCuenta);
        } catch (EmptyResultDataAccessException e) {
            throw new CuentaDesconocidaException(numeroCuenta, e);
        }
    }


    /* (non-Javadoc)
	 * @see es.adama.spring.servicios.CuentaServicio#getTodosNumerosCuentas()
	 */

	@Transactional(readOnly = true)
    @Override
    public List<Long> getTodosNumerosCuentas() {
        List<Cuenta> cuentas = cuentaRepository.findAll();
        List<Long> resultado = new ArrayList<>(cuentas.size());
        for (Cuenta cuenta : cuentas) {
            resultado.add(cuenta.getNumeroCuenta());
        }
        return resultado;
    }


    private Cuenta getEntidadCuenta(Long numeroCuenta) throws CuentaDesconocidaException {
        Cuenta cuenta = cuentaRepository.findOne(numeroCuenta);
        if (cuenta == null) {
            throw new CuentaDesconocidaException(numeroCuenta);
        }
        return cuenta;
    }
}
