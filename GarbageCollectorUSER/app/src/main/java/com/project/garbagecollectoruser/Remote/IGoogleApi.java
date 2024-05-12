package com.project.garbagecollectoruser.Remote;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

// Enable billing on API to use this
public interface IGoogleApi {
    @GET("maps/api/directions/json")
    Observable<String> getDirection (
            @Query("mode") String mode,
            @Query("trans_routing_preference") String trans_routing,
            @Query("origin") String from,
            @Query("destination") String to,
            @Query("key") String key
    );
}
