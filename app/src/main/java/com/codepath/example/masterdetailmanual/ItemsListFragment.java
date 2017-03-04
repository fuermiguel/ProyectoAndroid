package com.codepath.example.masterdetailmanual;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ItemsListFragment extends Fragment {
	private AdapterDeportes adapterDeportes;
	private ListView lvItems;


	private OnItemSelectedListener listener;//Almacena la interface que implementa el activity padre

	//Definimos la interface que tiene que implementar la activity padre
	public interface OnItemSelectedListener {
		void onItemSelected(Deporte i);
	}

	/*
	 En este método obtenemos el listener de la activity padre si existe
	 */
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if (activity instanceof OnItemSelectedListener) {
			listener = (OnItemSelectedListener) activity;
		} else {
			throw new ClassCastException(activity.toString()
					+ " must implement ItemsListFragment.OnItemSelectedListener");
		}
	}
	/*
	Creación inicial del fragment
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//insertarDatosPrueba();//todo Cuidado con descomentar que si ya existen los datos me dá error
		//solo descomentar cuando ejecuto el programa por primera vez.


		Cursor deportes = (new OperacionesBD()).obtenerDeportes(getActivity());
		ArrayList<Deporte> lista_deportes= new ArrayList();

		while (deportes.moveToNext()){
			Deporte deporte = new Deporte(deportes.getString(1),deportes.getString(2));
			lista_deportes.add(deporte);
		}
		//Un adapter personalizado
		adapterDeportes = new AdapterDeportes(getActivity(),lista_deportes);

	}

	/*
	Donde se relaciona el layout al fragment
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate view
		View view = inflater.inflate(R.layout.fragment_items_list, container,
				false);
		// Bind adapter to ListView
		lvItems = (ListView) view.findViewById(R.id.lvItems);
		lvItems.setAdapter(adapterDeportes);
		lvItems.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View item, int position,
					long rowId) {
				// Retrieve item based on position
				Deporte deporte = (Deporte) adapterDeportes.getItem(position); //todo esto queda por añadir al adapter personalizado
				// llamo al método implemantado en la activity padre de la interface definida en este fragment.
				listener.onItemSelected(deporte);
			}
		});
		return view;
	}
	
	/**
	 * Turns on activate-on-click mode. When this mode is on, list items will be
	 * given the 'activated' state when touched.
	 */
	public void setActivateOnItemClick(boolean activateOnItemClick) {
		// When setting CHOICE_MODE_SINGLE, ListView will automatically
		// give items the 'activated' state when touched.
		lvItems.setChoiceMode(
				activateOnItemClick ? ListView.CHOICE_MODE_SINGLE
						: ListView.CHOICE_MODE_NONE);
	}

	private void insertarDatosPrueba(){

		OperacionesBD operacionesBD = new OperacionesBD();

		// Inserción Deporte
		operacionesBD.insertarDeporte(new Deporte("ciclismo","ciclismo"), getActivity());
		operacionesBD.insertarDeporte(new Deporte("natacion","natacion"), getActivity());
		operacionesBD.insertarDeporte(new Deporte("trail","trail"), getActivity());

		// Inseción lista
		operacionesBD.insertarLista(new Lista("foto1","detalle1"),getActivity(),"ciclismo");
		operacionesBD.insertarLista(new Lista("foto2","detalle2"),getActivity(),"ciclismo");
		operacionesBD.insertarLista(new Lista("foto3","detalle3"),getActivity(),"ciclismo");
		operacionesBD.insertarLista(new Lista("foto4","detalle4"),getActivity(),"ciclismo");
		operacionesBD.insertarLista(new Lista("foto5","detalle5"),getActivity(),"ciclismo");

	}
}
