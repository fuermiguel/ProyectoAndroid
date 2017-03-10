package com.codepath.example.masterdetailmanual;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import android.view.Menu;

public class ItemDetailActivity extends Activity {
	ItemDetailFragment fragmentItemDetail;

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

		//Paso3: Crear un nuevo fragmento y añadirlo a la actividad
		Fragment itemDetailFragment = ItemDetailFragment.newInstance(nombreDeporte);
		transaction.replace(R.id.containerDetalle,itemDetailFragment);

		//Paso4: Confirmar los cambios
		transaction.commit();

		/*if (savedInstanceState == null) {
			fragmentItemDetail = ItemDetailFragment.newInstance(item);
			FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
			ft.replace(R.id.flDetailContainer, fragmentItemDetail);
			ft.commit();
		}*/
	}

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.item_detail, menu);
		return true;
	}*/

}
