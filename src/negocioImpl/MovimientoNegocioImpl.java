package negocioImpl;

import java.util.ArrayList;
import java.util.Date;

import dao.MovimientoDao;
import daoImpl.MovimientoDaoImpl;
import entidad.Cuenta;
import entidad.Movimiento;
import entidad.Tipo_Movimiento;
import negocio.MovimientoNegocio;

public class MovimientoNegocioImpl implements MovimientoNegocio{
	
	private MovimientoDao mvDao = new MovimientoDaoImpl(); 
	
	public ArrayList<Movimiento> traerMovimientos(int id){
		return mvDao.traerMovimientos(id);
	} //devuelve todos los movimientos de un solo usuario
	
	public boolean transferirCbu(Cuenta cuenta, String cbuDestino, float monto, String tipoCuenta){
		return mvDao.TransferirCbu(cuenta, cbuDestino, monto, tipoCuenta);
	}

	@Override
	public String ObtenerCbu(int id) {
		return mvDao.ObtenerCbu(id);
	}

	@Override
	public boolean TransferirEntreCuentas(Cuenta cuenta, String cbuDestino, String cbuEmisor, float monto) {
		return mvDao.TransferirEntreCuentas(cuenta, cbuDestino, cbuEmisor, monto);
	}



	@Override
	public float VerificarSaldoxCuenta(int id) {
		return mvDao.VerificarSaldoxCuenta(id);
	}

	@Override
	public float obtenerMontoEntre(Date inicial, Date fin) {
		return mvDao.obtenerMontoEntre(inicial, fin);
	}

	@Override
	public int obtenerMovimientosEntre(Date inicial, Date fin) {
		return mvDao.obtenerMovimientosEntre(inicial, fin);
	}

	@Override
	public ArrayList<Tipo_Movimiento> traerTipos() {
		return mvDao.traerTipos();
	}
}
