package py.edu.ucom.is2.proyectocamel.bancos3;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;


@Component
public class ProcesamientoResultadoDeLaCola implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		
		RespuestaTransferencia transferenciaRequest = exchange.getIn().getBody(RespuestaTransferencia.class);
				
		if(transferenciaRequest != null) {
			
			
			System.out.println("El Nº de transferecia es: " +transferenciaRequest.getIdTransaccion() + transferenciaRequest.getMensaje());


			
		}
		else
			System.err.print("bancoService NULL");
	}
}
