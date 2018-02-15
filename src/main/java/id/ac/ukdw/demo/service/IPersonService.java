package id.ac.ukdw.demo.service;

import java.util.List;

import id.ac.ukdw.demo.model.Person;
import id.ac.ukdw.demo.model.Phone;

public interface IPersonService {
	List<Person> getAllPerson();
    Person getPersonById(Long personId);
    void addPerson(Person person);
    void addPhone(Phone phone);
    void updatePhone(Phone phone);
    void deletePhone(Long phoneId);
    void updatePerson(Person person);
    void deletePerson(Long personId);
}
