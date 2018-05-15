package me.aungkooo.yts.viewholder;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.aungkooo.yts.Base;
import me.aungkooo.yts.R;
import me.aungkooo.yts.model.NavigationItem;


public class NavigationViewHolder extends Base.RecyclerViewHolder<NavigationItem>
{
    @BindView(R.id.txt_category_navigation_item)
    TextView txtCategoryNavigationItem;
    @BindView(R.id.img_navigation_item)
    ImageView imgNavigationItem;
    @BindView(R.id.txt_navigation_item)
    TextView txtNavigationItem;
    @BindView(R.id.navigation_item_view)
    LinearLayout navigationItemView;
    @BindView(R.id.navigation_divider)
    View navigationDivider;

    private NavigationItem item;

    public NavigationViewHolder(View itemView, Context context) {
        super(itemView, context);
        ButterKnife.bind(this, itemView);
    }

    public LinearLayout getNavigationItemView() {
        return navigationItemView;
    }

    @Override
    public void onBind(ArrayList<NavigationItem> itemList)
    {
        item = getItem(itemList);

        boolean selected = false;
        for (NavigationItem each : itemList) {
            if (each.isSelected()) {
                selected = true;
            }
        }

        if (!selected) {
            itemList.get(0).setSelected(true);
        }

        setSelected(item.isSelected());

        txtNavigationItem.setText(item.getTitle());
        imgNavigationItem.setImageResource(item.getIcon());
        txtCategoryNavigationItem.setTextColor(getContext().getColor(R.color.grey_light));

        int position = getAdapterPosition();
        switch (position) {
            case 0:
                navigationDivider.setVisibility(View.VISIBLE);
                break;

            case 1:
                txtCategoryNavigationItem.setVisibility(View.VISIBLE);
                txtCategoryNavigationItem.setText(R.string.category);
                navigationDivider.setVisibility(View.VISIBLE);
                break;

            case 2:
                txtCategoryNavigationItem.setVisibility(View.VISIBLE);
                txtCategoryNavigationItem.setText(R.string.more);
                break;
        }
    }

    public void setSelected(boolean value)
    {
        final int colorAccent = getContext().getColor(R.color.colorAccent);

        if(value) {
            navigationItemView.setBackgroundColor(getContext().getColor(R.color.grey_blue));
            txtNavigationItem.setTextColor(colorAccent);
            imgNavigationItem.setImageTintList(ColorStateList.valueOf(colorAccent));
        }
        else {
            navigationItemView.setBackgroundColor(Color.TRANSPARENT);
            txtNavigationItem.setTextColor(getContext().getColor(R.color.white));
            imgNavigationItem.setImageTintList(ColorStateList.valueOf(
                    getContext().getColor(R.color.navigationItemIcon)));
        }

        item.setSelected(value);
    }
}
