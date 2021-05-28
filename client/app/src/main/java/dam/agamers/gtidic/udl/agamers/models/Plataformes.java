package dam.agamers.gtidic.udl.agamers.models;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

public class Plataformes {

    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;
    @SerializedName("manufacturer")
    private String manufacturer;

    public Plataformes(Integer id, String name, String manufacturer) {
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
    }

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

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @NotNull
    public String toString(){
        return name;
    }

}
