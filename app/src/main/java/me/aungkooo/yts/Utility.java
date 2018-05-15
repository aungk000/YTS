package me.aungkooo.yts;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;


public class Utility
{
    public static void changeActivity(Context from, Class to)
    {
        Intent intent = new Intent(from, to);
        if(intent.resolveActivity(from.getPackageManager()) != null)
        {
            from.startActivity(intent);
        }
    }

    public static boolean isNetworkAvailable(Context context)
    {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return connectivityManager != null
                && connectivityManager.getActiveNetworkInfo() != null
                && connectivityManager.getActiveNetworkInfo().isAvailable()
                && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
