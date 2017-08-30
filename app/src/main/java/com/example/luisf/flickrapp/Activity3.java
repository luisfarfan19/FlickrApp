package com.example.luisf.flickrapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Activity3 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        /*Get Activity Elements*/
        final ImageView ivPhoto = (ImageView) findViewById(R.id.ivPhoto);
        final ImageView ivUserPhoto = (ImageView) findViewById(R.id.ivUserPhoto);
        final TextView tvPhotoInfo = (TextView) findViewById(R.id.tvPhotoInfo);
        final TextView tvUserInfo = (TextView) findViewById(R.id.tvUserInfo) ;

        Bundle bundle = getIntent().getExtras();
        final String imageInfoUrl = bundle.getString("imageInfo");
        final String imageUrl = bundle.getString("imageUrl");

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, imageInfoUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            /*Obtain all the information from the API*/
                            JSONObject mainObject = new JSONObject(response);
                            JSONObject photoObject = mainObject.getJSONObject("photo");
                            JSONObject ownerObject = photoObject.getJSONObject("owner");
                            JSONObject titleObject = photoObject.getJSONObject("title");
                            JSONObject descriptionObject = photoObject.getJSONObject("description");
                            JSONObject datesObject = photoObject.getJSONObject("dates");

                            String username = ownerObject.getString("username");
                            String title = titleObject.getString("_content");
                            String description = descriptionObject.getString("_content");
                            String date = datesObject.getString("taken");

                            String iconFarm = ownerObject.getString("iconfarm");
                            String iconServer = ownerObject.getString("iconserver");
                            String nsid = ownerObject.getString("nsid");
                            String userLocation = ownerObject.getString("location");
                            String userRealName = ownerObject.getString("realname");

                            String urlUserPhoto = "http://farm" + iconFarm +
                                    ".staticflickr.com/" + iconServer +
                                    "/buddyicons/" + nsid + ".jpg";

                            Picasso.with(getApplicationContext()).load(urlUserPhoto).into(ivUserPhoto);
                            tvUserInfo.setText("Username: " + username +
                                    "\nReal Name: " + userRealName+
                                    "\nLocation: " + userLocation);

                            /*Set all the information in the textView and ImageView*/
                            Picasso.with(getApplicationContext()).load(imageUrl).into(ivPhoto);
                            tvPhotoInfo.setText("Title: " + title +
                                    "\nDescription: " + description +
                                    "\nDate: " + date );

                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("log2=", error.toString());
                Log.e("log3=", error.toString());

            }
        });
        requestQueue.add(stringRequest);
    }
}
