package com.uabc.amc.beeradviser;

import java.util.ArrayList;
import java.util.List;

public class BeerExpert {
    List<String> getBrands(String color) {
        List<String> _brands = new ArrayList<String>();

        if(color.equals("amber")) {
            _brands.add("Jack Amber");
            _brands.add("Red Moose");
        } else {
            _brands.add("Jail Pale Ale");
            _brands.add("Gout Stout");
        }

        return _brands;
    }
}
