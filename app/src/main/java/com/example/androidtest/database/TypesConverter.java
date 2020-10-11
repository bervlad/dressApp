package com.example.androidtest.database;

import androidx.room.TypeConverter;

import java.util.ArrayList;
import java.util.Arrays;

public class TypesConverter {

    @TypeConverter
    public String fromArray(ArrayList<String> itemsArray) {
        StringBuilder itemsString= new StringBuilder();

        if (itemsArray!=null && itemsArray.size()>0) {
            int i = 0;
            for (String s : itemsArray) {
                if (i < itemsArray.size() - 1) {
                    itemsString.append(s).append(", ");
                } else itemsString.append(s);
                i++;
            }
        } else return null;
        return itemsString.toString();
    }

    @TypeConverter
    public ArrayList <String> toArray(String itemsString) {
        ArrayList<String> authorsArray;
        if (itemsString!=null) {
            authorsArray = new ArrayList<String>(
                    Arrays.asList(itemsString.split(", "))
            ); } else return null;
        return authorsArray;
    }

}
