package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import dao.CuentaDao;
import entidad.Cuenta;

public class CuentaDaoImpl implements CuentaDao {

	private static final String CUENTA_DNI = "SELECT * FROM bdbanco.cuenta where dni = ?";
	private static final String CUENTA_CLIENTE= "SELECT * FROM bdbanco.cuenta where id = ?";
	@Override
	public void crearCuenta(Cuenta cuenta) {
		// TODO Auto-generated method stub
	}

	@Override
	public void modificarCuenta(Cuenta cuenta) {
		// TODO Auto-generated method stub

	}

	@Override
	public void cambiarEstadoCuenta(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<Cuenta> obtenerTodasLasCuentas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Cuenta> obtenerTodasLasCuentas(int dni) {
		ArrayList<Cuenta> cuentas = new ArrayList<>();
		try (Connection conexion = Conexion.getConexion().getSQLConexion();
				PreparedStatement stmt = conexion.prepareStatement(CUENTA_CLIENTE)) {
			stmt.setInt(1, dni);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Cuenta cuenta = new Cuenta(rs.getInt("id"),rs.getString("usuario"), rs.getInt("dni"),
						rs.getString("cbu"), rs.getDate("fechaCreacion"), rs.getString("tipoCuenta"),
						rs.getFloat("saldo"), rs.getBoolean("estado"));
				cuentas.add(cuenta);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error: No pudieron recuperar las cuentas del cliente [" + dni + "]");
		}
		return cuentas;
	}

	@Override
	public Cuenta obtenerCuenta(int id) {
		Cuenta cuenta = null;

		String sql = "SELECT id, usuario, dni, cbu, fechaCreacion, tipoCuenta, saldo, estado FROM cuenta WHERE id = ?";
		try (Connection conexion = Conexion.getConexion().getSQLConexion();
				PreparedStatement stmt = conexion.prepareStatement(sql)) {

			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				cuenta = new Cuenta(rs.getInt("id"), rs.getString("usuario"), rs.getInt("dni"),
						rs.getString("cbu"), rs.getDate("fechaCreacion"), rs.getString("tipoCuenta"),
						rs.getFloat("saldo"), rs.getBoolean("estado"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error: No pudo recuperar la cuenta [" + id + "]");
		}

		return cuenta;
	}

	@Override
	public Cuenta obtenerUsuario(int dni) {
		// TODO Auto-generated method stub
		PreparedStatement statement = null;
		Connection conexion = null;
		ResultSet rs = null;
		Cuenta c = new Cuenta();

		try {
			conexion = Conexion.getConexion().getSQLConexion();
			statement = conexion.prepareStatement(CUENTA_DNI);
			statement.setInt(1, dni);

			rs = statement.executeQuery();

			if (rs.next()) {
				c.setId(rs.getInt("id"));
		        c.setUsuario(rs.getString("usuario"));
		        c.setDni(rs.getInt("dni"));
		        c.setCBU(rs.getString("cbu"));
		        c.setCreacion(rs.getDate("fechaCreacion"));
		        c.setTipo(rs.getString("tipoCuenta"));
		        c.setSaldo(rs.getFloat("saldo"));
		        c.setEstado(rs.getBoolean("estado"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			try {
				if (conexion != null) {
					conexion.rollback();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return c;
	}

}