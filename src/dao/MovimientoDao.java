package dao;

import java.util.ArrayList;

import entidad.Movimiento;

public interface MovimientoDao {
	ArrayList<Movimiento> traerMovimientos(int id);
}
