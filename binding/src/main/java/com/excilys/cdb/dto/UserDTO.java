package com.excilys.cdb.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.excilys.cdb.annotation.PasswordMatches;

@PasswordMatches
public class UserDTO {
    @NotNull
    @NotEmpty
    private String firstName = "";
    
    @NotNull
    @NotEmpty
    private String lastName = "";
    
    @NotNull
    @NotEmpty
    private String password = "";
    
    private String matchingPassword = "";
    
    @NotNull
    @NotEmpty
    private String role = "";
    
	public UserDTO() {
	}
	
	public UserDTO(String firstName, String lastName, String password, String role) {
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
	public String getMatchingPassword() {
		return matchingPassword;
	}
	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}
	public String getRoles() {
		return role;
	}
	public void setRoles(String role) {
		this.role = role;
	}    
}