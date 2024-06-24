package daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.PrestamoDao;
import entidad.Cuenta;
import entidad.Prestamo;

public class PrestamoDaoImpl implements PrestamoDao {
	private static final String insert = "INSERT INTO prestamo(id_cuenta, cant_meses, fecha, capital_pedido, monto_mensual, monto_total,peticion) VALUES(?, ?, ?, ?, ?, ?, 0)";
	
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
		    	System.out.print(e.getMessage());
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
	public ArrayList<Prestamo> traerTodos(int dni) {
		ArrayList<Prestamo> prestamos = new ArrayList<>();

		String sql = "SELECT p.id, p.capital_pedido, p.id_cuenta, p.cant_meses, p.monto_mensual, p.monto_total, p.pagado, p.peticion, p.fecha FROM bdbanco.prestamo p inner join cuenta cta on cta.id = p.id_cuenta inner join cliente cli on cli.dni = cta.dni where cli.dni = ?";
		try (Connection conexion = Conexion.getConexion().getSQLConexion();
				PreparedStatement stmt = conexion.prepareStatement(sql)) {

			stmt.setInt(1, dni);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Prestamo prestamo = new Prestamo(rs.getInt("id"), rs.getFloat("capital_pedido"), rs.getInt("id_cuenta"), rs.getInt("cant_meses"), rs.getFloat("monto_mensual"),
						rs.getFloat("monto_total"), rs.getBoolean("pagado"), rs.getBoolean("peticion"), rs.getDate("fecha"));

				prestamos.add(prestamo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error: No pudieron recuperar los prestamos del cliente [" + dni + "]");
		}
		
		if (prestamos.size() > 0) {
			CuentaDaoImpl ctaDao = new CuentaDaoImpl();
			CuotaDaoImpl cuotaDao = new CuotaDaoImpl();
			
			for (Prestamo p: prestamos) {
				p.setCuenta(ctaDao.obtenerCuenta(p.getIdCuenta()));
				p.setCoutas(cuotaDao.traerCuotas(p.getId()));
			}
		}
		

		return prestamos;
	}

	@Override
	public Prestamo traerPrestamo(int id) {
		Prestamo prestamo = null;

		String sql = "SELECT p.id, p.capital_pedido, p.id_cuenta, p.cant_meses, p.monto_mensual, p.monto_total, p.pagado, p.peticion, p.fecha FROM bdbanco.prestamo p where p.id = ?";
		try (Connection conexion = Conexion.getConexion().getSQLConexion();
				PreparedStatement stmt = conexion.prepareStatement(sql)) {

			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				prestamo = new Prestamo(rs.getInt("id"), rs.getFloat("capital_pedido"), rs.getInt("id_cuenta"), rs.getInt("cant_meses"), rs.getFloat("monto_mensual"),
						rs.getFloat("monto_total"), rs.getBoolean("pagado"), rs.getBoolean("peticion"), rs.getDate("fecha"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error: No pudieron recuperar los datos del Prestamo [" + id + "]");
		}
		
		if (prestamo != null ) {
			CuentaDaoImpl ctaDao = new CuentaDaoImpl();
			CuotaDaoImpl cuotaDao = new CuotaDaoImpl();
			
			prestamo.setCuenta(ctaDao.obtenerCuenta(prestamo.getIdCuenta()));
			prestamo.setCoutas(cuotaDao.traerCuotas(prestamo.getId()));
		}

		return prestamo;
	}
}
