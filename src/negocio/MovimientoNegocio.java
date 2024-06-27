package negocio;

import java.util.ArrayList;

import entidad.Cuenta;
import entidad.Movimiento;

public interface MovimientoNegocio {
	ArrayList<Movimiento> traerMovimientos(int id);
	boolean transferirCbu(Cuenta cuenta, String cbuDestino, float monto);
	float VerificarSaldo(String usuario);
}
