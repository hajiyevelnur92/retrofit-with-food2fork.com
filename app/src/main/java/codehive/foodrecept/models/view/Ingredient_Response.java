package codehive.foodrecept.models.view;

/**
 * Created by elha on 2/19/2018.
 */

import com.google.gson.annotations.SerializedName;

public class Ingredient_Response {
    @SerializedName("recipe")
    private Ingredient results;

    public Ingredient getResults() {
        return results;
    }

    public void setResults(Ingredient results) {
        this.results = results;
    }
}