package com.codepath.example.masterdetailmanual;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fuerm on 04/03/2017.
 */

public class AdapterDeportes extends ArrayAdapter {

    private Context context;
    private ArrayList<Deporte> datos;


    public AdapterDeportes(Context context, ArrayList<Deporte> datos) {
        //Pasamos al padre (ArrayAdapter el contexto, el layout a usar y los datos
        super(context, R.layout.lista_deportes, datos);
        // Guardamos los parámetros en variables de clase.
        this.context = context;
        this.datos = datos;
    }

    /*Método que nos da la vista donde se mostraran los datos*/
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        //Tenemos que obtener una view del layout para poder manipular los controles
        View item = inflater.inflate(R.layout.lista_deportes, null);

        // A partir de la vista, recogeremos los controles que contiene para
        // poder manipularlos.
        ImageView imagen_deporte = (ImageView) item.findViewById(R.id.img_deporte);
        //Ahora obtenemos el recurso drawable por el nombre de la imagen almacenada en la base de datos
        int recurso = context.getResources().getIdentifier(datos.get(position).getFoto_deporte()
                , "drawable",context.getPackageName());
        //Asignamos la imagen al imageview
        imagen_deporte.setImageResource(recurso);

        // Recogemos el TextView para mostrar el nombre y establecemos el
        // nombre.
        TextView nombre_deporte = (TextView) item.findViewById(R.id.txt_nombreDeporte);
        nombre_deporte.setText(datos.get(position).getNombre());

        // Devolvemos la vista para que se muestre en el ListView.
        return item;
    }
}
