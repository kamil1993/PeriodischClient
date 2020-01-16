package com.example.periodischclient.Rest;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.periodischclient.BerichtigungsManager;
import com.example.periodischclient.Rest.ReqRes.TrackidReq;
import com.example.periodischclient.Rest.ReqRes.TrackidRes;
import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;

public class RestManager {

    private static int trackid = 0;
    static  TrackidRes resp;
    public static RequestQueue  queue;
    public static int trackidInit(AppCompatActivity activity, TrackidReq tid) {
        BerichtigungsManager.isInternetGranted(activity);
        queue = Volley.newRequestQueue(activity.getApplicationContext());
        String url ="http://pi-bo.dd-dns.de:8080/LM-Server/api/v2/track";
        Gson gson = new Gson();
        String json = gson.toJson(tid);
        JSONObject obj = null;
        try {
            obj = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // Request a string response from the provided URL.
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, url,obj,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        TrackidRes res = gson.fromJson(response.toString(),TrackidRes.class);
                        trackid = res.trackid;
                        resp = res;
                        Log.e("trackid","\t"+trackid);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("trackid Fehler", "\t" + trackid);
                Toast.makeText(activity.getApplicationContext(), "Trackid fehlgeschlagen", Toast.LENGTH_LONG);
            }

        })
            /*
        {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() {
                try {
                    return json.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    Log.e("Unsupported Encoding while trying to get the bytes of %s using %s", "fukkkkkkk");
                    return null;
                }
            }
        }

             */
        ;

        queue.add(stringRequest);
        return trackid;
    }


}
