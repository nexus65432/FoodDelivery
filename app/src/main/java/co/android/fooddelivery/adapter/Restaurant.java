package co.android.fooddelivery.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import co.android.fooddelivery.R;
import co.android.fooddelivery.listener.RecyclerViewClickListener;


/**
 * A ViewHolder object stores each of the component views inside the tag field of the Layout,
 * so you can immediately access them without the need to look them up repeatedly
 */
public class Restaurant extends RecyclerView.ViewHolder implements View.OnClickListener {

    private RecyclerViewClickListener mListener;

    public ImageView mRestaurantImage;
    public TextView mRestaurantName;
    public TextView mRestaurantDescription;
    public TextView mRestaurantStatus;

    public Restaurant(View itemView, RecyclerViewClickListener listener) {
        super(itemView);
        mListener = listener;
        mRestaurantImage = itemView.findViewById(R.id.restaurant_logo);
        mRestaurantName = itemView.findViewById(R.id.restaurant_name);
        mRestaurantDescription = itemView.findViewById(R.id.restaurant_description);
        mRestaurantStatus = itemView.findViewById(R.id.restaurant_status);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        mListener.onClick(view, getAdapterPosition());
    }
}
