package id.ac.ukdw.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import id.ac.ukdw.demo.model.PersonHistory;

@Repository
public interface PersonHistoryRepository extends JpaRepository<PersonHistory, Integer> {
	List<PersonHistory> findByPersonId(Long personid);
}
