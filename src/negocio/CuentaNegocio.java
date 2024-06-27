package negocio;


import java.util.ArrayList;

import entidad.Cuenta;

public interface CuentaNegocio {

	boolean crearCuenta(Cuenta cuenta);
	void modificarCuenta(Cuenta cuenta);
	void cambiarEstadoCuenta(int id); //no hay bajas fisicas, solo logicas
	ArrayList<Cuenta> obtenerTodasLasCuentas(); //trae todos
	ArrayList<Cuenta> obtenerTodasLasCuentas(int dni); //trae de un solo usuario todas sus cuentas
	Cuenta obtenerCuenta(int id);
	Cuenta obtenerCuentaxUsuario(String Usuario);
	Cuenta obtenerUsuario(int dni);
	int numeroCuentas(int dni);
}
