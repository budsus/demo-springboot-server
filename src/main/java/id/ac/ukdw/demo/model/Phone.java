package id.ac.ukdw.demo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.FetchType;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.json.JSONException;
import org.json.JSONObject;

@Entity
@Table(name = "phones")
public class Phone implements Serializable {
	private static final long serialVersionUID = -4818982760562286445L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	@Column(name = "phonenumber", nullable = true)
	private String phonenumber;
	@Column(name = "person_id", nullable = true)
	private Long person_id;
	
	/*
	 * Jika ingin dibuat sebagai asosiasi bidirectional, 
	 * maka perlu dituliskan directive di bawah ini
	 * sekaligus menyediakan atribut class parent untuk relasi dari Phone -> Person
	 */
	//@ManyToOne(fetch = FetchType.LAZY)
	//@JoinColumn (name="person_id",referencedColumnName="id",nullable=true)
	//private Person person;

	public Phone() {}

	public Phone(String phonenumber, Person person) {
		setPhonenumber(phonenumber);
		//setPerson(person);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	
	@SuppressWarnings("unused")
	private Long getPerson_id() {
		return person_id;
	}

	@SuppressWarnings("unused")
	private void setPerson_id(Long person_id) {
		this.person_id = person_id;
	}
	

//	private Person getPerson() {
//		return person;
//	}
//
//	private void setPerson(Person person) {
//		this.person = person;
//	}

	public String toString() {
		String info = "";

		JSONObject jsonInfo = new JSONObject();
		try {
			jsonInfo.put("phonenumber", getPhonenumber());
//			JSONObject personObj = new JSONObject();
//			personObj.put("first_name", getPerson().getFirstName());
//			personObj.put("last_name", getPerson().getLastName());
//			personObj.put("age", getPerson().getAge());
//			jsonInfo.put("person", personObj);
		} catch (JSONException e) {
		}

		info = jsonInfo.toString();
		return info;
	}

}
