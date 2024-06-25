package negocio;

import java.util.ArrayList;
import entidad.Movimiento;

public interface MovimientoNegocio {
	ArrayList<Movimiento> traerMovimientos(int id);
}
