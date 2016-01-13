package com.hsns.research;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.JsonObject;
import com.hsns.research.volley.utils.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class VolleyPostVsGetActivity extends AppCompatActivity {

    private Button mBtnPost;
    private Button mBtnGet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley_post_vs_get);
        initViews();
        initEvents();
    }

    private void initEvents() {
        mBtnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mBtnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void initViews() {
        mBtnPost = (Button) findViewById(R.id.btnPost);
        mBtnGet = (Button) findViewById(R.id.btnGet);
    }

    public void requestJsonPost(View view) {
        String url = "http://httpbin.org/post";

        JsonObjectRequest jsonRequest = new JsonObjectRequest
                (Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();

                    }
                });

        jsonRequest.setTag("POST");
        VolleySingleton.getInstance(this).addToRequestQueue(jsonRequest);

        /*StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.i("MyLog", response.toString());
                            JSONObject jsonResponse = new JSONObject(response).getJSONObject("form");

                            String site = jsonResponse.getString("site"),
                                    network = jsonResponse.getString("network");
                            System.out.println("Site: " + site + "\nNetwork: " + network);
                            ((TextView) findViewById(R.id.textView)).setText("Site: " + site + "\nNetwork: " + network);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("site", "code");
                params.put("network", "tutsplus");
                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(postRequest);*/
    }
}
