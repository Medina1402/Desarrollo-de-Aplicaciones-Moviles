package com.uabc.amc.starbuzz.activities.stores;

import com.uabc.amc.starbuzz.R;
import com.uabc.amc.starbuzz.activities.ItemTools;

public class Store extends ItemTools {
    private String serviceHrs;

    public static final Store[] stores = {
            new Store("Tijuana (Otay)", "09:00 - 19:00", "We are in an ideal area to visit before traveling to the USA, we visit we have many promotions", R.drawable.map_otay),
            new Store("Tijuana (Alameda Otay)", "06:00 - 18:00", "If you visit Alameda Otay Square, you should visit us and try our delicious LATTE drink, it is one of the best in the city", R.drawable.map_alameda),
            new Store("Tijuana (Zona Rio)", "09:00 - 22:00", "Before we go to work, visit us, we have many promotions during morning hours", R.drawable.map_zonario)
    };

    protected Store(String name, String serviceHrs, String description, int imageResourceId) {
        super(name, description, imageResourceId);
        this.serviceHrs = serviceHrs;
    }

    public String getServiceHrs() {
        return serviceHrs;
    }
}
