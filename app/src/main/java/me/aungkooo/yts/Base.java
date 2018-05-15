package me.aungkooo.yts;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Ko Oo on 15/5/2018.
 */

public abstract class Base
{
    public static abstract class Activity extends AppCompatActivity
    {
        private ProgressDialog progressDialog;

        public void setActionBarIcon(@DrawableRes int resId)
        {
            if(getSupportActionBar() != null)
            {
                getSupportActionBar().setIcon(resId);
            }
        }

        public void setActionBarLogo(@DrawableRes int resId)
        {
            if(getSupportActionBar() != null)
            {
                getSupportActionBar().setLogo(resId);
                getSupportActionBar().setDisplayOptions(
                        ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_USE_LOGO);
            }
        }

        public void setActionBarDrawerToggle(DrawerLayout drawerLayout, Toolbar toolbar)
        {
            ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
                    this,
                    drawerLayout,
                    toolbar,
                    R.string.open,
                    R.string.close);

            DrawerArrowDrawable arrowDrawable = new DrawerArrowDrawable(this);
            arrowDrawable.setColor(getColor(R.color.colorAccent));
            drawerToggle.setDrawerArrowDrawable(arrowDrawable);
        }

        public void setDefaultToolbar(@NonNull Toolbar toolbar, @StringRes int title)
        {
            toolbar.setTitle(title);
            toolbar.setTitleTextColor(getColor(R.color.colorAccent));
            toolbar.setNavigationIcon(R.drawable.ic_arrow);
        }

        public void changeActivity(Class to)
        {
            Intent intent = new Intent(this, to);
            if(intent.resolveActivity(getPackageManager()) != null)
            {
                startActivity(intent);
            }
        }

        public void changeActivityWithTransition(Class to, View sharedView,
                                                        String transitionName)
        {
            Intent intent = new Intent(this, to);
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    this,
                    sharedView,
                    transitionName
            );

            if(intent.resolveActivity(getPackageManager()) != null)
            {
                startActivity(intent, options.toBundle());
            }
        }

        public void changeFragment(int containerId, Fragment fragment)
        {
            getSupportFragmentManager().beginTransaction()
                    .replace(containerId, fragment)
                    .commit();
        }

        public void addFragment(int containerId, Fragment fragment)
        {
            getSupportFragmentManager().beginTransaction()
                    .add(containerId, fragment)
                    .commit();
        }

        public void showProgressDialog(String message)
        {
            if(progressDialog == null)
            {
                progressDialog = new ProgressDialog(this, R.style.DarkTheme_Dialog);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setIndeterminate(true);
                progressDialog.setCancelable(false);
            }

            progressDialog.setMessage(message);

            progressDialog.show();
        }

        public void stopProgressDialog()
        {
            if(progressDialog != null && progressDialog.isShowing())
            {
                progressDialog.dismiss();
            }
        }

        public void makeShortSnackbar(View view, String message)
        {
            Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT);
            snackbar.getView().setBackgroundColor(getColor(R.color.colorPrimary));
            snackbar.show();
        }
    }

    public static abstract class RecyclerAdapter<VH extends RecyclerViewHolder, OBJ> extends RecyclerView.Adapter<VH>
    {
        private Context context;
        private ArrayList<OBJ> itemList;

        public RecyclerAdapter(Context context, ArrayList<OBJ> itemList) {
            this.context = context;
            this.itemList = itemList;
        }

        public View createView(@LayoutRes int resource, @Nullable ViewGroup parent)
        {
            return LayoutInflater.from(context).inflate(resource, parent, false);
        }

        @Override
        public int getItemCount() {
            return itemList.size();
        }

        public ArrayList<OBJ> getItemList() {
            return itemList;
        }

        public Context getContext() {
            return context;
        }

        public void add(OBJ item)
        {
            itemList.add(item);
            notifyDataSetChanged();
        }
    }

    public static abstract class RecyclerViewHolder<OBJ> extends RecyclerView.ViewHolder
    {
        private Context context;
        private View view;

        public RecyclerViewHolder(View itemView, Context context) {
            super(itemView);
            this.view = itemView;
            this.context = context;
        }

        public OBJ getItem(ArrayList<OBJ> itemList)
        {
            return itemList.get(getAdapterPosition());
        }

        public Context getContext() {
            return context;
        }

        public View getView() {
            return view;
        }

        public abstract void onBind(ArrayList<OBJ> itemList);
    }
}
