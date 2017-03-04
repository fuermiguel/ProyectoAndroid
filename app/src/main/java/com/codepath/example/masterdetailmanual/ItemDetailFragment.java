package com.codepath.example.masterdetailmanual;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ItemDetailFragment extends Fragment {
	private String nombreDeporte;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Recupero el argumento pasado por la activity
		nombreDeporte = getArguments().getString("nombreDeporte");

		//todo tengo que hacer el layout, hacer la consulta a la base de datos y poblar el layout
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_item_detail, 
				container, false);
		TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);
		TextView tvBody = (TextView) view.findViewById(R.id.tvBody);
		/*tvTitle.setText(item.getTitle());
		tvBody.setText(item.getBody());*/
		return view;
	}

    public static ItemDetailFragment newInstance(Item item) {
    	ItemDetailFragment fragmentItemDetail = new ItemDetailFragment();
        Bundle args = new Bundle();
		//Aquí pasamos el objeto completo(serializable), pero podria pasar un string con el nombre del deporte
        //args.putSerializable("item", item);
		args.putString("nombreDeporte", item.toString());
        fragmentItemDetail.setArguments(args);
        return fragmentItemDetail;
    }
}
