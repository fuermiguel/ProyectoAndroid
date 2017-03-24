package com.codepath.example.masterdetailmanual;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import java.util.ArrayList;
import java.util.Map;

public class ItemsListActivity extends Activity implements AdapterRecycler.ClickAdapterRecycler, AdapterDetalles.OnCheckBoxSelectedListener {
	private boolean isTwoPane = false;

	/*La implementación de la interface definida en el Adapter y que implementa esta activity.
	Es llamada desde el Adapter pasandole el item seleccionado */

	@Override
	public void onItemAdapterSeleted(Deporte item) {
		if (isTwoPane) { // si tenemos pantalla grande añadimos  un fragmneto al layout  de pantalla
			// grande y no tenemos que llamar a otra activity para mostrar los detalles

			ItemDetailFragment itemDetailFragment = ItemDetailFragment.newInstance(item.getNombre());
			FragmentTransaction ft = getFragmentManager().beginTransaction();


			ft.replace(R.id.flDetailContainer,itemDetailFragment);

			ft.commit();
		} else { // Son actividades separadas
			// Lanzamos la activity detalles con intent.
			Intent i = new Intent(this, ItemDetailActivity.class);
			i.putExtra("nombreDeporte", item.getNombre());
			startActivity(i);
		}
	}



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_items);
		setTitle("Cheking del Material Deportivo");
		determinePaneLayout();

		//Cargo fragmnet de presentación antes de elegir deporte.
		if (isTwoPane) { // si tenemos pantalla grande añadimos  un fragmneto al layout  de pantalla
			// grande y no tenemos que llamar a otra activity para mostrar los detalles

			InicialFragment inicialFragment = InicialFragment.newInstance();
			FragmentTransaction ft = getFragmentManager().beginTransaction();


			ft.replace(R.id.flDetailContainer, inicialFragment);

			ft.commit();
		}
	}

	private void determinePaneLayout() {
		//Busco el identificador del FrameLayout del layout dos paneles donde se insertará el fragment
		//de manera programatica.
		FrameLayout fragmentItemDetail = (FrameLayout) findViewById(R.id.flDetailContainer);
		//Si existe, activo la comunicación del fragmento con la activity para poder escuchar los eventos
		//que se producen sobre la lista en el activity.
		if (fragmentItemDetail != null) {
			isTwoPane = true;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.items, menu);
		return true;
	}



	@Override
	public void onCheckBoxSelected(View i, String detalle, ArrayList datos) {
		//Guardo en sharedPreferences la selección del checkbox
		SharedPreferences preferences = getSharedPreferences("MisPreferencias", MODE_PRIVATE);
		SharedPreferences.Editor editor = preferences.edit();

		CheckBox checkBox = (CheckBox) i;

		if (checkBox.isChecked()) {
			editor.putString(detalle, "true");
		}else{
			editor.putString(detalle,"false");
		}


		editor.commit();


		int size = 0;
		Map<String, ?> allEntries = preferences.getAll();
		for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
			if(entry.getValue().toString().equals("true")) size++;
		}
		if (size == datos.size()){

			//Limpiamos las preferencias

			editor.clear().commit();


			//Cargamos el fragmnet de completo

			//Paso1: Obtener instancia del administrador de fragmentos
			FragmentManager fragmentManager = getFragmentManager();

			//Paso2: Crear la instancia de la transacción
			FragmentTransaction transaction = fragmentManager.beginTransaction();

			//Paso3: Crear un nuevo fragmento y añadirlo a la actividad.
			Fragment completoFragment = CompletoFragment.newInstance();
			transaction.replace(R.id.flDetailContainer, completoFragment);

			//Paso4: Confirmar los cambios
			transaction.commit();
		}

	}
}
