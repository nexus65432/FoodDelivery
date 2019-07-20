package co.android.fooddelivery.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import co.android.fooddelivery.R;
import co.android.fooddelivery.listener.RecyclerViewClickListener;
import co.android.fooddelivery.model.RestaurantModel;
import co.android.fooddelivery.util.MySharedPreference;

/**
 * Adapter providing a binding Restaurant data set to views that are displayed in List
 */
public class RestaurantsAdapter extends RecyclerView.Adapter<Restaurant> {

    private Context mContext;
    private List<RestaurantModel> mRestaurantModels;
    private RecyclerViewClickListener mRecyclerViewClickListener;

    public RestaurantsAdapter(@NonNull Context context,
                              @NonNull List<RestaurantModel> restaurantsList,
                              @NonNull RecyclerViewClickListener listener) {
        mContext = context;
        mRestaurantModels = restaurantsList;
        mRecyclerViewClickListener = listener;
    }

    @Override
    public Restaurant onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext)
                .inflate(R.layout.restaurant_row_item, parent, false);

        return new Restaurant(itemView, mRecyclerViewClickListener);
    }

    @Override
    public void onBindViewHolder(Restaurant holder, int position) {

        final RestaurantModel restaurant = mRestaurantModels.get(position);

        if (restaurant != null) {
            if (restaurant.getRestaurantImageUrl() != null) {
                Glide.with(mContext)
                        .load(restaurant.getRestaurantImageUrl())
                        .centerCrop()
                        .placeholder(R.mipmap.ic_default_restaurant)
                        .error(R.mipmap.ic_default_restaurant)
                        .into(holder.mRestaurantImage);
            }

            if (restaurant.getRestaurantName() != null) {
                holder.mRestaurantName.setText(restaurant.getRestaurantName());
            }

            if (restaurant.getRestaurantDescription() != null) {
                holder.mRestaurantDescription.setText(restaurant.getRestaurantDescription());
            }

            if (restaurant.getStatus() != null) {
                holder.mRestaurantStatus.setText(restaurant.getStatus());
            }

            boolean isFavourite = MySharedPreference.getInstance(mContext).getPreference(restaurant.getRestaurantId());
            holder.mFavourite.setVisibility(isFavourite ? View.VISIBLE : View.GONE);
        }
    }

    public void setNewRestaurants(List<RestaurantModel> list) {
        if (list == null) {
            return;
        }
        mRestaurantModels.addAll(list);
    }

    @Override
    public int getItemCount() {
        int retVal = 0;
        if (mRestaurantModels != null) {
            retVal = mRestaurantModels.size();
        }
        return retVal;
    }
}
