package ny.gelato.chewsit;

import ny.gelato.chewsit.data.models.PlacesResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by joshuagoldberg on 10/9/16.
 */

public interface PlacesService {

    public static final String BASE_URL = "https://maps.googleapis.com/maps/api/place/";

    @GET("nearbysearch/json")
    Call<PlacesResponse> getNearbyPlaces(@Query("key") String apiKey,
                                        @Query("location") String location,
                                        @Query("type") String type,
                                        @Query("rankby") String rankBy);
}
