package com.bbva.mjjz;

import com.bbva.elara.domain.transaction.Advice;
import com.bbva.elara.domain.transaction.Severity;
import com.bbva.mjjz.lib.r001.MJJZR001;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Transaccion para el borrado de clientes
 *
 */
public class MJJZT00401MXTransaction extends AbstractMJJZT00401MXTransaction {

	private static final Logger LOGGER = LoggerFactory.getLogger(MJJZT00401MXTransaction.class);

	/**
	 * The execute method...
	 */
	@Override
	public void execute() {
		LOGGER.info("Ejecutando metodo execute de la transaccion MJJZT00401MXTransaction");
		
		MJJZR001 mjjzR001 = this.getServiceLibrary(MJJZR001.class);
		
		//Obtener campos de entrada
		long idCustomer = getIdcustomer();
		
		//validacion de campos de entrada
		if(idCustomer != 0 && idCustomer >= 10000000) {
			//logica de negocio(librerias)
			long result = 0;
			result = mjjzR001.executeDeleteCustomer(idCustomer);
			
			
			//seteo de salidas
			LOGGER.info("Seteando campos de salida");
			setResultdeletes(result);
		}else {
			LOGGER.info("Los campos no cumplen con las reglas de validacion");
			addAdvice("MJJZ43215684");
		}
	
		//Gestion de errores
		//validación de errores
		Advice advice = getAdvice();
		
		if(advice != null) {
			if("MJJZ43215679".equals(advice.getCode())) {
				setSeverity(Severity.ENR);
			}else if("MJJZ43215684".equals(advice.getCode())) {
				setSeverity(Severity.ENR);
			}else if("MJJZ43215685".equals(advice.getCode())) {
				setSeverity(Severity.ENR);
			}
		}
	}

}
