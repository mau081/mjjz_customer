package com.bbva.mjjz;

import com.bbva.mjjz.lib.r001.MJJZR001;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bbva.elara.domain.transaction.Advice;
import com.bbva.elara.domain.transaction.Severity;
import com.bbva.mjjz.dto.customer.Customer;

/**
 * Transaccion para el guardado de clientes
 *
 */
public class MJJZT00201MXTransaction extends AbstractMJJZT00201MXTransaction {

	private static final Logger LOGGER = LoggerFactory.getLogger(MJJZT00201MXTransaction.class);

	/**
	 * The execute method...
	 */
	@Override
	public void execute() {
		MJJZR001 mjjzR001 = this.getServiceLibrary(MJJZR001.class);
		LOGGER.info("Ejecutando metodo execute de la transaccion MJJZT00201MXTransaction");
		//Obtener campos de entrada
		Customer customer = getCustomer();
		
		//Validacion de campos de entrada
		if(customer.getIdCustomer() != 0 && customer.getIdCustomer() >= 10000000 && !customer.getName().isEmpty()) {
			//logica de negocio(librerias)
			long result = 0;
			result = mjjzR001.executeCreateCustomer(customer);
			
			//seteo de salidas
			LOGGER.info("Seteando campos de salida");
			setResultinserts(result);
		}else {
			LOGGER.info("Los campos no cumplen con las reglas de validacion");
			addAdvice("MJJZ43215680");
		}
		
		
		//validacion de errores
		
		Advice advice = getAdvice();
		
		if(advice != null) {
			if("MJJZ43215680".equals(advice.getCode())) {
				setSeverity(Severity.ENR);
			}else if("MJJZ43215681".equals(advice.getCode())) {
				setSeverity(Severity.ENR);
			}
		}
		
	}

}
