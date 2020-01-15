package de.retest.recheck;

import static de.retest.recheck.RecheckProperties.FILE_OUTPUT_FORMAT_PROPERTY_KEY;

import de.retest.recheck.auth.RehubAuthenticationHandler;
import de.retest.recheck.auth.RetestAuthentication;
import de.retest.recheck.persistence.FileOutputFormat;

public class Rehub {

	private static final String REHUB_CLIENT = "marvin";

	private static RehubAuthenticationHandler handler;
	private static RetestAuthentication auth;

	private Rehub() {

	}

	/**
	 * Initializes rehub to be used as persistence and, if not already authenticated, asks for login.
	 */
	public static void init() {
		handler = new RehubAuthenticationHandler();
		auth = new RetestAuthentication( handler, REHUB_CLIENT );
		auth.authenticate();

		System.setProperty( FILE_OUTPUT_FORMAT_PROPERTY_KEY, FileOutputFormat.CLOUD.toString() );
	}

	/**
	 * Returns the given recheck API key for rehub.
	 *
	 * <p>
	 * <em>Treat this as a secret! Anyone with access to your token can add test reports to rehub.</em>
	 *
	 * @return The given recheck API key for rehub.
	 */
	public static String getRecheckApiKey() {
		return handler.getOfflineToken();
	}

	public static String getAccessToken() {
		return auth.getAccessToken().getToken();
	}

}
