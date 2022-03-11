package com.epf.rentmanager.model;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class Client {
	private int id;
	private String lastName;
	public Client(int id, String lastName, String firstName, String email, LocalDate birthDate) {
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.birthDate = birthDate;
	}

	public Client() {
	}

	private String firstName;
	private String email;
	private LocalDate birthDate;
	
	@Override
	public String toString() {
		return "Id : " + id + " lastName : " + lastName + " firstName : " + firstName + " Mail : " + email + " birthDate : " + birthDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	public boolean isAdult() {
		boolean isAdult = false;
	    Calendar curr = Calendar.getInstance();
	    Calendar birth = Calendar.getInstance();
	    birth.setTime(Date.from(getBirthDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
	    int age = curr.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
	    curr.add(Calendar.YEAR,-age);
	    if(birth.after(curr))
	    {
	      age = age - 1;
	    }
	    
	    if(age>17) {
	    	isAdult = true;
	    }
	    return isAdult;
	}
	
	public boolean lengthIsMoreThan3(String stringToTest) {
		if(stringToTest.replace(" ", "").length()>2) {
			return true;
		}else {
			return false;
		}
	}
}
