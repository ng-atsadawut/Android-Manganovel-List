package com.example.manganovellist;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.manganovellist.uitils.GridAdapter;
import com.example.manganovellist.uitils.Utils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MySQLConnect.CallBack {

    private static final String TAG = MainActivity.class.getSimpleName();

    private ListView dataView;
    private MySQLConnect mySQLConnect;
    private List<String> items= new ArrayList<>();

    private FirebaseAuth firebaseAuth;

    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;

    private ArrayAdapter<String> adapter;

    SearchView searchView;
    Intent myIntent;

    public String Uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myIntent = new Intent(this, GridAdapter.class);
        mDrawerlayout = (DrawerLayout) findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this,mDrawerlayout,R.string.open,R.string.close);
        mDrawerlayout.addDrawerListener(mToggle);
        mToggle.syncState();

        init();
        update();

        searchView = (SearchView) findViewById(R.id.search);
        searchView.setQueryHint("Search View");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
//                Toast.makeText(getBaseContext(), query, Toast.LENGTH_LONG).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
//                Toast.makeText(getBaseContext(), newText, Toast.LENGTH_LONG).show();
                adapter.getFilter().filter(newText.toString());
                return false;
            }
        });

        dataView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                myIntent.putExtra("Book",items.get(i));
//                Toast.makeText(getBaseContext(),items.get(i),Toast.LENGTH_SHORT).show();
                startActivity(myIntent);
            Log.d("Joe setOnItemClick", items.get(i));
            }
        });


//        Log.d("J onCreate", "Start onCreate");
    }

    //****************************  Menu Toolbar **********************************
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.drawermenu, menu);
        return true;
    }


    //******************************  Logout **************************************
    private void Logout() {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }

    public void getUID(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Uid = user.getUid();
    }

    //****************************** onBackPressed ***********************************
    //Do you want to exit?
    public void onBackPressed() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("MangaNovelList");
        dialog.setIcon(R.drawable.common_google_signin_btn_icon_dark_normal);
        dialog.setCancelable(true);
        dialog.setMessage("คุณต้องการออกจากแอปพลิเคชัน. \n ใช่ หรือ ไม่");
        dialog.setPositiveButton("ใช่", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_Collection: {
                startActivity(new Intent(MainActivity.this, Collection.class));
                finish();
                break;
            }
            case R.id.action_logout: {
                Logout();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void update(){
//        items = mySQLConnect.getData();
//        //วิธีใช้ log กรรี ดู list
//        Log.d("TAG", "item: "+items.toString());
//        dataView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,items));
    }

    public void init(){
        /**
         * แสดง progress ขณะโหลดข้อมูล
         */
        String action = "all";
        String message = "";
        String SQL = "";
        String UID = "";
        Utils.showProgressDialog(this);
        dataView = (ListView) findViewById(R.id.dataViewID);
        mySQLConnect = new MySQLConnect(MainActivity.this, this);//init this class.
        mySQLConnect.fetchData(action, message, SQL, UID);// start fetch.

    }

    @Override
    public void onSuccess() {
        /**
         * ปิด progress หลังจาก โหลดข้อมูลเสร็จ
         */
        Utils.dismiss();
        items.clear();

        items = mySQLConnect.getDataName();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,items );

        Log.d("Joe ", "init: "+new Gson().toJson(items));
        dataView.setAdapter(adapter);
    }

}