package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import dao.CuentaDao;
import entidad.Cuenta;
import entidad.Tipo_Cuenta;

public class CuentaDaoImpl implements CuentaDao {

	private static final String CUENTA_DNI = "SELECT * FROM bdbanco.cuenta where dni = ? LIMIT 1";
	private static final String CUENTA_CLIENTE= "SELECT c.id, c.usuario, c.dni, c.cbu as cbu, c.fechaCreacion creacion, c.tipoCuenta, c.saldo, c.estado FROM bdbanco.cuenta c " +
			"where dni = ? LIMIT 3";
	private static final String CANTIDAD_CUENTA = "SELECT COUNT(*) AS cantidad FROM bdbanco.cuenta WHERE dni = ?";
	private static final String CREAR_CUENTA = "INSERT INTO cuenta (usuario, dni, cbu, fechaCreacion, tipoCuenta, saldo, estado) VALUES(?, ?, ?, ?, ?, ?, ?)";
	private static final String CUENTAS_ENTRE = "SELECT COUNT(*) AS total_cuentas FROM cuenta WHERE fechaCreacion BETWEEN ? AND ?;";
	private static final String TRAER_TIPOS = "SELECT * FROM tipo_cuenta";
	private static final String TIPO_CUENTA = "SELECT * FROM tipo_cuenta WHERE id = ?";
	
	@Override
	public boolean crearCuenta(Cuenta cuenta) {
		PreparedStatement stmt=null;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		ResultSet rs = null;
		boolean isInsertExitoso = false;
	    try {
	        
	    	stmt = conexion.prepareStatement(CREAR_CUENTA);
	    	
	    	stmt.setString(1, cuenta.getUsuario()); 
	    	stmt.setInt(2, cuenta.getDni()); 
	    	stmt.setString(3, cuenta.getCBU()); 
	    	java.util.Date creacionUtilDate = cuenta.getCreacion();
	    	java.sql.Date creacionSqlDate = new java.sql.Date(creacionUtilDate.getTime());
	    	stmt.setDate(4, creacionSqlDate);
	    	stmt.setInt(5, cuenta.getTipo()); 
	    	stmt.setFloat(6, cuenta.getSaldo()); 
	    	stmt.setBoolean(7, cuenta.isEstado());
	    	
	        if (stmt.executeUpdate() > 0) {
	        	String ultimoId = "SELECT MAX(id) as id FROM bdbanco.cuenta";
	            stmt = conexion.prepareStatement(ultimoId);
	            rs = stmt.executeQuery();

	            int id = 0;
	            // Procesar el resultado
	            if (rs.next()) {
	                id = rs.getInt("id");
	                System.out.println("El ID de la nueva cuenta creada es: " + id);
	            }
	        	
	        	stmt = conexion.prepareStatement("INSERT INTO bdbanco.movimiento (id_Cuenta, fecha, concepto, importe, tipo) VALUES (?, ?, ?, ?, ?)");
	        	stmt.setInt(1, id);
	        	stmt.setDate(2, new java.sql.Date(System.currentTimeMillis()));
	        	stmt.setString(3, "Alta cuenta");
	        	stmt.setFloat(4, 10000);
	        	stmt.setInt(5, 1);
				if(stmt.executeUpdate() > 0) {
					conexion.commit();
					isInsertExitoso = true;
				}
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        try {
	            conexion.rollback();
	        } catch (SQLException e1) {
	            e1.printStackTrace();
	        }
	    } finally {
       	 try {
          	if (rs != null) rs.close();
              if (stmt != null) stmt.close();
          } catch (SQLException e) {
              e.printStackTrace();
          }
		}
	    
	    return isInsertExitoso;
	}

	@Override
	public ArrayList<Cuenta> obtenerTodasLasCuentas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Cuenta> obtenerTodasLasCuentas(int dni) {
		ArrayList<Cuenta> cuentas = new ArrayList<>();
		try (Connection conexion = Conexion.getConexion().getSQLConexion();
				PreparedStatement stmt = conexion.prepareStatement(CUENTA_CLIENTE)) {
			stmt.setInt(1, dni);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Cuenta cuenta = new Cuenta(rs.getInt("id"),rs.getString("usuario"), rs.getInt("dni"),
						rs.getString("cbu"), 
						rs.getDate("creacion"), 
						rs.getInt("tipoCuenta"),
						rs.getFloat("saldo"), 
						rs.getBoolean("estado"));
				cuentas.add(cuenta);
				cuenta.toString();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error: No pudieron recuperar las cuentas del cliente [" + dni + "]");
		} 
		return cuentas;
	}

	@Override
	public Cuenta obtenerCuenta(int id) {
		Cuenta cuenta = null;

		String sql = "SELECT id, usuario, dni, cbu, fechaCreacion, tipoCuenta, saldo, estado FROM cuenta WHERE id = ?";
		try (Connection conexion = Conexion.getConexion().getSQLConexion();
				PreparedStatement stmt = conexion.prepareStatement(sql)) {

			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				cuenta = new Cuenta(rs.getInt("id"), rs.getString("usuario"), rs.getInt("dni"),
						rs.getString("cbu"), rs.getDate("fechaCreacion"), rs.getInt("tipoCuenta"),
						rs.getFloat("saldo"), rs.getBoolean("estado"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error: No pudo recuperar la cuenta [" + id + "]");
		}

		return cuenta;
	}

	@Override
	public Cuenta obtenerUsuario(int dni) {
		PreparedStatement statement = null;
		Connection conexion = null;
		ResultSet rs = null;
		Cuenta c = new Cuenta();

		try {
			conexion = Conexion.getConexion().getSQLConexion();
			statement = conexion.prepareStatement(CUENTA_DNI);
			statement.setInt(1, dni);

			rs = statement.executeQuery();

			if (rs.next()) {
				c.setId(rs.getInt("id"));
		        c.setUsuario(rs.getString("usuario"));
		        c.setDni(rs.getInt("dni"));
		        c.setCBU(rs.getString("cbu"));
		        c.setCreacion(rs.getDate("fechaCreacion"));
		        c.setTipo(rs.getInt("tipoCuenta"));
		        c.setSaldo(rs.getFloat("saldo"));
		        c.setEstado(rs.getBoolean("estado"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			try {
				if (conexion != null) {
					conexion.rollback();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return c;
	}

	@Override
	public Cuenta obtenerCuentaxUsuario(String Usuario) {
		Cuenta cuenta = null;

		String sql = "SELECT id, usuario, dni, cbu, fechaCreacion, tipoCuenta, saldo, estado FROM cuenta WHERE usuario = ?";
		try (Connection conexion = Conexion.getConexion().getSQLConexion();
				PreparedStatement stmt = conexion.prepareStatement(sql)) {

			stmt.setString(1, Usuario);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				cuenta = new Cuenta(rs.getInt("id"), rs.getString("usuario"), rs.getInt("dni"),
						rs.getString("cbu"), rs.getDate("fechaCreacion"), rs.getInt("tipoCuenta"),
						rs.getFloat("saldo"), rs.getBoolean("estado"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cuenta;
	}
	
	@Override
	public int numeroCuentas(int dni) {
		int cant = 0;
		try (Connection conexion = Conexion.getConexion().getSQLConexion();
			PreparedStatement stmt = conexion.prepareStatement(CANTIDAD_CUENTA))
		{
			stmt.setInt(1, dni);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				cant = rs.getInt("cantidad");
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cant;
	}

	@Override
	public int obtenerCuentasEntre(Date inicio, Date fin) {
		int cantidad = 0;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conexion.prepareStatement(CUENTAS_ENTRE);
			stmt.setDate(1, new java.sql.Date(inicio.getTime()));
	        stmt.setDate(2, new java.sql.Date(fin.getTime()));
		    rs = stmt.executeQuery();
		        if (rs.next()) {
		            cantidad = rs.getInt("total_cuentas");
		        }
		} catch (SQLException e) {
		    e.printStackTrace();
		} 
		finally {
       	 try {
            	if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
		}
		return cantidad;
	}

	@Override
	public ArrayList<Tipo_Cuenta> traerTipos() {
		ArrayList<Tipo_Cuenta> cuentas = new ArrayList<>();
		try (Connection conexion = Conexion.getConexion().getSQLConexion();
				PreparedStatement stmt = conexion.prepareStatement(TRAER_TIPOS)) {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Tipo_Cuenta tipo = new Tipo_Cuenta(rs.getInt("id"),rs.getString("descripcion"));
				cuentas.add(tipo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cuentas;
	}

	@Override
	public Tipo_Cuenta traerDescripcion(int id) {
		Tipo_Cuenta t = new Tipo_Cuenta();
		try (Connection conexion = Conexion.getConexion().getSQLConexion();
			PreparedStatement stmt = conexion.prepareStatement(TIPO_CUENTA))
		{
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				t.setId(rs.getInt("id"));
				t.setDescripcion(rs.getString("descripcion"));
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return t;
	}

}