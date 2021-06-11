package dam.agamers.gtidic.udl.agamers.adapters;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
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

import dam.agamers.gtidic.udl.agamers.R;
import dam.agamers.gtidic.udl.agamers.models.Chat;
import dam.agamers.gtidic.udl.agamers.models.Message;
import dam.agamers.gtidic.udl.agamers.models.Message2;
import dam.agamers.gtidic.udl.agamers.preferences.PreferencesProvider;
import de.hdodenhof.circleimageview.CircleImageView;

public class MessageAdapter2 extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final String TAG = "MessageAdapter2";
    Context context;
    String currentUserName;

    public MessageAdapter2(Context context){
        this.context = context;
        currentUserName = PreferencesProvider.providePreferences().getString("username", "error");
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ImageMessageHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;

        public ImageMessageHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            //Imageview i textview
            imageView = itemView.findViewById(R.id.messengerImageView);
            textView = itemView.findViewById(R.id.textView8);

        }

        public void bind(@NotNull Message2 item){
            textView.setText(item.getSendername());
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
