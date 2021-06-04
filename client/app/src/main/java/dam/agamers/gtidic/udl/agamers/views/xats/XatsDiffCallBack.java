package dam.agamers.gtidic.udl.agamers.views.xats;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import dam.agamers.gtidic.udl.agamers.models.Jocs;
import dam.agamers.gtidic.udl.agamers.models.Message;

public class XatsDiffCallBack extends DiffUtil.ItemCallback<Message> {
    @Override
    public boolean areItemsTheSame(@NonNull Message oldItem, @NonNull Message newItem) {
        return oldItem.getId().equals(newItem.getId());
    }

    @Override
    public boolean areContentsTheSame(@NonNull Message oldItem, @NonNull Message newItem) {
        return oldItem.equals(newItem);
    }
}
