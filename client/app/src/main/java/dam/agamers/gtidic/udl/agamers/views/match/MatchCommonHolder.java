package dam.agamers.gtidic.udl.agamers.views.match;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.squareup.picasso.Picasso;

import dam.agamers.gtidic.udl.agamers.R;
import dam.agamers.gtidic.udl.agamers.models.Account;

public class MatchCommonHolder {
    private static final String TAG = "MatchCommonHolder";
    private ImageView matchImage;
    private TextView matchName;
    private TextView matchCommonGames;
    private TextView matchLevel;
    private TextView matchBday;

    public MatchCommonHolder(@NonNull View itemView){
        matchImage = itemView.findViewById(R.id.imageUser);
        matchName = itemView.findViewById(R.id.textName);
        matchCommonGames = itemView.findViewById(R.id.textCommonGames);
        matchLevel = itemView.findViewById(R.id.textLevel);
        matchBday = itemView.findViewById(R.id.textAge);

    }
    public void bindHolder(Account a){
        Log.d(TAG, "bindHolder()->Account:" +a);
        this.matchName.setText(a.getName());
        this.matchCommonGames.setText(a.getCommon_games());
        this.matchLevel.setText(a.getLevel());
        this.matchBday.setText(a.getBirthday());
        Log.d(TAG, "onBindViewHolder()->Account:" +a.getPhoto());
        Picasso.get().load(a.getPhoto()).into(this.matchImage);

    }

}
