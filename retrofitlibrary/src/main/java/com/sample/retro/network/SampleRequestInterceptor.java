package com.sample.retro.network;


import java.util.HashMap;

public class SampleRequestInterceptor extends BaseRequestInterceptor {

    HashMap<String, String> headerValues;
    private CookieManagerProxy cookieManagerProxy;

    public SampleRequestInterceptor(HashMap<String, String> headerValues) {
        super(headerValues);
        this.headerValues = headerValues;
        SessionCookieManager coockieManager = SessionCookieManager.getInstance();
        cookieManagerProxy = (CookieManagerProxy) coockieManager.getCookieManager();
    }

    @Override
    public void intercept(RequestFacade request) {
        super.intercept(request);

        //Add custom headers
        //this.headerValues.add(key,Value);
    }

}