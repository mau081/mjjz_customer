package com.bbva.mjjz.dto.customer;

import java.io.Serializable;
import java.util.Objects;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The Customer class...
 */
public class Customer implements Serializable  {
	private static final long serialVersionUID = 2931699728946643245L;

	/* Attributes section for the DTO */
	
	private long idCustomer;
	private String name;
	private String surname;
	private String rfc;
	private long contractNumber;
	private String email;
	
	public long getIdCustomer() {
		return idCustomer;
	}
	public void setIdCustomer(long idCustomer) {
		this.idCustomer = idCustomer;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	public long getContractNumber() {
		return contractNumber;
	}
	public void setContractNumber(long contractNumber) {
		this.contractNumber = contractNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public int hashCode() {
		return Objects.hash(contractNumber, email, idCustomer, name, rfc, surname);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return contractNumber == other.contractNumber && Objects.equals(email, other.email)
				&& idCustomer == other.idCustomer && Objects.equals(name, other.name) && Objects.equals(rfc, other.rfc)
				&& Objects.equals(surname, other.surname);
	}
	@Override
	public String toString() {
		return "Customer [idCustomer=" + idCustomer + ", name=" + name + ", surname=" + surname + ", rfc=" + rfc
				+ ", contractNumber=" + contractNumber + ", email=" + email + "]";
	}
	
	
	
}
