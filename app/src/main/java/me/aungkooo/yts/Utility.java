package me.aungkooo.yts;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;


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

    public static void changeActivityWithTransition(Context context, Class to, View sharedView,
                                             String transitionName)
    {
        Intent intent = new Intent(context, to);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                (Activity) context,
                sharedView,
                transitionName
        );

        if(intent.resolveActivity(context.getPackageManager()) != null)
        {
            context.startActivity(intent, options.toBundle());
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

    public static void changeFragment(AppCompatActivity activity, int containerId, Fragment fragment)
    {
        activity.getSupportFragmentManager()
                .beginTransaction()
                .replace(containerId, fragment)
                .commit();
    }

    public static void addFragment(AppCompatActivity activity, int containerId, Fragment fragment)
    {
        activity.getSupportFragmentManager()
                .beginTransaction()
                .add(containerId, fragment)
                .commit();
    }

    public static void makeShortToast(Context context, String message)
    {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void makeLongToast(Context context, String message)
    {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
