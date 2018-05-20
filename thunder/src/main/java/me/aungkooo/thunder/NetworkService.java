package me.aungkooo.thunder;

import android.app.IntentService;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.SystemClock;
import android.support.annotation.Nullable;

/**
 * Created by Ko Oo on 19/5/2018.
 */

public class NetworkService extends IntentService
{
    public static final String NETWORK_INFO = "networkInfo";
    public static final String NETWORK_AVAILABLE = "networkAvailable";

    private ConnectivityManager connectivityManager;

    public NetworkService() {
        super("NetworkService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent)
    {
        boolean networkAvailable = isNetworkAvailable();

        Intent data = new Intent();
        data.setAction(NETWORK_INFO);
        data.putExtra(NETWORK_AVAILABLE, networkAvailable);
        sendBroadcast(data);

        // WARNING: Wait an amount of time to reduce memory consuming
        SystemClock.sleep(1000);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
    }

    public boolean isNetworkAvailable() {
        return connectivityManager != null
                && connectivityManager.getActiveNetworkInfo() != null
                && connectivityManager.getActiveNetworkInfo().isAvailable()
                && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
