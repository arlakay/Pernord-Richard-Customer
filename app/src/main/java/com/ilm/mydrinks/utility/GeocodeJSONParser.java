package com.ilm.mydrinks.utility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ILM on 4/11/2016.
 */
public class GeocodeJSONParser {
    public List<HashMap<String,String>> parse(JSONObject jObject){

        JSONArray jPlaces = null;
        try {
            jPlaces = jObject.getJSONArray("results");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return getPlaces(jPlaces);
    }

    private List<HashMap<String, String>> getPlaces(JSONArray jPlaces){
        int placesCount = jPlaces.length();
        List<HashMap<String, String>> placesList = new ArrayList<HashMap<String,String>>();
        HashMap<String, String> place = null;

        /** Taking each place, parses and adds to list object */
        for(int i=0; i<placesCount;i++){
            try {
                /** Call getPlace with place JSON object to parse the place */
                place = getPlace((JSONObject)jPlaces.get(i));
                placesList.add(place);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return placesList;
    }

    private HashMap<String, String> getPlace(JSONObject jPlace){

        HashMap<String, String> place = new HashMap<String, String>();
        String formatted_address = "-NA-";
        String lat="";
        String lng="";

        try {
            // Extracting formatted address, if available
            if(!jPlace.isNull("formatted_address")){
                formatted_address = jPlace.getString("formatted_address");
            }

            lat = jPlace.getJSONObject("geometry").getJSONObject("location").getString("lat");
            lng = jPlace.getJSONObject("geometry").getJSONObject("location").getString("lng");

            place.put("formatted_address", formatted_address);
            place.put("lat", lat);
            place.put("lng", lng);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return place;
    }

}
