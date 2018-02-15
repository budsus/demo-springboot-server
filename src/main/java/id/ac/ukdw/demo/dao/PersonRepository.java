package id.ac.ukdw.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import id.ac.ukdw.demo.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
	@Query("SELECT p FROM Person p INNER JOIN p.regis r WHERE p.id = :id")
    Person findById(@Param("id") Long id);
}
