package com.hsns.research.volley.utils;

import android.app.Activity;

/**
 * Created by otdom on 1/13/16.
 */
public abstract class VolleyRequestBase {
    int method;
    String url = "";
    Activity activity;
    VolleyRequestListener volleyRequestListener;

    public void setVolleyRequestListener(VolleyRequestListener volleyRequestListener) {
        this.volleyRequestListener = volleyRequestListener;
    }

    public VolleyRequestBase(String url, Activity activity) {
        this.url = url;
        this.activity = activity;
    }

    public VolleyRequestBase(String url, int method, Activity activity) {
        this(url, activity);
        this.method = method;
    }

    public abstract void request(VolleyRequestListener volleyRequestListener);

    public String getRequestTag(){
        String tag = "";
        switch (method){
            case -1:
                tag = "DEPRECATED_GET_OR_POST";
                break;
            case 0:
                tag = "GET";
                break;
            case 1:
                tag = "POST";
                break;
            case 2:
                tag = "PUT";
                break;
            case 3:
                tag = "DELETE";
                break;
            case 4:
                tag = "HEAD";
                break;
            case 5:
                tag = "OPTIONS";
                break;
            case 6:
                tag = "TRACE";
                break;
            case 7:
                tag = "TRACE";
                break;
        }
        return tag;
    };
}
