package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.PaisDao;
import entidad.Localidad;
import entidad.Pais;
import entidad.Provincia;

public class PaisDaoImpl implements PaisDao{

	private static final String SELECT_PAISES = "SELECT * FROM bdbanco.Pais";
	private static final String SELECT_PAIS = "SELECT * FROM bdbanco.Pais WHERE id = ?";
	@Override
	public ArrayList<Pais> traerPaises() {
		Connection con = Conexion.getConexion().getSQLConexion();
		ArrayList<Pais> paises = new ArrayList<>();
		PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
			stmt = con.prepareStatement(SELECT_PAISES);
			rs = stmt.executeQuery();
			while(rs.next()){
				
				Pais p = new Pais();
				p.setId(rs.getInt("id"));
				p.setPais(rs.getString("pais"));
				
				
				paises.add(p);
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
        return paises;
	}
	@Override
	public Pais obtenerPais(int pais) {
		Connection con = Conexion.getConexion().getSQLConexion();
		Pais p = new Pais();
		PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
			stmt = con.prepareStatement(SELECT_PAIS);
			stmt.setInt(1, pais);
			rs = stmt.executeQuery();
			while(rs.next()){
				p.setId(rs.getInt("id"));
				p.setPais(rs.getString("pais"));
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
        return p;
	}
	

}
