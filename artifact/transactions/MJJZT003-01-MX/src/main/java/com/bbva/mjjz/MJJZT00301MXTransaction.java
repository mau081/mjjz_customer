package com.bbva.mjjz;

import com.bbva.elara.domain.transaction.Advice;
import com.bbva.elara.domain.transaction.Severity;
import com.bbva.mjjz.dto.customer.Customer;
import com.bbva.mjjz.lib.r001.MJJZR001;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Transaccion para la actualizacion de clientes
 *
 */
public class MJJZT00301MXTransaction extends AbstractMJJZT00301MXTransaction {

	private static final Logger LOGGER = LoggerFactory.getLogger(MJJZT00301MXTransaction.class);

	/**
	 * The execute method...
	 */
	@Override
	public void execute() {
		MJJZR001 mjjzR001 = this.getServiceLibrary(MJJZR001.class);
		
		LOGGER.info("Ejecutando metodo execute de la transaccion MJJZT00301MXTransaction");
		//Obtener campos de entrada
		Customer customer = getCustomer();
		
		//validacion de campos de entrada
		if(customer.getIdCustomer() != 0 && customer.getIdCustomer() >= 10000000 && !customer.getName().isEmpty() && !customer.getSurname().isEmpty()
				&& !customer.getRfc().isEmpty() && customer.getContractNumber() > 0 && !customer.getEmail().isEmpty()) {
			//logica de negocio(librerias)
			long result = 0;
			result = mjjzR001.executeUpdateCustomer(customer);
			
			
			//seteo de salidas
			LOGGER.info("Seteando campos de salida");
			setResultupdates(result);
		}else {
			LOGGER.info("Los campos no cumplen con las reglas de validacion");
			addAdvice("MJJZ43215682");
		}
		
		
		
		//validación de errores
		Advice advice = getAdvice();
		
		if(advice != null) {
			if("MJJZ43215679".equals(advice.getCode())) {
				setSeverity(Severity.ENR);
			}else if("MJJZ43215682".equals(advice.getCode())) {
				setSeverity(Severity.ENR);
			}else if("MJJZ43215683".equals(advice.getCode())) {
				setSeverity(Severity.ENR);
			}
		}
		
	}

}
