package dam.agamers.gtidic.udl.agamers.adapters;

import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.jetbrains.annotations.NotNull;

import dam.agamers.gtidic.udl.agamers.R;
import dam.agamers.gtidic.udl.agamers.databinding.ImageMessageBinding;
import dam.agamers.gtidic.udl.agamers.databinding.MessageBinding;
import dam.agamers.gtidic.udl.agamers.models.Message;


public class MessageAdapter{

    private final String TAG = "MessageAdapter";
    private final Integer VIEW_TYPE_TEXT = 1;
    private final Integer VIEW_TYPE_IMAGE = 2;
    private final String ANONYMUS = "anonymous";

    private ViewHolder a;

    public FirebaseRecyclerAdapter<Message, ViewHolder> options(FirebaseRecyclerOptions<Message> options, String currentUserName) {
        return new FirebaseRecyclerAdapter<Message, ViewHolder>(options) {

            //DONE
            @NotNull
            @Override
            public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType){
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());

                if (viewType == VIEW_TYPE_TEXT){
                    View view = inflater.inflate(R.layout.message, parent, false);
                    MessageBinding binding = MessageBinding.bind(view);
                    a = new MessageViewHolder(binding);
                    return a;
                }
                else {
                    View view = inflater.inflate(R.layout.image_message, parent, false);
                    ImageMessageBinding binding = ImageMessageBinding.bind(view);
                    return new ImageMessageViewHolder(binding);
                }
            }


            @Override
            public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position, @NonNull @NotNull Message model) {

                if (options.getSnapshots().get(position).getText() != null){
                    MessageViewHolder messageViewHolder= (MessageViewHolder) a;
                    messageViewHolder.bind(model);
                }
                else {
                    ImageMessageViewHolder imageMessageViewHolder = (ImageMessageViewHolder)a;
                    imageMessageViewHolder.bind(model);
                }
            }

            //DONE
            @Override
            public int getItemViewType(int position){
                if (options.getSnapshots().get(position).getText() != null){
                    return VIEW_TYPE_TEXT;
                }
                else {
                    return VIEW_TYPE_IMAGE;
                }
            }


            //DONE
            class MessageViewHolder extends ViewHolder{

                MessageBinding binding;

                public MessageViewHolder(MessageBinding binding){
                    super(binding.getRoot());
                    this.binding = binding;
                }

                //DONE
                public void bind(@NotNull Message item){
                    binding.messengerTextView.setText(item.getName());
                    binding.messageTextView.setText(item.getText());
                    setTextColor(item.getName(), binding.messageTextView);

                    if (item.getPhotoUrl() != null){
                        loadImageIntoView(binding.messengerImageView, item.getPhotoUrl());
                    } else {
                        binding.messengerImageView.setImageResource(R.drawable.ic_account_circle_black_36dp);
                    }
                }

                //DONE
                private void setTextColor(String username, TextView textView){
                    if (username != ANONYMUS && currentUserName == username && username != null){
                        textView.setBackgroundResource(R.drawable.rounded_message_blue);
                        textView.setTextColor(Color.WHITE);
                    }
                    else {
                        textView.setBackgroundResource(R.drawable.rounded_message_gray);
                        textView.setTextColor(Color.BLACK);
                    }
                }
            }


            class ImageMessageViewHolder extends ViewHolder{
                ImageMessageBinding imageMessageBinding;


                public ImageMessageViewHolder(ImageMessageBinding binding){
                    super(binding.getRoot());
                    imageMessageBinding = binding;
                }

                public void bind(@NotNull Message item) {
                    loadImageIntoView(imageMessageBinding.messageImageView, item.getImageUrl());

                    imageMessageBinding.messengerTextView.setText(item.getName());

                    if (item.getPhotoUrl() != null){
                        loadImageIntoView(imageMessageBinding.messengerImageView, item.getPhotoUrl());
                    } else {
                        imageMessageBinding.messengerImageView.setImageResource(R.drawable.ic_account_circle_black_36dp);
                    }
                }
            }


            public void loadImageIntoView(ImageView imageView, String url){
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
        };
    }



}

