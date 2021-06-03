package dam.agamers.gtidic.udl.agamers.views.match;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import dam.agamers.gtidic.udl.agamers.models.Account;

public class MatchDiffCallBack extends DiffUtil.ItemCallback<Account> {
    @Override
    public boolean areItemsTheSame(@NonNull Account oldItem, @NonNull Account newItem) {
        return oldItem.getName().equals(newItem.getName());
    }

    @Override
    public boolean areContentsTheSame(@NonNull Account oldItem, @NonNull Account newItem) {
        //return oldItem.equals(newItem);
    }
}
