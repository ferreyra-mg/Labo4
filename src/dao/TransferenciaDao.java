package dao;

import java.sql.SQLException;

public interface TransferenciaDao {
	
	boolean TransferirCbu(String usuario, String cbuDestino, double monto) throws SQLException;
	boolean TransferirCuenta(String usuario, int idCuentaDestino, double monto) throws SQLException;
	
}
