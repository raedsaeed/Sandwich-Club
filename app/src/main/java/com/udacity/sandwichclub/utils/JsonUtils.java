package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    private static final String TAG = "JsonUtils";
    public static Sandwich parseSandwichJson(String json) {

        Sandwich sandwich = null;
        String mainName;
        List<String> alsoKnownAs = new ArrayList<>();
        String placeOfOrigin;
        String description;
        String image;
        List<String> ingredients = new ArrayList<>();
        try {
            JSONObject allJson = new JSONObject(json);
            JSONObject jsonObject = allJson.getJSONObject("name");

            //Get the main name
            mainName = jsonObject.getString("mainName");

            //Get the other known names
            JSONArray jsonKnown = jsonObject.getJSONArray("alsoKnownAs");
            for (int i =0; i<jsonKnown.length(); i++) {
                alsoKnownAs.add(jsonKnown.getString(i));
            }

            //Get the sandwich place
            placeOfOrigin = allJson.getString("placeOfOrigin");

            //Get the description
            description = allJson.getString("description");

            //Get the image
            image = allJson.getString("image");

            JSONArray jsonIngrediants = allJson.getJSONArray("ingredients");
            for (int i=0; i<jsonIngrediants.length(); i++) {
                ingredients.add(jsonIngrediants.getString(i));
            }

            //Finally make the sandwich :)
            sandwich = new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (sandwich != null) {
            Log.d(TAG, "parseSandwichJson: " + sandwich.toString());
        }
        return sandwich;
    }
}
