package ny.gelato.chewsit.data.models;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Photo {

    @SerializedName("height")
    @Expose
    private int height;
    @SerializedName("html_attributions")
    @Expose
    private List<Object> htmlAttributions = new ArrayList<Object>();
    @SerializedName("photo_reference")
    @Expose
    private String photoReference;
    @SerializedName("width")
    @Expose
    private int width;

    /**
     * No args constructor for use in serialization
     *
     */
    public Photo() {
    }

    /**
     *
     * @param height
     * @param width
     * @param htmlAttributions
     * @param photoReference
     */
    public Photo(int height, List<Object> htmlAttributions, String photoReference, int width) {
        this.height = height;
        this.htmlAttributions = htmlAttributions;
        this.photoReference = photoReference;
        this.width = width;
    }

    /**
     *
     * @return
     * The height
     */
    public int getHeight() {
        return height;
    }

    /**
     *
     * @param height
     * The height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     *
     * @return
     * The htmlAttributions
     */
    public List<Object> getHtmlAttributions() {
        return htmlAttributions;
    }

    /**
     *
     * @param htmlAttributions
     * The html_attributions
     */
    public void setHtmlAttributions(List<Object> htmlAttributions) {
        this.htmlAttributions = htmlAttributions;
    }

    /**
     *
     * @return
     * The photoReference
     */
    public String getPhotoReference() {
        return photoReference;
    }

    /**
     *
     * @param photoReference
     * The photo_reference
     */
    public void setPhotoReference(String photoReference) {
        this.photoReference = photoReference;
    }

    /**
     *
     * @return
     * The width
     */
    public int getWidth() {
        return width;
    }

    /**
     *
     * @param width
     * The width
     */
    public void setWidth(int width) {
        this.width = width;
    }

}