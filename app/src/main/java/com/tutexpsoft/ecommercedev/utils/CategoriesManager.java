package com.tutexpsoft.ecommercedev.utils;

import com.tutexpsoft.ecommercedev.model.MiniCategory;
import com.tutexpsoft.ecommercedev.model.MiniSubCategory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by noushad on 12/10/17.
 */

public class CategoriesManager {

    public CategoriesManager() {
    }

    public static List<MiniCategory> getElectronicsList() {
        List<String> categoryTitles = new ArrayList<>();
        categoryTitles.add("Mobile");
        categoryTitles.add("Mobile Accessories");
        categoryTitles.add("Cameras & Accessories");
        categoryTitles.add("Audio & Video");
        categoryTitles.add("Smart Watches & Wearables");
        categoryTitles.add("Laptops");
        categoryTitles.add("Desktop PCs");
        categoryTitles.add("Gaming And Accessories");
        categoryTitles.add("Tablets");
        categoryTitles.add("Computer Accessories");
        categoryTitles.add("Televisions");
        categoryTitles.add("Personal HealthCare");
        categoryTitles.add("Printer, Monitors And More");


        List<MiniCategory> options = new ArrayList<>();

        List<MiniSubCategory> subOptions = new ArrayList<>();
        subOptions.add(new MiniSubCategory(1,"Headphone"));
        subOptions.add(new MiniSubCategory(2,"Speaker"));
        subOptions.add(new MiniSubCategory(3,"HomeTheater"));


        for (int i = 0; i < categoryTitles.size(); i++)
            if(i%2==0) {
                options.add(new MiniCategory(categoryTitles.get(i), subOptions));
            }else {
                options.add(new MiniCategory(categoryTitles.get(i), null));
            }

        return options;
    }


}
