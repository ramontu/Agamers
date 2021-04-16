package dam.agamers.gtidic.udl.agamers.models.enums;

import android.content.Context;
import android.content.res.Resources;
import dam.agamers.gtidic.udl.agamers.R;

public enum GenereEnum {
    M(R.string.genere_male),
    F(R.string.genere_female),
    NB(R.string.genere_no_binari),
    N(R.string.genere_sense_especificar);

    private Integer valor;

    GenereEnum (int in){
        valor = in;
    }

    /**
     * @param context permet obtenir el valor que conté els enums.
     * @return retorna el resultat a l'hora de seleccionar l'enum
     */
    public String toString(Context context) {
        Resources res = context.getResources();
        return res.getString(valor);
    }
}
