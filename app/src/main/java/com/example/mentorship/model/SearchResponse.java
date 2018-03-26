package com.example.mentorship.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by naimish on 24/1/18.
 */

public class SearchResponse {
    public long total;
    private long totalHits;

    @SerializedName("hits")
    private List<Image> images;

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public long getTotalHits() {
        return totalHits;
    }

    public void setTotalHits(long totalHits) {
        this.totalHits = totalHits;
    }
}
