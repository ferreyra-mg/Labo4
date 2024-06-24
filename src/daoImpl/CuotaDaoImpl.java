package daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.CuotaDao;
import dao.PrestamoDao;
import entidad.Cuenta;
import entidad.Cuota;
import entidad.Prestamo;

public class CuotaDaoImpl implements CuotaDao {
	private static final String insert = "INSERT INTO prestamo(id_cuenta, cant_meses, fecha, capital_pedido, monto_mensual, monto_total,peticion) VALUES(?, ?, ?, ?, ?, ?, 0)";
	

	@Override
	public ArrayList<Cuota> traerCuotas(int idPrestamo) {
		ArrayList<Cuota> cuotas = new ArrayList<>();

		String sql = "SELECT id, id_prestamo, pagado, fecha FROM cuota where id_prestamo = ? order by id";
		try (Connection conexion = Conexion.getConexion().getSQLConexion();
				PreparedStatement stmt = conexion.prepareStatement(sql)) {

			stmt.setInt(1, idPrestamo);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Cuota cuota = new Cuota(rs.getInt("id"), rs.getInt("id_prestamo"), rs.getFloat("pagado"), rs.getDate("fecha"));

				cuotas.add(cuota);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error: No pudieron recuperar las cuotas del Prestamo [" + idPrestamo + "]");
		}

		return cuotas;
	}


	@Override
	public boolean grabar(Cuota cuota) {
		PreparedStatement st;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
	    try {

	    	st = conexion.prepareStatement("INSERT INTO cuota (id_prestamo, pagado, fecha) VALUES (?,?,?)");
	    			    	
	        st.setInt(1, cuota.getId());
	        st.setFloat(2, cuota.getPagado());
	        st.setDate(3, new Date(cuota.getFecha().getTime()));
	
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
}
