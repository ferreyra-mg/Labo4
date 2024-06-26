package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.PaisDao;

public class PaisDaoImpl implements PaisDao{

	private static final String SELECT_PAISES = "SELECT * FROM bdbanco.Pais";
	@Override
	public ArrayList<String> traerPaises() {
		Connection con = Conexion.getConexion().getSQLConexion();
		ArrayList<String> paises = new ArrayList<>();
		PreparedStatement stmt = null;
        ResultSet rs = null;
		
        try {
			stmt = con.prepareStatement(SELECT_PAISES);
			rs = stmt.executeQuery();
			
			while(rs.next()){
				
				paises.add(rs.getString("pais"));
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

}
