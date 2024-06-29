package dao;

import java.util.ArrayList;

import entidad.Cuenta;
import entidad.Movimiento;

public interface MovimientoDao {
	ArrayList<Movimiento> traerMovimientos(int id);
	boolean TransferirCbu(Cuenta cuenta, String cbuDestino, float monto);
	float VerificarSaldo(String usuario);
	
	int ObtenerCbuDestino(String tipoCuenta, Cuenta cuenta);
	int ObtenerCbuEmisor(String tipoCuenta, Cuenta cuenta);
	boolean TransferirEntreCuentas(Cuenta cuenta, int cbuDestino, int cbuEmisor, float monto);
	
	
}
