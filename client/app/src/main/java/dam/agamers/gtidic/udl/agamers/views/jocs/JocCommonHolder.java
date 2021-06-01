package dam.agamers.gtidic.udl.agamers.views.jocs;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.squareup.picasso.Picasso;

import dam.agamers.gtidic.udl.agamers.R;
import dam.agamers.gtidic.udl.agamers.models.Jocs;

public class JocCommonHolder {

        private static final String TAG = "JocCommonHolder";
        private TextView jocName;
        private ImageView jocPoster;


        public JocCommonHolder(@NonNull View itemView) {

            jocName = itemView.findViewById(R.id.jocItemName);
            jocPoster = itemView.findViewById(R.id.jocItemPoster);
        }

        public void bindHolder(Jocs j) {

            Log.d(TAG, "bindHolder() -> Jocs: " + j);

            this.jocName.setText(j.getName());

            Log.d(TAG, "onBindViewHolder() -> cJocs: " + j.getImage());
            Picasso.get().load(j.getImage()).into(this.jocPoster); //per obtenir la imatge des de una URL
        }
}