package com.example.coronago;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class GovermentApp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goverment_app);
    }

    public void fetchPlaystore(View v){

        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=nic.goi.aarogyasetu")));
        }catch(ActivityNotFoundException e){
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=nic.goi.aarogyasetu")));
        }

    }
}
