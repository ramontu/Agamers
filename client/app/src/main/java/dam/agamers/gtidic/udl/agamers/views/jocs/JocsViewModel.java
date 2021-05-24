package dam.agamers.gtidic.udl.agamers.views.jocs;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import dam.agamers.gtidic.udl.agamers.models.Categories;
import dam.agamers.gtidic.udl.agamers.models.Jocs;
import dam.agamers.gtidic.udl.agamers.models.Plataformes;
import dam.agamers.gtidic.udl.agamers.repositories.CategoriesRepo;
import dam.agamers.gtidic.udl.agamers.repositories.JocsRepo;
import dam.agamers.gtidic.udl.agamers.repositories.PlataformesRepo;
import dam.agamers.gtidic.udl.agamers.services.plataformes.PlataformesService;

public class JocsViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private JocsRepo jocsRepo;
    private CategoriesRepo categoriesRepo;
    private PlataformesRepo plataformesRepo;

    public JocsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is jocs fragment");
        jocsRepo = new JocsRepo();
        categoriesRepo = new CategoriesRepo();
        plataformesRepo = new PlataformesRepo();
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void getJocs() {
        jocsRepo.getJocs();
    }

    public MutableLiveData<List<Jocs>> returnJocs() {
        return this.jocsRepo.getmResponseJocs();
    }

    public void getCategories(){categoriesRepo.download_categories_info();}

    public MutableLiveData<List<Categories>> returnCategories(){return this.categoriesRepo.getmCategoriesInfo();}

    public void getPlataformes(){plataformesRepo.download_plataformes_info();}

    public MutableLiveData<List<Plataformes>> returnPlataformes(){return this.plataformesRepo}


}
