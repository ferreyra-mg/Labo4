package negocio;


import java.util.ArrayList;
import java.util.Date;

import entidad.Cuenta;

public interface CuentaNegocio {

	boolean crearCuenta(Cuenta cuenta);
	ArrayList<Cuenta> obtenerTodasLasCuentas(); //trae todos
	ArrayList<Cuenta> obtenerTodasLasCuentas(int dni); //trae de un solo usuario todas sus cuentas
	Cuenta obtenerCuenta(int id);
	Cuenta obtenerCuentaxUsuario(String Usuario);
	Cuenta obtenerUsuario(int dni);
	int numeroCuentas(int dni);
	int obtenerCuentasEntre(Date inicio, Date fin);
}
