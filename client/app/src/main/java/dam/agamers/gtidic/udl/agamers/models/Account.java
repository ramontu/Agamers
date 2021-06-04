package dam.agamers.gtidic.udl.agamers.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.File;
import java.util.Date;
import java.util.List;

import dam.agamers.gtidic.udl.agamers.models.enums.AccountTypeEnum;
import dam.agamers.gtidic.udl.agamers.models.enums.GenereEnum;
import dam.agamers.gtidic.udl.agamers.utils.Utils;

public class Account implements Parcelable {

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
    @SerializedName("name")
    private String name;
    @SerializedName("surname")
    private String surname;
    @SerializedName("birthday")
    private String birthday;
    @SerializedName("genere")
    private GenereEnum genere;
    @SerializedName("photo")
    private String photo;
    @SerializedName("recovery_code")
    private String recovery_code;
    @SerializedName("location")
    private String location;
    @SerializedName("jocs")
    private List<Jocs> common_games;
    @SerializedName("level")
    private String level;


    public Account(){

    }
    protected Account(Parcel in){
        if (in.readByte() == 0) {
            username = null;
        } else {
            username = in.readString();
        }
        username = in.readString();
        level = in.readString();
        photo = in.readString();
        birthday = in.readString();
    }
    public static final Creator<Account> CREATOR = new Creator<Account>() {
        @Override
        public Account createFromParcel(Parcel in) {
            return new Account(in);
        }

        @Override
        public Account[] newArray(int size) {
            return new Account[size];
        }
    };
    private File imatge;

    public File getImatge() {
        return imatge;
    }

    public void setImatge(File imatge) {
        this.imatge = imatge;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String value) {
        this.email = value;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }

    public String getPassword() { return password; }
    public void setPassword(String password) {
        String salt = "16";
        this.password = Utils.encode(password,salt,29000);
    }

    public String getDate() { return birthday; }
    public void setDate(String date) { this.birthday = date; }

    public String getCreated_at() { return created_at; }
    public void setCreated_at(String created_at) { this.created_at = created_at; }

    public AccountTypeEnum getAccount_type() { return account_type; }
    public void setAccount_type(AccountTypeEnum account_type) { this.account_type = account_type; }

    public String getShort_description() { return short_description; }
    public void setShort_description(String short_description) { this.short_description = short_description; }

    public String getLong_description() { return long_description; }
    public void setLong_description(String long_description) { this.long_description = long_description; }

    public String getBirthday() { return birthday; }
    public void setBirthday(String birthday) { this.birthday = birthday; }

    public GenereEnum getGenere() { return genere; }
    public void setGenere(GenereEnum genere) { this.genere = genere; }

    public String getPhoto() { return photo; }
    public void setPhoto(String photo) { this.photo = photo; }

    public String getRecovery_code() { return recovery_code; }
    public void setRecovery_code(String recovery_code) { this.recovery_code = recovery_code; }

    public List<Jocs> getCommon_games() { return common_games; }
    public void setCommon_games(List<Jocs> common_games) { this.common_games = common_games; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }

    @Override
    public boolean equals(Object o) {

        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

       /* Check if o is an instance of Complex or not
         "null instanceof [type]" also returns false */
        if (!(o instanceof Account)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        Account e = (Account) o;

        // Compare the data members and return accordingly
        return this.name.equals(e.getName());
    }

    @Override
    public String toString(){
        return this.name + " " + this.surname  + " " + this.username  + " Birthday:" + this.birthday  + " " + this.email + " " + this.password;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.username);
        dest.writeString(this.level);
        dest.writeString(this.birthday);
        dest.writeString(this.photo);
    }

}
