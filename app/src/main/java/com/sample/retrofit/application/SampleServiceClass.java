package com.sample.retrofit.application;


import com.sample.retro.network.AdapterProvider;
import com.sample.retro.network.SampleAdapterProvider;
import com.sample.retro.network.SampleRequestInterceptor;
import com.sample.retro.network.ServiceGenerator;
import com.sample.retrofit.application.bean.GitHubResponse;
import com.sample.retrofit.application.bean.WeatherResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedString;

/**
 * Service class for getting card details, for replacing card and for activating
 * card using service calls Requests are made by using url's defined under
 * SampleServiceInterface
 *
 * @author 388402
 */
public class SampleServiceClass {


    public void postData(final RequestEventListener listener) {

        HashMap<String,String> postRequestHeaders = new HashMap<String,String>();
        AdapterProvider adapterProvider = new SampleAdapterProvider(
                "http://androidexample.com", new SampleRequestInterceptor(postRequestHeaders));
        JSONObject jsonBundle = new JSONObject();

        try {
            jsonBundle.put("name", "C02G8416DRJM");
            jsonBundle.put("email", "sdsds@gmail.com");
            jsonBundle.put("user", "sdsds");
            jsonBundle.put("pass", "12345");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        TypedString postBundleTypStr = new TypedString(jsonBundle.toString());
        adapterProvider.createRestAdapter();

        SampleServiceInterface cardDetailServiceProxy = ServiceGenerator
                .createService(SampleServiceInterface.class,
                        adapterProvider);

        cardDetailServiceProxy.postLoginData(postBundleTypStr,new Callback<String>() {

            @Override
            public void success(String statusResponse, Response arg1) {
                listener.onSuccess(statusResponse);
            }

            @Override
            public void failure(RetrofitError retrofitError) {


                listener.OnError(retrofitError);

            }
        });
    }

    public void getWeatherData(final RequestEventListener listener) {

        AdapterProvider adapterProvider = new SampleAdapterProvider(
                null, new SampleRequestInterceptor(null ));

        adapterProvider.createRestAdapter();

        SampleServiceInterface cardDetailServiceProxy = ServiceGenerator
                .createService(SampleServiceInterface.class,
                        adapterProvider);

        cardDetailServiceProxy.getWeatherData("Pune","IN","5432a3d71cb9f06bace8e1e4edaa4889", new Callback<WeatherResponse>() {

            @Override
            public void success(WeatherResponse statusResponse, Response arg1) {

                listener.onSuccess(statusResponse);
            }

            @Override
            public void failure(RetrofitError retrofitError) {

                listener.OnError(retrofitError);
            }
        });

    }

    public void getGithubData(final RequestEventListener listener) {

        AdapterProvider adapterProvider = new SampleAdapterProvider(
                "http://api.github.com", new SampleRequestInterceptor(null ));

        adapterProvider.createRestAdapter();

        SampleServiceInterface cardDetailServiceProxy = ServiceGenerator
                .createService(SampleServiceInterface.class,
                        adapterProvider);

        cardDetailServiceProxy.getGithubData(new Callback<GitHubResponse>() {

            @Override
            public void success(GitHubResponse statusResponse, Response arg1) {

                listener.onSuccess(statusResponse);
            }

            @Override
            public void failure(RetrofitError retrofitError) {

                listener.OnError(retrofitError);
            }
        });

    }

}
