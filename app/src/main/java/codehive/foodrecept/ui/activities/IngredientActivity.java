package codehive.foodrecept.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import butterknife.BindView;
import butterknife.ButterKnife;
import codehive.foodrecept.R;
import codehive.foodrecept.interfaces.ApiInterface;
import codehive.foodrecept.models.view.Ingredient;
import codehive.foodrecept.models.view.Ingredient_Response;
import codehive.foodrecept.networks.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IngredientActivity extends AppCompatActivity {

    /*It is prefered that to use constraintlayout which flat evething inside into one*/

    private final static String API_KEY = "PLEASE_PASTE_YOUR_API_HERE";
    private static final String TAG = "SecondActivity";
    private String query;

    @BindView(R.id.toolbar_ingredient)
    Toolbar toolbar;

    @BindView(R.id.title_ingredient)
    TextView title_ingredient;


    @BindView(R.id.textView)
    TextView textView;

    @BindView(R.id.container)
    LinearLayout linearLayout;

    @BindView(R.id.ingredient_photo)
    ImageView ingredient_photo;

    @BindView(R.id.ratingBar)
    RatingBar ratingBar;

    private float rateValue =0;
    private String ingedients = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient);
        ButterKnife.bind(this);

        if (API_KEY.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please obtain your API KEY first", Toast.LENGTH_LONG).show();
            return;
        }

        if(getIntent()!=null){
            query = getIntent().getStringExtra("recipe_id");
        }
        setupToolbar();
        sendNetworkRequest();

    }

    private void setupToolbar() {
        toolbar.setTitle(query);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void sendNetworkRequest(){
        ApiInterface apiService =
                ApiClient.getSearch().create(ApiInterface.class);
        String search = "http://food2fork.com/api/get?key={PLEASE_PASTE_YOUR_API_HERE}&rId=";
        Call<Ingredient_Response> call = apiService.getIngredient(search + query);
        Log.d("url:", call.request().url() + " url");
        call.enqueue(new Callback<Ingredient_Response>() {
            @Override
            public void onResponse(Call<Ingredient_Response>call, Response<Ingredient_Response> response) {
                Log.d("items:",response.body().getResults() + " " + response.body().getResults());
                Ingredient items = response.body().getResults();
                title_ingredient.setText(items.getTitle());
                Glide.with(getApplicationContext()).load(items.getImage_url()).into(ingredient_photo);
                float d= (float) ((items.getSocial_rank()*5) /100);
                ratingBar.setRating(d);
                rateValue = d;
                toolbar.setTitle(items.getPublisher());
                //this part can be also done by programically adding textview into linearlayout container
                for(int i=0;i<items.getIngredients().length;i++){
                    ingedients = ingedients + (i+1)+". " + items.getIngredients()[i] + "\n";
                }
                textView.setText(ingedients);
            }
            @Override
            public void onFailure(Call<Ingredient_Response>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }
}
