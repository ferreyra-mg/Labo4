package negocioImpl;

import java.util.ArrayList;
import dao.ClienteDao;
import daoImpl.ClienteDaoImpl;
import entidad.Cliente;
import negocio.ClienteNegocio;

public class ClienteNegocioImpl implements ClienteNegocio{
	
	private ClienteDao clienteDao = new ClienteDaoImpl();

	
	public void agregarCliente(Cliente cliente) {
		clienteDao.agregarCliente(cliente);
		
	}

	@Override
	public void modificarCliente(Cliente cliente) {
		clienteDao.modificarCliente(cliente);
	}

	@Override
	public void eliminarCliente(int dni) {//no hay bajas fisicas, solo logicas
		clienteDao.eliminarCliente(dni);
		
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
