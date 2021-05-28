package dam.agamers.gtidic.udl.agamers.views.jocs;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import dam.agamers.gtidic.udl.agamers.models.Jocs;
import dam.agamers.gtidic.udl.agamers.repositories.JocsRepo;

public class AddGameViewModel extends ViewModel {

    private static final int PICK_IMAGE_REQUEST = 14;
    JocsRepo jocsRepo;
    String TAG = "AddGameViewModel";
    public MutableLiveData<Boolean> responseUpdate;
    //TODO abans d'enviar el joc s'ha de agafar si te mode online del radiobutton
    //TODO s'ha de fer una llista amb totes les categories i plataformes per a que surtin a la llista de selecció
    public MutableLiveData<Jocs> m_Jocs;
    View view;

    public AddGameViewModel() {
        jocsRepo = new JocsRepo();
        m_Jocs = new MutableLiveData<>();
    }

   /* public void uploadJocImage(File imageFile){
        Log.d("VM", "uploading image... using repo");
        this.jocsRepo.uploadPhoto(imageFile);
    }*/

    public void createJoc(Jocs jocs){
        Log.d(TAG, "Create new game");
        this.jocsRepo.create_jocs(jocs);
    }

    public MutableLiveData<Boolean> jocIsCreated(){
        return this.jocsRepo.getmCreateOkOk();
    }

    public MutableLiveData<Jocs> getm_Jocs(){return jocsRepo.getmJocsInfo(); }

    public MutableLiveData<Jocs> get_JocFromRepo(){return jocsRepo.getmDescarregarInfoJocs();}

    /*public void set_date(View v) {
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
    }*/
}
