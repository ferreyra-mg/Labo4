package daoImpl;

import entidad.Administrador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.AdministradorDao;

public class AdministradorDaoImpl implements AdministradorDao {

	@Override
	public Administrador logear(String user, String psw) {
		 String sql = "SELECT * FROM administrador WHERE usuario = ? AND contrasena = ?";
	        try (Connection conexion = Conexion.getConexion().getSQLConexion();
	             PreparedStatement stmt = conexion.prepareStatement(sql)) {
	            
	            stmt.setString(1, user);
	            stmt.setString(2, psw);
	            ResultSet rs = stmt.executeQuery();
	            
	            if (rs.next()) {
	                Administrador administrador = new Administrador();
	                administrador.setID(rs.getInt("id"));
	                administrador.setUsuario(rs.getString("usuario"));
	                administrador.setContrasena(rs.getString("contrasena"));
	                return administrador;
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            System.out.println("error aca admin");
	        }
	        return null;
	}
}
