package exceptions;


@SuppressWarnings("serial")
public class DALException extends Exception {
	public DALException(Exception e) {
		super(e);
	}

	public DALException(String e) {
		super(e);
	}
}
