package co.android.fooddelivery.viewModel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import co.android.fooddelivery.R;
import co.android.fooddelivery.model.RestaurantInfo;
import co.android.fooddelivery.model.RestaurantModel;
import co.android.fooddelivery.network.RetrofitService;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


public class RestaurantViewModel extends ViewModel {

    private static final String TAG = "RestaurantViewModel";

    private static final double LOC_LAT = 37.422740;
    private static final double LOC_LNG = -122.139956;

    private MutableLiveData<List<RestaurantModel>> mRestaurantModelLiveData;
    private MutableLiveData<String> mErrorMessageLiveData;

    private CompositeDisposable mCompositeDisposable = null;

    public RestaurantViewModel() {
        mCompositeDisposable = new CompositeDisposable();
    }

    public MutableLiveData<List<RestaurantModel>> getRestaurantsData() {
        if (mRestaurantModelLiveData == null) {
            mRestaurantModelLiveData = new MutableLiveData<List<RestaurantModel>>();
        }
        return mRestaurantModelLiveData;
    }

    public MutableLiveData<String> getErrorMessage() {
        if (mErrorMessageLiveData == null) {
            mErrorMessageLiveData = new MutableLiveData<String>();
        }
        return mErrorMessageLiveData;
    }

    /**
     * Get Restaurants for the current Location
     *
     * @param context
     */
    public void getRestaurantsForCurrentLocation(@NonNull Context context) {
        Log.d(TAG, "getRestaurantsForCurrentLocation ");
        mCompositeDisposable.add(RetrofitService.getInstance().getRestaurantsForCurrentLocation(LOC_LAT, LOC_LNG)
                .subscribeOn(Schedulers.single())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getRestaurantObservable(context)));
    }

    private DisposableSingleObserver<JsonArray> getRestaurantObservable(final @NonNull Context context) {
        return new DisposableSingleObserver<JsonArray>() {
            @Override
            public void onSuccess(JsonArray value) {
                Log.d(TAG, "getRestaurantObservable onSuccess ");
                List<RestaurantModel> modelList = getListFromServerResponse(value);
                if (modelList != null) {
                    mRestaurantModelLiveData.setValue(modelList);
                }
                mCompositeDisposable.dispose();
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "getRestaurantObservable onError ");
                mRestaurantModelLiveData.setValue(null);
                // Check if we loaded data previously if not show else no
                mErrorMessageLiveData.setValue(context.getString(R.string.error_message));
            }
        };
    }

    @VisibleForTesting
    public List<RestaurantModel> getListFromServerResponse(@NonNull JsonArray value) {

        List<RestaurantModel> modelList = null;
        if (value != null && value.size() > 0) {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<RestaurantInfo>>() {}.getType();
            List<RestaurantInfo> items = gson.fromJson(value.toString(), listType);

            // This layer of abstraction is added to pass data which is required for the list.
            modelList = new ArrayList<>(items.size());
            for (RestaurantInfo info : items) {
                if (info != null) {
                    RestaurantModel model = new RestaurantModel();
                    model.setRestaurantDescription(info.getDescription());
                    model.setRestaurantImageUrl(info.getImageUrl());
                    if (info.getBusiness() != null && info.getBusiness().getName() != null) {

                        if (info.getBusiness().getName() != null) {
                            model.setRestaurantName(info.getBusiness().getName());
                        }

                        if (info.getBusiness().getName() != null) {
                            model.setRestaurantId(info.getBusiness().getId());
                        }
                    }

                    // ToDo: Define as constant variable
                    if ("open".equals(info.getStatusType())) {
                        model.setStatus(info.getStatus());
                    } else {
                        model.setStatus(info.getStatusType());
                    }
                    modelList.add(model);
                }
            }
        }
        return modelList;
    }

}
