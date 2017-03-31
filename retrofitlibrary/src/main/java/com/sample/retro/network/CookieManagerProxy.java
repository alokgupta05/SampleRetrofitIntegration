package com.sample.retro.network;




import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CookieManagerProxy extends CookieManager {

    private static final String TAG = CookieManagerProxy.class.getSimpleName();


    public static final String PERSIST_URL = "url";
    private CookieStore cookieStore = null;


    public CookieManagerProxy() {
        this.cookieStore = new CookieStoreProxy(this);
        android.webkit.CookieManager.getInstance().setAcceptCookie(true);

        clearAllCookies();
    }

    @Override
    public Map<String, List<String>> get(URI uri,
                                         Map<String, List<String>> requestHeaders) throws IOException {

        Map<String, List<String>> result = new HashMap<String, List<String>>();
        String cookies = android.webkit.CookieManager.getInstance().getCookie(
                uri.toString());
        if (cookies != null) {
            result.put("Cookie", Arrays.asList(cookies));
        }

        return result;
    }

    @Override
    public void put(URI uri, Map<String, List<String>> responseHeaders)
            throws IOException {
        if (uri == null || responseHeaders == null) {
            return;
        }

        String url = uri.toString();
        for (String headerKey : responseHeaders.keySet()) {
            if (headerKey == null
                    ) {
                continue;
            }

            for (String cookie : responseHeaders.get(headerKey)) {
                android.webkit.CookieManager.getInstance().setCookie(url,
                        cookie);

            }
        }
    }

    @Override
    public void setCookiePolicy(CookiePolicy cookiePolicy) {
        // Do nothing. This is handled by the webkit cookie manager.
    }

    @Override
    public CookieStore getCookieStore() {
        return cookieStore;
    }

    public void clearAllCookies() {

        android.webkit.CookieManager.getInstance().removeAllCookie();

    }

    /**
     * A function to get the respective cookie value from
     s
     * completely skipped in retro-fit calls.
     *
     * @param uri        - the Uri to retrieve cookies from.
     * @param cookieName - the cookie to be retrieved.
     * @return - the cookie value.
     */
    public String getCookieValue(URI uri, String cookieName) {
        // TODO Auto-generated method stub
        ArrayList<HttpCookie> cookieList = new ArrayList<HttpCookie>();

        try {
            Map<String, List<String>> cookieMap = get(
                    new URI(PERSIST_URL), null);
            for (String key : cookieMap.keySet()) {
                List<String> cookies = cookieMap.get(key);
                for (String cookie : cookies) {
                    for (String c : cookie.split(";")) {
                        cookieList.addAll(HttpCookie.parse(c));
                    }
                }
            }
        } catch (IOException e) {
            // Just return the empty arraylist
        } catch (URISyntaxException e) {
            // Just return the empty arraylist
        }
        for (final HttpCookie cookie : cookieList) {
            if (cookieName.equalsIgnoreCase(cookie.getName())) {
                return cookie.getValue();

            }
        }
        return null;
    }


}
