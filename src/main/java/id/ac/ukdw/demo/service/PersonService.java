package id.ac.ukdw.demo.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.ac.ukdw.demo.model.Person;
import id.ac.ukdw.demo.model.PersonHistory;
import id.ac.ukdw.demo.model.Phone;
import id.ac.ukdw.aop.Timed;
import id.ac.ukdw.demo.dao.PersonHistoryRepository;
import id.ac.ukdw.demo.dao.PersonRepository;
import id.ac.ukdw.demo.dao.PhoneRepository;
import id.ac.ukdw.demo.dao.RegistrasiRepository;

@Service
public class PersonService implements IPersonService {
	private static final Logger LOGGER =
		LoggerFactory.getLogger(PersonService.class);

	@Autowired
	PersonRepository personRepository;
	@Autowired
	PhoneRepository phoneRepository;
	@Autowired
	RegistrasiRepository registrasiRepository;
	@Autowired
	PersonHistoryRepository personHistoryRepository;

	@Timed
	@Override
	public List<Person> getAllPerson() {
		return personRepository.findAll();
		// return personDAO.getAllPerson();
	}

	@Timed
	@Override
	public Person getPersonById(Long personId) {
		return personRepository.findById(personId);
		// Person person = personDAO.getPersonById(personId);
		// return person;
	}

	@Timed
	@Override
	public synchronized void addPerson(Person person) {
		LOGGER.info("addPerson: " + person);
		Person p = new Person();
		p.setFirstName(person.getFirstName());
		p.setLastName(person.getLastName());
		p.setAge(person.getAge());
		if (person.getPhones() != null) {
			Collection<Phone> phn = new ArrayList<Phone>();
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

	@Timed
	@Override
	public void updatePerson(Person person) {
		Person p = personRepository.findOne(person.getId());
		p.setFirstName(person.getFirstName());
		p.setLastName(person.getLastName());
		p.setAge(person.getAge());
		personRepository.save(p);
	}
	
	@Timed
	@Override
	public void deletePerson(Long personId) {
		Person p = personRepository.findOne(personId);
		for(Phone phone:p.getPhones()){
			phoneRepository.delete(phone);
			phoneRepository.flush();
		}
		List<PersonHistory> phList = personHistoryRepository.findByPersonId(personId);
		for(PersonHistory ph:phList) {
			ph.setPerson(null);
			personHistoryRepository.save(ph);
			personHistoryRepository.flush();
		}
		
		personRepository.delete(personId);
	}

	@Timed
	@Override
	public void addPhone(Phone phone) {
		phoneRepository.save(phone);
	}

	@Timed
	@Override
	public void updatePhone(Phone phone) {
		phoneRepository.updateById(phone.getPhonenumber(), phone.getId());
	}

	@Timed
	@Override
	public void deletePhone(Long phoneId) {
		phoneRepository.delete(phoneId);
	}
}
