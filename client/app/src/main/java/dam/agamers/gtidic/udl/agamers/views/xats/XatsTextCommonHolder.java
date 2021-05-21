package dam.agamers.gtidic.udl.agamers.views.xats;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.squareup.picasso.Picasso;

import dam.agamers.gtidic.udl.agamers.R;
import dam.agamers.gtidic.udl.agamers.models.Jocs;
import dam.agamers.gtidic.udl.agamers.models.Message;

public class XatsTextCommonHolder {

    private static final String TAG = "XatsTextCommonHolder";
    private TextView senderName;
    private TextView message;

    private String photoUrl;
    private String imageUrl;
    private String messageTime;


    public XatsTextCommonHolder(@NonNull View itemView) {
        senderName = itemView.findViewById(R.id.messengerTextView);
        message = itemView.findViewById(R.id.messageTextView);

        //TODO acabar

    }

    public void bindHolder(Message m) {

        Log.d(TAG, "bindHolder() -> Message: " + m.getText());
        this.message.setText(m.getText());
        this.senderName.setText(m.getName());


        //TODO fer amb les imatges
        //Log.d(TAG, "onBindViewHolder() -> cJocs: " + j.getPoster_url());
        //Picasso.get().load(j.getPoster_url()).into(this.jocPoster);
    }
}
