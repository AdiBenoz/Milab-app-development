package com.example.stockmonitor;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class stockPriceFetcher {
    private RequestQueue _queue;
    private final static String REQUEST_URL = "http://10.0.2.2:8080/stockPrice";

    public class stockResponse {
        public boolean isError;
        public String price;

        public stockResponse(boolean isError,String price) {
            this.isError = isError;
            this.price = price;
        }

    }

    public interface stockResponseListener {
        public void onResponse(stockResponse response);
    }

    public stockPriceFetcher(Context context) {
        _queue = Volley.newRequestQueue(context);
    }

    private stockResponse createErrorResponse() {
        return new stockResponse(true, "0");
    }

    public void dispatchRequest(final String stock, final String user, final String userToken , final stockResponseListener listener) {

        JSONObject postBody = new JSONObject();
        try {
            postBody.put("stock", stock);
            postBody.put("user", user);
            postBody.put("token", userToken);
        }
        catch (JSONException e) {
            listener.onResponse(createErrorResponse());
            return;
        }

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, REQUEST_URL, postBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            stockResponse res = new stockResponse(false,
                                    response.getString("price"));
                            listener.onResponse(res);
                        }
                        catch (JSONException e) {
                            listener.onResponse(createErrorResponse());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onResponse(createErrorResponse());
            }
        });

        _queue.add(req);
    }
}
