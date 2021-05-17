package dam.agamers.gtidic.udl.agamers.views.jocs;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import dam.agamers.gtidic.udl.agamers.R;
import dam.agamers.gtidic.udl.agamers.models.Jocs;

public class JocAdapter extends ListAdapter<Jocs, JocAdapter.JocHolder> {

    private JocCommonHolder jocCommonHolder;

    public JocAdapter(@NonNull @NotNull DiffUtil.ItemCallback<Jocs> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @NotNull
    @Override
    public JocAdapter.JocHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jocs, null, false);
        return new JocAdapter.JocHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull JocAdapter.JocHolder holder, int position) {

        Jocs jocs = getItem(position);
        jocCommonHolder.bindHolder(jocs);
    }

    public class JocHolder extends RecyclerView.ViewHolder {

        public JocHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            jocCommonHolder = new JocCommonHolder(itemView);
        }
    }

}