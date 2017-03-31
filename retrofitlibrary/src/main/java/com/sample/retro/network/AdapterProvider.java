package com.sample.retro.network;


import retrofit.RestAdapter;

public class AdapterProvider {

    protected String mEndPoint;
    protected BaseRequestInterceptor mBaseRequestInterceptor;
    protected RestAdapter restAdapter;


    public AdapterProvider(BaseRequestInterceptor requestInterceptor) {

        if (requestInterceptor != null) {
            mBaseRequestInterceptor = requestInterceptor;
        } else {

            mBaseRequestInterceptor = new BaseRequestInterceptor(null);
        }

    }

    public void createRestAdapter() {

        restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setRequestInterceptor(mBaseRequestInterceptor)
                .setEndpoint(mEndPoint).build();

    }

    public RestAdapter getRestAdapter() {
        return restAdapter;
    }
}
