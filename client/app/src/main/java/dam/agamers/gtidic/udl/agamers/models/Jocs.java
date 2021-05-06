package dam.agamers.gtidic.udl.agamers.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Jocs {

    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("categories")
    private List<String> categories;
    @SerializedName("min_players")
    private Integer min_players;
    @SerializedName("max_players")
    private Integer max_players;
    @SerializedName("online_mode")
    private Boolean online__mode;
    @SerializedName("published")
    private String published;
    @SerializedName("studio")
    private String studio;
    @SerializedName("image")
    private String image;
    @SerializedName("pegi")
    private Integer pegi;
    @SerializedName("aproved")
    private Boolean aproved;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public Integer getMin_players() {
        return min_players;
    }

    public void setMin_players(Integer min_players) {
        this.min_players = min_players;
    }

    public Integer getMax_players() {
        return max_players;
    }

    public void setMax_players(Integer max_players) {
        this.max_players = max_players;
    }

    public Boolean getOnline__mode() {
        return online__mode;
    }

    public void setOnline__mode(Boolean online__mode) {
        this.online__mode = online__mode;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getPegi() {
        return pegi;
    }

    public void setPegi(Integer pegi) {
        this.pegi = pegi;
    }

    public Boolean getAproved() {
        return aproved;
    }

    public void setAproved(Boolean aproved) {
        this.aproved = aproved;
    }
}
