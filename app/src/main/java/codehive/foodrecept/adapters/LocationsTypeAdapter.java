package codehive.foodrecept.adapters;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

import codehive.foodrecept.models.view.Ingredient_Response;

/**
 * Created by elha on 2/20/2018.
 */


public class LocationsTypeAdapter extends TypeAdapter<Ingredient_Response> {

    private Gson gson = new Gson();

    @Override
    public void write(JsonWriter jsonWriter, Ingredient_Response locations) throws IOException {
        gson.toJson(locations, Ingredient_Response.class, jsonWriter);
    }

    @Override
    public Ingredient_Response read(JsonReader jsonReader) throws IOException {
        Ingredient_Response locations = null;

        jsonReader.beginObject();
        jsonReader.nextName();

        if (jsonReader.peek() == JsonToken.BEGIN_ARRAY) {
            //locations = new Ingredient_Response((Ingredient[]) gson.fromJson(jsonReader, Ingredient[].class));
        } else if(jsonReader.peek() == JsonToken.BEGIN_OBJECT) {
            //locations = new Ingredient_Response((Ingredient) gson.fromJson(jsonReader, Ingredient.class));
        } else {
            throw new JsonParseException("Unexpected token " + jsonReader.peek());
        }

        jsonReader.endObject();
        return locations;
    }
}