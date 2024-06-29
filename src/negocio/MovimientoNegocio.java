package negocio;

import java.util.ArrayList;

import entidad.Cuenta;
import entidad.Movimiento;

public interface MovimientoNegocio {
	ArrayList<Movimiento> traerMovimientos(int id);
	boolean transferirCbu(Cuenta cuenta, String cbuDestino, float monto);
	float VerificarSaldo(String usuario);

	int ObtenerCbuDestino(String tipoCuenta, Cuenta cuenta);
	int ObtenerCbuEmisor(String tipoCuenta, Cuenta cuenta);
	boolean TransferirEntreCuentas(Cuenta cuenta, int cbuCuentaDestino, int cbuCuentaEmisor, float monto);
}
