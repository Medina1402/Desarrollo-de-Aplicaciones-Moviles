package com.uabc.amc.starbuzz.activities.foods;

import com.uabc.amc.starbuzz.R;
import com.uabc.amc.starbuzz.activities.ItemTools;

public class Food extends ItemTools {

    public static final Food[] foods = {
            new Food("Sandwich", "Enjoy the experience of fresh ingredients and the best meat in town", R.drawable.sandwich),
            new Food("Creeps", "A light dessert ideal to accompany with one of our drinks", R.drawable.crepas),
            new Food("Muffin", "The best of high pastries perfectly balanced with healthy ingredients", R.drawable.muffin)
    };

    protected Food(String name, String description, int imageResourceId) {
        super(name, description, imageResourceId);
    }
}
