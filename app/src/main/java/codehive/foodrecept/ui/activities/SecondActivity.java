package codehive.foodrecept.ui.activities;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import codehive.foodrecept.R;
import codehive.foodrecept.adapters.ResultItemAdapter;
import codehive.foodrecept.interfaces.ApiInterface;
import codehive.foodrecept.models.view.Item;
import codehive.foodrecept.models.decoration.GridSpacingItemDecoration;
import codehive.foodrecept.models.view.ItemResponse;
import codehive.foodrecept.networks.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecondActivity extends AppCompatActivity {

    private final static String API_KEY = "PLEASE_PASTE_YOUR_API_HERE";
    private static final String TAG = "SecondActivity";

    private String query;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler_view_result)
    RecyclerView recyclerView;
    @BindView(R.id.result_swipe)
    SwipeRefreshLayout swipeRefreshLayout;

    private ResultItemAdapter adapter;
    private List<Item> feedItemList;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        ButterKnife.bind(this);

        if (API_KEY.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please obtain your API KEY first", Toast.LENGTH_LONG).show();
            return;
        }

        if(getIntent()!=null){
            query = getIntent().getStringExtra("search_value");
        }
        setupToolbar();

        init();

        sendNetworkRequest();
    }

    private void sendNetworkRequest(){
        ApiInterface apiService =
                ApiClient.getSearch().create(ApiInterface.class);
        String search = "http://food2fork.com/api/search?key={PLEASE_PASTE_YOUR_API_HERE}&q=";
        Call<ItemResponse> call = apiService.getsearchList(search + query);
        Log.d("url:", call.request().url() + " url");
        call.enqueue(new Callback<ItemResponse>() {
            @Override
            public void onResponse(Call<ItemResponse>call, Response<ItemResponse> response) {
                Log.d("items:",response.body().getResults() + " " + response.body().getCount());
                toolbar.setTitle(query + " (" + response.body().getCount() + ")");
                List<Item> items = response.body().getResults();
                recyclerView.setAdapter(new ResultItemAdapter(getApplicationContext(),items));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ItemResponse>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }

        });
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
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void init() {
        feedItemList = new ArrayList<Item>();
        adapter = new ResultItemAdapter(this, feedItemList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(calculateNoOfColumns(getApplicationContext()), dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        setupSwipeRefreshLayout();
    }

    private void setupSwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //feedItemList.clear();
                getCoinMarketCapData();
            }
        });
    }

    public void getCoinMarketCapData() {
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(false);
        }

    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    public static int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int scalingFactor = 200; // You can vary the value held by the scalingFactor
        int columnCount = (int) (dpWidth / scalingFactor);
        return (columnCount>=2?columnCount:2); // if column no. is less than 2, we still display 2 columns
    }
}
