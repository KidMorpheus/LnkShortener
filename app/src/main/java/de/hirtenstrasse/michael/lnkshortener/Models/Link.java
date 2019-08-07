package de.hirtenstrasse.michael.lnkshortener.Models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "link_table")
public class Link {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    public int id;

    @NonNull
    @ColumnInfo(name = "short_url")
    private String mShortUrl;

    @ColumnInfo(name = "long_url")
    private String mLongUrl;

    @ColumnInfo(name = "created")
    private Date mCreated;

    @ColumnInfo(name = "page_title")
    private String mPageTitle;

    public Link(@NonNull String shortUrl, @NonNull String longUrl, @NonNull Date created) {
        this.mShortUrl = shortUrl;
        this.mLongUrl = longUrl;
        this.mCreated = created;
    }

    public void setPageTitle(String pageTitle) { this.mPageTitle = pageTitle; }

    public String getShortUrl(){ return this.mShortUrl;}

    public String getLongUrl(){ return this.mLongUrl; }

    public Date getCreated() { return this.mCreated; }

    public String getPageTitle() {
        return mPageTitle;
    }
}
