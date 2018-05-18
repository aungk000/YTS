package me.aungkooo.yts.model;


import android.support.annotation.DrawableRes;

public class NavigationItem implements Selectable
{
    private String title;
    private int icon;
    private boolean selected;

    public NavigationItem(String title, @DrawableRes int icon) {
        this.title = title;
        this.icon = icon;
        selected = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(@DrawableRes int icon) {
        this.icon = icon;
    }

    @Override
    public boolean isSelected() {
        return selected;
    }

    @Override
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
