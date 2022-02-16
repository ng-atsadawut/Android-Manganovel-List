package com.example.manganovellist.uitils;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manganovellist.MainActivity;
import com.example.manganovellist.MySQLConnect;
import com.example.manganovellist.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

public class GridAdapter extends AppCompatActivity implements MySQLConnect.CallBack {

    private MySQLConnect mySQLConnect;
    String SQL = "";
    private ArrayList<String> book_number = new ArrayList<>();
    private ArrayList<String> book_name = new ArrayList<>();
    private ArrayList<String> flag_old = new ArrayList<>();
    private ArrayList<String> flag_new = new ArrayList<>();
    private ArrayList<String> price = new ArrayList<>();
    private ArrayList<String> kid = new ArrayList<>();
    private ArrayList<String> bid = new ArrayList<>();
    private ArrayList<String> nid = new ArrayList<>();
    public String Uid;
    public ImageView Img;

    public String Path_img = "";
    public String directors, type, status;

    private String URL = "http://192.168.1.35:8080/anime/connect.php?status=0";
    public String action,message;
    TextView txtBook, txtPrice, txtDirector, txtStatus, txtType;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        try {
            init();

            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("J Onclick","flag_old " + flag_old);
                    Log.d("J Onclick","flag_new " + flag_new);
                    Log.d("J Onclick","kid " + kid);
                    Log.d("J Onclick","uid " + Uid);
                    Log.d("J Onclick","bid " + bid);
                    Log.d("J Onclick","nid " + nid);

                    AlertDialog.Builder dialog = new AlertDialog.Builder(GridAdapter.this);
                    dialog.setTitle("MangaNovelList");
                    dialog.setIcon(R.drawable.common_google_signin_btn_icon_dark_normal);
                    dialog.setCancelable(true);
                    dialog.setMessage("คุณต้องการบันทึกข้อมูล. \n ใช่ หรือ ไม่");
                    dialog.setPositiveButton("ใช่", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            management(flag_old, flag_new, kid, Uid, bid, nid);
                            finish();
                        }
                    });

                    dialog.setNegativeButton("ไม่", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    dialog.show();
                }
            });
        }catch(Exception e){
            Log.e("GridAdapter ",e.getMessage());
        }
    }

    public void getUID(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Uid = user.getUid();
    }


    public void init(){
        /**
         * แสดง progress ขณะโหลดข้อมูล
         */
        getUID();

        Img = findViewById(R.id.imgShow);
        btnSave = findViewById(R.id.btn_save);
        action = "collection";
        Intent BookDetailIntent = getIntent();
        message = BookDetailIntent.getStringExtra("Book");
        txtPrice = findViewById(R.id.txt_price);
        txtBook = findViewById(R.id.txt_bookname);
        txtBook.setText(message);
        URL = URL + "&action="+action+"&bookName="+message+"&userId='"+Uid+"'";

        txtDirector = findViewById(R.id.txt_director);
        txtStatus = findViewById(R.id.txtstatus);
        txtType = findViewById(R.id.txttype);

//        txtBook.setText(message);
        Utils.showProgressDialog(this);
        AsyncTaskRunner runner = new AsyncTaskRunner();
        runner.execute();
//        mySQLConnect = new MySQLConnect(GridAdapter.this, this);//init this class.
//        mySQLConnect.fetchData(action, message);// start fetch.

    }

    @Override
    public void onSuccess() {
        /**
         * ปิด progress หลังจาก โหลดข้อมูลเสร็จ
         */
        /*Utils.dismiss();
        // get the reference of RecyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        // set a GridLayoutManager with 2 number of columns , horizontal gravity and false value for reverseLayout to show the items from start to end
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 4);
        recyclerView.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
        //  call the constructor of CustomAdapter to send the reference and data to Adapter
        CustomAdapter customAdapter = new CustomAdapter(GridAdapter.this, personNames2);
        recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView*/

    }

    public void management(List<String> flag_old, List<String> flag_new, List<String> kid, String Uid, List<String> bid, List<String> nid){

        for(int i=0;i<flag_new.size();i++) {
            Log.d("J management","flag_old "+ flag_old.get(i));
            Log.d("J management","flag_new "+ flag_new.get(i));
            if (flag_old.get(i) != flag_new.get(i)) {
                if (flag_new.get(i).equals("Y")) {
                    insert(Uid,bid.get(i),nid.get(i));
                } else {
                    delete(kid.get(i));
                }
            }
        }

        Log.d("J Mamage","SQL "+ SQL);

//        ExecuteSQL(SQL);


    }

    public void ExecuteSQL(String SQL){
        String action = "save";
        String message = "";
        String UID = "";

        mySQLConnect = new MySQLConnect(GridAdapter.this, this);//init this class.
        mySQLConnect.fetchData(action, message, SQL, UID);// start fetch.
    }

    public void insert(String Uid, String bid, String nid){
        SQL = "";
        SQL = SQL + " INSERT INTO keep (uid, bid, nid) VALUES ('" + Uid + "'," + bid + "," + nid + ") ; ";
        ExecuteSQL(SQL);
    }

    public void delete(String kid){
        SQL = "";
        SQL = SQL + " DELETE FROM keep WHERE kid = " + kid + ";" ;
        ExecuteSQL(SQL);
    }

    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        //            When AsyncTask to use it start firsty
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d("J onPreExecute ","onPreExecute");

        }

        @Override
        protected String doInBackground(String... strings) {
            Log.d("J doInBackground ", "doInBackground");
            try {
                Log.d("J doInBackground", "Try");
                Log.d("J doInBackground", URL);
                URL url = new URL(URL);
                URLConnection urlConnection = url.openConnection();
                Log.d("J doInBackground", "URL");

                HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
                httpURLConnection.setAllowUserInteraction(false);
                httpURLConnection.setInstanceFollowRedirects(true);
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.connect();
                Log.d("J doInBackground", "httpURLConnection");


                InputStream inputStream = null;
                if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    Log.d("J doInBackground", "HTTP_OK");
                    inputStream = httpURLConnection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);

                    StringBuilder stringBuilder = new StringBuilder();
                    String line = null;

                    while ((line = reader.readLine()) != null) {
                        stringBuilder.append(line + "\n");
                    }

                    inputStream.close();

//                    JSONObject jsonObject = new JSONObject(stringBuilder.toString());

                    JSONArray data = new JSONArray(stringBuilder.toString());

                    ArrayList<HashMap<String, String>> myArrList = new ArrayList<HashMap<String, String>>();
                    HashMap<String, String> map;

                    for(int i = 0; i < data.length(); i++){
                        JSONObject c = data.getJSONObject(i);
                        map = new HashMap<String, String>();
                        map.put("flag", c.getString("flag"));
                        map.put("book_name", c.getString("book_name"));
                        map.put("book_no", c.getString("book_no"));
                        map.put("price", c.getString("price"));
                        map.put("kid", c.getString("kid"));
                        map.put("uid", c.getString("uid"));
                        map.put("bid", c.getString("bid"));
                        map.put("nid", c.getString("nid"));
                        map.put("director_name", c.getString("director_name"));
                        map.put("type_name", c.getString("type_name"));
                        map.put("status_name", c.getString("status_name"));
                        map.put("path_img", c.getString("path_img"));
                        myArrList.add(map);
                    }

                    /*** Write Outout ***/

                    int countPrice = 0;
                    for (int i = 0; i < myArrList.size(); i++) {

                        String sflag = myArrList.get(i).get("flag").toString();
                        String sbook_name = myArrList.get(i).get("book_name").toString();
                        String sbook_no = myArrList.get(i).get("book_no").toString();
                        String sprice = myArrList.get(i).get("price").toString();
                        String sKid = myArrList.get(i).get("kid").toString();
                        String sUid = myArrList.get(i).get("uid").toString();
                        String sBid = myArrList.get(i).get("bid").toString();
                        String sNid = myArrList.get(i).get("nid").toString();

                        directors = myArrList.get(i).get("director_name").toString();
                        type = myArrList.get(i).get("type_name").toString();
                        status = myArrList.get(i).get("status_name").toString();

                        Path_img = myArrList.get(i).get("path_img").toString();
                        if(sflag.equals("Y")){
                            int result = Integer.parseInt(sprice);
                            countPrice = countPrice + result;
                        }

                        flag_old.add(sflag);
                        flag_new.add(sflag);
                        book_name.add(sbook_name);
                        price.add(sprice);
                        book_number.add(sbook_no);
                        kid.add(sKid);
                        bid.add(sBid);
                        nid.add(sNid);


                        Log.d("J doInBackground Array", "flag " + sflag);
                        Log.d("J doInBackground Array", "book_name " + sbook_name);
                        Log.d("J doInBackground Array", "book_no " + sbook_no);
                        Log.d("J doInBackground Array", "price "+ sprice);
                        Log.d("J doInBackground Array", "countPrice "+ countPrice);
                        Log.d("J doInBackground Array", "kid "+ kid);
                        Log.d("J doInBackground Array", "uid "+ Uid);
                        Log.d("J doInBackground Array", "bid "+ bid);
                        Log.d("J doInBackground Array", "nid "+ nid);
                        Log.d("J doInBackground Array", "Path_img "+ Path_img);

                    }

                    txtPrice.setText(String.valueOf(countPrice));

                    Log.d("J sbook_no", "sbook_no "+ book_number);

                } else{

                    Toast.makeText(getBaseContext(),"ขออภัยครับ ผมไม่สามารถติดต่อเซิร์ฟเวอร์ได้ในขณะนี้",Toast.LENGTH_SHORT);
                }



            } catch (MalformedURLException e) {
                Log.d("J doInBackground catch", "MalformedURLException");
                e.printStackTrace();
            } catch (IOException e) {
                Log.d("J doInBackground catch", "IOException");
                e.printStackTrace();
            } catch (JSONException e) {
                Log.d("J doInBackground catch", "JSONException");
                e.printStackTrace();
            } catch (RuntimeException e) {
                Log.d("J doInBackground catch", "RuntimeException");
                e.printStackTrace();
            }finally {

            }

            return null;
        }

        @Override
        protected void onPostExecute(String aVoid) {
            super.onPostExecute(aVoid);
            Utils.dismiss();
            Log.d("J 2", "sbook_no "+ book_number);
            // get the reference of RecyclerView
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            // set a GridLayoutManager with 2 number of columns , horizontal gravity and false value for reverseLayout to show the items from start to end
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 4);
            recyclerView.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
            //  call the constructor of CustomAdapter to send the reference and data to Adapter
            CustomAdapter customAdapter = new CustomAdapter(GridAdapter.this, book_number,flag_new,price);
            recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView
            txtDirector.setText(directors);
            txtStatus.setText(status);
            txtType.setText(type);
            Picasso.with(GridAdapter.this).load(Path_img).into(Img);
            Log.d("J onPostExecute","Start onPostExecute");
            return;
        }

    }


}