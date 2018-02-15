package id.ac.ukdw.demo.dao;

import java.util.List;

import id.ac.ukdw.demo.model.Person;;

public interface IPersonDAO {
	List<Person> getAllPerson();
    Person getPersonById(Long personId);
    void addPerson(Person person);
    boolean updatePerson(Person person);
    boolean deletePerson(Long personId);
    boolean personExists(String firstName, String lastName);
}
