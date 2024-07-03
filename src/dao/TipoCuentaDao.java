package dao;

import java.util.ArrayList;

import entidad.Tipo_Cuenta;

public interface TipoCuentaDao {
	ArrayList<String> traerTipo();
	Tipo_Cuenta traerTipo(int id);
}
