package com.codepath.example.masterdetailmanual;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

import android.view.Menu;

public class ItemDetailActivity extends Fragment {
	ItemDetailFragment fragmentItemDetail;
/*
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_detail);
		Item item = (Item) getIntent().getSerializableExtra("item");
		if (savedInstanceState == null) {
			fragmentItemDetail = ItemDetailFragment.newInstance(item);
			FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
			ft.replace(R.id.flDetailContainer, fragmentItemDetail);
			ft.commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.item_detail, menu);
		return true;
	}
*/
}
