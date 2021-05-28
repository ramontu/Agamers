package dam.agamers.gtidic.udl.agamers.models;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

public class Categories {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    public String toString(){
        return name;
    }
}
