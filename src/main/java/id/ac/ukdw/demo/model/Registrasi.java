package id.ac.ukdw.demo.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@SuppressWarnings({ "unused"})
@Entity
@Table(name = "registrasi")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class Registrasi implements Serializable {
	private static final long serialVersionUID = 6914639240407093719L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "noregis")
	private String noregis;

	@JsonIgnore
	@OneToOne(mappedBy="regis", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JsonManagedReference
	private Person biodata;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNoregis() {
		return noregis;
	}

	public void setNoregis(String noregis) {
		this.noregis = noregis;
	}

	public Person getPerson() {
		return biodata;
	}

	public void setPerson(Person person) {
		this.biodata = person;
		getPerson().setRegistrasi(this);
	}
	
	@Override
	public String toString() {
		String info = "";

		JSONObject jsonInfo = new JSONObject();
		try {
			jsonInfo.put("noregis", getNoregis());
		} catch (JSONException e) {
		}

		info = jsonInfo.toString();
		return info;
	}
}
