package py.edu.ucom.is2.proyectocamel.bancos3;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Component;

@Component
public class ServiciosDeTransferencia {
	public SolicitudTransferencia genDateID(SolicitudTransferencia genDateID) {
		
		int int_random = ThreadLocalRandom.current().nextInt(1 , 99999) ;  

		genDateID.setNumtransferencia(int_random);
		
		if(genDateID.getFecha() == null) {
			DateTimeFormatter formatoDeFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		    LocalDateTime fechaHoy = LocalDateTime.now();
		    //System.out.println(dtf.format(now));
		    	genDateID.setFecha(formatoDeFecha.format(fechaHoy));
		}
		return genDateID;
		
		}
	
	public SolicitudTransferencia getDate(SolicitudTransferencia getDate) {
		DateTimeFormatter formatoDeFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		   LocalDateTime now = LocalDateTime.now();
		   getDate.setFecha(formatoDeFecha.format(now));

		return getDate;
		
		}	
	
	
	public String MontoPermitido(SolicitudTransferencia request) {
		RespuestaTransferencia respuesta = new RespuestaTransferencia();
		respuesta.setMensaje("Solo se puede operar hasta 20.000.000, intente con un monto menor " + 
				" Numero de Transaccion: " +request.getNumtransferencia() + 
				" Monto : " +request.getMonto() );
		
		return respuesta.getMensaje();
	}
	
		
	public String result(SolicitudTransferencia request) {
		RespuestaTransferencia respuesta = new RespuestaTransferencia();
		respuesta.setMensaje(" Tranferencia Encolada -  Banco Destino: " +request.getBanco_destino() + " Numero de Transaccion: " +request.getNumtransferencia() );
		return respuesta.getMensaje();
	}
	
	public RespuestaTransferencia TransferenciaAceptada(SolicitudTransferencia request) {
		RespuestaTransferencia respuesta = new RespuestaTransferencia();
		respuesta.setMensaje(" La Transferencia se realizo con exito. Gracias por utilizar nuestros servicios. ");
		respuesta.setIdTransaccion(request.getNumtransferencia());
		return respuesta;
	}
	public RespuestaTransferencia TransferenciaRechazada(SolicitudTransferencia request) {
		RespuestaTransferencia respuesta = new RespuestaTransferencia();
		respuesta.setMensaje(" No se pudo realizar la transferencia - El mensaje ha expirado, lo sentimos." );
		respuesta.setIdTransaccion(request.getNumtransferencia());
		return respuesta;
	}
}
