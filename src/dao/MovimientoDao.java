package dao;

import java.util.ArrayList;
import java.util.Date;

import entidad.Cuenta;
import entidad.Movimiento;

public interface MovimientoDao {
	ArrayList<Movimiento> traerMovimientos(int id);
	boolean TransferirCbu(Cuenta cuenta, String cbuDestino, float monto, String tipoCuenta);
	float VerificarSaldoxCuenta(String usuario, String tipoCuenta);
	
	String ObtenerCbuDestino(String tipoCuenta, Cuenta cuenta);
	String ObtenerCbuEmisor(String tipoCuenta, Cuenta cuenta);
	boolean TransferirEntreCuentas(Cuenta cuenta, String cbuDestino, String cbuEmisor, float monto);
	float obtenerMontoEntre(Date inicial, Date fin);
	
}
