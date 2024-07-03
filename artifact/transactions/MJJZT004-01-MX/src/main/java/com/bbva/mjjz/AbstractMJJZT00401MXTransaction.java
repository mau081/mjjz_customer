package com.bbva.mjjz;

import com.bbva.elara.transaction.AbstractTransaction;

/**
 * In this class, the input and output data is defined automatically through the setters and getters.
 */
public abstract class AbstractMJJZT00401MXTransaction extends AbstractTransaction {

	public AbstractMJJZT00401MXTransaction(){
	}


	/**
	 * Return value for input parameter idCustomer
	 */
	protected Long getIdcustomer(){
		return (Long)this.getParameter("idCustomer");
	}

	/**
	 * Set value for Long output parameter resultDeletes
	 */
	protected void setResultdeletes(final Long field){
		this.addParameter("resultDeletes", field);
	}
}
