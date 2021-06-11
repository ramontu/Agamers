package dam.agamers.gtidic.udl.agamers.models;

public class Message2 {
    private String text;
    private String imageUrl;
    private String sendername;
    private String senderphotoUrl;
    private String messageTime;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSendername() {
        return sendername;
    }

    public void setSendername(String sendername) {
        this.sendername = sendername;
    }

    public String getSenderphotoUrl() {
        return senderphotoUrl;
    }

    public void setSenderphotoUrl(String senderphotoUrl) {
        this.senderphotoUrl = senderphotoUrl;
    }

    public String getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }
}
