package games.xeed.hackerrank.com.assignment2.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nitinraj.arvind on 7/11/2015.
 */
public class GameItem  implements Parcelable{

    String name;
    String image;
    String url;
    String price;
    String rating;
    String description;
    List<DemographicItem> demographic;


    /**
     * @hide
     * */
    public static final Creator<GameItem> CREATOR =
            new Creator<GameItem>() {

                @Override
                public GameItem createFromParcel(Parcel source) {
                    return new GameItem(source);
                }

                @Override
                public GameItem[] newArray(int size) {
                    return new GameItem[size];
                }
            };

    /**
     * @hide
     * */
    public GameItem(Parcel in){
        readFromParcel(in);
    }

    /**
     * @hide
     * */
    public GameItem(){
        super();
    }

    /**
     * @hide
     * */
    private void readFromParcel(Parcel in){
        name = in.readString();
        image = in.readString();
        url = in.readString();
        price = in.readString();
        rating = in.readString();
        description = in.readString();
        if(demographic==null){
            demographic = new ArrayList<DemographicItem>();
        }
        in.readList(demographic, DemographicItem.class.getClassLoader());
    }

    /**
     * @hide
     * */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(image);
        dest.writeString(url);
        dest.writeString(price);
        dest.writeString(rating);
        dest.writeString(description);
        if(demographic==null){
            demographic = new ArrayList<DemographicItem>();
        }
        dest.writeList(demographic);
    }


    /**
     * @hide
     * */
    @Override
    public int describeContents() {
        return 0;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<DemographicItem> getDemographic() {
        return demographic;
    }

    public void setDemographic(List<DemographicItem> demographic) {
        this.demographic = demographic;
    }
}
