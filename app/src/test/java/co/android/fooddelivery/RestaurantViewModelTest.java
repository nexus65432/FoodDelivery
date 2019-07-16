package co.android.fooddelivery;

import com.google.gson.JsonArray;

import org.junit.Before;
import org.junit.Test;

import co.android.fooddelivery.viewModel.RestaurantViewModel;

public class RestaurantViewModelTest {

    private RestaurantViewModel mRestaurantViewModel;

    private static final JsonArray JSON_RESPONSE = new JsonArray();

    @Before
    public void setUp() {
        mRestaurantViewModel = new RestaurantViewModel();
    }

    @Test
    public void testingServerResponse() {

        // Test 1 Testing null response
        mRestaurantViewModel.getListFromServerResponse(null);

        // Test 2 Testing empty response
        mRestaurantViewModel.getListFromServerResponse(JSON_RESPONSE);
    }

}
