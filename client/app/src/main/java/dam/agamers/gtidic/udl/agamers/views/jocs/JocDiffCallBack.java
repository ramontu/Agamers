package dam.agamers.gtidic.udl.agamers.views.jocs;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import dam.agamers.gtidic.udl.agamers.models.Jocs;

public class JocDiffCallBack extends DiffUtil.ItemCallback<Jocs> {
    @Override
    public boolean areItemsTheSame(@NonNull Jocs oldItem, @NonNull Jocs newItem) {
        return oldItem.getId().equals(newItem.getId());
    }

    @Override
    public boolean areContentsTheSame(@NonNull Jocs oldItem, @NonNull Jocs newItem) {
        return oldItem.equals(newItem);
    }
}
