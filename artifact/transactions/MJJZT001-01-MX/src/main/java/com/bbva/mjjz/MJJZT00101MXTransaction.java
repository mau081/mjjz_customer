package com.bbva.mjjz;

import com.bbva.mjjz.lib.r001.MJJZR001;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bbva.elara.domain.transaction.Advice;
import com.bbva.elara.domain.transaction.Severity;
import com.bbva.mjjz.dto.customer.Customer;

/**
 * Transaccion de consulta de un cliente
 *
 */
public class MJJZT00101MXTransaction extends AbstractMJJZT00101MXTransaction {

	private static final Logger LOGGER = LoggerFactory.getLogger(MJJZT00101MXTransaction.class);

	/**
	 * The execute method...
	 */
	@Override
	public void execute() {
		MJJZR001 mjjzR001 = this.getServiceLibrary(MJJZR001.class);
		LOGGER.info("Ejecuatandose transaccion MJJZT001");
		// Obtener campos de entrada
		long id = getCustomer().getIdCustomer();
		//Validacion de campos de entrada
		if((id != 0L)&&(id > 10000000)) {
			//Llenado a logica de negocio(librerias)
			Customer cliente = new Customer();
			cliente = mjjzR001.executeGetCustomer(id);
			
			//Mandar campos de salida
			setCustomer(cliente);
		}else {
			addAdvice("MJJZ43215678");
		}
		
	
		//Gestion de errores
		Advice advice = getAdvice();
		if(advice != null) {
			if("MJJZ43215678".equals(advice.getCode())) {
				//Estatus de la transaccion
				setSeverity(Severity.ENR);
			}else if("MJJZ43215679".equals(advice.getCode())){
				setSeverity(Severity.ENR);
			}
		}
	}

}
