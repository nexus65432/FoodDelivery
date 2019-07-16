package co.android.fooddelivery.network;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Single;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Rest interface to fetch data from server
 */
public class RetrofitService {

    private Retrofit mRetroFit;
    private RestaurantsApi mRestaurantsApi;

    private static final String END_POINT = "https://api.doordash.com/";

    private static class SingletonRetroFitServiceHelper {
        private static final RetrofitService INSTANCE = new RetrofitService();
    }

    public static RetrofitService getInstance() {
        return SingletonRetroFitServiceHelper.INSTANCE;
    }

    private RetrofitService() {
        final Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setLenient()
                .create();
        mRetroFit = new Retrofit.Builder().baseUrl(END_POINT)
                .client(getOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        mRestaurantsApi = mRetroFit.create(RestaurantsApi.class);
    }

    private OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder().build();
    }

    public Single<JsonArray> getRestaurantsForCurrentLocation(double lat, double lng) {
        return mRestaurantsApi.fetchNearbyRestaurants(queryBuilder(lat, lng));
    }

    private Map<String, String> queryBuilder(double lat, double lng) {

        Map<String, String> queryMap = new HashMap<>();

        queryMap.put("lat", String.valueOf(lat));
        queryMap.put("lng", String.valueOf(lng));

        return queryMap;
    }

}
