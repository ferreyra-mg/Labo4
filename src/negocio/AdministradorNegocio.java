package negocio;

import entidad.Administrador;

public interface AdministradorNegocio {
	Administrador logear(String user, String psw);
}
