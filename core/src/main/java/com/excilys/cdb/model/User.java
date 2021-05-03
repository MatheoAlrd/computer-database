package com.excilys.cdb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "user")
@Table(name = "users")
public class User {

	// ID of the User
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	// First name of the User
	@Column(name = "first_name")
	private String firstName = "";
	// Last name of the User
	@Column(name="last_name")
	private String lastName = "";
	// Password of the User
	@Column(name="pwd")
	private String password = "";
	// Roles of the User
	@Column(name="roles")
	private Role role;
	
	public User() {
	}
	
	public User(String firstName, String lastName, String password, Role role) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.role = role;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Role getRoles() {
		return role;
	}
	public void setRoles(Role role) {
		this.role = role;
	}
	
	

}
