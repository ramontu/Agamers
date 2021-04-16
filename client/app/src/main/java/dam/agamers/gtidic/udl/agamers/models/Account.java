package dam.agamers.gtidic.udl.agamers.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

import dam.agamers.gtidic.udl.agamers.models.enums.AccountTypeEnum;
import dam.agamers.gtidic.udl.agamers.models.enums.GenereEnum;
import dam.agamers.gtidic.udl.agamers.utils.Utils;
import dam.agamers.gtidic.udl.agamers.validators.AccountValidator;




public class Account {

    @SerializedName("created_at")
    private String created_at;
    @SerializedName("username")
    private String username;
    @SerializedName("account_type")
    private AccountTypeEnum account_type;
    @SerializedName("short_description")
    private String short_description;
    @SerializedName("long_description")
    private String long_description;
    @SerializedName("password")
    private String password;
    @SerializedName("email")
    private String email;
    //TODO potser incorporar tokens per si els vol borrar?
    @SerializedName("name")
    private String name;
    @SerializedName("surname")
    private String surname;
    @SerializedName("birthday")
    private String birthday;
    @SerializedName("genere")
    private GenereEnum genere;
    @SerializedName("photo")
    private String photo; //TODO modificar amb el de veritat


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
        return birthday;
    }

    public void setDate(String date) {
        this.birthday = date;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public AccountTypeEnum getAccount_type() {
        return account_type;
    }

    public void setAccount_type(AccountTypeEnum account_type) {
        this.account_type = account_type;
    }

    public String getShort_description() {
        return short_description;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
    }

    public String getLong_description() {
        return long_description;
    }

    public void setLong_description(String long_description) {
        this.long_description = long_description;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public GenereEnum getGenere() {
        return genere;
    }

    public void setGenere(GenereEnum genere) {
        this.genere = genere;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString(){
        return this.name + " " + this.surname  + " " + this.username  + " Birthday:" + this.birthday  + " " + this.email + " " + this.password;
    }









}
