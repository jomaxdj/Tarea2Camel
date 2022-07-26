package py.edu.ucom.is2.proyectocamel.bancos3;


import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.jsonpath.JsonPath;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ProductorDeTransferencias extends RouteBuilder{
	
	private JacksonDataFormat jsonDataFormat;

	@Autowired
	ServiciosDeTransferencia service;
	
	@Override
	public void configure() throws Exception {
			
		jsonDataFormat = new JacksonDataFormat(SolicitudTransferencia.class);
		
		restConfiguration().component("servlet").bindingMode(RestBindingMode.auto);
		
		rest().path("/api")
			.consumes("application/json")
			.produces("application/json")
			
		.post("/transferencia")
			.type(SolicitudTransferencia.class)
			.outType(RespuestaTransferencia.class)
			.to("direct:procesarTransferencia");
			
				
		from("direct:procesarTransferencia")
			.bean(service,"genDateID")
			
			.filter().method(FiltroDeTransferencia.class,"validarMontoEnviado")
							
				.to("direct:procesarSwitch").stop()
				
				.end()

			.bean(service,"MontoPermitido");
		
		
		//Encolamos los Mensajes recibidos
		from("direct:procesarSwitch")
		.marshal(jsonDataFormat)
			.choice()
			.when().jsonpath("$.[?(@.banco_destino == 'BBVA')]")
				.setExchangePattern(ExchangePattern.InOnly)
				.to("activemq:JoseMendoza-BBVA-IN")
				.setExchangePattern(ExchangePattern.InOut)
				.unmarshal(jsonDataFormat)
				.bean(service,"result")
				.endChoice()
			.when().jsonpath("$.[?(@.banco_destino == 'BNG')]")
				.setExchangePattern(ExchangePattern.InOnly)
				.to("activemq:JoseMendoza-BNG-IN")
				.setExchangePattern(ExchangePattern.InOut)
				.unmarshal(jsonDataFormat)
				.bean(service,"result")
				.endChoice()
			.when().jsonpath("$.[?(@.banco_destino == 'CONTINENTAL')]")
				.setExchangePattern(ExchangePattern.InOnly)
				.to("activemq:JoseMendoza-CONTINENTAL-IN")
				.setExchangePattern(ExchangePattern.InOut)
				.unmarshal(jsonDataFormat)
				.bean(service,"result")
				.endChoice()
			
			.otherwise()
				
				.transform().constant("El valor enviado no es valido")
		    .end();
		
	}

}
