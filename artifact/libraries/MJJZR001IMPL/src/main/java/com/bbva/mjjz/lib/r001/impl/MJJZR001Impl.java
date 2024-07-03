package com.bbva.mjjz.lib.r001.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;

import com.bbva.apx.exception.db.NoResultException;
import com.bbva.mjjz.dto.customer.Customer;

/**
 * The MJJZR001Impl class...
 */
public class MJJZR001Impl extends MJJZR001Abstract {

	private static final Logger LOGGER = LoggerFactory.getLogger(MJJZR001Impl.class);

	@Override
	public Customer executeGetCustomer(long idCustomer) {

		LOGGER.info("Ejecutando el metodo executeGetCustomer");

		// Variables de apoyo
		Map<String, Object> mapCustomer = null;
		Customer cliente = new Customer();

		try {
			// Ejecución de la query
			mapCustomer = jdbcUtils.queryForMap("getCustomer", idCustomer);
		} catch (NoResultException e) {
			LOGGER.info("No se obtuvieron resultados de clientes con ese ID");
			addAdvice("MJJZ43215679");

		}

		// Mapeo de la respuesta
		if (mapCustomer != null && !mapCustomer.isEmpty()) {
			LOGGER.debug("Se obtuvieron resultados de la consulta");
			cliente.setIdCustomer(Long.valueOf(mapCustomer.get("ID_CUSTOMER").toString()));
			cliente.setName(mapCustomer.get("NAME").toString());
			cliente.setSurname(mapCustomer.get("SURNAME").toString());
			cliente.setContractNumber(Long.valueOf(mapCustomer.get("CONTRACTNUMBER").toString()));
			cliente.setRfc(mapCustomer.get("RFC").toString());
			cliente.setEmail(mapCustomer.get("EMAIL").toString());

		}

		LOGGER.debug("El objeto Customer es: {}", cliente);

		return cliente;
	}

	@Override
	public long executeCreateCustomer(Customer customer) {
		LOGGER.info("Ejecutando el metodo executeCreateCustomer");
		LOGGER.debug("");

		long result = 0;

		try {

			result = jdbcUtils.update("insertCustomer", customer.getIdCustomer(), customer.getName(),
					customer.getSurname(), customer.getRfc(),

					customer.getContractNumber(), customer.getEmail());
		} catch (DuplicateKeyException e) {
			LOGGER.info("El id se encuentra duplicado");
			addAdvice("MJJZ43215681");
		}

		LOGGER.debug("El resultado de la consulta me da: " + result);
		return result;
	}

	@Override
	public long executeUpdateCustomer(Customer customer) {
		LOGGER.info("Ejecutando el metodo executeUpdateCustomer");

		long result = 0;

		Customer cliente = executeGetCustomer(customer.getIdCustomer());

		try {

			if (cliente != null) {
				result = jdbcUtils.update("updateCustomer", customer.getName(), customer.getSurname(),
						customer.getRfc(), customer.getContractNumber(), customer.getEmail(), customer.getIdCustomer());
			} else {
				// TODO: SE maneja el mismo codigo?
				LOGGER.info("No se obtuvieron resultados de clientes con ese ID");
				addAdvice("MJJZ43215679");
			}

			// TODO: Revisar que excepcion manejar
		} catch (DuplicateKeyException e) {
			LOGGER.info("No se realizo la actualizacion del cliente con id {}", customer.getIdCustomer());
			addAdvice("MJJZ43215683");
		}

		LOGGER.debug("El resultado de la actualización me da: " + result);
		return result;
	}

	@Override
	public long executeDeleteCustomer(long idCustomer) {
		LOGGER.info("Ejecutando el metodo executeDeleteCustomer");

		long result = 0;

		Customer cliente = executeGetCustomer(idCustomer);

		try {

			if (cliente != null) {
				result = jdbcUtils.update("deleteCustomer", cliente.getIdCustomer());
			} else {
				// TODO: SE maneja el mismo codigo?
				LOGGER.info("No se obtuvieron resultados de clientes con ese ID");
				addAdvice("MJJZ43215679");
			}

			// TODO: Revisar que excepcion manejar
		} catch (DuplicateKeyException e) {
			LOGGER.info("No se realizo el borrado del cliente con id {}", cliente.getIdCustomer());
			addAdvice("MJJZ43215685");
		}

		LOGGER.debug("El resultado de la actualización me da: " + result);
		return result;
	}

	@Override
	public List<Customer> executeGetListCustomer(int pageSize, int pageIndex) {
		List<Customer> listCustomers = new ArrayList<Customer>();
		Customer customer;
		
		List<Map<String, Object>> listMap = jdbcUtils.pagingQueryForList("getListCustomer", pageSize, pageIndex);
		
		if(listMap != null && !listMap.isEmpty()) {
			for(Map<String, Object> mapCustomer : listMap) {
				customer = new Customer();
				customer.setIdCustomer((long)mapCustomer.get("idCustomer"));
				customer.setName(mapCustomer.get("name").toString());
				customer.setSurname(mapCustomer.get("surname").toString());
				
				listCustomers.add(customer);
			}
		}
		return listCustomers;
	}

}
