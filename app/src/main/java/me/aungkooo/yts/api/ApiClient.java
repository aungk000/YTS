package me.aungkooo.yts.api;

import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.concurrent.TimeUnit;

import me.aungkooo.yts.api.entry.MovieListResponse;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient
{
    public static final String BASE_URL = "https://yts.am/api/v2/";
    private Retrofit client;
    private static ApiService service;
    private static ApiClient instance;

    private ApiClient(Retrofit client) {
        this.client = client;
    }

    public static ApiClient getClient()
    {
        if(instance == null)
        {
            synchronized (ApiClient.class) {
                instance = new Builder().build();
            }
        }

        return instance;
    }

    public ApiService getService()
    {
        if(service == null) {
            service = client.create(ApiService.class);
        }

        return service;
    }

    public static class Builder
    {
        private GsonConverterFactory converterFactory;
        private CookieManager cookieManager;
        private OkHttpClient okHttpClient;
        private JavaNetCookieJar cookieJar;
        private Retrofit client;

        public Builder() {
        }

        public ApiClient build()
        {
            if(converterFactory == null) {
                converterFactory = GsonConverterFactory.create();
            }

            if(okHttpClient == null) {
                if(cookieJar == null) {
                    if(cookieManager == null) {
                        cookieManager = new CookieManager();
                        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
                    }

                    cookieJar = new JavaNetCookieJar(cookieManager);
                }

                okHttpClient = new OkHttpClient.Builder()
                        .cookieJar(cookieJar)
                        .connectTimeout(15, TimeUnit.SECONDS)
                        .writeTimeout(15, TimeUnit.SECONDS)
                        .readTimeout(30, TimeUnit.SECONDS)
                        .build();
            }

            if(client == null)
            {
                client = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .client(okHttpClient)
                        .addConverterFactory(converterFactory)
                        .build();
            }

            return new ApiClient(client);
        }
    }

    public interface OnApiResponseListener extends Callback<MovieListResponse>
    {
        @Override
        void onResponse(Call<MovieListResponse> call, Response<MovieListResponse> response);

        @Override
        void onFailure(Call<MovieListResponse> call, Throwable t);
    }
}
