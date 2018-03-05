package id.ac.ukdw.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import id.ac.ukdw.demo.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
	@Query("SELECT p FROM Person p INNER JOIN p.regis r WHERE p.id = :id")
    Person findById(@Param("id") Long id);
	
	@Modifying
	@Transactional
	@Query("update Person p set p.firstName = ?1, p.lastName = ?2, p.age = ?3 where p.id = ?4")
	int updatePerson(String fName, String lName, Integer age, Long id);
}
