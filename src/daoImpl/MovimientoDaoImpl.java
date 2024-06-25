package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.MovimientoDao;
import entidad.Movimiento;

public class MovimientoDaoImpl implements MovimientoDao{

	private static final String query = "SELECT * FROM bdbanco.movimiento WHERE id_Cuenta = ?";
	
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

}
