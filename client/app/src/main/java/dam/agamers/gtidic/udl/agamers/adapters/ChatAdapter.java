package dam.agamers.gtidic.udl.agamers.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import dam.agamers.gtidic.udl.agamers.R;
import dam.agamers.gtidic.udl.agamers.models.Chat;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatHolder>{


    Context context;
    ViewGroup parent;
    List<Chat> chatList;
    OnItemClickListener onItemClickListener;



    public ChatAdapter(Context context, List<Chat> list){
        this.context = context;
        chatList = list;
    }



    public ChatHolder addChats(List<Chat> list){
        chatList = list;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_resume_xat_view, parent, false);
        return new ChatHolder(view);
    }

    @NonNull
    @NotNull
    @Override
    public ChatHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        this.parent = parent;
        View view = inflater.inflate(R.layout.item_resume_xat_view, parent, false);

        return new ChatHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ChatHolder holder, int position) {
            holder.bind(chatList.get(position));
            holder.itemView.setOnClickListener(v -> onItemClickListener.onItemClick(chatList.get(position)));
    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }




    public static class ChatHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;

        public ChatHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            //Assignem Imageview i textview
            imageView = itemView.findViewById(R.id.messengerImageView);
            textView = itemView.findViewById(R.id.textView8);

        }

        //TODO mirrar si existeix una imageUrl i si existeix assignar l'imageView a allo
        //TODO en cas contrari que s'assigni la que ve per defecte
        public void bind(Chat chat){
            textView.setText(chat.getName());
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Chat chat);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }
}
