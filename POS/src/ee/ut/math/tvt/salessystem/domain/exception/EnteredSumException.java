package ee.ut.math.tvt.salessystem.domain.exception;

/**
 * Thrown when domain controller's verification fails.
 */
public class EnteredSumException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs new <code>VerificationFailedException</code>.
	 */
	public EnteredSumException() {
		super();
	}

	/**
	 * Constructs new <code>VerificationFailedException</code> with with the
	 * specified detail message.
	 * 
	 * @param message - the detail message.
	 */
	public EnteredSumException(final String message) {
		super(message);
	}
}
