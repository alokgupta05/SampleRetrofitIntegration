package com.sample.retro.network;



import java.net.CookieManager;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Class used to manage the session related tokens and information
 */

public final class SessionCookieManager {

    private static final String TAG = SessionCookieManager.class.getSimpleName();
    private static SessionCookieManager sessionCookieManager;
    private final CookieManagerProxy cookieManager;
    private URI baseUri;
    /**
     * Follows a singleton design pattern. Therefore this constructor is made
     * private
     */

    private SessionCookieManager() {
        cookieManager = new CookieManagerProxy();
    }

    public static synchronized SessionCookieManager getInstance(
            ) {

        if (sessionCookieManager == null) {
            sessionCookieManager = new SessionCookieManager();
        }

        CookieManager.setDefault(sessionCookieManager.getCookieManager());
        return sessionCookieManager;
    }


    /**
     * This method will provide the current cookiestore used by cookiemanager
     */
    public void clearAllCookie() {
        cookieManager.clearAllCookies();
    }

    public URI getBaseUri() {

        if (null == baseUri) {

            String url  = CookieManagerProxy.PERSIST_URL;
            try {
                baseUri = new URI(url);
            } catch (final URISyntaxException e) {
                // This is pretty bad, since the URL should always be parseable.
                throw new IllegalArgumentException("Environment base url not parseable as URI: "
                        + url);
            }
        }
        return baseUri;
    }

    public CookieManager getCookieManager() {
        return cookieManager;
    }

}
