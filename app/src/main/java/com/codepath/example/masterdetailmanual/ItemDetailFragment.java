package com.codepath.example.masterdetailmanual;

import android.app.Activity;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemDetailFragment extends Fragment implements AdapterDetalles.OnCheckBoxSelectedListener {
	private String nombreDeporte;
	private AdapterDetalles adapterDetalles;
	//public  static SharedPreferences preferencias;

	private AdapterDetalles.OnCheckBoxSelectedListener listener;//Almacena la interface que implementa el activity padre



	/*
 	En este método obtenemos el listener de la activity padre si existe.
 	Fijar las callback para interactuar con el Activity durante onAttach():
 	*/
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	/*
	 Instanciar todos los objetos que no sean vistas(Buttons, ListViews, TextViews)
	 en el método onCreate()
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Recupero el argumento pasado por la activity
		nombreDeporte = getArguments().getString("nombreDeporte");

		int idDeporte = (new OperacionesBD()).obtenerIdPorNombre(nombreDeporte,getActivity());

		Cursor detalles = (new OperacionesBD()).obtenerDetalles(getActivity(),idDeporte);
		ArrayList<Lista> lista_detalles= new ArrayList();

		while (detalles.moveToNext()){
			Lista detalle = new Lista(detalles.getString(1),detalles.getString(2));
			lista_detalles.add(detalle);
		}
		//Un adapter personalizado
		adapterDetalles = new AdapterDetalles(getActivity(),lista_detalles);
	}

	@Override
	public void onCheckBoxSelected(View i,String detalle) {
		listener.onCheckBoxSelected(i,detalle);
	}
	/*
	Instanciar los objetos que si son vistas, durante onCreateView():
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_item_detail, 
				container, false);
		ListView listViewDetalles = (ListView) view.findViewById(R.id.lstv_activity_detail);
		listViewDetalles.setAdapter(adapterDetalles);
/*
		View view1 = view.findViewById(R.id.chk_detalle);
		//Tengo que hacer el listener sobre el checkbox
		view1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
				listener.onItemSelected(v);
			}
		});
		*/

		listViewDetalles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View item, int position,
									long rowId) {
				// Retrieve item based on position
				//Lista lista = (Lista) adapterDetalles.getItem(position);
				// llamo al método implemantado en la activity padre de la interface definida en este fragment.
				//listener.onItemSelected(item);
			}
		});
		return view;
	}

    public static ItemDetailFragment newInstance(String nombreDeporte) {
		//ItemDetailActivity.preferences = preferences;
    	ItemDetailFragment fragmentItemDetail = new ItemDetailFragment();
        Bundle args = new Bundle();

		args.putString("nombreDeporte", nombreDeporte);
        fragmentItemDetail.setArguments(args);
        return fragmentItemDetail;
    }
}
