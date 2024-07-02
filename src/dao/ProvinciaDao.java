package dao;

import java.util.ArrayList;

import entidad.Provincia;

public interface ProvinciaDao {

	ArrayList<Provincia> obtenerProvinciasPorPais(int paisId);
}
