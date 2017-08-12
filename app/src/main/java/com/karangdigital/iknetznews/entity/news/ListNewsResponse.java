package com.karangdigital.iknetznews.entity.news;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dimata005 on 8/6/2017.
 */

public class ListNewsResponse {
    @SerializedName("status")
    private String status;
    @SerializedName("source")
    private String source;
    @SerializedName("sortBy")
    private String sortBy;

    @SerializedName("articles") /*ini digunakan untuk ujung json, namanya harus sama oke*/
    private List<Articles> vListNews;

    public ListNewsResponse(List<Articles> v_laporan) {
        this.setvListNews(v_laporan);
    }

    public List<Articles> getV_laporan() {
        if(getvListNews() ==null){
            return getvListNews();
        }
        return getvListNews();
    }

    public void setV_laporan(List<Articles> v_laporan) {
        this.setvListNews(v_laporan);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public List<Articles> getvListNews() {
        return vListNews;
    }

    public void setvListNews(List<Articles> vListNews) {
        this.vListNews = vListNews;
    }
}
