package excepcion;

public class ClienteInvalidoException extends Exception{

	public ClienteInvalidoException()
	{
		
	}

	@Override
	public String getMessage() {
		
		return "El cliente es invalido";
	}

	
}
