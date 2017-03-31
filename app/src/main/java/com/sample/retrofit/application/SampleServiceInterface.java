package com.sample.retrofit.application;

import com.sample.retrofit.application.bean.GitHubResponse;
import com.sample.retrofit.application.bean.WeatherResponse;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.mime.TypedString;

public interface SampleServiceInterface {

    @Headers("Content-Type: application/json")
    @GET("/data/2.5/weather?q={city},{country}&appid={apikey}")
    void getWeatherData(@Path("city") String city, @Path("country") String country,@Path("apikey") String apikey, Callback<WeatherResponse> termsAndConditionsCallback);

    @Headers("Content-Type: application/json")
    @POST("/media/webservice/httppost.php")
    void postLoginData(@Body TypedString jsonBundle, Callback<String> responseCallback);

    @Headers("Content-Type: application/json")
    @GET("/")
    void getGithubData(Callback<GitHubResponse> gitHubResponseCallback);



}
