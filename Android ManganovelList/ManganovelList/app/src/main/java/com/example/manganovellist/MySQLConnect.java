package com.example.manganovellist;

import android.app.Activity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MySQLConnect {

    private final Activity main;
    private List<String> list;
    private String URL = "http://192.168.1.35:8080/anime/connect.php?status=0";
    private CallBack mCallBack;

    public MySQLConnect(Activity mainA, CallBack callBack){
        main = mainA;
        list = new ArrayList<String>();
        this.mCallBack = callBack;

    }

    public void fetchData(final String action,final String  message, String SQL, String UID){

        String url = URL ;

        url = url+"&action="+action;
        if(message != ""){
            url = url+"&bookName="+message;
        }

        if(UID != ""){
            url = url+"&userId="+UID;
        }

        if(SQL != ""){
            url = url+"&sql="+SQL;
        }

        Log.d("Joe  Connection URL ", url);


        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(action == "all") {
                    showJSONgetDataName(response);
                }else if(action == "all_by_user") {
                    showJSONgetDataName(response);
                }else if(action == "collection") {
                    showJSONgetDatabookNo(response);
                }
//                Toast.makeText(main, list.get(0), Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(main, error.getMessage().toString(), Toast.LENGTH_LONG).show();
            }
        }
        );

        RequestQueue requestQueue = Volley.newRequestQueue(main.getApplicationContext());
        requestQueue.add(stringRequest);
    }

    public void showJSONgetDataName(String response){
        String comment = "";

        try {

            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray("result");

            for(int i =0; i< result.length();i++){
                JSONObject collectData = result.getJSONObject(i);
                comment = collectData.getString("book_name");
                list.add(comment);
            }

            /**
             * ตัว call back บอกว่าทำงานเสร็จแล้วนะ
             */
            mCallBack.onSuccess();
            getDataName();

        }catch (JSONException ex){ex.printStackTrace();}

    }

    public List<String> getDataName(){
        return list;
    }

    public void showJSONgetDatabookNo(String response){
        String comment = "";
        String comment2 = "";

        try {

            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray("result");

            for(int i =0; i< result.length();i++){
                JSONObject collectData = result.getJSONObject(i);
                comment = collectData.getString("flag");
                list.add(0,comment);
                Log.d("Joe showJSON", comment);
            }


            /**
             * ตัว call back บอกว่าทำงานเสร็จแล้วนะ
             */
            mCallBack.onSuccess();
            getDatabookNo();

        }catch (JSONException ex){ex.printStackTrace();}

    }

    /**
     * @return data ที่ใช้งานได้จริง จะอยู่ใน method นี้
     */
    public List<String> getDatabookNo(){
        return list;
    }

    public interface CallBack{
        void onSuccess();
    }
}
