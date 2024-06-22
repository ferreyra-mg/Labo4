package dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import dao.PrestamoDao;
import daoImpl.Conexion;
import entidad.Cliente;
import entidad.Cuenta;
import entidad.Prestamo;

public class PrestamoDaoImpl implements PrestamoDao {
	private static final String insert = "INSERT INTO prestamo(id_cuenta, cant_meses, fecha, capital_pedido, monto_mensual, monto_total)"
			+ " VALUES(?, ?, ?, ?, ?, ?)";
	
	@Override
	public boolean grabar(Prestamo prestamo) {
		
			PreparedStatement statement;
			Connection conexion = Conexion.getConexion().getSQLConexion();
			boolean isInsertExitoso = false;
		    try {

		        
		    	statement = conexion.prepareStatement(insert);
		    	
		        statement.setInt(1, prestamo.getIdCuenta()); 
		        
      
		        if (statement.executeUpdate() > 0) {
		            conexion.commit();
		            isInsertExitoso = true;
		        }
		        
		    } catch (SQLException e) {
		        e.printStackTrace();
		        try {
		            conexion.rollback();
		        } catch (SQLException e1) {
		            e1.printStackTrace();
		        }
		    }
		    return isInsertExitoso;
		
	}
}
