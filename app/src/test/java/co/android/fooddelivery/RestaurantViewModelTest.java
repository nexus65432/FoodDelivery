package co.android.fooddelivery;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import co.android.fooddelivery.model.RestaurantModel;
import co.android.fooddelivery.viewModel.RestaurantViewModel;

public class RestaurantViewModelTest {

    private RestaurantViewModel mRestaurantViewModel;

    private static final JsonArray EMPTY_JSON_RESPONSE = new JsonArray();

    private static final String INVALID_INPUT = "[{\n" +
            "  \"is_time\": false,\n" +
            "  \"max_order\": null,\n" +
            "  \"delivery\": 0,\n" +
            "  \"max_score\": 10" +
            "}]";
    private static final JsonArray INVALID_JSON_RESPONSE = new JsonParser().parse(INVALID_INPUT).getAsJsonArray();

    private static final String ONE_RECORD_INPUT = "[{\n" +
            "  \"is_time_surging\": false,\n" +
            "  \"max_order_size\": null,\n" +
            "  \"delivery_fee\": 0,\n" +
            "  \"max_composite_score\": 10,\n" +
            "  \"id\": 4,\n" +
            "  \"merchant_promotions\": [\n" +
            "    {\n" +
            "      \"minimum_subtotal_monetary_fields\": {\n" +
            "        \"currency\": \"USD\",\n" +
            "        \"display_string\": \"$0.00\",\n" +
            "        \"unit_amount\": null,\n" +
            "        \"decimal_places\": 2\n" +
            "      },\n" +
            "      \"delivery_fee\": null,\n" +
            "      \"delivery_fee_monetary_fields\": {\n" +
            "        \"currency\": \"USD\",\n" +
            "        \"display_string\": \"$0.00\",\n" +
            "        \"unit_amount\": null,\n" +
            "        \"decimal_places\": 2\n" +
            "      },\n" +
            "      \"minimum_subtotal\": null,\n" +
            "      \"new_store_customers_only\": false,\n" +
            "      \"id\": 18429\n" +
            "    },\n" +
            "    {\n" +
            "      \"minimum_subtotal_monetary_fields\": {\n" +
            "        \"currency\": \"USD\",\n" +
            "        \"display_string\": \"$0.00\",\n" +
            "        \"unit_amount\": null,\n" +
            "        \"decimal_places\": 2\n" +
            "      },\n" +
            "      \"delivery_fee\": null,\n" +
            "      \"delivery_fee_monetary_fields\": {\n" +
            "        \"currency\": \"USD\",\n" +
            "        \"display_string\": \"$0.00\",\n" +
            "        \"unit_amount\": null,\n" +
            "        \"decimal_places\": 2\n" +
            "      },\n" +
            "      \"minimum_subtotal\": null,\n" +
            "      \"new_store_customers_only\": false,\n" +
            "      \"id\": 6342\n" +
            "    },\n" +
            "    {\n" +
            "      \"minimum_subtotal_monetary_fields\": {\n" +
            "        \"currency\": \"USD\",\n" +
            "        \"display_string\": \"$0.00\",\n" +
            "        \"unit_amount\": null,\n" +
            "        \"decimal_places\": 2\n" +
            "      },\n" +
            "      \"delivery_fee\": null,\n" +
            "      \"delivery_fee_monetary_fields\": {\n" +
            "        \"currency\": \"USD\",\n" +
            "        \"display_string\": \"$0.00\",\n" +
            "        \"unit_amount\": null,\n" +
            "        \"decimal_places\": 2\n" +
            "      },\n" +
            "      \"minimum_subtotal\": null,\n" +
            "      \"new_store_customers_only\": false,\n" +
            "      \"id\": 6340\n" +
            "    },\n" +
            "    {\n" +
            "      \"minimum_subtotal_monetary_fields\": {\n" +
            "        \"currency\": \"USD\",\n" +
            "        \"display_string\": \"$0.00\",\n" +
            "        \"unit_amount\": null,\n" +
            "        \"decimal_places\": 2\n" +
            "      },\n" +
            "      \"delivery_fee\": null,\n" +
            "      \"delivery_fee_monetary_fields\": {\n" +
            "        \"currency\": \"USD\",\n" +
            "        \"display_string\": \"$0.00\",\n" +
            "        \"unit_amount\": null,\n" +
            "        \"decimal_places\": 2\n" +
            "      },\n" +
            "      \"minimum_subtotal\": null,\n" +
            "      \"new_store_customers_only\": false,\n" +
            "      \"id\": 6338\n" +
            "    },\n" +
            "    {\n" +
            "      \"minimum_subtotal_monetary_fields\": {\n" +
            "        \"currency\": \"USD\",\n" +
            "        \"display_string\": \"$0.00\",\n" +
            "        \"unit_amount\": null,\n" +
            "        \"decimal_places\": 2\n" +
            "      },\n" +
            "      \"delivery_fee\": null,\n" +
            "      \"delivery_fee_monetary_fields\": {\n" +
            "        \"currency\": \"USD\",\n" +
            "        \"display_string\": \"$0.00\",\n" +
            "        \"unit_amount\": null,\n" +
            "        \"decimal_places\": 2\n" +
            "      },\n" +
            "      \"minimum_subtotal\": null,\n" +
            "      \"new_store_customers_only\": false,\n" +
            "      \"id\": 6337\n" +
            "    }\n" +
            "  ],\n" +
            "  \"average_rating\": 4.6,\n" +
            "  \"menus\": [\n" +
            "    {\n" +
            "      \"popular_items\": [\n" +
            "        {\n" +
            "          \"price\": 1695,\n" +
            "          \"description\": \"Marinated chicken breast cubes, charcoal grilled.\",\n" +
            "          \"img_url\": \"https://res.cloudinary.com/doordash/image/fetch/c_fill,w_1200,h_672,q_auto:eco,f_auto/https://doordash-static.s3.amazonaws.com/media/photos/8b00da3b-85b2-4f11-a760-3d80bda5c913-retina-large.jpg\",\n" +
            "          \"id\": 31,\n" +
            "          \"name\": \"31. Chicken Shish-Kebab Plate\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"price\": 550,\n" +
            "          \"description\": \"Honey syrup with pistachios baked in filo.\",\n" +
            "          \"img_url\": \"https://res.cloudinary.com/doordash/image/fetch/c_fill,w_1200,h_672,q_auto:eco,f_auto/https://doordash-static.s3.amazonaws.com/media/photos/b41c62d7-8bd7-4740-832d-47af47c02544-retina-large.jpg\",\n" +
            "          \"id\": 41,\n" +
            "          \"name\": \"41. Baklava\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"price\": 1695,\n" +
            "          \"description\": \"Minced lamb spiced with parsley, onion, charcoal grilled.\",\n" +
            "          \"img_url\": \"https://res.cloudinary.com/doordash/image/fetch/c_fill,w_1200,h_672,q_auto:eco,f_auto/https://doordash-static.s3.amazonaws.com/media/photos/33bea136-8c5e-4616-93c7-72423100a628-retina-large.jpg\",\n" +
            "          \"id\": 35,\n" +
            "          \"name\": \"35. Adana Kebab\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"price\": 1075,\n" +
            "          \"description\": \"Hummus, dolma, baba ghanoujm potato salad, tahini sauce.\",\n" +
            "          \"img_url\": \"https://res.cloudinary.com/doordash/image/fetch/c_fill,w_1200,h_672,q_auto:eco,f_auto/https://doordash-static.s3.amazonaws.com/media/photos/a5537545-7e1f-4594-b1a3-7c8970a8d9cd-retina-large.jpg\",\n" +
            "          \"id\": 28,\n" +
            "          \"name\": \"28. Veggie Wrap\"\n" +
            "        }\n" +
            "      ],\n" +
            "      \"is_catering\": false,\n" +
            "      \"subtitle\": \"\",\n" +
            "      \"id\": 1,\n" +
            "      \"name\": \"Cafe 220\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"composite_score\": 9,\n" +
            "  \"status_type\": \"open\",\n" +
            "  \"is_only_catering\": false,\n" +
            "  \"status\": \"26 mins\",\n" +
            "  \"number_of_ratings\": 1601,\n" +
            "  \"asap_time\": 26,\n" +
            "  \"description\": \"Casual Mediterranean, serves halal meat\",\n" +
            "  \"business\": {\n" +
            "    \"id\": 7,\n" +
            "    \"name\": \"Cafe 220\"\n" +
            "  },\n" +
            "  \"tags\": [\n" +
            "    \"Mediterranean\",\n" +
            "    \"Middle East\",\n" +
            "    \"Turkish\"\n" +
            "  ],\n" +
            "  \"yelp_review_count\": 365,\n" +
            "  \"business_id\": 7,\n" +
            "  \"extra_sos_delivery_fee\": 0,\n" +
            "  \"yelp_rating\": 3.5,\n" +
            "  \"cover_img_url\": \"https://cdn.doordash.com/media/restaurant/cover/cafe_220_bayarea.png\",\n" +
            "  \"header_img_url\": \"https://res.cloudinary.com/doordash/image/fetch/c_fill,w_1200,h_672,q_auto:eco,f_auto/https://doordash-static.s3.amazonaws.com/media/photos/8b00da3b-85b2-4f11-a760-3d80bda5c913-retina-large.jpg\",\n" +
            "  \"address\": {\n" +
            "    \"city\": \"Palo Alto\",\n" +
            "    \"state\": \"CA\",\n" +
            "    \"street\": \"220 University Avenue\",\n" +
            "    \"lat\": 37.4449689,\n" +
            "    \"lng\": -122.1623888,\n" +
            "    \"printable_address\": \"220 University Avenue, Palo Alto, CA 94301, USA\"\n" +
            "  },\n" +
            "  \"price_range\": 2,\n" +
            "  \"slug\": \"cafe-220-palo-alto\",\n" +
            "  \"name\": \"Cafe 220\",\n" +
            "  \"is_newly_added\": false,\n" +
            "  \"url\": \"/store/cafe-220-palo-alto-4/\",\n" +
            "  \"service_rate\": 11,\n" +
            "  \"promotion\": null,\n" +
            "  \"featured_category_description\": null\n" +
            "}]";
    private static final JsonArray ONE_JSON_RESPONSE = new JsonParser().parse(ONE_RECORD_INPUT).getAsJsonArray();

    @Before
    public void setUp() {
        mRestaurantViewModel = new RestaurantViewModel();
    }

    @Test
    public void testingServerResponse() {

        // Test 1 Testing null response
        Assert.assertEquals(null, mRestaurantViewModel.getListFromServerResponse(null));

        // Test 2 Testing empty response
        Assert.assertEquals(null, mRestaurantViewModel.getListFromServerResponse(EMPTY_JSON_RESPONSE));

        // Test 3 Testing invalid JsonArray
        Assert.assertNotEquals(null, mRestaurantViewModel.getListFromServerResponse(INVALID_JSON_RESPONSE));

        List<RestaurantModel> items = new ArrayList<>();
        RestaurantModel model = new RestaurantModel();
        model.setRestaurantName("Cafe 220");
        items.add(model);

        // Test 4 Testing one restaurant
        Assert.assertEquals(model.getRestaurantName(),
                mRestaurantViewModel.getListFromServerResponse(ONE_JSON_RESPONSE).get(0).getRestaurantName());
    }

}
