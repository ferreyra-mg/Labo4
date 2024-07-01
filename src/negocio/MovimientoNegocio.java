package negocio;

import java.util.ArrayList;
import java.util.Date;

import entidad.Cuenta;
import entidad.Movimiento;

public interface MovimientoNegocio {
	ArrayList<Movimiento> traerMovimientos(int id);
	boolean transferirCbu(int  id, String cbuDestino, float monto, String tipoCuenta);
	float VerificarSaldoxCuenta(int id);

	String ObtenerCbu(String tipoCuenta, Cuenta cuenta);
	boolean TransferirEntreCuentas(int id, String cbuCuentaDestino, String cbuCuentaEmisor, float montoCuenta);
	float obtenerMontoEntre(Date inicial, Date fin);
	int obtenerMovimientosEntre(Date inicial, Date fin);
}
