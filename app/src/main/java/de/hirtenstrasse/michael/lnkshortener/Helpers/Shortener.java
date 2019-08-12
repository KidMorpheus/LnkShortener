package de.hirtenstrasse.michael.lnkshortener.Helpers;

public class Shortener {

    private String longUrl, shortUrl;

    public Shortener(String longUrl){
        this.longUrl = longUrl;
    }

    private void shortenUrl(){

        this.shortUrl = "";
    }

    public String getLongUrl(){
        return this.longUrl;
    }

    public String getShortUrl() {

        if(shortUrl==null)
            shortenUrl();
        return shortUrl;
    }
}
