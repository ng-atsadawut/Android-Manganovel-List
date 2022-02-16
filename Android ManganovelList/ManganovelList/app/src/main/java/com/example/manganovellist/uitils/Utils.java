package com.example.manganovellist.uitils;

import android.app.ProgressDialog;
import android.content.Context;

public class Utils {

    static ProgressDialog progressDialog;

    public static void showProgressDialog(Context context){
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(android.R.style.Widget_ProgressBar);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public static void dismiss(){
        try {
            if (progressDialog != null && progressDialog.isShowing()){
                progressDialog.dismiss();
            }

        }catch (Exception e){

        }
    }
}
