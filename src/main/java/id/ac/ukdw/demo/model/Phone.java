package id.ac.ukdw.demo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.json.JSONException;
import org.json.JSONObject;

@Entity
@Table(name = "phones")
public class Phone implements Serializable {
	private static final long serialVersionUID = -4818982760562286445L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", insertable = false, updatable = false)
	private Long id;
	
	@Column(name = "phonenumber")
	private String phonenumber;
	
	@ManyToOne(optional = false, targetEntity=Person.class)
	@JoinColumn(name="person_id", referencedColumnName="id")
	private Person person;

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Phone() {}

	public Phone(String phonenumber, Person person) {
		setPhonenumber(phonenumber);
		setPerson(person);
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
	
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 31);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Phone)) {
            return false;
        }
        Phone other = (Phone) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

	public String toString() {
		String info = "";

		JSONObject jsonInfo = new JSONObject();
		try {
			jsonInfo.put("id", getId());
			jsonInfo.put("person_id", this.getPerson().getId());
			jsonInfo.put("phonenumber", getPhonenumber());
		} catch (JSONException e) {
		}

		info = jsonInfo.toString();
		return info;
	}

}
