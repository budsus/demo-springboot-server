package id.ac.ukdw.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import id.ac.ukdw.demo.model.Phone;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {
	@Modifying
	@Transactional
	@Query("update Phone p set p.phonenumber = :numbers where p.id = :pid")
	void updateById(@Param("numbers") String phonenumber, @Param("pid")Long phoneId);
}
