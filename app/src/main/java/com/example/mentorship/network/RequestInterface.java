package com.example.mentorship.network;


import com.example.mentorship.model.SearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by naimish on 9/11/17.
 */

public interface RequestInterface {

    @GET("/api/")
    Call<SearchResponse> search(@Query("key") String key,
                                @Query("image_type") String imageType,
                                @Query("q") String query,
                                @Query("page") int page,
                                @Query("per_page") int perPage);
}