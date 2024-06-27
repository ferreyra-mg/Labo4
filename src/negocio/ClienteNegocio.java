package negocio;

import java.util.ArrayList;
import entidad.Cliente;

public interface ClienteNegocio {

	boolean agregarCliente(Cliente cliente);
	boolean modificarCliente(Cliente cliente);
	boolean eliminarCliente(int dni); //no hay bajas fisicas, solo logicas
	ArrayList<Cliente> obtenerTodosLosClientes();
	Cliente obtenerCliente(int dni);
	Cliente logear(String user, String psw);
	boolean existeDNI(int dni);
}
