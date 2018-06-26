package codehive.foodrecept.models.view;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ItemResponse {
    @SerializedName("count")
    private int count;
    @SerializedName("recipes")
    private List<Item> results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Item> getResults() {
        return results;
    }

    public void setResults(List<Item> results) {
        this.results = results;
    }
}