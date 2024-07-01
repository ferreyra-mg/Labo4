package dao;

import java.util.ArrayList;
import java.util.Date;

import entidad.Cuenta;
import entidad.Movimiento;

public interface MovimientoDao {
	ArrayList<Movimiento> traerMovimientos(int id);
	boolean TransferirCbu(int id, String cbuDestino, float monto, String tipoCuenta);
	float VerificarSaldoxCuenta(int id);
	
	String ObtenerCbu(String tipoCuenta, Cuenta cuenta);
	boolean TransferirEntreCuentas(int id, String cbuDestino, String cbuEmisor, float monto);
	float obtenerMontoEntre(Date inicial, Date fin);
	int obtenerMovimientosEntre(Date inicial, Date fin);
}
