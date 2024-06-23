package daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import dao.CuentaDao;
import entidad.Cuenta;

public class CuentaDaoImpl implements CuentaDao {

	private static final String user_name = "SELECT usuario FROM bdbanco.cuenta where dni = ?";

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

		String sql = "SELECT id, usuario, dni, cbu, fechaCreacion, tipoCuenta, saldo, estado FROM cuenta WHERE dni = ?";
		try (Connection conexion = Conexion.getConexion().getSQLConexion();
				PreparedStatement stmt = conexion.prepareStatement(sql)) {

			stmt.setInt(1, dni);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Cuenta cuenta = new Cuenta(rs.getInt("id"), rs.getString("usuario"), rs.getInt("dni"),
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
	public String obtenerUsuario(int dni) {
		// TODO Auto-generated method stub
		PreparedStatement statement = null;
		Connection conexion = null;
		ResultSet resultSet = null;
		String nombreUsuario = null;

		try {
			conexion = Conexion.getConexion().getSQLConexion();
			statement = conexion.prepareStatement(user_name);
			statement.setInt(1, dni);

			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				nombreUsuario = resultSet.getString("usuario");
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
				if (resultSet != null) {
					resultSet.close();
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

		return nombreUsuario != null ? nombreUsuario : "no se encontraron cuentas";
	}

}