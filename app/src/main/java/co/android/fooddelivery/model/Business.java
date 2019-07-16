package co.android.fooddelivery.model;

import com.google.gson.annotations.SerializedName;

public class Business {

    @SerializedName("id")
    long id;
    @SerializedName("name")
    String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
