package py.edu.ucom.is2.proyectocamel.bancos3;




import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsumidorTranferenciaBancoBBVA extends RouteBuilder{

	private JacksonDataFormat jsonDataFormat;
	
	@Autowired
	ServiciosDeTransferencia service;
	
	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		
		jsonDataFormat = new JacksonDataFormat(SolicitudTransferencia.class);

	
		from("activemq:JoseMendoza-BBVA-IN")
		.log("Consumidor BBVA-IN")
		.unmarshal(jsonDataFormat)
		.process(new AgregarCabeceraDeProcesador())
		.filter().method(FiltroDeTransferencia.class,"validarFechaActual")
			.to("direct:procesarSwitchAceptadoBBVA").stop()
			.end()

		.to("direct:procesarSwitchRechazadoBBVA").stop()
		.end();
			
		from("direct:procesarSwitchAceptadoBBVA")
		.log("La Fecha ha caducado, lo sentimos :_(")
			.bean(service,"TransferenciaAceptada")
			.choice()
			.when(header("banco_origen").contains("BNG"))
			.log("Banco origen BNG")
			.setExchangePattern(ExchangePattern.InOnly)
			.marshal(jsonDataFormat)
			.to("activemq:JoseMendoza-BNG-OUT")
			.endChoice()
			.when(header("banco_origen").contains("CONTINENTAL"))
				.log("Banco origen CONTINENTAL")
				.setExchangePattern(ExchangePattern.InOnly)
				.marshal(jsonDataFormat)
				.to("activemq:JoseMendoza-CONTINENTAL-OUT")
				.endChoice()
			.when(header("banco_origen").contains("FAMLIAR"))
				.log("Banco origen BBVA")
				.setExchangePattern(ExchangePattern.InOnly)
				.marshal(jsonDataFormat)
				.to("activemq:JoseMendoza-BBVA-OUT")
				.endChoice()
			.otherwise()
				.log("Ninguna de las opciones")
			.end();
			
		from("direct:procesarSwitchRechazadoBBVA")
		.log("La Fecha ha caducado, lo sentimos :_(")
			.bean(service,"TransferenciaRechazada")
			.choice()
			.when(header("banco_origen").contains("CONTINENTAL"))
				.log("Banco origen CONTINENTAL")
				.setExchangePattern(ExchangePattern.InOnly)
				.marshal(jsonDataFormat)
				.to("activemq:JoseMendoza-CONTINENTAL-OUT")
				.endChoice()
			.when(header("banco_origen").contains("BNG"))
				.log("Banco origen BNG")
				.setExchangePattern(ExchangePattern.InOnly)
				.marshal(jsonDataFormat)
				.to("activemq:JoseMendoza-BNG-OUT")
				.endChoice()
			.when(header("banco_origen").contains("BNG"))
				.log("Banco origen BNG")
				.setExchangePattern(ExchangePattern.InOnly)
				.marshal(jsonDataFormat)
				.to("activemq:JoseMendoza-BNG-OUT")
				.endChoice()	
			.otherwise()
				.log("Ninguna de las opciones")
			.end();
				
	}

	

	
	

}
