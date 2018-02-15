package id.ac.ukdw.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import id.ac.ukdw.demo.model.Person;

@Transactional
@Repository
public class PersonDAO implements IPersonDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Person> getAllPerson() {
		String hql = "FROM Person as person ORDER BY person.id";
		return (List<Person>)entityManager.createQuery(hql).getResultList();
	}

	@Override
	public Person getPersonById(Long id) {
		return entityManager.find(Person.class, id);
	}

	@Override
	public void addPerson(Person person) {
		entityManager.persist(person);
	}

	@Override
	public boolean updatePerson(Person person) {
		Person prsn = getPersonById(person.getId());
		if (prsn != null) {
			prsn.setFirstName(person.getFirstName());
			prsn.setLastName(person.getLastName());
			prsn.setAge(person.getAge());
			entityManager.flush();
			return true;
		}
		return false;
	}

	@Override
	public boolean deletePerson(Long personId) {
		Person prsn = getPersonById(personId);
		if (prsn != null) {
			entityManager.remove(getPersonById(personId));
			return true;
		}
		return false;
	}

	@Override
	public boolean personExists(String firstName, String lastName) {
		String hql = "FROM Person as person WHERE person.firstName = ? and person.lastName = ?";
		int count = entityManager.createQuery(hql)
				.setParameter(1,  firstName)
				.setParameter(2,  lastName)
				.getResultList().size();
		return count > 0 ? true : false;
	}
}
