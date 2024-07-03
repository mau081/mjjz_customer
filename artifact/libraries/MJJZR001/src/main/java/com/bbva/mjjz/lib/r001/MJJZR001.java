package com.bbva.mjjz.lib.r001;

import java.util.List;

import com.bbva.mjjz.dto.customer.Customer;

/**
 * The  interface MJJZR001 class...
 */
public interface MJJZR001 {

	Customer executeGetCustomer(long idCustomer);
	long executeCreateCustomer(Customer customer);
	long executeUpdateCustomer(Customer customer);
	long executeDeleteCustomer(long idCustomer);
	List<Customer> executeGetListCustomer(int pageSize, int pageIndex);

}
