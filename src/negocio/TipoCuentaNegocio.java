package negocio;

import java.util.ArrayList;

import entidad.Tipo_Cuenta;

public interface TipoCuentaNegocio {
	
	ArrayList<String> traerTipo();
	
	Tipo_Cuenta traerTipo(int id);
}