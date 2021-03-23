package dam.agamers.gtidic.udl.agamers.views;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import dam.agamers.gtidic.udl.agamers.R;
import dam.agamers.gtidic.udl.agamers.validators.AccountValidator;

public class SignUpActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registre_1);

        /* No cal per defecte android ja fa aquesta acció, si voleu anar a una activity que no es
        l'anterior llavors si que cal fer-ho però de moment no es necesari
        getSupportActionBar().hide();
        //boto per a tornar a inici (el propi de android)
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent intent = new Intent(getApplicationContext(), Inici_de_sessio.class);
                startActivity(intent);
                finish();
            }
        };
        this.getOnBackPressedDispatcher().addCallback(this,callback);*/
        comprovacions();
    }

    private boolean b_nom = false;
    private boolean b_cog = false;
    private boolean b_contra = false;
    private boolean b_con_contra = false;
    private boolean b_mail = false;
    private boolean b_terms = false;


    //TODO falta refactor
    /**
     * S'encarrega de fer totes les comprovacions necessaries per a que el registre es ralitzi de forma satisfactòria
     */
    protected void comprovacions() {
        //Comprovació nom
        TextInputLayout nom = (TextInputLayout) findViewById(R.id.name_textinputlayout);
        nom.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (!AccountValidator.comprovar_nom_o_cognom(s.toString())){
                    nom.setError(getString(R.string.error_nom_no_vàlid));
                    b_nom = false;
                }
                else {
                    nom.setError(null);
                    b_nom = true;
                }
                tots_camps_valids();
            }
        });

        //Comprovació cognom
        TextInputLayout cognom = (TextInputLayout) findViewById(R.id.cognom_textinputlayout);
        cognom.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (!AccountValidator.comprovar_nom_o_cognom(s.toString())){
                    cognom.setError(getString(R.string.error_cognom_no_vàlid));
                    b_cog = false;
                }
                else {
                    cognom.setError(null);
                    b_cog = true;
                }
                tots_camps_valids();
            }
        });


        //comprovació contra
        TextInputLayout contra = (TextInputLayout) findViewById(R.id.contra_textinputlayout);
        contra.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (!AccountValidator.comprovar_contrasenya(s.toString())){
                    contra.setError(getString(R.string.error_contra_no_vàlida));
                    b_contra = false;
                }
                else {
                    contra.setError(null);
                    b_contra = true;
                }
                tots_camps_valids();
            }
        });


        //comprovació confirmar contra
        TextInputLayout confi_contra = (TextInputLayout) findViewById(R.id.confirmar_contra_textinputlayout);
        confi_contra.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals(String.valueOf(contra.getEditText().getText()))){
                    confi_contra.setError(getString(R.string.error_confirmar_contra));
                    b_con_contra = false;
                }
                else {
                    confi_contra.setError(null);
                    b_con_contra = true;
                }
                tots_camps_valids();

            }
        });

        //comprovació MAIL
        TextInputLayout email = (TextInputLayout) findViewById(R.id.email_textinputlayout);
        email.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (!AccountValidator.comprovar_mail(s.toString())){
                    email.setError(getString(R.string.error_mail_no_vàlid));
                    b_mail = false;
                }
                else {
                    email.setError(null);
                    b_mail = true;

                }
                tots_camps_valids();
            }
        });







    }

    /**
     * Es l'encarregat de que al donar-li al CheckBox de termes i condicions aparegui un diàleg on s'informa a l'usuari dels termes i condicions de l'app
     * @param view Pantalla actual
     */
    public void termes_condicions(View view){
        CheckBox term = findViewById(R.id.termes);

        if (term.isPressed()){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.termes_i_condicions_text);
            builder.setTitle(R.string.termes_i_condicions_titol);
            builder.setPositiveButton(getString(R.string.terms_button_acceptar), (dialog, id) -> {
                CheckBox a = findViewById(R.id.termes);
                a.setChecked(true);
                b_terms = true;
                tots_camps_valids();
            });
            builder.setNegativeButton(getString(R.string.terms_button_declinar), (dialog, id) -> {
                CheckBox a = findViewById(R.id.termes);
                a.setChecked(false);
                b_terms = false;
                tots_camps_valids();
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }

    }


    /**
     * Comprova que tots els camps del formaulari de registre són correctes, de ser així permetrà a l'usuari premer el botó de registre
     */
    private void tots_camps_valids(){
        Button b =findViewById(R.id.boto_registrar);
        b.setEnabled(b_nom&&b_cog&&b_contra&&b_con_contra&&b_mail&&b_terms);
    }

    public void missatge_registrat(View view){

        Snackbar snackbar;
        if (true){
            snackbar = Snackbar.make(view, getString(R.string.registre_ok), 5000);
        }
        else {
            snackbar = Snackbar.make(view, getString(R.string.registre_error), 10000);
        }
        snackbar.show();
    }








}