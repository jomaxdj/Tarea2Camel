package py.edu.ucom.is2.proyectocamel.bancos3;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import org.springframework.stereotype.Component;

@Component
public class FiltroDeTransferencia {
	public boolean validarMontoEnviado(SolicitudTransferencia transferenciaRequest) {
	
		if (transferenciaRequest.getMonto() < 20000000) {
			return true;
		}else {
			return false;
		}
		
	}
	 
	public boolean validarFechaActual(SolicitudTransferencia transferenciaRequest) {
		
		DateTimeFormatter formatoDeFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDateTime fechaHoy = LocalDateTime.now();
		
		String  requestDate = transferenciaRequest.getFecha();
		
		LocalDate fecha = LocalDate.parse(requestDate,formatoDeFecha);

	    LocalDate fechaActual = LocalDate.now();
		
		
		long fechaDeLlegada = ChronoUnit.DAYS.between(fecha,fechaActual);
		
		if (formatoDeFecha.format(fechaHoy).equals(transferenciaRequest.getFecha())) {
			return true;
		}else if(fechaDeLlegada < 1){
			return true;
		}else {
			return false;
		}
		

		
	}
	
	

}
