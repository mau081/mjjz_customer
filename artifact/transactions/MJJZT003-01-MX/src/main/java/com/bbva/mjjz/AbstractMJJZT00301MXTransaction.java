package com.bbva.mjjz;

import com.bbva.elara.transaction.AbstractTransaction;
import com.bbva.mjjz.dto.customer.Customer;

/**
 * In this class, the input and output data is defined automatically through the setters and getters.
 */
public abstract class AbstractMJJZT00301MXTransaction extends AbstractTransaction {

	public AbstractMJJZT00301MXTransaction(){
	}


	/**
	 * Return value for input parameter Customer
	 */
	protected Customer getCustomer(){
		return (Customer)this.getParameter("Customer");
	}

	/**
	 * Set value for Long output parameter resultUpdates
	 */
	protected void setResultupdates(final Long field){
		this.addParameter("resultUpdates", field);
	}
}
