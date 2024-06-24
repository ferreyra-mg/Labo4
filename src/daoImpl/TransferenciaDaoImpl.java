package daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.TransferenciaDao;

public class TransferenciaDaoImpl implements TransferenciaDao {
	


	@Override
	public boolean TransferirCbu(String usuario, String cbuDestino, double monto) throws SQLException {
		
		 String sqlSaldo = "SELECT saldo FROM cuenta WHERE usuario = ?";
	     String sqlDebito = "UPDATE cuenta SET saldo = saldo - ? WHERE usuario = ?";
	     String sqlCredito = "UPDATE cuenta SET saldo = saldo + ? WHERE cbu = ?";
	     String sqlMovimiento = "INSERT INTO movimiento (id_Cuenta, fecha, concepto, importe, tipo) VALUES (?, ?, ?, ?, ?)";
		
		Connection conexion = Conexion.getConexion().getSQLConexion();

	        try (PreparedStatement stmtSaldo = conexion.prepareStatement(sqlSaldo);
	             PreparedStatement stmtDebito = conexion.prepareStatement(sqlDebito);
	             PreparedStatement stmtCredito = conexion.prepareStatement(sqlCredito);
	             PreparedStatement stmtMovimiento = conexion.prepareStatement(sqlMovimiento)) {

	            stmtSaldo.setString(1, usuario);
	            ResultSet rsSaldo = stmtSaldo.executeQuery();
	            if (rsSaldo.next()) {
	                double saldoActual = rsSaldo.getDouble("saldo");
	                if (saldoActual < monto) {
	                    return false;
	                }
	            } else {
	                return false;
	            }

	            stmtDebito.setDouble(1, monto);
	            stmtDebito.setString(2, usuario);
	            stmtDebito.executeUpdate();

	            stmtCredito.setDouble(1, monto);
	            stmtCredito.setString(2, cbuDestino);
	            stmtCredito.executeUpdate();

	            stmtMovimiento.setInt(1, obtenerIdCuenta(conexion, usuario));
	            stmtMovimiento.setDate(2, new Date(System.currentTimeMillis()));
	            stmtMovimiento.setString(3, "Transferencia a CBU: " + cbuDestino);
	            stmtMovimiento.setDouble(4, -monto);
	            stmtMovimiento.setString(5, "Débito");
	            stmtMovimiento.executeUpdate();

	            stmtMovimiento.setInt(1, obtenerIdCuentaPorCbu(conexion, cbuDestino));
	            stmtMovimiento.setDate(2, new Date(System.currentTimeMillis()));
	            stmtMovimiento.setString(3, "Transferencia desde: " + usuario);
	            stmtMovimiento.setDouble(4, monto);
	            stmtMovimiento.setString(5, "Crédito");
	            stmtMovimiento.executeUpdate();

	            return true;
	        }
	}

	@Override
	public boolean TransferirCuenta(String usuario, int idCuentaDestino, double monto) throws SQLException{
		 String sqlSaldo = "SELECT saldo FROM cuenta WHERE usuario = ? AND id != ?";
	        String sqlDebito = "UPDATE cuenta SET saldo = saldo - ? WHERE usuario = ? AND id != ?";
	        String sqlCredito = "UPDATE cuenta SET saldo = saldo + ? WHERE usuario = ? AND id = ?";
	        String sqlMovimiento = "INSERT INTO movimiento (id_Cuenta, fecha, concepto, importe, tipo) VALUES (?, ?, ?, ?, ?)";
	        
	        Connection conexion = Conexion.getConexion().getSQLConexion();

	        try (PreparedStatement stmtSaldo = conexion.prepareStatement(sqlSaldo);
	             PreparedStatement stmtDebito = conexion.prepareStatement(sqlDebito);
	             PreparedStatement stmtCredito = conexion.prepareStatement(sqlCredito);
	             PreparedStatement stmtMovimiento = conexion.prepareStatement(sqlMovimiento)) {

	            stmtSaldo.setString(1, usuario);
	            stmtSaldo.setInt(2, idCuentaDestino);
	            ResultSet rsSaldo = stmtSaldo.executeQuery();
	            if (rsSaldo.next()) {
	                double saldoActual = rsSaldo.getDouble("saldo");
	                if (saldoActual < monto) {
	                    return false;
	                }
	            } else {
	                return false;
	            }

	            stmtDebito.setDouble(1, monto);
	            stmtDebito.setString(2, usuario);
	            stmtDebito.setInt(3, idCuentaDestino);
	            stmtDebito.executeUpdate();

	            stmtCredito.setDouble(1, monto);
	            stmtCredito.setString(2, usuario);
	            stmtCredito.setInt(3, idCuentaDestino);
	            stmtCredito.executeUpdate();

	            stmtMovimiento.setInt(1, obtenerIdCuenta(conexion, usuario));
	            stmtMovimiento.setDate(2, new Date(System.currentTimeMillis()));
	            stmtMovimiento.setString(3, "Transferencia a cuenta propia: " + idCuentaDestino);
	            stmtMovimiento.setDouble(4, -monto);
	            stmtMovimiento.setString(5, "Débito");
	            stmtMovimiento.executeUpdate();

	            stmtMovimiento.setInt(1, idCuentaDestino);
	            stmtMovimiento.setDate(2, new Date(System.currentTimeMillis()));
	            stmtMovimiento.setString(3, "Transferencia desde cuenta propia");
	            stmtMovimiento.setDouble(4, monto);
	            stmtMovimiento.setString(5, "Crédito");
	            stmtMovimiento.executeUpdate();

	            return true;
	        }
	}
	
	 private int obtenerIdCuenta(Connection conn, String usuario) throws SQLException {
	        String sql = "SELECT id FROM cuenta WHERE usuario = ?";
	        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setString(1, usuario);
	            ResultSet rs = stmt.executeQuery();
	            if (rs.next()) {
	                return rs.getInt("id");
	            }
	            
	        }
			return 0;
	    }
	 
	 private int obtenerIdCuentaPorCbu(Connection conn, String cbu) throws SQLException {
	        String sql = "SELECT id FROM cuenta WHERE cbu = ?";
	        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setString(1, cbu);
	            ResultSet rs = stmt.executeQuery();
	            if (rs.next()) {
	                return rs.getInt("id");
	            }
	            throw new SQLException("Cuenta no encontrada para el CBU: " + cbu);
	        }
	    }

}
