package id.ac.ukdw.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.ac.ukdw.demo.model.Registrasi;

@Repository
public interface RegistrasiRepository extends JpaRepository<Registrasi, Long> {

}
