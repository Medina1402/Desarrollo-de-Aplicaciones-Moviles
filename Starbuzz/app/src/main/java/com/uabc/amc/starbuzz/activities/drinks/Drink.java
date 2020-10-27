package com.uabc.amc.starbuzz.activities.drinks;

import com.uabc.amc.starbuzz.R;
import com.uabc.amc.starbuzz.activities.ItemTools;

public class Drink extends ItemTools {

    public static final Drink[] drinks = {
            new Drink("Latte", "A couple of espresso shots with steamed milk", R.drawable.latte2),
            new Drink("Cappuccino", "Espresso, hot milk, and steamed milk foam", R.drawable.cappuccino),
            new Drink("Filter", "Highest quality beans roasted and brewed fresh", R.drawable.filter)
    };

    private Drink(String name, String description, int imageResourceId) {
        super(name, description, imageResourceId);
    }
}
