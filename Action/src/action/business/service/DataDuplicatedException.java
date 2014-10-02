package action.business.service;

/**
 * 중복된 정보가 존재할 경우 발생하는 예외
 * 
 * @author Sin-eon
 * */
public class DataDuplicatedException extends Exception {
	private static final long serialVersionUID = 1L;

	public DataDuplicatedException() {
		super();
	}

	public DataDuplicatedException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataDuplicatedException(String message) {
		super(message);
	}

	public DataDuplicatedException(Throwable cause) {
		super(cause);
	}
}
