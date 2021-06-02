package dam.agamers.gtidic.udl.agamers.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import dam.agamers.gtidic.udl.agamers.R;
import dam.agamers.gtidic.udl.agamers.models.Chat;
import dam.agamers.gtidic.udl.agamers.models.Message;

public class ChatHolder extends RecyclerView.ViewHolder {


    ImageView imageView;
    TextView textView;


    public ChatHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        //Imageview i textview
        imageView = itemView.findViewById(R.id.messengerImageView);
        textView = itemView.findViewById(R.id.textView8);
    }

    //TODO mirrar si existeix una imageUrl i si existeix assignar l'imageView a allo
    //TODO en cas contrari que s'assigni la que ve per defecte
    public void bind(Chat chat){
        textView.setText(chat.getName());
    }
}
