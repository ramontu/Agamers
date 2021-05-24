package dam.agamers.gtidic.udl.agamers.models;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Jocs {

    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;
    @SerializedName("categories")
    private List<String> categories;
    @SerializedName("platforms")
    private List<String> platforms;
    @SerializedName("min_players")
    private int min_players;
    @SerializedName("max_players")
    private int max_players;
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
    private int pegi;
    @SerializedName("aproved")
    private Boolean aproved;
    @SerializedName("poster_url")
    private String poster_url;

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

    public int getMin_players() {
        return min_players;
    }

    public void setMin_players(int min_players) {
        this.min_players = min_players;
    }

    public int getMax_players() {
        return max_players;
    }

    public void setMax_players(int max_players) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPegi() {
        return pegi;
    }

    public void setPegi(int pegi) {
        this.pegi = pegi;
    }

    public Boolean getAproved() {
        return aproved;
    }

    public void setAproved(Boolean aproved) {
        this.aproved = aproved;
    }

    public List<String> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<String> platforms) {
        this.platforms = platforms;
    }

    public String getPoster_url() {
        return poster_url;
    }

    public void setPoster_url(String poster_url) {
        this.poster_url = poster_url;
    }

    @Override
    public boolean equals(Object o) {

        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

       /* Check if o is an instance of Complex or not
         "null instanceof [type]" also returns false */
        if (!(o instanceof Jocs)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        Jocs e = (Jocs) o;

        // Compare the data members and return accordingly
        return this.name.equals(e.getName());
             //   && this.max_players == e.getMax_players()
             //   && this.min_players == e.getMin_players()
//                && this.studio.equals(e.getStudio())
//                && this.description.equals(e.getDescription())
//                && this.pegi == e.getPegi()
//                && this.published.equals(e.getPublished());
    }
    @NotNull
    @Override
    public String toString(){

        return "name: " + name  + " descripciío: " + description + " platforms: " +platforms.toString();
    }

}
