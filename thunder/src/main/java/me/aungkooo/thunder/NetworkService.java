package me.aungkooo.thunder;

import android.app.Service;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;

/**
 * Created by Ko Oo on 19/5/2018.
 */

public class NetworkService extends Service
{
    public static final String NETWORK_INFO = "networkInfo";
    public static final String NETWORK_AVAILABLE = "networkAvailable";
    public static final String TAG = "NetworkService";

    private volatile Looper serviceLooper;
    private volatile ServiceHandler serviceHandler;
    private ConnectivityManager connectivityManager;

    public NetworkService() {
        super();
    }

    private final class ServiceHandler extends Handler
    {
        private ServiceHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            onHandle((Intent) msg.obj);
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();

        HandlerThread thread = new HandlerThread(TAG);
        thread.start();

        serviceLooper = thread.getLooper();
        serviceHandler = new ServiceHandler(serviceLooper);

        connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        sendMessage(intent);

        return START_NOT_STICKY;
    }

    public void onHandle(Intent intent)
    {
        boolean networkAvailable = isNetworkAvailable();

        Intent data = new Intent();
        data.setAction(NETWORK_INFO);
        data.putExtra(NETWORK_AVAILABLE, networkAvailable);
        sendBroadcast(data);

        // WARNING: Repeat service after an amount of time to reduce memory consuming
        SystemClock.sleep(2000);
        sendMessage(intent);
    }

    private void sendMessage(Intent intent)
    {
        Message msg = serviceHandler.obtainMessage();
        msg.obj = intent;
        serviceHandler.sendMessage(msg);
    }

    @Override
    public void onDestroy() {
        serviceLooper.quit();
    }

    public boolean isNetworkAvailable() {
        return connectivityManager != null
                && connectivityManager.getActiveNetworkInfo() != null
                && connectivityManager.getActiveNetworkInfo().isAvailable()
                && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
