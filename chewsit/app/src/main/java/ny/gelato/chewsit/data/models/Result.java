package ny.gelato.chewsit.data.models;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("geometry")
    @Expose
    private Geometry geometry;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("opening_hours")
    @Expose
    private OpeningHours openingHours;
    @SerializedName("photos")
    @Expose
    private List<Photo> photos = new ArrayList<Photo>();
    @SerializedName("place_id")
    @Expose
    private String placeId;
    @SerializedName("scope")
    @Expose
    private String scope;
    @SerializedName("price_level")
    @Expose
    private int priceLevel;
    @SerializedName("rating")
    @Expose
    private float rating;
    @SerializedName("alt_ids")
    @Expose
    private List<AltId> altIds = new ArrayList<AltId>();
    @SerializedName("reference")
    @Expose
    private String reference;
    @SerializedName("types")
    @Expose
    private List<String> types = new ArrayList<String>();
    @SerializedName("vicinity")
    @Expose
    private String vicinity;

    /**
     * No args constructor for use in serialization
     *
     */
    public Result() {
    }

    /**
     *
     * @param photos
     * @param id
     * @param icon
     * @param vicinity
     * @param scope
     * @param placeId
     * @param openingHours
     * @param name
     * @param altIds
     * @param types
     * @param reference
     * @param geometry
     * @param priceLevel
     */
    public Result(Geometry geometry, String icon, String id, String name, OpeningHours openingHours, List<Photo> photos, String placeId, String scope, List<AltId> altIds, String reference, List<String> types, String vicinity, int priceLevel, float rating) {
        this.geometry = geometry;
        this.icon = icon;
        this.id = id;
        this.name = name;
        this.openingHours = openingHours;
        this.photos = photos;
        this.placeId = placeId;
        this.scope = scope;
        this.altIds = altIds;
        this.reference = reference;
        this.types = types;
        this.vicinity = vicinity;
        this.priceLevel = priceLevel;
        this.rating = rating;
    }

    /**
     *
     * @return
     * The geometry
     */
    public Geometry getGeometry() {
        return geometry;
    }

    /**
     *
     * @param geometry
     * The geometry
     */
    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    /**
     *
     * @return
     * The icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     *
     * @param icon
     * The icon
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     *
     * @return
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The openingHours
     */
    public OpeningHours getOpeningHours() {
        return openingHours;
    }

    /**
     *
     * @param openingHours
     * The opening_hours
     */
    public void setOpeningHours(OpeningHours openingHours) {
        this.openingHours = openingHours;
    }

    /**
     *
     * @return
     * The photos
     */
    public List<Photo> getPhotos() {
        return photos;
    }

    /**
     *
     * @param photos
     * The photos
     */
    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    /**
     *
     * @return
     * The placeId
     */
    public String getPlaceId() {
        return placeId;
    }

    /**
     *
     * @param placeId
     * The place_id
     */
    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    /**
     *
     * @return
     * The scope
     */
    public String getScope() {
        return scope;
    }

    /**
     *
     * @param priceLevel
     * The priceLevel
     */
    public void setPriceLevel(int priceLevel) {
        this.priceLevel = priceLevel;
    }

    /**
     *
     * @return
     * The rating
     */
    public float getRating() {
        return rating;
    }

    /**
     *
     * @param rating
     * The rating
     */
    public void setRating(float rating) {
        this.rating = rating;
    }

    /**
     *
     * @return
     * The priceLevel
     */
    public int getPriceLevel() {
        return priceLevel;
    }

    /**
     *
     * @param scope
     * The scope
     */
    public void setScope(String scope) {
        this.scope = scope;
    }

    /**
     *
     * @return
     * The altIds
     */
    public List<AltId> getAltIds() {
        return altIds;
    }

    /**
     *
     * @param altIds
     * The alt_ids
     */
    public void setAltIds(List<AltId> altIds) {
        this.altIds = altIds;
    }

    /**
     *
     * @return
     * The reference
     */
    public String getReference() {
        return reference;
    }

    /**
     *
     * @param reference
     * The reference
     */
    public void setReference(String reference) {
        this.reference = reference;
    }

    /**
     *
     * @return
     * The types
     */
    public List<String> getTypes() {
        return types;
    }

    /**
     *
     * @param types
     * The types
     */
    public void setTypes(List<String> types) {
        this.types = types;
    }

    /**
     *
     * @return
     * The vicinity
     */
    public String getVicinity() {
        return vicinity;
    }

    /**
     *
     * @param vicinity
     * The vicinity
     */
    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

}
