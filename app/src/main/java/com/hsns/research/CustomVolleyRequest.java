package com.hsns.research;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;

/**
 * Created by SENEY SEAN on 1/12/16.
 */
public class CustomVolleyRequest extends Request<String> {

    private CustomVolleyRequestListener customVolleyRequestListener;

    public CustomVolleyRequest(String url, Response.ErrorListener listener) {
        super(url, listener);
    }

    public CustomVolleyRequest(int method, String url, Response.ErrorListener listener) {
        super(method, url, listener);
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        return null;
    }

    @Override
    protected void deliverResponse(String response) {
        if (customVolleyRequestListener != null)
            customVolleyRequestListener.onRespond(response);
    }

    public void setCustomVolleyRequestListener(CustomVolleyRequestListener customVolleyRequestListener) {
        this.customVolleyRequestListener = customVolleyRequestListener;
    }

    public interface CustomVolleyRequestListener {
        void onRespond(Object response);
    }
}
