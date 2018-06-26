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
import codehive.foodrecept.models.view.Test;
import codehive.foodrecept.ui.activities.SecondActivity;


public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> implements Filterable {

    private Context mContext;
    private List<Test> feedItemList;
    private List<Test> mFilteredList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, realname;
        public ImageView prof;
        private View view;

        public MyViewHolder(View view) {
            super(view);
            this.view=itemView;
            name = (TextView) view.findViewById(R.id.name);
            realname = (TextView) view.findViewById(R.id.real_name);
            prof = (ImageView) view.findViewById(R.id.prof);
        }

        public View getView() {
            return view;
        }
    }


    public ItemAdapter(Context mContext, List<Test> feedItemList) {
        this.mContext = mContext;
        this.feedItemList = feedItemList;
        this.mFilteredList = feedItemList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Test feedItem = mFilteredList.get(position);
        holder.name.setText(feedItem.getName());
        holder.realname.setText(feedItem.getRname());
        // loading album cover using Glide library
        Glide.with(mContext).load(feedItem.getImage()).into(holder.prof);
        holder.getView().setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.zoom_in));
        holder.prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i;
                i = new Intent(mContext,SecondActivity.class);
                /*you can also use bundle to send more data*/
                i.putExtra("search_value",feedItem.getName());
                mContext.startActivity(i);
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
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                Log.d("buraaa",charSequence.toString() + " text");
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mFilteredList = feedItemList;
                    Log.d("buraaa","bosdu");
                } else {
                    List<Test> filteredList = new ArrayList<>();
                    for (Test coin : feedItemList) {
                        /*if (coin.getStatus().toLowerCase().contains(charString) || coin.getDes().toLowerCase().contains(charString)
                                || coin.getName().toLowerCase().contains(charString)) {
                            filteredList.add(coin);
                        }*/
                    }
                    mFilteredList = filteredList;
                    Log.d("buraaa","aha: " + mFilteredList.size());
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilteredList = (ArrayList<Test>)filterResults.values;
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