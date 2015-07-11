package games.xeed.hackerrank.com.assignment2.model;

import java.util.List;

/**
 * Created by nitinraj.arvind on 7/11/2015.
 */
public class GameItem {

    String name;
    String image;
    String url;
    String price;
    String rating;
    String description;
    List<DemographicItem> demographic;

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
