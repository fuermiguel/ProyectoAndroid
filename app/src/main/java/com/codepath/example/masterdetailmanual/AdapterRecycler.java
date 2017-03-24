package com.codepath.example.masterdetailmanual;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by fuerm on 24/03/2017.
 */

public class AdapterRecycler extends RecyclerView.Adapter<AdapterRecycler.ViewHolder> {
    private ArrayList<Deporte> deportes; //Datos a visualizar
    private Context context; //Contexto de la activity, no del fragment
    private ClickAdapterRecycler listener; //Variable que va almacenar el listener implementado en la activity

    /*Interface que va a implementar la activity cpara cuando se selecciona un elemento*/
    public interface ClickAdapterRecycler{
        void onItemAdapterSeleted(Deporte i);
    }


    /*Constructor del adaptador*/
    public AdapterRecycler(Context context, ArrayList deportes, ClickAdapterRecycler listener) {
        this.deportes = deportes;
        this.context = context;
        this.listener = listener;

    }

    /*
   Clase Interna
    Proporciona una referencia a las views para cada elemento de la lista. Esto ahorra muchos
    recursos al no tener que buscar la referencia cada vez que muevo la lista, al tener las
    referencias guardadas.
    Datos complejos pueden necesitar más de una vista por elemento.
    */
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nombreDeporte;
        public ImageView imagenDeporte;

        //Constructor del ViewHolder donde almacenaría las referencias a las views de la View inflada.
        public ViewHolder(View v) {
            super(v);
            this.nombreDeporte = (TextView) v.findViewById(R.id.txt_nombreDeporte);
            this.imagenDeporte = (ImageView) v.findViewById(R.id.img_deporte);
        }

        //Método llamado desde onBindViewHolder
        public void bind(final Deporte deporte, int resource) {
            this.nombreDeporte.setText(deporte.getNombre());
            this.imagenDeporte.setImageResource(resource);

            //itemView es un elemento de la lista por defecto. Asigno mi listener al listener por defecto
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemAdapterSeleted(deporte);
                }
            });
        }
    }

    /* Creamos nuevas views (invocadas por el layout manager)*/
    @Override
    public AdapterRecycler.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Creamos la nueva vista
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lista_deportes, parent, false);

        //Creo el ViewHolder que es estatico y le paso el view de donde cogerá las referencias a las views.
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    //Encargado de actualizar los datos de un ViewHolder ya existente.
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //Ahora obtenemos el recurso drawable por el nombre de la imagen almacenada en la base de datos
        int recurso = context.getResources().getIdentifier(deportes.get(position).getFoto_deporte()
                , "drawable",context.getPackageName());
        holder.bind(deportes.get(position),recurso);
    }

    @Override
    public int getItemCount() {
        return deportes.size();
    }


}
