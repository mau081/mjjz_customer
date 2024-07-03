package com.bbva.mjjz;

import com.bbva.elara.transaction.AbstractTransaction;
import com.bbva.mjjz.dto.customer.Customer;

/**
 * In this class, the input and output data is defined automatically through the setters and getters.
 */
public abstract class AbstractMJJZT00101MXTransaction extends AbstractTransaction {

	public AbstractMJJZT00101MXTransaction(){
	}


	/**
	 * Return value for input parameter Customer
	 */
	protected Customer getCustomer(){
		return (Customer)this.getParameter("Customer");
	}

	/**
	 * Set value for Customer output parameter Customer
	 */
	protected void setCustomer(final Customer field){
		this.addParameter("Customer", field);
	}
}
