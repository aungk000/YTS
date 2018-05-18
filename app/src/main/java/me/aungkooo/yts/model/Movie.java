package me.aungkooo.yts.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class Movie
{
    @SerializedName("id") private String id;
    @SerializedName("url") private String url;
    @SerializedName("imbd_code") private String imbdCode;
    @SerializedName("title") private String title;
    @SerializedName("title_english") private String titleEnglish;
    @SerializedName("title_long") private String titleLong;
    @SerializedName("slug") private String slug;
    @SerializedName("year") private String year;
    @SerializedName("rating") private String rating;
    @SerializedName("runtime") private String runtime;
    @SerializedName("genres") private ArrayList<String> genres;
    @SerializedName("summary") private String summary;
    @SerializedName("description_full") private String descriptionFull;
    @SerializedName("synopsis") private String synopsis;
    @SerializedName("yt_trailer_code") private String ytTrailerCode;
    @SerializedName("language") private String language;
    @SerializedName("mpa_rating") private String mpaRating;
    @SerializedName("background_image") private String backgroundImage;
    @SerializedName("background_image_original") private String backgroundImageOriginal;
    @SerializedName("small_cover_image") private String smallCoverImage;
    @SerializedName("medium_cover_image") private String mediumCoverImage;
    @SerializedName("large_cover_image") private String largeCoverImage;
    @SerializedName("state") private String state;
    @SerializedName("torrents") private ArrayList<Torrent> torrents;
    @SerializedName("date_uploaded") private String dateUploaded;
    @SerializedName("date_uploaded_unix") private String dateUploadedUnix;

    public Movie(String id, String url, String imbdCode, String title, String titleEnglish,
                 String titleLong, String slug, String year, String rating, String runtime,
                 ArrayList<String> genres, String summary, String descriptionFull, String synopsis,
                 String ytTrailerCode, String language, String mpaRating, String backgroundImage,
                 String backgroundImageOriginal, String smallCoverImage, String mediumCoverImage,
                 String largeCoverImage, String state, ArrayList<Torrent> torrents, String dateUploaded,
                 String dateUploadedUnix)
    {
        this.id = id;
        this.url = url;
        this.imbdCode = imbdCode;
        this.title = title;
        this.titleEnglish = titleEnglish;
        this.titleLong = titleLong;
        this.slug = slug;
        this.year = year;
        this.rating = rating;
        this.runtime = runtime;
        this.genres = genres;
        this.summary = summary;
        this.descriptionFull = descriptionFull;
        this.synopsis = synopsis;
        this.ytTrailerCode = ytTrailerCode;
        this.language = language;
        this.mpaRating = mpaRating;
        this.backgroundImage = backgroundImage;
        this.backgroundImageOriginal = backgroundImageOriginal;
        this.smallCoverImage = smallCoverImage;
        this.mediumCoverImage = mediumCoverImage;
        this.largeCoverImage = largeCoverImage;
        this.state = state;
        this.torrents = torrents;
        this.dateUploaded = dateUploaded;
        this.dateUploadedUnix = dateUploadedUnix;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getImbdCode() {
        return imbdCode;
    }

    public String getTitle() {
        return title;
    }

    public String getTitleEnglish() {
        return titleEnglish;
    }

    public String getTitleLong() {
        return titleLong;
    }

    public String getSlug() {
        return slug;
    }

    public String getYear() {
        return year;
    }

    public String getRating() {
        return rating;
    }

    public String getRuntime() {
        return runtime;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public String getSummary() {
        return summary;
    }

    public String getDescriptionFull() {
        return descriptionFull;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getYtTrailerCode() {
        return ytTrailerCode;
    }

    public String getLanguage() {
        return language;
    }

    public String getMpaRating() {
        return mpaRating;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public String getBackgroundImageOriginal() {
        return backgroundImageOriginal;
    }

    public String getSmallCoverImage() {
        return smallCoverImage;
    }

    public String getMediumCoverImage() {
        return mediumCoverImage;
    }

    public String getLargeCoverImage() {
        return largeCoverImage;
    }

    public String getState() {
        return state;
    }

    public ArrayList<Torrent> getTorrents() {
        return torrents;
    }

    public String getDateUploaded() {
        return dateUploaded;
    }

    public String getDateUploadedUnix() {
        return dateUploadedUnix;
    }
}
