package ny.gelato.chewsit.data;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ny.gelato.chewsit.data.models.Result;

/**
 * Created by joshuagoldberg on 9/9/16.
 */
public class DataSingleton {

    List<Result> restaurants;

    HashMap<String, Bitmap> photos;

    private static DataSingleton sInstance;

    private DataSingleton() {
        restaurants = new ArrayList<>();
        photos = new HashMap<>();
    }

    public static DataSingleton getInstance() {
        if (sInstance == null) {
            sInstance = new DataSingleton();
        }
        return sInstance;
    }

    public List<Result> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Result> restaurants) {
        this.restaurants = restaurants;
    }

    public HashMap<String, Bitmap> getPhotos() {
        return photos;
    }
}
