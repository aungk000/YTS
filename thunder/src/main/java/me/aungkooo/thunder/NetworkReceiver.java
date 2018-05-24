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

    public void setNetworkListener(NetworkListener networkListener) {
        this.networkListener = networkListener;
    }

    @Override
    public void onReceive(Context context, Intent intent)
    {
        boolean networkAvailable = intent.getBooleanExtra(NetworkService.NETWORK_AVAILABLE, false);

        if(networkAvailable) {
            networkListener.onNetworkAvailable();
        }
        else {
            networkListener.onNetworkUnavailable();
        }
    }
}
