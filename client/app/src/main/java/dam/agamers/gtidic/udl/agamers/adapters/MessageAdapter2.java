package dam.agamers.gtidic.udl.agamers.adapters;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import dam.agamers.gtidic.udl.agamers.R;
import dam.agamers.gtidic.udl.agamers.models.Chat;
import dam.agamers.gtidic.udl.agamers.models.Message;
import dam.agamers.gtidic.udl.agamers.models.Message2;
import dam.agamers.gtidic.udl.agamers.preferences.PreferencesProvider;
import de.hdodenhof.circleimageview.CircleImageView;

public class MessageAdapter2 extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final String TAG = "MessageAdapter2";

    Context context;
    ViewGroup parent;
    List<Message2> messageList = new ArrayList<>();
    String currentUserName;


    public MessageAdapter2(Context context,  List<Message2> list){
        this.context = context;
        messageList = list;
        currentUserName = PreferencesProvider.providePreferences().getString("username", "error");
    }

    public MessageViewHolder addMessages(List<Message2> list){
        messageList = list;
        //TODO distingir entre tipus
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_message, parent, false);
        return new MessageViewHolder(view);
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        this.parent = parent;
        return assign();
    }


    private RecyclerView.ViewHolder assign(){
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_message, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        if (messageList.get(position).getImageUrl() == null){
            MessageViewHolder messageViewHolder = new MessageViewHolder(holder.itemView);
            messageViewHolder.bind(messageList.get(position));
        }
        else {
            ImageMessageHolder imageMessageHolder = new ImageMessageHolder(holder.itemView);
            imageMessageHolder.bind(messageList.get(position));
        }
    }



    public void onBindViewHolder(@NonNull @NotNull ImageMessageHolder holder, int position) {
        holder.bind(messageList.get(position));
    }


    public void onBindViewHolder(@NonNull @NotNull MessageViewHolder holder, int position) {
        holder.bind(messageList.get(position));
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }


    public class ImageMessageHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView senderName;

        public ImageMessageHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            //Imageview i textview
            imageView = itemView.findViewById(R.id.messengerImageView);
            senderName = itemView.findViewById(R.id.messengerTextView);

        }

        public void bind(@NotNull Message2 item){
            senderName.setText(item.getSendername());
            loadImageIntoView(imageView, item.getImageUrl());
        }
    }


    //OK
    class MessageViewHolder extends RecyclerView.ViewHolder {

        TextView messageView;
        TextView sendername;
        CircleImageView senderPhotoUrl; //TODO de moment no s'utilitzarà

        public MessageViewHolder(@NonNull @NotNull View itemView){
            super(itemView);
            messageView = itemView.findViewById(R.id.messageTextView);
            sendername = itemView.findViewById(R.id.messengerTextView);
            senderPhotoUrl = itemView.findViewById(R.id.messengerImageView);
        }

        //DONE
        public void bind(@NotNull Message2 item){
            sendername.setText(item.getSendername());
            messageView.setText(item.getText());
            setTextColor(item.getSendername(), messageView);

            //Treball futur: Assignar la foto corresponent al senderuser en el cercle
            //De moment utilitzem una predefinida
            senderPhotoUrl.setImageResource(R.drawable.ic_account_circle_black_36dp);
        }

        //DONE
        private void setTextColor(String username, TextView textView){
            if (currentUserName.equals(username) && !currentUserName.equals("error")){
                textView.setBackgroundResource(R.drawable.rounded_message_blue);
                textView.setTextColor(Color.WHITE);
            }
            else {
                textView.setBackgroundResource(R.drawable.rounded_message_gray);
                textView.setTextColor(Color.BLACK);
            }
        }
    }

    private void loadImageIntoView(ImageView imageView, String url){
        if (url.startsWith("gs://")){
            StorageReference storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(url);
            storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    String download_url = uri.toString();
                    Glide.with(imageView.getContext())
                            .load(download_url)
                            .into(imageView);

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull @NotNull Exception e) {
                    Log.d(TAG,"Getting download url was not successful.",e);
                }
            });

        } else {
            Glide.with(imageView.getContext()).load(url).into(imageView);
        }
    }
}
