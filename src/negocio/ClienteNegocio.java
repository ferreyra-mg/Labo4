package negocio;

import java.util.ArrayList;
import entidad.Cliente;

public interface ClienteNegocio {

	void agregarCliente(Cliente cliente);
	void modificarCliente(Cliente cliente);
	void eliminarCliente(int dni); //no hay bajas fisicas, solo logicas
	ArrayList<Cliente> obtenerTodosLosClientes();
	Cliente obtenerCliente(int dni);
	Cliente logear(String user, String psw);
}
