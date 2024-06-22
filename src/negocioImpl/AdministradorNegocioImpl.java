package negocioImpl;

import dao.AdministradorDao;
import daoImpl.AdministradorDaoImpl;
import entidad.Administrador;
import negocio.AdministradorNegocio;

public class AdministradorNegocioImpl implements AdministradorNegocio {
	
	private AdministradorDao admDao = new AdministradorDaoImpl();
	
	@Override
	public Administrador logear(String user, String psw) {
		return admDao.logear(user,psw);
	}
}
