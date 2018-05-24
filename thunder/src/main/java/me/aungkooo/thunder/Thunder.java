package me.aungkooo.thunder;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/**
 * Created by Ko Oo on 19/5/2018.
 */

public class Thunder
{
    private final Context context;
    private Intent networkIntent;
    private NetworkReceiver networkReceiver;
    private IntentFilter intentFilter;

    private static volatile Thunder instance;

    private Thunder(Context context, Intent networkIntent, NetworkReceiver networkReceiver,
                    IntentFilter intentFilter)
    {
        this.context = context;
        this.networkIntent = networkIntent;
        this.networkReceiver = networkReceiver;
        this.intentFilter = intentFilter;
    }

    public static Thunder with(Context context) {
        if(instance == null) {
            synchronized (Thunder.class) {
                instance = new Builder(context).build();
            }
        }

        return instance;
    }

    private static class Builder
    {
        private final Context context;
        private Intent networkIntent;
        private NetworkReceiver networkReceiver;
        private IntentFilter intentFilter;

        private Builder(Context context) {
            if(context == null) {
                throw new IllegalArgumentException("Context must not be null");
            }

            this.context = context.getApplicationContext();
        }

        private Thunder build()
        {
            Context context = this.context;

            if(networkIntent == null) {
                networkIntent = new Intent(context, NetworkService.class);
            }

            if(networkReceiver == null) {
                networkReceiver = new NetworkReceiver();
            }

            if(intentFilter == null) {
                intentFilter = new IntentFilter();
                intentFilter.addAction(NetworkService.NETWORK_INFO);
            }

            return new Thunder(context, networkIntent, networkReceiver, intentFilter);
        }
    }

    public void listen(NetworkListener networkListener) {
        networkReceiver.setNetworkListener(networkListener);
    }

    public void start() {
        context.registerReceiver(networkReceiver, intentFilter);
        context.startService(networkIntent);
    }

    public void stop() {
        context.unregisterReceiver(networkReceiver);
        context.stopService(networkIntent);
    }
}
