package ny.gelato.chewsit.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Geometry {

    @SerializedName("location")
    @Expose
    private Location location;

    /**
     * No args constructor for use in serialization
     *
     */
    public Geometry() {
    }

    /**
     *
     * @param location
     */
    public Geometry(Location location) {
        this.location = location;
    }

    /**
     *
     * @return
     * The location
     */
    public Location getLocation() {
        return location;
    }

    /**
     *
     * @param location
     * The location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

}
