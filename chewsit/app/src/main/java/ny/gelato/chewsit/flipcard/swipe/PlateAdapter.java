package ny.gelato.chewsit.flipcard.swipe;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.gms.location.places.GeoDataApi;
import com.yelp.clientlib.entities.Business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ny.gelato.chewsit.R;
import ny.gelato.chewsit.YelpHelper;
import ny.gelato.chewsit.data.DataSingleton;
import ny.gelato.chewsit.data.models.Result;

/**
 * Created by joshuagoldberg on 10/8/16.
 */

public class PlateAdapter extends RecyclerView.Adapter<PlateAdapter.ViewHolder> {

    private static final String TAG = "PlateAdapter";

    private ArrayList<Business> businesses;

    private List<Result> restaurants;

    private HashMap<String, Bitmap> photos;

    public PlateAdapter() {
        businesses = YelpHelper.getInstance().getBusinesses();
        restaurants = DataSingleton.getInstance().getRestaurants();
        photos = DataSingleton.getInstance().getPhotos();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View parentView = inflater.inflate(R.layout.plate, parent, false);
        ViewHolder viewHolder = new ViewHolder(parentView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        // Have to change the url path from /ms.jpg to /o.jpg to get full size images
        String imageUrl = businesses.get(position).imageUrl();
        String updatedImageUrl = null;

        if (imageUrl != null) {
            updatedImageUrl = imageUrl.substring(0, imageUrl.length() - 6) + "o.jpg";
        }

        Glide.with(holder.foodImage.getContext())
                    .load(updatedImageUrl)
                    .into(holder.foodImage);
    }

    @Override
    public int getItemCount() {
        return businesses.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.food_image)
        ImageView foodImage;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
