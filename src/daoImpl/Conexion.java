package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Conexion {
	public static Conexion instancia;
	private Connection con = null;
	
	private Conexion()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver"); // quitar si no es necesario
			this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdbanco","root","root");
			this.con.setAutoCommit(false);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public static Conexion getConexion()   
	{								
		if(instancia == null)
		{
			instancia = new Conexion();
		}
		return instancia;
	}

	public Connection getSQLConexion() 
	{
        try {
            if (this.con == null || this.con.isClosed()) {
            	//Esto vuelve a abrir la conexion si es que se cerro en algun momento
                this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdbanco", "root", "root");
                this.con.setAutoCommit(false);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.con;
	}
	

	public void cerrarConexion()
	{
		try 
		{
			if (this.con != null && !this.con.isClosed()) {
				this.con.close();
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		instancia = null;
	}
}
