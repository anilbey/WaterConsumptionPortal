package it.polimi.awt.springmvc.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


/**
 * The persistent class for the user database table.
 * 
 */
/**
 * @author anil
 *
 */
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer oid;

	//bi-directional one-to-one association to NeutralUser
	@OneToOne
	@JoinColumn(name="oid", insertable = false, updatable = false)
	private NeutralUser neutralUser;

	
	@Column(name="birth_date")
	private String birthDate;

	private String email;

	@Column(name="first_name")
	private String firstName;

	private Boolean internal;

	@Column(name="last_name")
	private String lastName;

	private String password;

	private String username;

	/**
	 * 
	 */
	public User() {
	}

	/**
	 * @return
	 */
	public Integer getOid() {
		return oid;
	}

	/**
	 * @param oid
	 */
	public void setOid(Integer oid) {
		this.oid = oid;
	}

	/**
	 * @return
	 */
	public String getBirthDate() {
		return birthDate;
	}

	/**
	 * @param birthDate
	 */
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	/**
	 * @return
	 */
	public Boolean getInternal() {
		return internal;
	}

	/**
	 * @param internal
	 */
	public void setInternal(Boolean internal) {
		this.internal = internal;
	}

	/**
	 * @return
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return
	 */
	public NeutralUser getNeutralUser() {
		return neutralUser;
	}

	/**
	 * @param neutralUser
	 */
	public void setNeutralUser(NeutralUser neutralUser) {
		this.neutralUser = neutralUser;
	}



}