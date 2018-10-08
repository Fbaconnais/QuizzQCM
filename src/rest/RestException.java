package rest;

public class RestException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RestException() {
		super();
	}

	public RestException(String message) {
		super(message);
	}
	
	public RestException(String message, Throwable exception) {
		super(message, exception);
	}

	//Méthodes
	@Override
	public String getMessage() {
		StringBuffer sb = new StringBuffer("Couche Rest - ");
		sb.append(super.getMessage());
		
		return sb.toString() ;
	}
}
