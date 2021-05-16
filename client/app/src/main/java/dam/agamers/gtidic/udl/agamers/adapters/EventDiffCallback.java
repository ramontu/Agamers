package dam.agamers.gtidic.udl.agamers.adapters;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import dam.agamers.gtidic.udl.agamers.models.Event;

public class EventDiffCallback extends DiffUtil.ItemCallback<Event> {
    @Override
    public boolean areItemsTheSame(@NonNull Event oldItem, @NonNull Event newItem) {
        return oldItem.getId().equals(newItem.getId());
    }

    @Override
    public boolean areContentsTheSame(@NonNull Event oldItem, @NonNull Event newItem) {
        return oldItem.equals(newItem);
    }
}
