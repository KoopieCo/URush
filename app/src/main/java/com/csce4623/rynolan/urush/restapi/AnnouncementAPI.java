package com.csce4623.rynolan.urush.restapi;

import com.csce4623.rynolan.urush.models.Announcement;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface AnnouncementAPI {
    @Headers("Authorization:Basic dXNlcjoxODQyNDVhYnNk")
    @GET("/announcements")
    Call<List<Announcement>> getAnnouncements();
}
