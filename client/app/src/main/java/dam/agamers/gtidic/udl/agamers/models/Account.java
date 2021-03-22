package dam.agamers.gtidic.udl.agamers.models;

import com.google.gson.annotations.SerializedName;

public class Account {

    @SerializedName("username")
    private String username;
    @SerializedName("email")
    private String email;
    @SerializedName("name")
    private String name;
    @SerializedName("surname")
    private String surname;
    @SerializedName("genere")
    private String genere;
    @SerializedName("password")
    private String password;



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    /*
    //TODO protegeix la contrasenya, cal acabar, clase22/3
    public void setPassword(String password) {
        String salt = "16";
        this.password = Utils.encode(password,salt,29000);
    }
    */







}
