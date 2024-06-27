package daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.MovimientoDao;
import entidad.Cuenta;
import entidad.Movimiento;

public class MovimientoDaoImpl implements MovimientoDao{

	private static final String query = "SELECT * FROM bdbanco.movimiento WHERE id_Cuenta = ?";
	
	private static final String sqlSaldo = "SELECT saldo FROM bdbanco.cuenta WHERE usuario = ?";
	private static final String sqlResta = "UPDATE bdbanco.cuenta SET saldo = saldo - ? WHERE usuario = ?";
	private static final String sqlSuma = "UPDATE bdbanco.cuenta SET saldo = saldo + ? WHERE cbu = ?";
	private static final String sqlMovimiento = "INSERT INTO bdbanco.movimiento (id_Cuenta, fecha, concepto, importe, tipo) VALUES (?, ?, ?, ?, ?)";
	
	@Override
	public ArrayList<Movimiento> traerMovimientos(int id) {
		
		Connection con = Conexion.getConexion().getSQLConexion();
		ArrayList<Movimiento> listMovimientos = new ArrayList<>();
		PreparedStatement stmt = null;
        ResultSet rs = null;
		
        try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			
			while(rs.next()){	
				Movimiento mv = new Movimiento();
				
				mv.setId(rs.getInt("id"));
				mv.setDetalle(rs.getString("concepto"));
				mv.setFecha(rs.getDate("fecha"));
				mv.setImporte(rs.getFloat("importe"));
				mv.setMovimiento(rs.getString("tipo"));
				listMovimientos.add(mv);
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
        
        return listMovimientos;
	}

	@Override
	public boolean TransferirCbu(Cuenta cuenta, String cbuDestino, float monto) {
		
		Connection conexion = Conexion.getConexion().getSQLConexion();
		PreparedStatement stmtDebito;
		PreparedStatement stmtCredito;
		PreparedStatement stmtMov;
		boolean exitoDebito = false;
		boolean exitoCredito = false;
		boolean exitoMov = false;
		boolean exito = false;
        
		try {
			stmtDebito = conexion.prepareStatement(sqlResta);
			stmtDebito.setFloat(1, monto);
			stmtDebito.setString(2, cuenta.getUsuario());
			if(stmtDebito.executeUpdate() > 0) {
				conexion.commit();
				exitoDebito = true;
			}
			
			
			stmtCredito = conexion.prepareStatement(sqlSuma);
			stmtCredito.setFloat(1, monto);
			stmtCredito.setString(2, cbuDestino);
			if(stmtCredito.executeUpdate() > 0) {
				conexion.commit();
				exitoCredito = true;
			}
			
			if(exitoDebito == true && exitoCredito == true) {
				stmtMov = conexion.prepareStatement(sqlMovimiento);
				stmtMov.setInt(1, cuenta.getId());
				stmtMov.setDate(2, new java.sql.Date(System.currentTimeMillis()));
				stmtMov.setString(3, "Transferencia");
				stmtMov.setFloat(4, monto);
				stmtMov.setString(5, "Transferencia");
				if(stmtMov.executeUpdate() > 0) {
					conexion.commit();
					exitoMov = true;
				}
				
			}
			
			if(exitoDebito == true && exitoCredito == true && exitoMov == true) {
				exito = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return exito;
	}

	@Override
	public float VerificarSaldo(String usuario) {
		Connection conexion = Conexion.getConexion().getSQLConexion();
		PreparedStatement stmtSaldo = null;
		ResultSet rs = null;
		float saldoActual = 0;
		
		try {
			stmtSaldo = conexion.prepareStatement(sqlSaldo);
			stmtSaldo.setString(1, usuario);
			rs = stmtSaldo.executeQuery();
			
			if(rs.next()) {
				saldoActual = rs.getFloat("saldo");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		

		return saldoActual;
	}

}
