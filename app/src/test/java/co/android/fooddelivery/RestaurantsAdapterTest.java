package co.android.fooddelivery;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;

import co.android.fooddelivery.adapter.RestaurantsAdapter;
import co.android.fooddelivery.model.RestaurantModel;


public class RestaurantsAdapterTest {

    private RestaurantsAdapter mRestaurantsAdapter;

    @Mock
    private Context context;

    @Before
    public void setUp() {
        mRestaurantsAdapter = new RestaurantsAdapter(context, new ArrayList<RestaurantModel>(), null);
    }

    @Test
    public void testingAddingValuesToAdapter() {

        // Test 1 passing null values
        mRestaurantsAdapter.setNewRestaurants(null);

        // Test 2 passing emptylist
        mRestaurantsAdapter.setNewRestaurants(new ArrayList<RestaurantModel>());
    }

}
