package com.codepath.example.masterdetailmanual;

import android.app.Activity;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by fuerm on 09/03/2017.
 */

public class AdapterDetalles extends ArrayAdapter {
    private Activity activity;
    private ArrayList<Lista> datos;
    private OnCheckBoxSelectedListener listener;

    //Definimos la interface que tiene que implementar la activity padre
    public interface OnCheckBoxSelectedListener {
        void onCheckBoxSelected(View i, String detalle);
    }

    public AdapterDetalles(Activity activity, ArrayList<Lista> datos) {
        //Pasamos al padre (ArrayAdapter el contexto, el layout a usar y los datos
        super(activity, R.layout.lista_details, datos);
        // Guardamos los parámetros en variables de clase.
        this.activity = activity;
        this.datos = datos;

        if ( activity instanceof OnCheckBoxSelectedListener) {
            listener = (OnCheckBoxSelectedListener) activity;
        } else {
            throw new ClassCastException(activity.toString()
                    + " must implement ItemDetailFragment.OnCheckBoxSelectedListener");
        }




    }

    /*Método que nos da la vista donde se mostraran los datos*/
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(activity);
        //Tenemos que obtener una view del layout para poder manipular los controles
        View item = inflater.inflate(R.layout.lista_details, null);

        // A partir de la vista, recogeremos los controles que contiene para
        // poder manipularlos.
        ImageView imagen_detalle = (ImageView) item.findViewById(R.id.img_detalle);

        //Ahora obtenemos el recurso drawable por el nombre de la imagen almacenada en la base de datos
        int recurso = activity.getResources().getIdentifier(datos.get(position).getFoto_detalle()
                , "drawable", activity.getPackageName());

        //Asignamos la imagen al imageview
        imagen_detalle.setImageResource(recurso);

        // Recogemos el TextView para mostrar el nombre y establecemos el
        // nombre.
        final TextView detalle_lista = (TextView) item.findViewById(R.id.txt_detalle);
        detalle_lista.setText(datos.get(position).getDescripcion_detalle());

        //Recogemos el CheckBox para colocarlo en su estado almacenado en las preferences
        CheckBox checkBox = (CheckBox) item.findViewById(R.id.chk_detalle);

        //Asignamos el listener
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onCheckBoxSelected(v,detalle_lista.getText().toString());
            }
        });

        //Obtenemos los valores almacenados en preferences
        SharedPreferences preferences = activity.getSharedPreferences("MisPreferencias",MODE_PRIVATE);
        if (preferences != null) {
            Map<String, ?> allEntries = preferences.getAll();

            for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
                if (entry.getKey().equals(detalle_lista.getText().toString()) &&
                        entry.getValue().toString().equals("true")) checkBox.setSelected(true);
            }
        }

        // Devolvemos la vista para que se muestre en el ListView.
        return item;
    }


}
