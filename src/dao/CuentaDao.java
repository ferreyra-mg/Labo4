package dao;

import java.util.ArrayList;
import java.util.Date;

import entidad.Cuenta;

public interface CuentaDao {

	boolean crearCuenta(Cuenta cuenta);
	ArrayList<Cuenta> obtenerTodasLasCuentas();
	ArrayList<Cuenta> obtenerTodasLasCuentas(int dni);
	Cuenta obtenerCuenta(int id);
	Cuenta obtenerCuentaxUsuario(String Usuario);
	Cuenta obtenerUsuario(int dni);
	int numeroCuentas(int dni);
	int obtenerCuentasEntre(Date inicio, Date fin);
}
