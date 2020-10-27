package com.uabc.amc.starbuzz.activities.stores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.uabc.amc.starbuzz.R;

public class StoreCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_category);
        setTitle(R.string.title_category_activity_store);

        ArrayAdapter<Store> listAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                Store.stores
        );

        ListView listStore = (ListView) findViewById(R.id.list_store);
        listStore.setAdapter(listAdapter);

        /*
         * Change item Listener
         */
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(StoreCategoryActivity.this, StoreActivity.class);
                intent.putExtra(StoreActivity.EXTRA_STORE_ID, (int) l);
                startActivity(intent);
            }
        };

        listStore.setOnItemClickListener(itemClickListener);

    }
}