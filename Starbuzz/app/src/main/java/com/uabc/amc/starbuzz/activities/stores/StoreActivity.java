package com.uabc.amc.starbuzz.activities.stores;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.uabc.amc.starbuzz.R;
import com.uabc.amc.starbuzz.activities.foods.Food;

import java.util.Objects;

public class StoreActivity extends AppCompatActivity {

    public static final String EXTRA_STORE_ID = "store_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        int storeId = (int) Objects.requireNonNull(getIntent().getExtras()).get(EXTRA_STORE_ID);
        Store store = Store.stores[storeId];
        setTitle("Store - " + store.getName());

        TextView name = findViewById(R.id.name);
        name.setText(store.getName());

        TextView description = findViewById(R.id.description);
        description.setText(store.getDescription());

        TextView service = findViewById(R.id.service);
        service.setText(store.getServiceHrs());

        ImageView imageView = findViewById(R.id.photo);
        imageView.setImageResource(store.getImageResourceId());
        imageView.setContentDescription(store.getName());
    }
}