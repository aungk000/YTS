package me.aungkooo.yts.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.Toast;

import java.util.ArrayList;

import me.aungkooo.yts.R;
import me.aungkooo.yts.adapter.NavigationViewAdapter;
import me.aungkooo.yts.listener.OnNavigationItemClickedListener;
import me.aungkooo.yts.model.NavigationItem;

/**
 * Created by Ko OO on 16/5/2018.
 */

public class NavigationRecyclerView extends RecyclerView
{
    private NavigationViewAdapter adapter;

    public NavigationRecyclerView(Context context) {
        super(context);
    }

    public void setOnNavigationItemClickedListener(OnNavigationItemClickedListener listener) {
        if(adapter != null) {
            adapter.setOnNavigationItemClickedListener(listener);
        }
    }

    public NavigationRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        this.setLayoutManager(new LinearLayoutManager(context));
        this.setHasFixedSize(true);

        String[] title = getResources().getStringArray(R.array.navigation_recycler_view_item);
        int[] icon = new int[]{R.drawable.ic_home, R.drawable.ic_3d_glasses,
                R.drawable.ic_settings, R.drawable.ic_about};

        ArrayList<NavigationItem> itemList = new ArrayList<>();
        for (int i = 0, len = title.length; i < len; i++) {
            itemList.add(new NavigationItem(title[i], icon[i]));
        }

        adapter = new NavigationViewAdapter(getContext(), itemList);
        this.setAdapter(adapter);
    }

    public NavigationRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }
}
