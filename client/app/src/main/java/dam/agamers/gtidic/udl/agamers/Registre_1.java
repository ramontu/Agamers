package dam.agamers.gtidic.udl.agamers;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.google.android.material.slider.Slider;
import com.google.android.material.textfield.TextInputLayout;

import dam.agamers.gtidic.udl.agamers.user.Utils;

public class Registre_1 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registre_1);
        getSupportActionBar().hide();
        comprovacions();
    }


    //TODO falta refactor
    /*
    TODO no es treu missatge d'error al nom
    TODO scrollbar
    TODO implementar boto per a tornar a inici (el propi de android)

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
                if (!Utils.comprovar_nom_o_cognom(s.toString())){
                    nom.setError("El nom conté valors no vàlids. Revisa que no hi hagi cap numero o caràcter especial.");
                }
                else {
                    nom.setError(null);
                }
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
                if (!Utils.comprovar_nom_o_cognom(s.toString())){
                    cognom.setError("El nom conté valors no vàlids. Revisa que no hi hagi cap numero o caràcter especial.");
                }
                else {
                    cognom.setError(null);
                }
            }
        });

        /*
        //comprovació username
        TextInputLayout username = (TextInputLayout) findViewById(R.id.user_name_textinputlayout);
        username.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (!Utils.comprovar_username(s.toString())){
                    username.setError("El nom conté valors no vàlids. Revisa que no hi hagi cap espai i/o caràcters especials.");
                }
                else {
                    username.setError(null);
                }
            }
        });

         */

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
                if (!Utils.comprovar_contrasenya(s.toString())){
                    contra.setError("8-XX caràcters. Com a mínim 3: Majúscules, minúscules, numeros i/o caracters especials (!@#$%^&*)");
                }
                else {
                    contra.setError(null);
                }
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
                    confi_contra.setError("La contrasenya que has introduït no es la mateixa que la de sobre. Revisa-ho");
                }
                else {
                    confi_contra.setError(null);
                }


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
                if (!Utils.comprovar_mail(s.toString())){
                    email.setError("El correu no es vàlid. Torna a comprovar-ho");
                }
                else {
                    email.setError(null);
                }
            }
        });







    }

    public void termes_condicions(View view){
        CheckBox term = findViewById(R.id.termes);

        if (term.isChecked()){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.termes_i_condicions_text);
            builder.setTitle(R.string.termes_i_condicions_titol);
            builder.setPositiveButton("Accepto", null);
            builder.setNegativeButton("Declino", null);
            AlertDialog dialog = builder.create();
            dialog.show();



        }


    }









}