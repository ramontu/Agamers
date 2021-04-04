package dam.agamers.gtidic.udl.agamers.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

import dam.agamers.gtidic.udl.agamers.utils.Utils;
import dam.agamers.gtidic.udl.agamers.validators.AccountValidator;

public class Account {

    @SerializedName("username")
    private String username;
    @SerializedName("email")
    private String email;
    @SerializedName("name")
    private String name;
    @SerializedName("surname")
    private String surname;



    @SerializedName("birthdate")
    private String date;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        String salt = "16";
        this.password = Utils.encode(password,salt,29000);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    @Override
    public String toString(){
        return this.name + " " + this.surname  + " " + this.username  + " " + this.date  + " " + this.email + " " + this.password;
    }







}
