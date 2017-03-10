package com.codepath.example.masterdetailmanual;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.FrameLayout;

import com.codepath.example.masterdetailmanual.ItemsListFragment.OnItemSelectedListener;

public class ItemsListActivity extends Activity implements OnItemSelectedListener {
	private boolean isTwoPane = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_items);
		determinePaneLayout();
	}

	private void determinePaneLayout() {
		//Busco el identificador del FrameLayout del layout dos paneles donde se insertará el fragment
		//de manera programatica.
		FrameLayout fragmentItemDetail = (FrameLayout) findViewById(R.id.flDetailContainer);
		//Si existe, activo la comunicación del fragmento con la activity para poder escuchar los eventos
		//que se producen sobre la lista en el activity.
		if (fragmentItemDetail != null) {
			isTwoPane = true;
			/*
			//Si el fragmento está definido de forma estática lo puedo encontrar por su identificador.
			ItemsListFragment fragmentItemsList = 
					(ItemsListFragment) getFragmentManager().findFragmentById(R.id.fragmentItemsList);
			//Para poder activar la comunicación vía listener de eventos.
			fragmentItemsList.setActivateOnItemClick(true);
			*/
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.items, menu);
		return true;
	}

	/*La implementación de la interface definida en el fragmento y que implementa esta activity.
	Es llamada desde el fragment pasondole el item seleccionado */
	@Override
	public void onItemSelected(Deporte item) {
		if (isTwoPane) { // si tenemos pantalla grande añadimos  un fragmneto al layout  de pantalla
						// grande y no tenemos que llamar a otra activity para mostrar los detalles

			ItemDetailFragment fragmentItem = ItemDetailFragment.newInstance(item.getNombre());

			FragmentTransaction ft = getFragmentManager().beginTransaction();
			ft.add(R.id.flDetailContainer, fragmentItem);
			ft.commit();
		} else { // Son actividades separadas
			// Lanzamos la activity detalles con intent.
			Intent i = new Intent(this, ItemDetailActivity.class);
			i.putExtra("nombreDeporte", item.getNombre());
			startActivity(i);
		}
	}

}
