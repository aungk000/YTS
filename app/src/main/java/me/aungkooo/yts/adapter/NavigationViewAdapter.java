package me.aungkooo.yts.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import me.aungkooo.yts.Base;
import me.aungkooo.yts.R;
import me.aungkooo.yts.listener.OnNavigationItemClickedListener;
import me.aungkooo.yts.model.NavigationItem;
import me.aungkooo.yts.viewholder.NavigationViewHolder;


public class NavigationViewAdapter extends Base.RecyclerAdapter<NavigationViewHolder, NavigationItem>
{
    private OnNavigationItemClickedListener listener;

    public NavigationViewAdapter(Context context, ArrayList<NavigationItem> itemList) {
        super(context, itemList);
    }

    public void setOnNavigationItemClickedListener(OnNavigationItemClickedListener listener) {
        this.listener = listener;
    }

    @Override
    public NavigationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = createView(R.layout.navigation_item, parent);

        return new NavigationViewHolder(view, getContext());
    }

    @Override
    public void onBindViewHolder(final NavigationViewHolder holder, int position)
    {
        holder.onBind(getItemList());

        holder.getNavigationItemView().setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                NavigationItem item = getItemList().get(position);

                if(position < 2)
                {
                    holder.setSelected(true);

                    for(int i = 0; i < 2; i++)
                    {
                        NavigationItem eachItem = getItemList().get(i);
                        if(!eachItem.equals(item) && eachItem.isSelected())
                        {
                            eachItem.setSelected(false);
                        }
                        else if(eachItem.equals(item) && eachItem.isSelected())
                        {
                            eachItem.setSelected(true);
                        }
                    }
                }

                if(listener != null)
                {
                    listener.onNavigationItemClicked(position);
                }

                notifyDataSetChanged();
            }
        });
    }
}
