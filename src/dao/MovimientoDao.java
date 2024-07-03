package dao;

import java.util.ArrayList;
import java.util.Date;

import entidad.Cuenta;
import entidad.Movimiento;
import entidad.Tipo_Movimiento;

public interface MovimientoDao {
	ArrayList<Movimiento> traerMovimientos(int id);
	boolean TransferirCbu(Cuenta cuenta, String cbuDestino, float monto, String tipoCuenta);
	float VerificarSaldoxCuenta(int id);
	
	String ObtenerCbu(int id);
	boolean TransferirEntreCuentas(Cuenta cuenta, String cbuDestino, String cbuEmisor, float monto);
	float obtenerMontoEntre(Date inicial, Date fin);
	int obtenerMovimientosEntre(Date inicial, Date fin);
	ArrayList<Tipo_Movimiento> traerTipos();
}
