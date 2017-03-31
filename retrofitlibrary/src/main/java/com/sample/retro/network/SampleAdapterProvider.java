package com.sample.retro.network;

import com.squareup.okhttp.OkHttpClient;

import android.content.Context;

import java.net.CookiePolicy;
import java.security.SecureRandom;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

public class SampleAdapterProvider extends AdapterProvider {

    public SampleAdapterProvider(String endPoint,
                                 BaseRequestInterceptor requestInterceptor) {
        super(requestInterceptor);
        mEndPoint = endPoint;
        if (endPoint != null) {
            mEndPoint = endPoint;
        } else {
            mEndPoint = CookieManagerProxy.PERSIST_URL;
        }
    }



    public static OkHttpClient configureClient(final OkHttpClient client) {
        final TrustManager[] certs = new TrustManager[]{new X509TrustManager() {

            @Override
            public void checkClientTrusted(
                    java.security.cert.X509Certificate[] chain, String authType)
                    throws java.security.cert.CertificateException {
                // TODO Auto-generated method stub

            }

            @Override
            public void checkServerTrusted(
                    java.security.cert.X509Certificate[] chain, String authType)
                    throws java.security.cert.CertificateException {
                // TODO Auto-generated method stub

            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                // TODO Auto-generated method stub
                return null;
            }

        }};

        SSLContext ctx = null;
        try {
            ctx = SSLContext.getInstance("TLS");
            ctx.init(null, certs, new SecureRandom());
        } catch (final java.security.GeneralSecurityException ex) {
        }

        try {
            final HostnameVerifier hostnameVerifier = new HostnameVerifier() {

                @Override
                public boolean verify(String hostname, SSLSession session) {
                    // TODO Auto-generated method stub
                    return true;
                }

            };
            client.setHostnameVerifier(hostnameVerifier);
            client.setSslSocketFactory(ctx.getSocketFactory());
        } catch (final Exception e) {
        }

        return client;
    }

    @Override
    public void createRestAdapter() {
        super.createRestAdapter();

        OkHttpClient client = new OkHttpClient();
        SessionCookieManager cookieManager = SessionCookieManager.getInstance();
        CookieManagerProxy cookieProxy = (CookieManagerProxy) cookieManager.getCookieManager();
        cookieProxy.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        client.setCookieHandler(cookieProxy);

        restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setClient(new OkClient(client))
                .setRequestInterceptor(mBaseRequestInterceptor)
                .setEndpoint(mEndPoint).build();


    }

}