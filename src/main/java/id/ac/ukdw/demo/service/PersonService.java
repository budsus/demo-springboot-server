package id.ac.ukdw.demo.service;

import java.util.HashSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.ac.ukdw.demo.model.Person;
import id.ac.ukdw.demo.model.Phone;
//import id.or.pantirapih.demo.dao.IPersonDAO;
import id.ac.ukdw.demo.dao.PersonRepository;
import id.ac.ukdw.demo.dao.PhoneRepository;
import id.ac.ukdw.demo.dao.RegistrasiRepository;

@Service
public class PersonService implements IPersonService {
	@SuppressWarnings("unused")
	private static final Logger LOGGER =
		LoggerFactory.getLogger(PersonService.class);

	//@Autowired
	//private IPersonDAO personDAO;

	@Autowired
	PersonRepository personRepository;
	@Autowired
	PhoneRepository phoneRepository;
	@Autowired
	RegistrasiRepository registrasiRepository;

	@Override
	public List<Person> getAllPerson() {
		return personRepository.findAll();
		// return personDAO.getAllPerson();
	}

	@Override
	public Person getPersonById(Long personId) {
		return personRepository.findById(personId);
		// Person person = personDAO.getPersonById(personId);
		// return person;
	}

	@Override
	public synchronized void addPerson(Person person) {
		// personDAO.addPerson(person);
		//LOGGER.info("Person: " + person);
		Person p = new Person();
		p.setFirstName(person.getFirstName());
		p.setLastName(person.getLastName());
		p.setAge(person.getAge());
		if (person.getPhones() != null) {
			HashSet<Phone> phn = new HashSet<Phone>();
			for (Phone ph : person.getPhones()) {
				Phone newph = new Phone(ph.getPhonenumber(), p);
				phn.add(newph);
			}
			if (phn.size() > 0)
				p.setPhones(phn);
		}
		p.setRegistrasi(person.getRegistrasi());
		
		personRepository.save(p);
	}

	@Override
	public void updatePerson(Person person) {
		//return personDAO.updatePerson(person);
		personRepository.save(person);
	}

	@Override
	public void deletePerson(Long personId) {
		//return personDAO.deletePerson(personId);
		personRepository.delete(personId);
	}

	@Override
	public void addPhone(Phone phone) {
		phoneRepository.save(phone);
	}

	@Override
	public void updatePhone(Phone phone) {
		phoneRepository.updateById(phone.getPhonenumber(), phone.getId());
	}

	@Override
	public void deletePhone(Long phoneId) {
		phoneRepository.delete(phoneId);
	}
}
