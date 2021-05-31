package dam.agamers.gtidic.udl.agamers.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.MissingFormatArgumentException;

import dam.agamers.gtidic.udl.agamers.R;
import dam.agamers.gtidic.udl.agamers.databinding.ImageMessageBinding;
import dam.agamers.gtidic.udl.agamers.databinding.MessageBinding;
import dam.agamers.gtidic.udl.agamers.models.Message;

public class MessageAdapter2 extends RecyclerView.Adapter<MessageHolder> {


    Context context;
    List<Message> messagesList;


    public MessageAdapter2 (Context context, List<Message> list){
        this.context = context;
        messagesList = list;
    }

    @NonNull
    @NotNull
    @Override
    public MessageHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_resume_xat_view, parent, false);

        return new MessageHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MessageHolder holder, int position) {
            holder.bind(messagesList.get(position));
    }

    @Override
    public int getItemCount() {
        return messagesList.size();
    }
}
