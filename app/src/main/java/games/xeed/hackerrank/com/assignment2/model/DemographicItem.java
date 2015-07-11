package games.xeed.hackerrank.com.assignment2.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by nitinraj.arvind on 7/11/2015.
 */
public class DemographicItem implements Parcelable {
    String country;
    String percentage;



    /**
     * @hide
     * */
    public static final Creator<DemographicItem> CREATOR =
            new Creator<DemographicItem>() {

                @Override
                public DemographicItem createFromParcel(Parcel source) {
                    return new DemographicItem(source);
                }

                @Override
                public DemographicItem[] newArray(int size) {
                    return new DemographicItem[size];
                }
            };

    /**
     * @hide
     * */
    public DemographicItem(Parcel in){
        readFromParcel(in);
    }

    /**
     * @hide
     * */
    public DemographicItem(){
        super();
    }

    /**
     * @hide
     * */
    private void readFromParcel(Parcel in){
        country = in.readString();
        percentage = in.readString();
    }

    /**
     * @hide
     * */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(country);
        dest.writeString(percentage);
    }


    /**
     * @hide
     * */
    @Override
    public int describeContents() {
        return 0;
    }



    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }
}
