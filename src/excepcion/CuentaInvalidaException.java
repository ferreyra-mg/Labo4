package excepcion;

public class CuentaInvalidaException extends Exception {
	
	public CuentaInvalidaException()
	{
		
	}

	@Override
	public String getMessage() {
		
		return "La cuenta es invalida";
	}
	
	
}
