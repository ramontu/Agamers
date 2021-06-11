package dam.agamers.gtidic.udl.agamers.adapters;

/*

public class MessageAdapter extends FirebaseRecyclerAdapter<Message, ViewHolder>{

    private final String TAG = "MessageAdapter";
    private final Integer VIEW_TYPE_TEXT = 1;
    private final Integer VIEW_TYPE_IMAGE = 2;
    private final String ANONYMUS = "anonymous";

    private ViewHolder a;

    private Message message;
    private FirebaseRecyclerOptions<Message> options;
    private String currentUserName;





    public MessageAdapter(FirebaseRecyclerOptions<Message> options, String currentUserName){
        super(options);
        this.options = options;
        this.currentUserName = currentUserName;
    }



    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if (viewType == VIEW_TYPE_TEXT){
            View view = inflater.inflate(R.layout.item_message, parent, false);
            MessageBinding binding = MessageBinding.bind(view);
            a = new MessageViewHolder(binding);
            return a;
        }
        else {
            View view = inflater.inflate(R.layout.item_image_message, parent, false);
            ImageMessageBinding binding = ImageMessageBinding.bind(view);
            return new ImageMessageViewHolder(binding);
        }
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position, Message model) {

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

    @Override
    public int getItemCount() {
        return 0;
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

 */

