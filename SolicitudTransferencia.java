package py.edu.ucom.is2.proyectocamel.bancos3;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class SolicitudTransferencia {
	private int cuenta;
	private int monto;
	private int numtransferencia;
	
	
	private String banco_origen;
	private String banco_destino;
	private String fecha;
	
	
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public int getNumtransferencia() {
		return numtransferencia;
	}
	public void setNumtransferencia(int numtransferencia) {
		this.numtransferencia = numtransferencia;
	}
	
	public int getCuenta() {
		return cuenta;
	}
	public void setCuenta(int cuenta) {
		this.cuenta = cuenta;
	}
	public int getMonto() {
		return monto;
	}
	public void setMonto(int monto) {
		this.monto = monto;
	}
	public String getBanco_origen() {
		return banco_origen;
	}
	public void setBanco_origen(String banco_origen) {
		this.banco_origen = banco_origen;
	}
	@Override
	public String toString() {
		return "TransferenciaRequest [cuenta=" + cuenta + ", monto=" + monto + ", banco_origen=" + banco_origen
				+ ", banco_destino=" + banco_destino + "]";
	}
	public String getBanco_destino() {
		return banco_destino;
	}
	public void setBanco_destino(String banco_destino) {
		this.banco_destino = banco_destino;
	}
	
	
}
