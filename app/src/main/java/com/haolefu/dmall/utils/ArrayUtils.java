package com.haolefu.dmall.utils;

import org.json.JSONArray;
import org.json.JSONException;

public class ArrayUtils {

    public static String[] toArrays(JSONArray ja) throws JSONException {
        String[] objs = new String[ja.length()];
        for (int i = 0; i < ja.length(); i++) {
            objs[i] = (String) ja.get(i);
        }
        return objs;
    }
}
