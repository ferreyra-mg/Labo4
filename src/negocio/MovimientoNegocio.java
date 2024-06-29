package negocio;

import java.util.ArrayList;

import entidad.Cuenta;
import entidad.Movimiento;

public interface MovimientoNegocio {
	ArrayList<Movimiento> traerMovimientos(int id);
	boolean transferirCbu(Cuenta cuenta, String cbuDestino, float monto, String tipoCuenta);
	float VerificarSaldoxCuenta(String usuario, String tipoCuenta);

	String ObtenerCbuDestino(String tipoCuenta, Cuenta cuenta);
	String ObtenerCbuEmisor(String tipoCuenta, Cuenta cuenta);
	boolean TransferirEntreCuentas(Cuenta cuenta, String cbuCuentaDestino, String cbuCuentaEmisor, float montoCuenta);
}
