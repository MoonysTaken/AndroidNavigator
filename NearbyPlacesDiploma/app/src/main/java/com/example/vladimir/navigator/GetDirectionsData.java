package com.example.vladimir.navigator;

import android.graphics.Color;
import android.os.AsyncTask;
import android.view.View;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;

import java.io.IOException;
import java.util.HashMap;


public class GetDirectionsData extends AsyncTask<Object,String,String> {

    private GoogleMap mMap;
    private String googleDirectionsData;
    private LatLng latLng;

    protected void onPreExecute() {
        MapsActivity.progressBar.setVisibility(View.VISIBLE);
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Object... objects) {
        mMap = (GoogleMap)objects[0];
        String url = (String) objects[1];
        latLng = (LatLng)objects[2];



        DownloadURL downloadUrl = new DownloadURL();
        try {
            googleDirectionsData = downloadUrl.readUrl(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return googleDirectionsData;
    }

    @Override
    protected void onPostExecute(String s) {
        MapsActivity.progressBar.setVisibility(View.INVISIBLE);
        HashMap<String,String> directionsList;
        DataParser parser = new DataParser();
        directionsList = parser.parseDirections(s);
        String duration = directionsList.get("duration");
        String distance = directionsList.get("distance");

        mMap.clear();
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.draggable(true);
        markerOptions.title("Duration ="+ duration);
        markerOptions.snippet("Distance = "+ distance);

        mMap.addMarker(markerOptions);


        String[] directionsListData;
        directionsListData = parser.parseDirectionsData(s);
        displayDirection(directionsListData);

    }

    private void displayDirection(String[] directionsList)
    {

        for (String aDirectionsList : directionsList) {
            PolylineOptions options = new PolylineOptions();
            options.color(Color.RED);
            options.width(10);
            options.addAll(PolyUtil.decode(aDirectionsList));
            mMap.addPolyline(options);
        }
    }






}