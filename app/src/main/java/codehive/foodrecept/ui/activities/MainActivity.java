package codehive.foodrecept.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.speech.RecognizerIntent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import codehive.foodrecept.MyApplication;
import codehive.foodrecept.R;
import codehive.foodrecept.adapters.ItemAdapter;
import codehive.foodrecept.models.view.Test;
import codehive.mysearch.persistentsearch.SearchBox;
import codehive.mysearch.persistentsearch.SearchResult;

import static codehive.foodrecept.data.Constant_Data.hashtags;
import static codehive.foodrecept.data.Constant_Data.imgURL;
import static codehive.foodrecept.data.Constant_Data.names;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private static final String TAG = "MainActivity";
    public static final long FIND_SUGGESTION_SIMULATED_DELAY = 250;
    private String mLastQuery = "Search...";
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.searchbox)
    SearchBox mSearchView;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private ItemAdapter adapter;
    private List<Test> feedItemList;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        context = this;

        if (!MyApplication.isIntroShown(this)) {
            startActivity(new Intent(this, IntroActivity.class));
            overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
        }
        setupSearchBar();
        navigationView.setNavigationItemSelectedListener(this);
        initialize();
    }

    private void initialize(){
        feedItemList = new ArrayList<Test>();
        adapter = new ItemAdapter(this, feedItemList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        int resId = R.anim.layout_animation_fall_down;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(this, resId);
        recyclerView.setLayoutAnimation(animation);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        //sample test
        createSample();
    }

    private void createSample(){
        Test generalItem;
        for(int i=0;i<names.length;i++){
            generalItem = new Test(i,names[i],hashtags[i],imgURL[i],"nextpage");
            feedItemList.add(generalItem);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        /*if (mSearchView.setSearchFocused(false)) {
            super.onBackPressed();
        }*/
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.home) {
            // Handle the camera action
        } else if (id == R.id.favorite) {
            Toast.makeText(getApplicationContext(),"coming soon...",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.shopping) {
            Toast.makeText(getApplicationContext(),"coming soon...",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_share) {
            Toast.makeText(getApplicationContext(),"coming soon...",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.setting) {
            Toast.makeText(getApplicationContext(),"coming soon...",Toast.LENGTH_SHORT).show();
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setupSearchBar() {
        mSearchView.enableVoiceRecognition(this);
        mSearchView.setLogoText("Search...");
        mSearchView.setMenuListener(new SearchBox.MenuListener(){

            @Override
            public void onMenuClick() {
                //Hamburger has been clicked
                drawer.openDrawer(Gravity.START);
            }

        });
        mSearchView.setSearchListener(new SearchBox.SearchListener(){

            @Override
            public void onSearchOpened() {
                //Use this to tint the screen
            }

            @Override
            public void onSearchClosed() {
                //Use this to un-tint the screen
            }

            @Override
            public void onSearchTermChanged(String s) {
                //React to the search term changing
                //Called after it has updated results
            }

            @Override
            public void onSearch(String searchTerm) {
                Intent i;
                i = new Intent(MainActivity.this,SecondActivity.class);
                i.putExtra("search_value",searchTerm);
                startActivity(i);
                overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
            }

            @Override
            public void onResultClick(SearchResult result){
                //React to a result being clicked
            }

            @Override
            public void onSearchCleared() {

            }

        });
    }

    //check that voice recognition is available on the user's device.
    public void checkVoiceRecognition() {
        // Check if voice recognition is present
        PackageManager pm = getPackageManager();
        List<ResolveInfo> activities = pm.queryIntentActivities(new Intent(
                RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);
        if (activities.size() == 0) {
            Toast.makeText(this, "Voice recognizer not present",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SearchBox.VOICE_RECOGNITION_CODE && resultCode == RESULT_OK) {
            ArrayList<String> matches = data
                    .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            mSearchView.populateEditText(matches.toString().replace("[","").replace("]",""));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}