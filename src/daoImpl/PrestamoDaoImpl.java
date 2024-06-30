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
	private static final String insert = "INSERT INTO prestamo(id_cuenta, cant_meses, fecha, capitalPedido, monto_mensual, monto_total) VALUES(?, ?, ?, ?, ?, ? )";
	private static final String PRESTAMOS_FECHA = "SELECT COUNT(*) AS total_prestamos FROM prestamo WHERE fecha BETWEEN ? AND ?";
	private static final String autorizPrest = "UPDATE prestamo SET peticion = ? WHERE id_Cuenta = ?";
	

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
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean esExitoso = false;
	    try {
	    	statement = conexion.prepareStatement(autorizPrest);  
	    	
	    	statement.setBoolean(1, autoriz);
	        statement.setInt(2, idCuenta); 
	        
	        	        	        
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
	    return esExitoso;
	}
	
}
