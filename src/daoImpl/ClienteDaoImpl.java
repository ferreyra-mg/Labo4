package daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import dao.ClienteDao;
import entidad.Cliente;


public class ClienteDaoImpl implements ClienteDao {

	
	private static final String insert = "INSERT INTO cliente(dni, cuil, nombre, apellido, sexo, nacionalidad, fechaNacimiento, direccion, localidad, provincia, correoElectronico, telefono, contrasena)"
			+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	private static final String listarClientes = "SELECT * FROM bdbanco.cliente";
	
	@Override
	public boolean agregarCliente(Cliente cliente) {
		
			PreparedStatement statement;
			Connection conexion = Conexion.getConexion().getSQLConexion();
			boolean isInsertExitoso = false;
		    try {
		        
		    	statement = conexion.prepareStatement(insert);
		        statement.setInt(1, cliente.getDni()); 
		        statement.setString(2, cliente.getCuil()); 
		        statement.setString(3, cliente.getNombre()); 
		        statement.setString(4, cliente.getApellido()); 
		        statement.setBoolean(5, cliente.getSexo()); 
		        statement.setString(6, cliente.getNacionalidad()); 
		        //statement.setDate(7, new java.sql.Date(cliente.getFechaNacimiento().getTime()));
		        statement.setDate(7, (Date) cliente.getFechaNacimiento());
		        statement.setString(8, cliente.getDireccion()); 
		        statement.setString(9, cliente.getLocalidad()); 
		        statement.setString(10, cliente.getProvincia()); 
		        statement.setString(11, cliente.getCorreoElectronico()); 
		        statement.setInt(12, cliente.getTelefono()); 
		        statement.setString(13, cliente.getContrasena()); 
		    			        
      
		        if (statement.executeUpdate() > 0) {
		            conexion.commit();
		            isInsertExitoso = true;
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		        try {
		            conexion.rollback();
		        } catch (SQLException e1) {
		            e1.printStackTrace();
		        }
		    }
		    return isInsertExitoso;
		
	}

	@Override
	public void modificarCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarCliente(int dni) { //no hay bajas fisicas, solo logicas
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Cliente> obtenerTodosLosClientes() {
		Connection con = Conexion.getConexion().getSQLConexion();
		ArrayList<Cliente> listClientes = new ArrayList<>();
		PreparedStatement stmt = null;
        ResultSet rs = null;
		
        try {
			stmt = con.prepareStatement(listarClientes);
			rs = stmt.executeQuery();
			
			while(rs.next()){
				
				Cliente cli = new Cliente();
				cli.setDni(rs.getInt("dni"));
				cli.setCuil(rs.getString("cuil"));
				cli.setNombre(rs.getString("nombre"));
				cli.setApellido(rs.getString("apellido"));
				cli.setSexo(rs.getBoolean("sexo"));
				cli.setNacionalidad(rs.getString("nacionalidad"));
				cli.setFechaNacimiento(rs.getDate("fechaNacimiento"));
				cli.setDireccion(rs.getString("direccion"));
				cli.setLocalidad(rs.getString("localidad"));
				cli.setProvincia(rs.getString("provincia"));
				cli.setCorreoElectronico(rs.getString("correoElectronico"));
				cli.setTelefono(rs.getInt("telefono"));
				cli.setContrasena(rs.getString("contrasena"));
				
				
				listClientes.add(cli);
			}
		} catch (Exception e5) {
			e5.printStackTrace();
		}
        finally {
        	 try {
             	if (rs != null) rs.close();
                 if (stmt != null) stmt.close();
             } catch (SQLException e) {
                 e.printStackTrace();
             }
		}
        
        return listClientes;
	}
	
	@Override
	public Cliente obtenerCliente(int dni) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Cliente logear(String user, String psw) {
		
		//private static final String _obtenerCliente = "SELECT * FROM usuario WHERE nombreUsuario = ? AND contrasena = ?";
		// TODO Auto-generated method stub
		/*Connection conexion = Conexion.getConexion().getSQLConexion();
		Cliente cl = null;
		
		try {
			PreparedStatement stmt = conexion.prepareStatement(obtenerUsu);
			stmt.setString(1, nombreUsuario);
			stmt.setString(2, contra);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				cl = new Cliente();
				cl.setId(rs.getInt("id"));
				cl.setNombreUsuario(rs.getString("nombreUsuario"));
				cl.setContrasena(rs.getString("contrasena"));
				cl.setTipoUsuario(rs.getString("tipoUsuario"));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Cliente;*/
		
		
		String sql = "SELECT dni, contrasena FROM cliente WHERE dni = ? AND contrasena = ?";
        try (Connection conexion = Conexion.getConexion().getSQLConexion();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {
            
            stmt.setString(1, user);
            stmt.setString(2, psw);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setDni(rs.getInt("dni"));
                cliente.setContrasena(rs.getString("contrasena"));
                return cliente;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error aca cliente");
        }
        return null;
	}
	
	
}
