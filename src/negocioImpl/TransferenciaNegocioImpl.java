package negocioImpl;

import java.sql.SQLException;

import dao.TransferenciaDao;
import daoImpl.TransferenciaDaoImpl;
import negocio.TransferenciaNegocio;

public class TransferenciaNegocioImpl implements TransferenciaNegocio{
	
	private TransferenciaDao transferenciaDao =  new TransferenciaDaoImpl();

	public boolean transferirOtroCbu(String usuario, String cbuDestino, double monto) throws SQLException {
		return transferenciaDao.TransferirCbu(usuario, cbuDestino, monto);
	}

	public boolean transferirPropiaCuenta(String usuario, int idCuentaDestino, double monto) throws SQLException {
		return transferenciaDao.TransferirCuenta(usuario, idCuentaDestino, monto);
	}

}
