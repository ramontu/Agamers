package dam.agamers.gtidic.udl.agamers.models;

import com.google.gson.annotations.SerializedName;

public class Profile {
    @SerializedName("username")
    String username;
    @SerializedName("longdesc")
    String longdesc;
    @SerializedName("shortdesc")
    String shortdesc;
    @SerializedName("image")
    String image;
    @SerializedName("location")
    String location;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLongdesc() {
        return longdesc;
    }

    public void setLongdesc(String longdesc) {
        this.longdesc = longdesc;
    }

    public String getShortdesc() {
        return shortdesc;
    }

    public void setShortdesc(String shortdesc) {
        this.shortdesc = shortdesc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Profile(String username, String longdesc, String shortdesc, String image, String location) {
        this.username = username;
        this.longdesc = longdesc;
        this.shortdesc = shortdesc;
        this.image = image;
        this.location = location;
    }



}
