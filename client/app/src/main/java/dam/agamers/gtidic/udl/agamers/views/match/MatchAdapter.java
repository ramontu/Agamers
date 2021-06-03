package dam.agamers.gtidic.udl.agamers.views.match;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.annotations.NotNull;

import dam.agamers.gtidic.udl.agamers.R;
import dam.agamers.gtidic.udl.agamers.models.Account;

public class MatchAdapter extends ListAdapter<Account, MatchAdapter.MatchHolder> {

    private MatchCommonHolder matchCommonHolder;
    private OnItemClickListener matchItemListener;

    public MatchAdapter(@NonNull @NotNull DiffUtil.ItemCallback<Account> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @NotNull
    @Override
    public MatchAdapter.MatchHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.matching_item, null, false);
        return new MatchAdapter.MatchHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MatchAdapter.MatchHolder holder, int position) {

        Account account = getItem(position);
        matchCommonHolder.bindHolder(account);
    }

    public class MatchHolder extends RecyclerView.ViewHolder {

        public MatchHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            matchCommonHolder = new MatchCommonHolder(itemView);
            itemView.setOnClickListener(v -> {
                int position = getBindingAdapterPosition();
                if (matchItemListener != null && position != RecyclerView.NO_POSITION) {
                    matchItemListener.onItemClick(getItem(position));
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Account account);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.matchItemListener = listener;
    }

}