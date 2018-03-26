package com.example.mentorship.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/**
 * Created by naimish on 24/1/18.
 */
@Entity
public class Image {

    @NonNull
    @PrimaryKey
    @SerializedName("id")
    private String id;

    @ColumnInfo(name = "tags")
    @SerializedName("tags")
    private String tags;

    @ColumnInfo(name = "webformatURL")
    @SerializedName("webformatURL")
    private String webformatUrl;

    @ColumnInfo(name = "user")
    @SerializedName("user")
    private String userName;

    @ColumnInfo(name = "userImageURL")
    @SerializedName("userImageURL")
    private String userImageUrl;

    @ColumnInfo(name = "downloads")
    @SerializedName("downloads")
    private long downloadsCount;

    public String getWebformatUrl() {
        return webformatUrl;
    }

    public void setWebformatUrl(String webformatUrl) {
        this.webformatUrl = webformatUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserImageUrl() {
        return userImageUrl;
    }

    public void setUserImageUrl(String userImageUrl) {
        this.userImageUrl = userImageUrl;
    }

    public long getDownloadsCount() {
        return downloadsCount;
    }

    public void setDownloadsCount(long downloadsCount) {
        this.downloadsCount = downloadsCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
