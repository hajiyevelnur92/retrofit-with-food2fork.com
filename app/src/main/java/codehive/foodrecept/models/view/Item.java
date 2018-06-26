package codehive.foodrecept.models.view;

/**
 * Created by elha on 2/19/2018.
 */

import com.google.gson.annotations.SerializedName;


public class Item {
    @SerializedName("publisher")
    private String publisher;
    @SerializedName("f2f_url")
    private String f2f_url;
    @SerializedName("title")
    private String title;
    @SerializedName("source_url")
    private String source_url;
    @SerializedName("recipe_id")
    private String recipe_id;
    @SerializedName("image_url")
    private String image_url;
    @SerializedName("social_rank")
    private Float social_rank;
    @SerializedName("publisher_url")
    private String publisher_url;

    public Item(String publisher, String f2f_url, String title, String source_url, String recipe_id, String image_url, Float social_rank, String publisher_url) {
        this.publisher = publisher;
        this.f2f_url = f2f_url;
        this.title = title;
        this.source_url = source_url;
        this.recipe_id = recipe_id;
        this.image_url = image_url;
        this.social_rank = social_rank;
        this.publisher_url = publisher_url;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getF2f_url() {
        return f2f_url;
    }

    public void setF2f_url(String f2f_url) {
        this.f2f_url = f2f_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource_url() {
        return source_url;
    }

    public void setSource_url(String source_url) {
        this.source_url = source_url;
    }

    public String getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(String recipe_id) {
        this.recipe_id = recipe_id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public Float getSocial_rank() {
        return social_rank;
    }

    public void setSocial_rank(Float social_rank) {
        this.social_rank = social_rank;
    }

    public String getPublisher_url() {
        return publisher_url;
    }

    public void setPublisher_url(String publisher_url) {
        this.publisher_url = publisher_url;
    }
}