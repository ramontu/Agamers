package dam.agamers.gtidic.udl.agamers.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Jocs {

    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;
    @SerializedName("categories")
    private List<String> categories;
    @SerializedName("min_players")
    private Integer min_players;
    private String min_players_string;
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
    @SerializedName("descrition")
    private String description;
    @SerializedName("pegi")
    private Integer pegi;
    @SerializedName("aproved")
    private Boolean aproved;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    //Mètode per a fragment
    public void setMin_players_string(String min_players) {
        this.min_players = Integer.parseInt(min_players);
    }
    //Mètode pel fragment
    public String getMin_players_string() {
        return String.valueOf(min_players);
    }

    public Integer getMax_players() {
        return max_players;
    }

    public void setMax_players(Integer max_players) {
        this.max_players = max_players;
    }

    //Mètode per a fragment
    public void setMax_players_string(String max_players) {
        this.max_players = Integer.parseInt(max_players);
    }
    //Mètode pel fragment
    public String getMax_players_string() {
        return String.valueOf(max_players);
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
    //mètode pel fragment
    public String getPegi_string(){
        return String.valueOf(pegi);
    }
    //mètode pel fragment
    public void setPegi_string(String pegi){
        this.pegi = Integer.valueOf(pegi);
    }

    public Boolean getAproved() {
        return aproved;
    }

    public void setAproved(Boolean aproved) {
        this.aproved = aproved;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
