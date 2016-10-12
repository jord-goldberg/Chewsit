package ny.gelato.chewsit.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OpeningHours {

    @SerializedName("open_now")
    @Expose
    private boolean openNow;

    /**
     * No args constructor for use in serialization
     *
     */
    public OpeningHours() {
    }

    /**
     *
     * @param openNow
     */
    public OpeningHours(boolean openNow) {
        this.openNow = openNow;
    }

    /**
     *
     * @return
     * The openNow
     */
    public boolean isOpenNow() {
        return openNow;
    }

    /**
     *
     * @param openNow
     * The open_now
     */
    public void setOpenNow(boolean openNow) {
        this.openNow = openNow;
    }

}
