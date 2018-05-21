package me.aungkooo.yts.api.entry;


import com.google.gson.annotations.SerializedName;

public class Torrent
{
    @SerializedName("url")
    private String url;
    @SerializedName("hash")
    private String hash;
    @SerializedName("quality")
    private String quality;
    @SerializedName("seeds")
    private String seeds;
    @SerializedName("peers")
    private String peers;
    @SerializedName("size")
    private String size;
    @SerializedName("size_bytes")
    private String sizeBytes;
    @SerializedName("date_uploaded")
    private String dateUploaded;
    @SerializedName("date_uploaded_unix")
    private String dateUploadedUnix;

    public Torrent(String url, String hash, String quality, String seeds, String peers, String size, String sizeBytes, String dateUploaded, String dateUploadedUnix) {
        this.url = url;
        this.hash = hash;
        this.quality = quality;
        this.seeds = seeds;
        this.peers = peers;
        this.size = size;
        this.sizeBytes = sizeBytes;
        this.dateUploaded = dateUploaded;
        this.dateUploadedUnix = dateUploadedUnix;
    }

    public String getUrl() {
        return url;
    }

    public String getHash() {
        return hash;
    }

    public String getQuality() {
        return quality;
    }

    public String getSeeds() {
        return seeds;
    }

    public String getPeers() {
        return peers;
    }

    public String getSize() {
        return size;
    }

    public String getSizeBytes() {
        return sizeBytes;
    }

    public String getDateUploaded() {
        return dateUploaded;
    }

    public String getDateUploadedUnix() {
        return dateUploadedUnix;
    }
}
