package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.ProvinciaDao;
import entidad.Provincia;

public class ProvinciaDaoImpl implements ProvinciaDao{
	
	private static final String SELECT_PROVINCIAS = "select * from bdbanco.provincia";
	private static final String SELECT_PROVINCIA = "select * from bdbanco.provincia WHERE id = ?";

	@Override
	public ArrayList<Provincia> obtenerProvinciasPorPais(int paisId) {
		Connection con = Conexion.getConexion().getSQLConexion();
		ArrayList<Provincia> Provincias = new ArrayList<>();
		PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
			stmt = con.prepareStatement(SELECT_PROVINCIAS);
			rs = stmt.executeQuery();
			
			while(rs.next()){
				
				Provincia pro = new Provincia();
				pro.setId(rs.getInt("id"));
				pro.setId_Pais(rs.getInt("pais"));
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

	@Override
	public Provincia obtenerProvincia(int pro) {
		Connection con = Conexion.getConexion().getSQLConexion();
		Provincia prov = new Provincia();
		PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
			stmt = con.prepareStatement(SELECT_PROVINCIA);
			stmt.setInt(1, pro);
			rs = stmt.executeQuery();
			
			while(rs.next()){
				prov.setId(rs.getInt("id"));
				prov.setId_Pais(rs.getInt("pais"));
				prov.setProvincia(rs.getString("provincia"));
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
        return prov;
	}

}
