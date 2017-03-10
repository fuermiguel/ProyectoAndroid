package com.codepath.example.masterdetailmanual;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.Menu;
import android.view.View;


public class ItemDetailActivity extends Activity implements  AdapterDetalles.OnCheckBoxSelectedListener{

	//SharedPreferences preferences = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_item_detail);
		//Obtenemos el parámetro nombre deporte que me pasa la activity padre y que puedo usar
		//para buscar en la base de datos su cheking corespondiente.
		String nombreDeporte =  getIntent().getExtras().getString("nombreDeporte");


		//Paso1: Obtener instancia del administrador de fragmentos
		FragmentManager fragmentManager = getFragmentManager();

		//Paso2: Crear la instancia de la transacción
		FragmentTransaction transaction = fragmentManager.beginTransaction();

		//Paso3: Crear un nuevo fragmento y añadirlo a la actividad, pasando como parámetro nombre deporte y las preferencias
		Fragment itemDetailFragment = ItemDetailFragment.newInstance(nombreDeporte);
		transaction.replace(R.id.containerDetalle,itemDetailFragment);

		//Paso4: Confirmar los cambios
		transaction.commit();


	}


//La v es el checkbox , pero necesito también el detalle
	@Override
	public void onCheckBoxSelected(View v, String detalle) {
		//guardar en un sharedPreferences los que estén checkeados.


		SharedPreferences preferences = getSharedPreferences("MisPreferencias",MODE_PRIVATE);
		SharedPreferences.Editor editor = preferences.edit();
		if (v.isSelected()) editor.putString(detalle, "true");
		editor.commit();
	}


	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.item_detail, menu);
		return true;
	}*/

}
