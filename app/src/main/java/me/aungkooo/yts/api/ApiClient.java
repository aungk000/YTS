package me.aungkooo.yts.api;

import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.concurrent.TimeUnit;

import me.aungkooo.yts.model.DataResponse;
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
    private static Retrofit client;
    private static ApiService service;
    private static GsonConverterFactory converterFactory;
    private static CookieManager cookieManager;
    private static OkHttpClient okHttpClient;
    private static JavaNetCookieJar cookieJar;

    private static Retrofit getClient()
    {
        if(client == null)
        {
            if(converterFactory == null)
            {
                converterFactory = GsonConverterFactory.create();
            }

            if(okHttpClient == null)
            {
                if(cookieJar == null)
                {
                    if(cookieManager == null)
                    {
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

            client = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(converterFactory)
                    .build();
        }

        return client;
    }

    public static ApiService getService()
    {
        if(service == null)
        {
            service = getClient().create(ApiService.class);
        }

        return service;
    }

    public interface OnApiResponseListener extends Callback<DataResponse>
    {
        @Override
        void onResponse(Call<DataResponse> call, Response<DataResponse> response);

        @Override
        void onFailure(Call<DataResponse> call, Throwable t);
    }
}
