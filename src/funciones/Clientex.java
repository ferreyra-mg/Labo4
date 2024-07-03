package funciones;

import negocio.LocalidadNegocio;
import negocio.PaisNegocio;
import negocio.ProvinciaNegocio;
import negocio.TipoCuentaNegocio;
import negocioImpl.LocalidadNegocioImpl;
import negocioImpl.PaisNegocioImpl;
import negocioImpl.ProvinciaNegocioImpl;
import negocioImpl.TipoCuentaNegocioImpl;

public class Clientex {
	public static String obtenerUbicacion(int loc, int pro, int pa) {
		PaisNegocio paNeg = new PaisNegocioImpl();
		ProvinciaNegocio proNeg = new ProvinciaNegocioImpl();
		LocalidadNegocio locNeg = new LocalidadNegocioImpl();
		String ubicacion = locNeg.obtenerLocalidad(loc).getLocalidad();
		ubicacion += ", " + proNeg.obtenerProvincia(pro).getProvincia();
		ubicacion += ", " + paNeg.obtenerPais(pa).getPais();
		return ubicacion;
	}
	
	public static String obtenerTipo(int tipo) {
		TipoCuentaNegocio tiNeg = new TipoCuentaNegocioImpl();
		String t = tiNeg.traerTipo(tipo).getDescripcion();
		return t;
	}
}
