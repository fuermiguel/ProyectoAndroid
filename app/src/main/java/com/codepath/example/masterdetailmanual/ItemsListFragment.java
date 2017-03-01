package com.codepath.example.masterdetailmanual;

import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ItemsListFragment extends Fragment {
	private ArrayAdapter<Item> adapterDeportes;
	private ListView lvItems;

	private OnItemSelectedListener listener;

	public interface OnItemSelectedListener {
		public void onItemSelected(Item i);
	}

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
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//insertarDatosPrueba();//todo Cuidado con descomentar que si ya existen los datos me dá error
		//solo descomentar cuando ejecuto el programa por primera vez.


		Cursor deportes = (new OperacionesBD()).obtenerDeportes(getActivity());
		ArrayList lista_deportes= new ArrayList();

		while (deportes.moveToNext()){
			lista_deportes.add(deportes.getString(1));
		}
		adapterDeportes = new ArrayAdapter<Item>(getActivity(),android.R.layout.simple_list_item_activated_1, lista_deportes);
	}

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
				Item i = adapterDeportes.getItem(position);
				// Fire selected event for item
				listener.onItemSelected(i);
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
		operacionesBD.insertarDeporte(new Deporte("ciclismo"), getActivity());
		operacionesBD.insertarDeporte(new Deporte("natacion"), getActivity());
		operacionesBD.insertarDeporte(new Deporte("trail"), getActivity());

		// Inseción lista
		operacionesBD.insertarLista(new Lista("foto1","detalle1"),getActivity(),"ciclismo");
		operacionesBD.insertarLista(new Lista("foto2","detalle2"),getActivity(),"ciclismo");
		operacionesBD.insertarLista(new Lista("foto3","detalle3"),getActivity(),"ciclismo");
		operacionesBD.insertarLista(new Lista("foto4","detalle4"),getActivity(),"ciclismo");
		operacionesBD.insertarLista(new Lista("foto5","detalle5"),getActivity(),"ciclismo");

		//todo falta insertar los datos de la lista(¿Las imagenes en assents?)


	}
}
