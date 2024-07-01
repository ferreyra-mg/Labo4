package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.CuotaDao;

public class CuotaDaoImpl implements CuotaDao{

	private static final String GET_MONTO_MENSUAL_SQL = 
	        "SELECT montoMensual FROM prestamo WHERE id = ?";
	    
	    private static final String INSERT_CUOTA_SQL = 
	        "INSERT INTO cuota (id_prestamo, pagado, fecha) VALUES (?, ?, CURDATE())";
	    
	    private static final String CHECK_AND_UPDATE_PRESTAMO_SQL = 
	        "UPDATE prestamo p " +
	        "SET p.pagado = 1 " +
	        "WHERE p.id = ? AND (" +
	        "  SELECT SUM(c.pagado) " +
	        "  FROM cuota c " +
	        "  WHERE c.id_prestamo = ?" +
	        ") >= p.montoTotal";

	    @Override
	    public boolean pagarCuota(int idPrestamo) {
	        Connection conexion = Conexion.getConexion().getSQLConexion();
	        PreparedStatement stmtGetMontoMensual = null;
	        PreparedStatement stmtInsertCuota = null;
	        PreparedStatement stmtCheckAndUpdate = null;
	        boolean exito = false;
	        float montoMensual = 0;

	        try {
	            conexion.setAutoCommit(false);
	            
	            // Obtener el montoMensual del préstamo
	            stmtGetMontoMensual = conexion.prepareStatement(GET_MONTO_MENSUAL_SQL);
	            stmtGetMontoMensual.setInt(1, idPrestamo);
	            ResultSet rs = stmtGetMontoMensual.executeQuery();
	            if (rs.next()) {
	                montoMensual = rs.getFloat("montoMensual");
	            }

	            // Insertar la nueva cuota
	            stmtInsertCuota = conexion.prepareStatement(INSERT_CUOTA_SQL);
	            stmtInsertCuota.setInt(1, idPrestamo);
	            stmtInsertCuota.setFloat(2, montoMensual);
	            stmtInsertCuota.executeUpdate();
	            
	            // Verificar y actualizar el estado del préstamo
	            stmtCheckAndUpdate = conexion.prepareStatement(CHECK_AND_UPDATE_PRESTAMO_SQL);
	            stmtCheckAndUpdate.setInt(1, idPrestamo);
	            stmtCheckAndUpdate.setInt(2, idPrestamo);
	            stmtCheckAndUpdate.executeUpdate();
	            
	            conexion.commit();
	            exito = true;
	        } catch (SQLException e) {
	            if (conexion != null) {
	                try {
	                    conexion.rollback();
	                } catch (SQLException ex) {
	                    ex.printStackTrace();
	                }
	            }
	            e.printStackTrace();
	        } finally {
	            try {
	                if (stmtGetMontoMensual != null) stmtGetMontoMensual.close();
	                if (stmtInsertCuota != null) stmtInsertCuota.close();
	                if (stmtCheckAndUpdate != null) stmtCheckAndUpdate.close();
	                if (conexion != null) conexion.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        return exito;
	    }

}
