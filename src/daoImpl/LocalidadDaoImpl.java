package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.LocalidadDao;
import entidad.Localidad;

public class LocalidadDaoImpl implements LocalidadDao {
	
	private static final String SELECT_LOCALIDADES = "select lo.id, lo.localidad from bdbanco.provincia pro\r\n" + 
			"join localidad lo on pro.id = lo.provincia\r\n" + 
			"where pro.id = ?";

	@Override
	public ArrayList<Localidad> obtenerLocalidadesPorProvincia(int provinciaId) {
		Connection con = Conexion.getConexion().getSQLConexion();
		ArrayList<Localidad> Localidades = new ArrayList<>();
		PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
			stmt = con.prepareStatement(SELECT_LOCALIDADES);
			stmt.setInt(1, provinciaId);
			rs = stmt.executeQuery();
			
			while(rs.next()){
				
				Localidad pro = new Localidad();
				pro.setId(rs.getInt("id"));
				pro.setLocalidad(rs.getString("localidad"));
				
				
				Localidades.add(pro);
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
        return Localidades;
	}

}
