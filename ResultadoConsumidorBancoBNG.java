package py.edu.ucom.is2.proyectocamel.bancos3;


import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import org.apache.camel.component.jackson.JacksonDataFormat;


@Component
public class ResultadoConsumidorBancoBNG extends RouteBuilder{
	
	private JacksonDataFormat jsonDataFormat;
	
		
	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
			
		jsonDataFormat = new JacksonDataFormat(RespuestaTransferencia.class);
		
		from("activemq:JoseMendoza-BNG-OUT")
		.log("Consumidor BNG-OUT")
		.unmarshal(jsonDataFormat)
		.process(new ProcesamientoResultadoDeLaCola())
		.end();
		
	}


}

