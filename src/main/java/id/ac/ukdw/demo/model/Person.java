package id.ac.ukdw.demo.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "person")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class Person implements Serializable {
	private static final long serialVersionUID = -2582690158977818750L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "age")
	private int age;
	// @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch =
	// FetchType.EAGER, orphanRemoval = true)
	// @ElementCollection(targetClass=Phone.class)
	// @Column(name = "person_id")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "person_id")
	private Set<Phone> phones;
	
	@OneToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "registrasi_id")
	@JsonBackReference
	@JsonIgnoreProperties("biodata")
	private Registrasi regis;

	public Person() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setPhones(Set<Phone> phones) {
		this.phones = phones;
	}

	public Set<Phone> getPhones() {
		return this.phones;
	}

	@Override
	public String toString() {
		String info = "";
		JSONObject jsonInfo = new JSONObject();
		try {
			jsonInfo.put("first_name", getFirstName());
			jsonInfo.put("last_name", getLastName());
			jsonInfo.put("age", getAge());

			JSONArray phonesArray = new JSONArray();
			if (getPhones() != null) {
				getPhones().forEach(phone -> {
					JSONObject subJson = new JSONObject();
					try {
						subJson.put("phonenumber", phone.getPhonenumber());
					} catch (JSONException e) {
					}
					phonesArray.put(subJson);
				});
			}
			jsonInfo.put("phones", phonesArray);
			jsonInfo.put("registrasi", getRegistrasi());
		} catch (JSONException e) {
		}

		info = jsonInfo.toString();
		return info;
	}

	public Registrasi getRegistrasi() {
		return regis;
	}

	public void setRegistrasi(Registrasi registrasi) {
		this.regis = registrasi;
	}
}
