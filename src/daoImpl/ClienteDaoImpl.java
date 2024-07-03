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
	
	private static final String existeDNI ="SELECT COUNT(*) AS cantidad FROM bdbanco.cliente WHERE dni = ? AND activo = 1";
	
	private static final String listarClientes = "SELECT * FROM cliente WHERE cliente.activo = 1;";
	
	private static final String update = "UPDATE cliente SET nombre = ?, apellido = ?, sexo = ?, nacionalidad = ?, "
			+ " direccion = ?, localidad = ?, provincia = ?, correoElectronico = ?, telefono = ?, contrasena = ? WHERE dni = ?;";
	
	private static final String clienteXdni = "SELECT * FROM cliente WHERE dni = ? and activo = 1;";
	
	private static final String deleteLogico = "UPDATE cliente SET activo = false WHERE dni =?;";
	
	@Override
	public boolean agregarCliente(Cliente cliente) {
		
			PreparedStatement statement = null;
			Connection conexion = Conexion.getConexion().getSQLConexion();
			boolean isInsertExitoso = false;
		    try {
		    	statement = conexion.prepareStatement(insert);
		    	statement.setInt(1, cliente.getDni());
		        statement.setString(2, cliente.getCuil()); 
		        statement.setString(3, cliente.getNombre()); 
		        statement.setString(4, cliente.getApellido()); 
		        statement.setBoolean(5, cliente.getSexo()); 
		        statement.setInt(6, cliente.getNacionalidad());
		        statement.setDate(7, (Date) cliente.getFechaNacimiento());
		        statement.setString(8, cliente.getDireccion()); 
		        statement.setInt(9, cliente.getLocalidad()); 
		        statement.setInt(10, cliente.getProvincia()); 
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
		    }finally {
	        	 try {
	                  if (statement != null) statement.close();
	              } catch (SQLException e) {
	                  e.printStackTrace();
	              }
	 		}
		    return isInsertExitoso;
	}

	@Override
	public boolean modificarCliente(Cliente cliente) {
		PreparedStatement statement=null;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean esExitoso = false;
	    try {
	    	
	    	statement = conexion.prepareStatement(update);  
	        statement.setString(1, cliente.getNombre()); 
	        statement.setString(2, cliente.getApellido()); 
	        statement.setBoolean(3, cliente.getSexo()); 
	        statement.setInt(4, cliente.getNacionalidad()); 
	        statement.setString(5, cliente.getDireccion()); 
	        statement.setInt(6, cliente.getLocalidad()); 
	        statement.setInt(7, cliente.getProvincia()); 
	        statement.setString(8, cliente.getCorreoElectronico()); 
	        statement.setInt(9, cliente.getTelefono()); 
	        statement.setString(10, cliente.getContrasena());
	        statement.setInt(11, cliente.getDni());  
	        
	        if (statement.executeUpdate() > 0) {
	            conexion.commit();
	            esExitoso = true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        try {
	            conexion.rollback();
	        } catch (SQLException e1) {
	            e1.printStackTrace();
	        }
	    }
	    finally {
       	 try {
             if (statement != null) statement.close();
             } catch (SQLException e) {
                 e.printStackTrace();
             }
		}
	    return esExitoso;
	}

	@Override
	public boolean eliminarCliente(int dni) {
		PreparedStatement statement = null;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean esExitoso = false;
	    try {
	    	statement = conexion.prepareStatement(deleteLogico);  
	        statement.setInt(1, dni); 
	        	        
	        
	        if (statement.executeUpdate() > 0) {
	            conexion.commit();
	            esExitoso = true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        try {
	            conexion.rollback();
	        } catch (SQLException e1) {
	            e1.printStackTrace();
	        }
	    }
	    finally {
	       	 try {
	             if (statement != null) statement.close();
	             } catch (SQLException e) {
	                 e.printStackTrace();
	             }
			}
	    return esExitoso;
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
				cli.setNacionalidad(rs.getInt("nacionalidad"));
				cli.setFechaNacimiento(rs.getDate("fechaNacimiento"));
				cli.setDireccion(rs.getString("direccion"));
				cli.setLocalidad(rs.getInt("localidad"));
				cli.setProvincia(rs.getInt("provincia"));
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
		Connection con = Conexion.getConexion().getSQLConexion();
		PreparedStatement stmt = null;
        ResultSet rs = null;
        Cliente cli = new Cliente();
        
        try {
			stmt = con.prepareStatement(clienteXdni);
			stmt.setInt(1, dni);
			rs = stmt.executeQuery();					
			
			if(rs.next()){		
				
				cli.setDni(rs.getInt("dni"));
				cli.setCuil(rs.getString("cuil"));
				cli.setNombre(rs.getString("nombre"));
				cli.setApellido(rs.getString("apellido"));
				cli.setSexo(rs.getBoolean("sexo"));
				cli.setNacionalidad(rs.getInt("nacionalidad"));
				cli.setFechaNacimiento(rs.getDate("fechaNacimiento"));
				cli.setDireccion(rs.getString("direccion"));
				cli.setLocalidad(rs.getInt("localidad"));
				cli.setProvincia(rs.getInt("provincia"));
				cli.setCorreoElectronico(rs.getString("correoElectronico"));
				cli.setTelefono(rs.getInt("telefono"));
				cli.setContrasena(rs.getString("contrasena"));				

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
        
        return cli;
	}
	
	@Override
	public Cliente logear(String user, String psw) {
		String sql = "SELECT * FROM cliente WHERE dni = ? AND contrasena = ? AND activo = 1";
        try (Connection conexion = Conexion.getConexion().getSQLConexion();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {
            
            stmt.setString(1, user);
            stmt.setString(2, psw);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setDni(rs.getInt("dni"));
                cliente.setContrasena(rs.getString("contrasena"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                return cliente;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
	}
	
	@Override
	public boolean existeDNI(int dni) {
		Connection con = Conexion.getConexion().getSQLConexion();
		boolean existe = false;
		PreparedStatement stmt = null;
        ResultSet rs = null;
		
        try {
			stmt = con.prepareStatement(existeDNI);
			stmt.setInt(1, dni);
			rs = stmt.executeQuery();
			
			while(rs.next()){
				
				if(Integer.parseInt(rs.getString("cantidad"))> 0)
				{
					existe = true;
				}
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
        return existe;
	}
	
}
