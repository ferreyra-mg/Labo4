package negocioImpl;

import java.util.ArrayList;
import java.util.Date;

import dao.MovimientoDao;
import daoImpl.MovimientoDaoImpl;
import entidad.Cuenta;
import entidad.Movimiento;
import negocio.MovimientoNegocio;

public class MovimientoNegocioImpl implements MovimientoNegocio{
	
	private MovimientoDao mvDao = new MovimientoDaoImpl(); 
	
	public ArrayList<Movimiento> traerMovimientos(int id){
		return mvDao.traerMovimientos(id);
	} //devuelve todos los movimientos de un solo usuario
	
	public boolean transferirCbu(int id, String cbuDestino, float monto, String tipoCuenta){
		return mvDao.TransferirCbu(id, cbuDestino, monto, tipoCuenta);
	}

	@Override
	public String ObtenerCbu(String tipoCuenta, Cuenta cuenta) {
		return mvDao.ObtenerCbu(tipoCuenta, cuenta);
	}

	@Override
	public boolean TransferirEntreCuentas(int id, String cbuDestino, String cbuEmisor, float monto) {
		return mvDao.TransferirEntreCuentas(id, cbuDestino, cbuEmisor, monto);
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
}
