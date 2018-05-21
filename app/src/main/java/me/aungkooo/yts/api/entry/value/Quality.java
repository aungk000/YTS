package me.aungkooo.yts.api.entry.value;

/**
 * Created by Ko Oo on 22/5/2018.
 */

public enum Quality
{
    QUALITY_DEFAULT("All"),
    QUALITY_3D("3D"),
    QUALITY_720P("720p"),
    QUALITY_1080P("1080p");

    private String value;

    Quality(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
