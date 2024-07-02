package dao;

import java.util.ArrayList;

import entidad.Localidad;

public interface LocalidadDao {

	ArrayList<Localidad> obtenerLocalidadesPorProvincia(int provinciaId);
}
