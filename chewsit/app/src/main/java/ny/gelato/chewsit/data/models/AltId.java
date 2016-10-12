package ny.gelato.chewsit.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AltId {

    @SerializedName("place_id")
    @Expose
    private String placeId;
    @SerializedName("scope")
    @Expose
    private String scope;

    /**
     * No args constructor for use in serialization
     *
     */
    public AltId() {
    }

    /**
     *
     * @param scope
     * @param placeId
     */
    public AltId(String placeId, String scope) {
        this.placeId = placeId;
        this.scope = scope;
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
     * @param scope
     * The scope
     */
    public void setScope(String scope) {
        this.scope = scope;
    }

}