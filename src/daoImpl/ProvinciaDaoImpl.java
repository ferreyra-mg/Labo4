package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.ProvinciaDao;
import entidad.Provincia;

public class ProvinciaDaoImpl implements ProvinciaDao{
	
	private static final String SELECT_PROVINCIAS = "select pro.id, pro.provincia from bdbanco.pais pa\r\n" + 
			"join provincia pro on pa.id = pro.pais\r\n" + 
			"where pa.id = ?";

	@Override
	public ArrayList<Provincia> obtenerProvinciasPorPais(int paisId) {
		Connection con = Conexion.getConexion().getSQLConexion();
		ArrayList<Provincia> Provincias = new ArrayList<>();
		PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
			stmt = con.prepareStatement(SELECT_PROVINCIAS);
			stmt.setInt(1, paisId);
			rs = stmt.executeQuery();
			
			while(rs.next()){
				
				Provincia pro = new Provincia();
				pro.setId(rs.getInt("id"));
				pro.setProvincia(rs.getString("provincia"));
				
				
				Provincias.add(pro);
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
        return Provincias;
	}

}
