package co.android.fooddelivery.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import co.android.fooddelivery.R;
import co.android.fooddelivery.adapter.RestaurantsAdapter;
import co.android.fooddelivery.listener.RecyclerViewClickListener;
import co.android.fooddelivery.model.RestaurantModel;
import co.android.fooddelivery.viewModel.RestaurantViewModel;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private RestaurantsAdapter mRestaurantsAdapter;
    private TextView mDefaultMessage;
    private RecyclerView mRestaurantsListView;
    private ProgressBar mProgressBar;

    private List<RestaurantModel> mRestaurantModels = new ArrayList<>();

    private RestaurantViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRestaurantsListView = findViewById(R.id.recylerView);
        mDefaultMessage = findViewById(R.id.error_message);
        mProgressBar = findViewById(R.id.progressbar_cyclic);
        setUpRecyclerView();
    }

    /**
     * Setup recyclerView
     */
    private void setUpRecyclerView() {
        mRestaurantsAdapter = new RestaurantsAdapter(this, mRestaurantModels, itemClickListener);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRestaurantsListView.getContext(),
                mLayoutManager.getOrientation());

        mRestaurantsListView.setItemAnimator(new DefaultItemAnimator());
        mRestaurantsListView.setLayoutManager(mLayoutManager);
        mRestaurantsListView.addItemDecoration(dividerItemDecoration);
        mRestaurantsListView.setAdapter(mRestaurantsAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        viewModel = ViewModelProviders.of(this).get(RestaurantViewModel.class);
        observerOnModel();
        // Making Network API call
        viewModel.getRestaurantsForCurrentLocation(this);
    }

    private void observerOnModel() {

        viewModel.getRestaurantsData().observe(this, new Observer() {
            @Override
            public void onChanged(@Nullable Object obj) {

                mProgressBar.setVisibility(View.GONE);
                if (obj == null) {
                    Log.d(TAG, "Not updating UI");
                    mDefaultMessage.setVisibility(View.VISIBLE);
                    mDefaultMessage.setText(getString(R.string.no_results_found));
                    return;
                }

                Log.d(TAG, "updating UI");
                mDefaultMessage.setVisibility(View.GONE);
                mRestaurantsListView.setVisibility(View.VISIBLE);

                mRestaurantModels.addAll((List<RestaurantModel>) obj);
                mRestaurantsAdapter.setNewRestaurants(mRestaurantModels);
                mRestaurantsAdapter.notifyDataSetChanged();
            }
        });

        viewModel.getErrorMessage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String value) {
                mProgressBar.setVisibility(View.GONE);
                if (value != null) {
                    mRestaurantsListView.setVisibility(View.GONE);
                    mDefaultMessage.setVisibility(View.VISIBLE);
                    mDefaultMessage.setText(value);
                } else {
                    mDefaultMessage.setVisibility(View.GONE);
                }
            }
        });
    }

    /**
     * Place holder to present detailed view of restaurant in a new activity
     * When user tap on any item on the list
     */
    RecyclerViewClickListener itemClickListener = new RecyclerViewClickListener() {
        @Override
        public void onClick(View view, int position) {
            Log.d(TAG, "User Clicked at position " + position);
            String displayText = mRestaurantModels.get(position).getRestaurantName();
            Toast.makeText(MainActivity.this, "Clicked on " + displayText, Toast.LENGTH_SHORT).show();
        }
    };

}
