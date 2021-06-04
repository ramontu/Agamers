package dam.agamers.gtidic.udl.agamers.views.xats;

import android.graphics.Color;
import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import org.jetbrains.annotations.NotNull;

import dam.agamers.gtidic.udl.agamers.R;
import dam.agamers.gtidic.udl.agamers.adapters.MessageAdapter;
import dam.agamers.gtidic.udl.agamers.models.Message;
import dam.agamers.gtidic.udl.agamers.preferences.PreferencesProvider;

public class XatsAdapter extends ListAdapter<Message, XatsAdapter.XatsHolder> {

     String TAG = "MessageAdapter";
     int VIEW_TYPE_TEXT = 1;
     int VIEW_TYPE_IMAGE = 2;


    private XatsTextCommonHolder xatsTextCommonHolder;
    //TODO fer un pel image



    private FirebaseRecyclerOptions<Message> options;
    private String current_user_id;



    public XatsAdapter (@NonNull @NotNull DiffUtil.ItemCallback<Message> diffCallback) {
        super(diffCallback);
    }


    @Override
    public XatsAdapter.XatsHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;
        if (viewType == VIEW_TYPE_TEXT){
            view = inflater.inflate(R.layout.message, parent, false);

        }else {
            view = inflater.inflate(R.layout.image_message, parent,false);
        }
        return new XatsAdapter.XatsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull XatsAdapter.XatsHolder holder, int position) {

        //TODO s'ha de comprovar si es imatge o text, de moment per defecte serà de text
        /*
        if (!options.getSnapshots().getSnapshot(position))){
            MessageAdapter.MessageViewHolder()
        }

         */
        Message message = getItem(position);
        xatsTextCommonHolder.bindHolder(message);
        //TODO mirar que fer amb imatge
    }

    public int getItemViewType(int position){
        Message message = (Message) options.getSnapshots().getSnapshot(position).getValue();
        if (message.getText() != null){
            return VIEW_TYPE_TEXT;
        }
        else {
            return VIEW_TYPE_IMAGE;
        }
    }


    public class XatsHolder extends RecyclerView.ViewHolder {
        public XatsHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            xatsTextCommonHolder = new XatsTextCommonHolder(itemView);
            //TODO possiblement haurem de fer un per imatge
        }
    }


    public class MessageViewHolder {

        public void bind(Message item, View view){

            setTextColor(item.getName(), view.findViewById(R.id.messageTextView));
        }

        private void setTextColor(String userName, TextView textView){
            String username = PreferencesProvider.providePreferences().getString("username","default");

            if (userName == username){
                textView.setBackgroundResource(R.drawable.rounded_message_blue);
                textView.setTextColor(Color.WHITE);
            }
            else {
                textView.setBackgroundResource(R.drawable.rounded_message_gray);
                textView.setTextColor(Color.BLACK);
            }
        }
    }
}
