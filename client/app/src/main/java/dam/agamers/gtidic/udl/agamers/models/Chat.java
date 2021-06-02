package dam.agamers.gtidic.udl.agamers.models;

import java.util.ArrayList;
import java.util.List;

public class Chat {
    private List<Message> messages = new ArrayList<>();
    private String name;
    private String imageUrl;



    public Chat(String name, List<Message> messages){
        this.name = name;

        this.messages = messages;
    }
    public Chat(){
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public String getName(){
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

