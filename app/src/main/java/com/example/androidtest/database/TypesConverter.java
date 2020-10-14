package com.example.androidtest.database;

import android.net.Uri;

import androidx.room.TypeConverter;

import java.util.ArrayList;
import java.util.Arrays;

public class TypesConverter {

    @TypeConverter
    public String fromUri(Uri uri) {
        if (uri!=null)
        return uri.toString();
        else return null;
    }

    @TypeConverter
    public Uri toUsi(String uriString) {
        if (uriString!=null)
        return Uri.parse(uriString);
        else return null;
    }

}
