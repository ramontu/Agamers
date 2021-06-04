package dam.agamers.gtidic.udl.agamers.models;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import dam.agamers.gtidic.udl.agamers.R;

public class Chat {
    private List<Message> messages = new ArrayList<>();
    private String name;
    private String imageUrl;
    private List<String[]> participants = new ArrayList<>();



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

    public List<String[]> getParticipants() {
        return participants;
    }

    public void setParticipants(List<String[]> participants) {
        this.participants = participants;
    }

    public class ChatHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        ImageView imageView;
        TextView textView;




        public ChatHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            //Imageview i textview
            imageView = itemView.findViewById(R.id.messengerImageView);
            textView = itemView.findViewById(R.id.textView8);
            itemView.setOnClickListener(this);

        }

        //TODO mirrar si existeix una imageUrl i si existeix assignar l'imageView a allo
        //TODO en cas contrari que s'assigni la que ve per defecte
        public void bind(Chat chat){
            textView.setText(chat.getName());
        }

        @Override
        public void onClick(View v) {

        }
    }
}

