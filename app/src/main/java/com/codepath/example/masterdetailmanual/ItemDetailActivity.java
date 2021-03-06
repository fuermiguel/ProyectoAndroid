package com.codepath.example.masterdetailmanual;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;


public class ItemDetailActivity extends Activity implements AdapterDetalles.OnCheckBoxSelectedListener {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_item_detail);
        setTitle("Cheking del Material Deportivo");
        //Obtenemos el parámetro nombre deporte que me pasa la activity padre y que puedo usar
        //para buscar en la base de datos su cheking corespondiente.
        String nombreDeporte = getIntent().getExtras().getString("nombreDeporte");


        //Paso1: Obtener instancia del administrador de fragmentos
        FragmentManager fragmentManager = getFragmentManager();

        //Paso2: Crear la instancia de la transacción
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        //Paso3: Crear un nuevo fragmento y añadirlo a la actividad, pasando como parámetro nombre deporte y las preferencias
        Fragment itemDetailFragment = ItemDetailFragment.newInstance(nombreDeporte);
        transaction.replace(R.id.containerDetalle, itemDetailFragment);

        //Paso4: Confirmar los cambios
        transaction.commit();


    }


    //La v es el checkbox , pero necesito también el detalle y el adaptador de la lista donde se encuentra
    @Override
    public void onCheckBoxSelected(View v, String detalle, ArrayList datos) {

        //Guardo en sharedPreferences la selección del checkbox
        SharedPreferences preferences = getSharedPreferences("MisPreferencias", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();


        CheckBox checkBox = (CheckBox) v;

        if (checkBox.isChecked()) {
            editor.putString(detalle, "true");
        } else {
            editor.putString(detalle, "false");
        }

        editor.commit();

        int size = 0;
        Map<String, ?> allEntries = preferences.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            if (entry.getValue().toString().equals("true")) size++;
        }
        if (size == datos.size()) {

            editor.clear().commit();

            //Paso1: Obtener instancia del administrador de fragmentos
            FragmentManager fragmentManager = getFragmentManager();

            //Paso2: Crear la instancia de la transacción
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            //Paso3: Crear un nuevo fragmento y añadirlo a la actividad.
            Fragment completoFragment = CompletoFragment.newInstance();
            transaction.replace(R.id.containerDetalle, completoFragment);

            //Paso4: Confirmar los cambios
            transaction.commit();

        }


    }


	/*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.item_detail, menu);
		return true;
	}*/

}
