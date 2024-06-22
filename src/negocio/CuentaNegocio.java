package negocio;


import java.util.ArrayList;

import entidad.Cuenta;

public interface CuentaNegocio {

	void crearCuenta(Cuenta cuenta);
	void modificarCuenta(Cuenta cuenta);
	void cambiarEstadoCuenta(int id); //no hay bajas fisicas, solo logicas
	ArrayList<Cuenta> obtenerTodasLasCuentas();
	ArrayList<Cuenta> obtenerTodasLasCuentas(int dni);
	Cuenta obtenerCuenta(int id);
	String obtenerUsuario(int dni);
}
