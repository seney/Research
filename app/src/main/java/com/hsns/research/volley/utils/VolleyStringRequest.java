package com.hsns.research.volley.utils;

import android.app.Activity;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hsns.research.R;

/**
 * Created by otdom on 1/13/16.
 */
public class VolleyStringRequest extends VolleyRequestBase {

    public VolleyStringRequest(String url, int method, Activity activity) {
        super(url, method, activity);
    }

    @Override
    public void request(VolleyRequestListener listener) {

        super.setVolleyRequestListener(listener);

        final VolleyRequestListener volleyRequestListener = super.volleyRequestListener;

        StringRequest stringRequest = new StringRequest(method, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(volleyRequestListener != null)
                            volleyRequestListener.onResponse(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Something went wrong!");
                error.printStackTrace();
                if(volleyRequestListener != null)
                    volleyRequestListener.onError(error);
            }
        });

        stringRequest.setTag(getRequestTag());
        VolleySingleton.getInstance(activity).addToRequestQueue(stringRequest);
    }
}
