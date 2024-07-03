package daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.PrestamoDao;
import entidad.Prestamo;

public class PrestamoDaoImpl implements PrestamoDao {
	private static final String insert = "INSERT INTO bdbanco.prestamo(id_cuenta, cant_meses, fecha, capitalPedido, montoMensual, montoTotal) VALUES(?, ?, ?, ?, ?, ? );";
	private static final String PRESTAMOS_FECHA = "SELECT COUNT(*) AS total_prestamos FROM bdbanco.prestamo WHERE fecha BETWEEN ? AND ?";
	private static final String autorizPrest = "UPDATE bdbanco.prestamo SET peticion = ? WHERE id_Cuenta = ?";
	private static final String traerPrestamos = "SELECT * FROM bdbanco.prestamo WHERE id_Cuenta = ? and peticion = 1 AND pagado = 0";

	@Override
	public boolean grabar(Prestamo prestamo) {
		PreparedStatement st;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
	    try {		        
		    st = conexion.prepareStatement(insert);
		    st.setInt(1, prestamo.getIdCuenta());
		    st.setInt(2, prestamo.getCantMeses());
		    st.setDate(3, new Date((new java.util.Date()).getTime()));
		    st.setFloat(4, prestamo.getPrestamo());
		    st.setFloat(5, prestamo.getMontoMensual());
		    st.setFloat(6, prestamo.getMontoTotal());

		    if (st.executeUpdate() > 0) {
		    	conexion.commit();
		    	isInsertExitoso = true;
		    }
		        
		    } catch (SQLException e) {
		    	System.out.println("Error: estoy en la bd [" + prestamo.getId() + "]");
				
		        e.printStackTrace();
		        try {
		            conexion.rollback();
		        } catch (SQLException e1) {
		        	System.out.println("Error: estoy en la bd2 [" + prestamo.getId() + "]");
		            e1.printStackTrace();
		        }
		    }
		    return isInsertExitoso;
		
	}

	@Override
	public ArrayList<Prestamo> traerTodos(int dni) {
		ArrayList<Prestamo> prestamos = new ArrayList<>();

		String sql = "SELECT * FROM bdbanco.prestamo p inner join cuenta cta on cta.id = p.id_cuenta inner join cliente cli on cli.dni = cta.dni where cli.dni = ?";
		try (Connection conexion = Conexion.getConexion().getSQLConexion();
				PreparedStatement stmt = conexion.prepareStatement(sql)) {

			stmt.setInt(1, dni);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Prestamo prestamo = new Prestamo();
				prestamo.setId(rs.getInt("id"));
                prestamo.setIdCuenta(rs.getInt("id_Cuenta"));
                prestamo.setCantMeses(rs.getInt("cant_Meses"));
                prestamo.setFecha(rs.getDate("fecha"));
                prestamo.setMontoMensual(rs.getFloat("montoMensual"));
                prestamo.setMontoTotal(rs.getFloat("montoTotal"));
                prestamo.setPagado(rs.getBoolean("pagado"));
                prestamo.setPeticion(rs.getBoolean("peticion"));

				prestamos.add(prestamo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error: No pudieron recuperar las cuentas del cliente [" + dni + "]");
		}
		
		if (prestamos.size() > 0) {
			CuentaDaoImpl ctaDao = new CuentaDaoImpl();
			
			for (Prestamo p: prestamos) {
				p.setCuenta(ctaDao.obtenerCuenta(p.getIdCuenta()));
			}
		}
		

		return prestamos;
	}
	
	
	public ArrayList<Prestamo> traerPendientes() {
		ArrayList<Prestamo> prestamos = new ArrayList<>();

		String sql = "SELECT * FROM prestamo WHERE peticion IS NULL";
		try (Connection conexion = Conexion.getConexion().getSQLConexion();
				PreparedStatement stmt = conexion.prepareStatement(sql)) {


			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Prestamo prestamo = new Prestamo();
				prestamo.setId(rs.getInt("id"));
                prestamo.setIdCuenta(rs.getInt("id_Cuenta"));
                prestamo.setCantMeses(rs.getInt("cant_Meses"));
                prestamo.setFecha(rs.getDate("fecha"));
                prestamo.setMontoMensual(rs.getFloat("montoMensual"));
                prestamo.setMontoTotal(rs.getFloat("montoTotal"));
                prestamo.setPagado(rs.getBoolean("pagado"));
                prestamo.setPeticion(rs.getBoolean("peticion"));
                
				prestamos.add(prestamo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error al traer de la base de datos");
		}
						
		return prestamos;
	}

	@Override
	public int obtenerPrestamosEntre(java.util.Date inicio, java.util.Date fin) {
		int cantidad = 0;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conexion.prepareStatement(PRESTAMOS_FECHA);
			stmt.setDate(1, new java.sql.Date(inicio.getTime()));
	        stmt.setDate(2, new java.sql.Date(fin.getTime()));
		    rs = stmt.executeQuery();
		        if (rs.next()) {
		            cantidad = rs.getInt("total_prestamos");
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
	public boolean autortizacionPrestamo(int idCuenta, boolean autoriz) {
	    PreparedStatement statement = null;
	    Connection conexion = Conexion.getConexion().getSQLConexion();
	    boolean esExitoso = false;

	    try {
	        // Iniciar una transacción
	        conexion.setAutoCommit(false);

	        // Actualizar la tabla prestamo para establecer peticion a 1
	        statement = conexion.prepareStatement("UPDATE prestamo SET peticion = ? WHERE id_Cuenta = ?");
	        statement.setBoolean(1, autoriz);
	        statement.setInt(2, idCuenta);

	        int updatePrestamo = statement.executeUpdate();
	        if (updatePrestamo > 0) {
	            // Obtener el capitalPedido del prestamo para actualizar la cuenta
	            statement = conexion.prepareStatement("SELECT capitalPedido FROM prestamo WHERE id_Cuenta = ?");
	            statement.setInt(1, idCuenta);

	            ResultSet rs = statement.executeQuery();
	            if (rs.next()) {
	                float capitalPedido = rs.getFloat("capitalPedido");

	                // Actualizar el saldo de la cuenta sumando el capitalPedido
	                statement = conexion.prepareStatement("UPDATE cuenta SET saldo = saldo + ? WHERE id = ?");
	                statement.setFloat(1, capitalPedido);
	                statement.setInt(2, idCuenta);

	                int updateCuenta = statement.executeUpdate();
	                if (updateCuenta > 0) {
	                    conexion.commit();
	                    esExitoso = true;
	                } else {
	                    conexion.rollback();
	                }
	            } else {
	                conexion.rollback();
	            }
	        } else {
	            conexion.rollback();
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
	            if (statement != null) statement.close();
	            conexion.setAutoCommit(true);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return esExitoso;
	}


	@Override
	public ArrayList<Prestamo> prestamosXCapital(float minimo, float maximo) {
		ArrayList<Prestamo> prestamos = new ArrayList<>();
		
		String sql = "SELECT * FROM prestamo WHERE  montoTotal> ? AND montoTotal< ? AND peticion IS NULL ";
		try {
				Connection conexion = Conexion.getConexion().getSQLConexion();
				PreparedStatement stmt = conexion.prepareStatement(sql); 
				stmt.setFloat(1, minimo);
				stmt.setFloat(2, maximo);
				
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Prestamo prestamo = new Prestamo();
				prestamo.setId(rs.getInt("id"));
                prestamo.setIdCuenta(rs.getInt("id_Cuenta"));
                prestamo.setCantMeses(rs.getInt("cant_Meses"));
                prestamo.setFecha(rs.getDate("fecha"));
                prestamo.setMontoMensual(rs.getFloat("montoMensual"));
                prestamo.setMontoTotal(rs.getFloat("montoTotal"));
                prestamo.setPagado(rs.getBoolean("pagado"));
                prestamo.setPeticion(rs.getBoolean("peticion"));
                
				prestamos.add(prestamo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
						
		return prestamos;
	}

	@Override
	public ArrayList<Prestamo> traerPrestamos(int idCuenta) { //TODO
		ArrayList<Prestamo> prestamos = new ArrayList<>();
		
		try {
				Connection conexion = Conexion.getConexion().getSQLConexion();
				PreparedStatement stmt = conexion.prepareStatement(traerPrestamos); 
				stmt.setInt(1, idCuenta);
				
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Prestamo prestamo = new Prestamo();
				prestamo.setId(rs.getInt("id"));
                prestamo.setIdCuenta(rs.getInt("id_Cuenta"));
                prestamo.setCantMeses(rs.getInt("cant_Meses"));
                prestamo.setFecha(rs.getDate("fecha"));
                prestamo.setMontoMensual(rs.getFloat("montoMensual"));
                prestamo.setMontoTotal(rs.getFloat("montoTotal"));
                prestamo.setPagado(rs.getBoolean("pagado"));
                prestamo.setPeticion(rs.getBoolean("peticion"));
				prestamos.add(prestamo);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error al traer de la base de datos");
		}
						
		return prestamos;

	}
	
}
