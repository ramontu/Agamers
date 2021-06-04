package dam.agamers.gtidic.udl.agamers.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message {
    private String id;
    private String text;
    private String name;
    private String photoUrl;
    private String imageUrl;
    private String messageTime;

    // Empty constructor needed for Firestore serialization
    void constructor(){

    }

    public Message(){
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getMessageTime() {
        return messageTime;
    }

    public void setMessageTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        this.messageTime = formatter.format(now);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {

        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

       /* Check if o is an instance of Complex or not
         "null instanceof [type]" also returns false */
        if (!(o instanceof Message)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        Message e = (Message) o;

        // Compare the data members and return accordingly
        return this.id.equals(e.getId());

    }
}
