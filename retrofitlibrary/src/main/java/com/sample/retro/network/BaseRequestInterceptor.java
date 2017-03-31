package com.sample.retro.network;


import android.os.Build;

import java.util.HashMap;
import java.util.Set;

import retrofit.RequestInterceptor;

public class BaseRequestInterceptor implements RequestInterceptor {

    private final static String TAG = "BaseRequestInterceptor";
    private final static String X_CONTENT_TYPE = "application/json";

    private HashMap<String, String> requestHeaderValues;

    private String X_DEVICEOS_VERSION;

    public BaseRequestInterceptor(HashMap<String, String> headerValues) {

        requestHeaderValues = headerValues;
        setupDefaultHeaders();

    }

    private void setupDefaultHeaders() {

        X_DEVICEOS_VERSION = Build.VERSION.RELEASE;
    }

    @Override
    public void intercept(RequestFacade request) {
        request.addHeader("ContentType", X_CONTENT_TYPE);
        request.addHeader("Device", X_DEVICEOS_VERSION);
        if (requestHeaderValues != null && requestHeaderValues.size() > 0) {

            final Set<String> keys = requestHeaderValues.keySet();
            for (final String key : keys) {
                request.addHeader(key, requestHeaderValues.get(key));

            }
            requestHeaderValues.clear();
        }

    }

}
