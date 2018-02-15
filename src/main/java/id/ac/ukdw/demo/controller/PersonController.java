package id.ac.ukdw.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import id.ac.ukdw.demo.model.Person;
import id.ac.ukdw.demo.model.Phone;
import id.ac.ukdw.demo.service.IPersonService;

@RestController
@RequestMapping("/persons")
public class PersonController {
	private static final Logger LOGGER =
			LoggerFactory.getLogger(PersonController.class);
	
	@Autowired
	IPersonService ps;
	
	@RequestMapping(
			value = "/add",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE) 
	@ResponseBody
	public ResponseEntity<Person> createPerson(@RequestBody Person p) {
		LOGGER.info("Person: " + p);
		/*
		 * Contoh JSON Request:
		 * {
		 *   "firstName": "Testing",
		 *   "lastName": "Lagi",
		 *   "age": 2,
		 *   "regis": {
		 *   	"noregis": "123123"
		 *   },
		 *   "phones": [{
		 *      "phonenumber": "084657373123"
		 *   }]
		 * }
		 * 
		 * atau
		 * 
		 * {
		 *   "firstName": "Testing",
		 *   "lastName": "Lagi",
		 *   "age": 2,
		 *   "regis": {
		 *       "noregis": "1231312"
		 *   }
		 * }
		 */
		ps.addPerson(p);

		return new ResponseEntity<Person>(p, HttpStatus.CREATED);
	}

	@RequestMapping(
			value = "/update/{id}",
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE) 
	@ResponseBody
	public ResponseEntity<Person> updatePerson(@PathVariable("id") final Long id, @RequestBody Person p) {
		/*
		 * Contoh JSON Request:
		 * {
		 *   "firstName": "Testing",
		 *   "lastName": "Lagi",
		 *   "age": 2
		 * }
		 */
		if (id != null) {
			p.setId(id);
			ps.updatePerson(p);
		}
		return new ResponseEntity<Person>(p, HttpStatus.OK);
		//return new ResponseEntity<Person>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(
			value = "/delete/{id}",
			method = RequestMethod.DELETE,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE) 
	@ResponseBody
	public ResponseEntity<Person> deletePerson(@PathVariable("id") final Long id, @RequestBody Person p) {
		ps.deletePerson(id);
		/*if (!isDeleted) {
			return new ResponseEntity<Person>(HttpStatus.INTERNAL_SERVER_ERROR);
		}*/
		return new ResponseEntity<Person>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(
			value = "/addphone",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE) 
	@ResponseBody
	public ResponseEntity<Phone> addPhone(@RequestBody Phone phone) {
		/*
		 * Contoh JSON Request: 
		 * {
		 *   "phonenumber": "084657373456",
		 *   "person_id": 3
		 * }
		 */
		ps.addPhone(phone);

		return new ResponseEntity<Phone>(phone, HttpStatus.CREATED);
	}
	
	@RequestMapping(
			value = "/updatephone",
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE) 
	@ResponseBody
	public ResponseEntity<Phone> updatePhone(@RequestBody Phone phone) {
		/*
		 * Contoh JSON Request: 
		 * {
		 *   "id": 4,
		 *   "phonenumber": "084657373456",
		 * }
		 */
		ps.updatePhone(phone);

		return new ResponseEntity<Phone>(phone, HttpStatus.CREATED);
	}
	
	@RequestMapping(
			value = "/delphone/{id}",
			method = RequestMethod.DELETE,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE) 
	@ResponseBody
	public ResponseEntity<Phone> deletePhone(@PathVariable("id") final Long id) {
		ps.deletePhone(id);
		return new ResponseEntity<Phone>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(
			value = "/all",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Person> getAll() {
		List<Person> allPerson = (List<Person>) ps.getAllPerson();
		return allPerson;
	}
	
	@RequestMapping(
			value = "/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Person> getPerson(@PathVariable("id") final Long id){
		Person person = ps.getPersonById(id);
		if (person == null) {
			return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Person>(person, HttpStatus.OK);
	}
}
