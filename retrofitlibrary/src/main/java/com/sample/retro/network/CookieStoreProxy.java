package com.sample.retro.network;

import java.io.IOException;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



public class CookieStoreProxy implements CookieStore {
    CookieManagerProxy cookieManager;

    public CookieStoreProxy(CookieManagerProxy cookieManager) {
        this.cookieManager = cookieManager;
    }


    @Override
    public void add(URI uri, HttpCookie cookie) {
        throw new UnsupportedOperationException("Don't use this method directly.");
    }

    @Override
    public List<HttpCookie> get(URI uri) {
        throw new UnsupportedOperationException("Don't use this method directly.");
    }

    @Override
    public List<HttpCookie> getCookies() {
        /*
         * This method is implemented only to support ServiceCallSession in
         * Common. It returns all cookies for the mapi domain and should be
         * replaced ASAP.
         */
        ArrayList<HttpCookie> cookieList = new ArrayList<HttpCookie>();
        try {
            Map<String, List<String>> cookieMap = cookieManager.get(
                    new URI(CookieManagerProxy.PERSIST_URL), null);
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

        return cookieList;
    }

    @Override
    public List<URI> getURIs() {
        throw new UnsupportedOperationException("Don't use this method directly.");
    }

    @Override
    public boolean remove(URI uri, HttpCookie cookie) {
        throw new UnsupportedOperationException("Don't use this method directly.");
    }

    @Override
    public boolean removeAll() {
        throw new UnsupportedOperationException("Don't use this method directly.");
    }

}