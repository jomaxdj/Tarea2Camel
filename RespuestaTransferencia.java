package py.edu.ucom.is2.proyectocamel.bancos3;

import java.io.Serializable;

import org.springframework.stereotype.Component;


public class RespuestaTransferencia implements Serializable{
			
		String mensaje;
		Integer idTransaccion;

		public Integer getIdTransaccion() {
			return idTransaccion;
		}

		public void setIdTransaccion(Integer idTransaccion) {
			this.idTransaccion = idTransaccion;
		}

		public RespuestaTransferencia(String mensaje, Integer idTransaccion) {
			super();
			this.mensaje = mensaje;
			this.idTransaccion = idTransaccion;
		}

		public RespuestaTransferencia() {
			// TODO Auto-generated constructor stub
		}

		public String getMensaje() {
			return mensaje;
		}

		public void setMensaje(String mensaje) {
			this.mensaje = mensaje;
		}
		
 
}
