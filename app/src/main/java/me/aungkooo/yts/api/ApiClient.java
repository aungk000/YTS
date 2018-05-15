package me.aungkooo.yts.api;


import me.aungkooo.yts.model.DataResponse;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient
{
    public static final String BASE_URL = "https://yts.am/";
    private static Retrofit client;
    private static ApiService service;
    private static GsonConverterFactory converterFactory;

    public static Retrofit getClient()
    {
        if(client == null)
        {
            if(converterFactory == null)
            {
                converterFactory = GsonConverterFactory.create();
            }

            client = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
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

    }
}
