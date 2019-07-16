package co.android.fooddelivery.model;

import com.google.gson.annotations.SerializedName;

public class RestaurantInfo {

    @SerializedName("cover_img_url")
    String imageUrl;

    @SerializedName("business")
    Business business;

    @SerializedName("description")
    String description;

    @SerializedName("status_type")
    String statusType;

    @SerializedName("status")
    String status;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatusType() {
        return statusType;
    }

    public void setStatusType(String statusType) {
        this.statusType = statusType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
