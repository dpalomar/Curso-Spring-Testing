package es.adama.spring.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.adama.spring.dominio.Cuenta;
@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

}
