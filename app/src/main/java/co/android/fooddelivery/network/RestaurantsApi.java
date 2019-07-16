package co.android.fooddelivery.network;

import com.google.gson.JsonArray;

import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface RestaurantsApi {

    @GET("v2/restaurant/")
    Single<JsonArray> fetchNearbyRestaurants(@QueryMap Map<String, String> options);

}
