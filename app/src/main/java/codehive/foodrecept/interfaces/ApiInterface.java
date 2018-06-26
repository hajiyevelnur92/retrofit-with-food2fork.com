package codehive.foodrecept.interfaces;

import codehive.foodrecept.models.view.Ingredient_Response;
import codehive.foodrecept.models.view.ItemResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;


public interface ApiInterface {
    @GET("search")
    Call<ItemResponse> getalllist(@Query("key") String apiKey);

    @GET
    Call<ItemResponse> getsearchList(@Url String url);

    @GET
    Call<Ingredient_Response> getIngredient(@Url String url);

}
