package ny.gelato.chewsit;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.GeoDataApi;
import com.google.android.gms.location.places.PlacePhotoMetadata;
import com.google.android.gms.location.places.PlacePhotoMetadataBuffer;
import com.google.android.gms.location.places.PlacePhotoMetadataResult;
import com.google.android.gms.location.places.Places;
import com.yelp.clientlib.entities.Business;
import com.yelp.clientlib.entities.options.CoordinateOptions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ny.gelato.chewsit.data.DataSingleton;
import ny.gelato.chewsit.data.models.PlacesResponse;
import ny.gelato.chewsit.data.models.Result;
import ny.gelato.chewsit.flipcard.FlipCardFragment;
import ny.gelato.chewsit.flipcard.FlipListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity
        implements FlipListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private static final String TAG = "MainActivity";
    
    private Location mCurrentLocation;
    
    private GoogleApiClient mGoogleApiClient;

    FlipCardFragment flipCardFragment;

    @BindView(R.id.tabs)
    TabLayout tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);

        buildGoogleApiClient();

        flipCardFragment = FlipCardFragment.newInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Get permission for fine location
        if (ActivityCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            // Location permission given, start location updates
            mGoogleApiClient.connect();

        } else {

            // Permission not yet given, ask for it
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.
                    ACCESS_FINE_LOCATION}, 300);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onFlip() {
        flipCardFragment.flip();
    }

    @Override
    public void onBackPressed() {
        if (flipCardFragment.isShowingBack()) {
            flipCardFragment.flip();
        } else {
            super.onBackPressed();
        }
    }

    private synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .addApi(Places.GEO_DATA_API)
                //.addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();
    }

    public void getNearbyPlaces() {

        String key = getString(R.string.places_api_key);
        String location = mCurrentLocation.getLatitude() + "," + mCurrentLocation.getLongitude();
        String type = "restaurant";
        String rankBy = "distance";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PlacesService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PlacesService service = retrofit.create(PlacesService.class);

        Call<PlacesResponse> call = service.getNearbyPlaces(key, location, type, rankBy);

        call.enqueue(new Callback<PlacesResponse>() {
            @Override
            public void onResponse(Call<PlacesResponse> call, Response<PlacesResponse> response) {
                try {

                    PlacesResponse placesResponse = response.body();
                    DataSingleton.getInstance().setRestaurants(placesResponse.getResults());
                    getPhotosFromResults(placesResponse.getResults(), 0);

                    for (Result result: placesResponse.getResults()){
                        Log.d(TAG, "onResponse: name: " + result.getName());
                        for (String type: result.getTypes()){
                            Log.d(TAG, "onResponse: type: " + type);
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<PlacesResponse> call, Throwable t) {
                Log.w(TAG, "onFailure: ", t);
            }
        });

        new YelpSearchTask() {
            @Override
            protected void onPostExecute(ArrayList<Business> businesses) {
                super.onPostExecute(businesses);

                getFragmentManager().beginTransaction()
                        .replace(R.id.main_content, flipCardFragment)
                        .commit();
            }
        }.execute();
    }

    public void getPhotosFromResults(final List<Result> results, final int position) {

        if (position < results.size()) {

            new PhotoTask() {
                @Override
                protected void onPostExecute(Bitmap bitmap) {
                    super.onPostExecute(bitmap);
                    DataSingleton.getInstance().getPhotos().put(results.get(position).getPlaceId(), bitmap);
                    getPhotosFromResults(results, position+1);
                }
            }.execute(results.get(position).getPlaceId());
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        Log.d(TAG, "onConnected: ");

        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(180_000);
        locationRequest.setFastestInterval(15_000);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        // Get permission for fine location
        if (ActivityCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            if (mCurrentLocation == null) {
                mCurrentLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            }

            // Location permission given, start location updates
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, locationRequest, this);

            getNearbyPlaces();
        }

    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "Connection suspended");
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = "
                + connectionResult.getErrorCode());
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG, "onLocationChanged: " + location.toString());
        mCurrentLocation = location;
    }

    class PhotoTask extends AsyncTask<String, Void, Bitmap> {

        /**
         * Loads the first photo for a place id from the Geo Data API.
         * The place id must be the first (and only) parameter.
         */
        @Override
        protected Bitmap doInBackground(String... params) {
            if (params.length != 1) {
                return null;
            }
            final String placeId = params[0];
            Bitmap image = null;

            PlacePhotoMetadataResult result = Places.GeoDataApi
                    .getPlacePhotos(mGoogleApiClient, placeId).await();

            if (result.getStatus().isSuccess()) {
                PlacePhotoMetadataBuffer photoMetadataBuffer = result.getPhotoMetadata();
                if (photoMetadataBuffer.getCount() > 0 && !isCancelled()) {
                    // Get the first bitmap and its attributions.
                    PlacePhotoMetadata photo = photoMetadataBuffer.get(0);
                    // Load a scaled bitmap for this photo.
                    image = photo.getScaledPhoto(mGoogleApiClient, 1000, 1000).await().getBitmap();
                }
                // Release the PlacePhotoMetadataBuffer.
                photoMetadataBuffer.release();
            }
            return image;
        }
    }

    class YelpSearchTask extends AsyncTask<Void, Void, ArrayList<Business>> {

        @Override
        protected ArrayList<Business> doInBackground(Void... voids) {

            double lat = mCurrentLocation.getLatitude();
            double lng = mCurrentLocation.getLongitude();

            CoordinateOptions coordinate = CoordinateOptions.builder()
                    .latitude(lat)
                    .longitude(lng)
                    .build();

            return YelpHelper.getInstance().businessSearch(coordinate, YelpHelper.getInstance().getRadius());
        }
    }
}
