package negocioImpl;

import java.util.ArrayList;
import dao.ClienteDao;
import daoImpl.ClienteDaoImpl;
import entidad.Cliente;
import negocio.ClienteNegocio;

public class ClienteNegocioImpl implements ClienteNegocio{
	
	private ClienteDao clienteDao = new ClienteDaoImpl();

	
	public boolean agregarCliente(Cliente cliente) {
		return clienteDao.agregarCliente(cliente);
		
	}

	@Override
	public boolean modificarCliente(Cliente cliente) {
		return clienteDao.modificarCliente(cliente);
	}

	@Override
	public boolean eliminarCliente(int dni) {//no hay bajas fisicas, solo logicas
		return clienteDao.eliminarCliente(dni);
		
	}

	@Override
	public ArrayList<Cliente> obtenerTodosLosClientes() {
		return clienteDao.obtenerTodosLosClientes();
	}
	
	@Override
	public Cliente obtenerCliente(int dni) {
		return clienteDao.obtenerCliente(dni);
	}
	
	@Override
	public Cliente logear(String user, String psw){
		return clienteDao.logear(user,psw);
	}

}
