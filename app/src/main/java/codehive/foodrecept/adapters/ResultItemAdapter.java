package codehive.foodrecept.adapters;

/**
 * Created by elha on 2/20/2018.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;

import codehive.foodrecept.R;
import codehive.foodrecept.models.view.Item;
import codehive.foodrecept.ui.activities.IngredientActivity;

public class ResultItemAdapter extends RecyclerView.Adapter<ResultItemAdapter.MyViewHolder> implements Filterable {

    private Context mContext;
    private List<Item> feedItemList;
    private List<Item> mFilteredList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, realname;
        public ImageView prof;
        private View view;

        public MyViewHolder(View view) {
            super(view);
            this.view=itemView;
            name = (TextView) view.findViewById(R.id.name_result);
            realname = (TextView) view.findViewById(R.id.real_name_result);
            prof = (ImageView) view.findViewById(R.id.prof_result);
        }

        public View getView() {
            return view;
        }
    }


    public ResultItemAdapter(Context mContext, List<Item> feedItemList) {
        this.mContext = mContext;
        this.feedItemList = feedItemList;
        this.mFilteredList = feedItemList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.result_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Item feedItem = mFilteredList.get(position);
        holder.name.setText(feedItem.getPublisher());
        holder.realname.setText(feedItem.getTitle());
        //loading album cover using Glide library
        Glide.with(mContext).load(feedItem.getImage_url()).into(holder.prof);
        holder.getView().setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.zoom_in));
        holder.prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, IngredientActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("recipe_id",feedItem.getRecipe_id());
                mContext.startActivity(intent);
            }
        });
    }

    /**
     * Showing popup menu when tapping on 3 dots
     */

    @Override
    public int getItemCount() {
        return mFilteredList.size();
    }


    @Override
    public Filter getFilter() {
        Log.d("buraaa"," " + feedItemList.size() + " " + mFilteredList.size());
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mFilteredList = feedItemList;
                    Log.d("buraaa","bosdu");
                } else {
                    List<Item> filteredList = new ArrayList<>();
                    for (Item coin : feedItemList) {
                        Log.d("coinT:",coin.getTitle() + " " + charString);
                        if (coin.getTitle().toLowerCase().contains(charString) || coin.getPublisher().toLowerCase().contains(charString)) {
                            filteredList.add(coin);
                        }
                    }
                    mFilteredList = filteredList;
                    Log.d("buraaa","aha: " + mFilteredList.size() + " + " + feedItemList.size());
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilteredList = (ArrayList<Item>)filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public int getItemViewType(int position)
    {
        return position;
    }

}