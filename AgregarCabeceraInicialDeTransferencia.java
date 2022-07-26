package py.edu.ucom.is2.proyectocamel.bancos3;

import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import py.edu.ucom.is2.proyectocamel.routes.ProcessorTest;


@Component
public class AgregarCabeceraInicialDeTransferencia implements Processor {
	
	Logger logger =  LoggerFactory.getLogger(ProcessorTest.class);
	
	@Override
	public void process(Exchange exchange) throws Exception {
		
		SolicitudTransferencia bancoRequest = (SolicitudTransferencia)exchange.getIn().getBody();
		
		Map<String, Object> headers = exchange.getIn().getHeaders();
		headers.put("banco_destino",bancoRequest.getBanco_origen());
		
		
		
		exchange.getIn().setHeaders(headers);
		
	}
}
