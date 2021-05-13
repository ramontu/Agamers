package dam.agamers.gtidic.udl.agamers.views.jocs;

import android.app.DatePickerDialog;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.material.textfield.TextInputLayout;

import java.io.File;
import java.util.Calendar;

import dam.agamers.gtidic.udl.agamers.R;
import dam.agamers.gtidic.udl.agamers.models.Jocs;
import dam.agamers.gtidic.udl.agamers.repositories.JocsRepo;
import dam.agamers.gtidic.udl.agamers.views.activitatsuser.SignUpActivity;

public class AddGameViewModel extends ViewModel {

    JocsRepo jocsRepo;
    String TAG = "AddGameViewModel";
    public MutableLiveData<Boolean> responseUpdate;
    public MutableLiveData<Jocs> m_Jocs;
    AddGameFragment addGameFragment;

    public AddGameViewModel(AddGameFragment addGameFragment) {
        jocsRepo = new JocsRepo();
        m_Jocs = new MutableLiveData<>();
        this.addGameFragment = addGameFragment;
    }

   /* public void uploadJocImage(File imageFile){
        Log.d("VM", "uploading image... using repo");
        this.jocsRepo.uploadPhoto(imageFile);
    }*/

    public void createJoc(){
        Log.d(TAG, "Create new game");
        this.jocsRepo.create_jocs(m_Jocs.getValue());
    }

    public MutableLiveData<Jocs> getm_Jocs(){return jocsRepo.getmJocsInfo(); }

    public void set_date(View v) {
        TextInputLayout publicaciotil = addGameFragment.getActivity().findViewById(R.id.data_publicaciotil);
        Calendar calendar = Calendar.getInstance();

        final int m_day = calendar.get(Calendar.DAY_OF_MONTH);
        final int m_month = calendar.get(Calendar.MONTH);
        final int m_year = calendar.get(Calendar.YEAR);


        DatePickerDialog datePickerDialog = new DatePickerDialog(publicaciotil.getContext(), (view, year, month, dayOfMonth) -> {
            calendar.set(year,month,dayOfMonth);
            String str = calendar.get(Calendar.DAY_OF_MONTH)+"/"+Integer.valueOf(calendar.get(Calendar.MONTH)+1)+"/"+calendar.get(Calendar.YEAR);
            Log.d("Date:",(str));
            m_Jocs.getValue().setPublished(str);
        }, m_year, m_month, m_day);

        Calendar min = Calendar.getInstance();
        min.add(Calendar.YEAR, -80);

        datePickerDialog.getDatePicker().setMinDate(min.getTimeInMillis());
        datePickerDialog.getDatePicker().getTouchables().get(0).performClick();
        datePickerDialog.show();
    }
}
