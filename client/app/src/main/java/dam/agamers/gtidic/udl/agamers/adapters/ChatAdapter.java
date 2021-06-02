package dam.agamers.gtidic.udl.agamers.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import dam.agamers.gtidic.udl.agamers.R;
import dam.agamers.gtidic.udl.agamers.models.Chat;

public class ChatAdapter extends RecyclerView.Adapter<ChatHolder> {


    Context context;
    List<Chat> chatList;


    public ChatAdapter(Context context, List<Chat> list){
        this.context = context;
        chatList = list;
    }

    @NonNull
    @NotNull
    @Override
    public ChatHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_resume_xat_view, parent, false);

        return new ChatHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ChatHolder holder, int position) {
            holder.bind(chatList.get(position));
    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }
}
