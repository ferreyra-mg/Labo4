package dao;

import java.util.ArrayList;

import entidad.Prestamo;

public interface PrestamoDao {

	boolean grabar(Prestamo prestamo);
	ArrayList<Prestamo> traerTodos(int dni);
}
