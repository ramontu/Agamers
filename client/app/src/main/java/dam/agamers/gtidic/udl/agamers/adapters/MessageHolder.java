package dam.agamers.gtidic.udl.agamers.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import dam.agamers.gtidic.udl.agamers.R;
import dam.agamers.gtidic.udl.agamers.models.Message;

public class MessageHolder extends RecyclerView.ViewHolder {


    ImageView imageView;
    TextView textView;


    public MessageHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        //Imageview i textview
        imageView = itemView.findViewById(R.id.messengerImageView);
        textView = itemView.findViewById(R.id.textView8);
    }

    public void bind(Message message){
        textView.setText(message.getName());
    }
}
