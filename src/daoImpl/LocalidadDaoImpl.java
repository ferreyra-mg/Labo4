package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.LocalidadDao;
import entidad.Localidad;

public class LocalidadDaoImpl implements LocalidadDao {
	
	private static final String SELECT_LOCALIDADES = "select * from bdbanco.localidad";
	private static final String SELECT_LOCALIDAD = "select * from bdbanco.localidad WHERE id= ?";

	@Override
	public ArrayList<Localidad> obtenerLocalidadesPorProvincia(int provinciaId) {
		Connection con = Conexion.getConexion().getSQLConexion();
		ArrayList<Localidad> Localidades = new ArrayList<>();
		PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
			stmt = con.prepareStatement(SELECT_LOCALIDADES);
			rs = stmt.executeQuery();
			
			while(rs.next()){
				
				Localidad pro = new Localidad();
				pro.setId(rs.getInt("id"));
				pro.setId_Provincia(rs.getInt("provincia"));
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

	@Override
	public Localidad obtenerLocalidad(int loc) {
		Connection con = Conexion.getConexion().getSQLConexion();
		Localidad pro = new Localidad();
		PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
			stmt = con.prepareStatement(SELECT_LOCALIDAD);
			stmt.setInt(1,loc);
			rs = stmt.executeQuery();
			
			while(rs.next()){
				pro.setId(rs.getInt("id"));
				pro.setId_Provincia(rs.getInt("provincia"));
				pro.setLocalidad(rs.getString("localidad"));
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
        return pro;
	}

}
