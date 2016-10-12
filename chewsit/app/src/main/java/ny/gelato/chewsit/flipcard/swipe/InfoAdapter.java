package ny.gelato.chewsit.flipcard.swipe;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yelp.clientlib.entities.Business;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ny.gelato.chewsit.R;
import ny.gelato.chewsit.YelpHelper;
import ny.gelato.chewsit.data.DataSingleton;
import ny.gelato.chewsit.data.models.Result;

/**
 * Created by joshuagoldberg on 10/9/16.
 */

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.ViewHolder> {

    private ArrayList<Business> businesses;

    private List<Result> restaurants;

    public InfoAdapter() {
        businesses = YelpHelper.getInstance().getBusinesses();
        restaurants = DataSingleton.getInstance().getRestaurants();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View parentView = inflater.inflate(R.layout.info, parent, false);
        ViewHolder viewHolder = new ViewHolder(parentView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.infoText.setText(businesses.get(position).name());
        holder.infoRating.setText(businesses.get(position).rating() + "");
    }

    @Override
    public int getItemCount() {
        return businesses.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.info_name)
        TextView infoText;

        @BindView(R.id.info_rating)
        TextView infoRating;

        @BindView(R.id.fab_icon)
        FloatingActionButton fabIcon;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
