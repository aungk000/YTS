package me.aungkooo.thunder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Ko Oo on 19/5/2018.
 */

public class NetworkReceiver extends BroadcastReceiver
{
    private NetworkListener networkListener;
    private Intent networkIntent;
    private Context context;

    public void setNetworkListener(NetworkListener networkListener) {
        this.networkListener = networkListener;
    }

    @Override
    public void onReceive(Context context, Intent intent)
    {
        this.context = context;

        boolean networkAvailable = intent.getBooleanExtra(NetworkService.NETWORK_AVAILABLE, false);

        if(networkAvailable) {
            networkListener.onNetworkAvailable();
        }
        else {
            networkListener.onNetworkUnavailable();
        }

        if(networkIntent == null) {
            networkIntent = new Intent(context, NetworkService.class);
        }

        context.startService(networkIntent);
    }

    public void stop()
    {
        context.stopService(networkIntent);
    }
}
