package dao;

import entidad.Administrador;

public interface AdministradorDao {
	Administrador logear(String user, String psw);
}
