package daoImpl;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import dao.MovimientoDao;
import entidad.Cuenta;
import entidad.Movimiento;

public class MovimientoDaoImpl implements MovimientoDao{

	private static final String query = "SELECT * FROM bdbanco.movimiento WHERE id_Cuenta = ?";
	private static final String sqlSaldo = "SELECT saldo FROM bdbanco.cuenta WHERE usuario = ?";
	
	private static final String sqlResta = "UPDATE bdbanco.cuenta SET saldo = saldo - ? WHERE usuario = ? and tipoCuenta = ?";
	private static final String sqlSuma = "UPDATE bdbanco.cuenta SET saldo = saldo + ? WHERE cbu = ?";
	private static final String sqlMovimiento = "INSERT INTO bdbanco.movimiento (id_Cuenta, fecha, concepto, importe, tipo) VALUES (?, ?, ?, ?, ?)";
	private static final String sqlCbu = "SELECT cbu FROM bdbanco.cuenta WHERE usuario = ? and tipoCuenta = ?";
	
	private static final String sqlRestaCuenta = "UPDATE bdbanco.cuenta SET saldo = saldo - ? WHERE cbu = ?";
	private static final String sqlSumaCuenta = "UPDATE bdbanco.cuenta SET saldo = saldo + ? WHERE cbu = ?";
	
	private static final String sqlMontoFecha = "SELECT SUM(importe) AS total_importe FROM movimiento WHERE fecha BETWEEN ? AND ?";
	
	private static final String sqlSaldoxCuenta = "SELECT saldo FROM bdbanco.cuenta WHERE usuario = ? and tipoCuenta = ?";
	private static final String sqlMovimientosFecha = "SELECT COUNT(*) AS total_movimientos FROM movimiento WHERE fecha BETWEEN ? AND ?;";

	
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
	public boolean TransferirCbu(Cuenta cuenta, String cbuDestino, float monto, String tipoCuenta) {
		
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
			stmtDebito.setString(3, tipoCuenta);
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
	public String ObtenerCbu(String tipoCuenta, Cuenta cuenta) {
		Connection conexion = Conexion.getConexion().getSQLConexion();
		PreparedStatement stmtCbu = null;
		ResultSet rs = null;
		String cbu = "";
		
		try {
			stmtCbu = conexion.prepareStatement(sqlCbu);
			stmtCbu.setString(1, cuenta.getUsuario());
			stmtCbu.setString(2, tipoCuenta);
			rs = stmtCbu.executeQuery();
			
			if(rs.next()) {
				cbu = rs.getString("cbu");
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error 1");
		}
		
		
		return cbu;
	}

	@Override
	public boolean TransferirEntreCuentas(Cuenta cuenta, String cbuDestino, String cbuEmisor, float monto) {
		Connection conexion = Conexion.getConexion().getSQLConexion();
		PreparedStatement stmtRestaCuenta;
		PreparedStatement stmtSumaCuenta;
		PreparedStatement stmtMov;
		boolean exitoResta = false;
		boolean exitoSuma = false;
		boolean exitoMov = false;
		boolean exito = false;
		
		try {
			stmtRestaCuenta = conexion.prepareStatement(sqlRestaCuenta);
			stmtRestaCuenta.setFloat(1, monto);
			stmtRestaCuenta.setString(2, cbuEmisor);
			if(stmtRestaCuenta.executeUpdate() > 0) {
				conexion.commit();
				exitoResta = true;
			}
			
			stmtSumaCuenta = conexion.prepareStatement(sqlSumaCuenta);
			stmtSumaCuenta.setFloat(1, monto);
			stmtSumaCuenta.setString(2, cbuDestino);
			if(stmtSumaCuenta.executeUpdate() > 0) {
				conexion.commit();
				exitoSuma = true;
			}
			
			if(exitoResta == true && exitoSuma == true) {
				stmtMov = conexion.prepareStatement(sqlMovimiento);
				stmtMov.setInt(1, cuenta.getId());
				stmtMov.setDate(2, new java.sql.Date(System.currentTimeMillis()));
				stmtMov.setString(3, "Transferencia entre cuentas");
				stmtMov.setFloat(4, monto);
				stmtMov.setString(5, "Transferencia");
				if(stmtMov.executeUpdate() > 0) {
					conexion.commit();
					exitoMov = true;
				}
				
			}
			
			if(exitoResta == true && exitoSuma == true && exitoMov == true) {
				exito = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error 2");
		}
		
		return exito;
	}


	@Override
	public float VerificarSaldoxCuenta(String usuario, String tipoCuenta) {
		Connection conexion = Conexion.getConexion().getSQLConexion();
		PreparedStatement stmtSaldo = null;
		ResultSet rs = null;
		float saldoxCuenta = 0;
		
		try {
			stmtSaldo = conexion.prepareStatement(sqlSaldoxCuenta);
			stmtSaldo.setString(1, usuario);
			stmtSaldo.setString(2, tipoCuenta);
			rs = stmtSaldo.executeQuery();
			
			if(rs.next()) {
				saldoxCuenta = rs.getFloat("saldo");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return saldoxCuenta;
	}

	@Override
	public float obtenerMontoEntre(Date inicial, Date fin) {
		float totalImporte = 0;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conexion.prepareStatement(sqlMontoFecha);
			stmt.setDate(1, new java.sql.Date(inicial.getTime()));
	        stmt.setDate(2, new java.sql.Date(fin.getTime()));
		    rs = stmt.executeQuery();
		        if (rs.next()) {
		            totalImporte = rs.getFloat("total_importe");
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
		return totalImporte;
	}
	
	@Override
	public int obtenerMovimientosEntre(Date inicial, Date fin)
	{
		int cantidad = 0;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conexion.prepareStatement(sqlMovimientosFecha);
			stmt.setDate(1, new java.sql.Date(inicial.getTime()));
	        stmt.setDate(2, new java.sql.Date(fin.getTime()));
		    rs = stmt.executeQuery();
		        if (rs.next()) {
		            cantidad = rs.getInt("total_movimientos");
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

}
