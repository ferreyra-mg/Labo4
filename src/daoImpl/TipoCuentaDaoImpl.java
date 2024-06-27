package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.TipoCuentaDao;

public class TipoCuentaDaoImpl implements TipoCuentaDao{
	
	private static final String SELECT_TIPOS = "SELECT * FROM bdbanco.tipo_cuenta";
	
	@Override
	public ArrayList<String> traerTipo()
	{
		Connection con = Conexion.getConexion().getSQLConexion();
		ArrayList<String> tipos = new ArrayList<>();
		PreparedStatement stmt = null;
        ResultSet rs = null;
		
        try {
			stmt = con.prepareStatement(SELECT_TIPOS);
			rs = stmt.executeQuery();
			
			while(rs.next()){
				tipos.add(rs.getString("descripcion"));
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
        
        return tipos;
	}
}
