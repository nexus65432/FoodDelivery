package co.android.fooddelivery.model;

public class RestaurantModel {

    String restaurantImageUrl;
    String restaurantName; // "name": "Tommy Thai" under Business
    String restaurantDescription; // "description": "Thai, Cambodian",
    String status; // "status": "26 mins",

    public String getRestaurantImageUrl() {
        return restaurantImageUrl;
    }

    public void setRestaurantImageUrl(String restaurantImageUrl) {
        this.restaurantImageUrl = restaurantImageUrl;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantDescription() {
        return restaurantDescription;
    }

    public void setRestaurantDescription(String restaurantDescription) {
        this.restaurantDescription = restaurantDescription;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
