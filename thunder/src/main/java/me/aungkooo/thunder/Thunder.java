package me.aungkooo.thunder;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import java.security.InvalidParameterException;

/**
 * Created by Ko Oo on 19/5/2018.
 */

public class Thunder
{
    private Context context;
    private Intent networkIntent;
    private NetworkReceiver networkReceiver;
    private IntentFilter intentFilter;

    private static volatile Thunder instance = null;

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

    public static class Builder
    {
        private Context context;
        private Intent networkIntent;
        private NetworkReceiver networkReceiver;
        private IntentFilter intentFilter;

        public Builder(Context context) {
            if(context == null) {
                throw new InvalidParameterException("Context must not be null");
            }

            this.context = context;
        }

        public Thunder build()
        {
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
        networkReceiver.stop();
        context.unregisterReceiver(networkReceiver);
        context.stopService(networkIntent);
    }
}
