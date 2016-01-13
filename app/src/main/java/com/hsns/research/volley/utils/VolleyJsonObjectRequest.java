package com.hsns.research.volley.utils;

import android.app.Activity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

/**
 * Created by otdom on 1/13/16.
 */
public class VolleyJsonObjectRequest extends VolleyRequestBase {

    public VolleyJsonObjectRequest(String url,int method, Activity activity) {
        super(url, method, activity);
    }

    @Override
    public void request(VolleyRequestListener volleyRequestListener) {

        super.setVolleyRequestListener(volleyRequestListener);

        JsonObjectRequest jsonRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(VolleyJsonObjectRequest.super.volleyRequestListener != null)
                            VolleyJsonObjectRequest.super.volleyRequestListener.onResponse(response);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        if(VolleyJsonObjectRequest.super.volleyRequestListener != null)
                            VolleyJsonObjectRequest.super.volleyRequestListener.onError(error);
                    }
                });

        jsonRequest.setTag(getRequestTag());
        VolleySingleton.getInstance(activity).addToRequestQueue(jsonRequest);
    }
}
