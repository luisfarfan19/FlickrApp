package com.example.luisf.flickrapp;

import android.app.DownloadManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.ArrayList;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        final GridView gvImageGridView = (GridView) findViewById(R.id.gvImageCategory);
        final ArrayList<String> imagesArray = new ArrayList<String>();
        final ArrayList<String> urlPhotoInfoArray = new ArrayList<String>();

        Bundle bundle = getIntent().getExtras();
        final String category = bundle.getString("category");

        final String flickrKey = "c603b03e207f60f233b5cd9c031913c7";
        //https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=c603b03e207f60f233b5cd9c031913c7&tags=City&safe_search=1&per_page=20&format=json&jsoncallback=?
        String url = "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key="+
                flickrKey +"&tags="+ category +"&safe_search=1&per_page=20&format=json&jsoncallback=?";

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response){
                        try{
                            String jsonString = response.substring(14,response.length()-1);
                            JSONObject mainObject = new JSONObject(jsonString);
                            JSONObject photosObject = mainObject.getJSONObject("photos");
                            JSONArray photosArray = photosObject.getJSONArray("photo");
                            for(int i = 0; i < photosArray.length(); i++){
                                JSONObject photoObject = photosArray.getJSONObject(i);
                                String farmId = photoObject.getString("farm");
                                String serverId = photoObject.getString("server");
                                String photoId = photoObject.getString("id");
                                String secret = photoObject.getString("secret");

                                String photoUrl = "https://farm" + farmId + ".staticflickr.com/"
                                        + serverId + "/"+photoId+"_"+secret+"_m.jpg";

                                String urlPhotoInfo = "https://api.flickr.com/services/rest/" +
                                        "?method=flickr.photos.getInfo&api_key=" + flickrKey +
                                        "&photo_id=" + photoId + "&secret=" + secret +
                                        "&format=json&nojsoncallback=1";

                                imagesArray.add(photoUrl);
                                urlPhotoInfoArray.add(urlPhotoInfo);
                            }

                            Log.d("imagesArray: ", imagesArray.toString());
                            gvImageGridView.setAdapter(new ImageAdapter(getApplicationContext(), imagesArray));

                            gvImageGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Intent intent1 = new Intent(Activity2.this, Activity3.class);
                                    intent1.putExtra("imageInfo", urlPhotoInfoArray.get(position));
                                    intent1.putExtra("imageUrl", imagesArray.get(position));
                                    startActivity(intent1);
                                }
                            });

                        }catch(JSONException e){
                            e.printStackTrace();
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
