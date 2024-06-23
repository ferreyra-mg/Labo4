package daoImpl;



import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

import dao.PrestamoDao;
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
}
