package negocio;

import java.sql.SQLException;

public interface TransferenciaNegocio {

    static boolean transferirOtroCbu(String usuario, String cbuDestino, double monto) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
    static boolean transferirPropiaCuenta(String usuario, int idCuentaDestino, double monto) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
}
