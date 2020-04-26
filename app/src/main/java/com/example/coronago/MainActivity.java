package com.example.coronago;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    public final Context context = this;
    ProgressDialog dialog;
    TextView deathText, caseText, recoverText;

    Button stateReportCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        intentFilter.addAction("android.net.conn.wifi.WIFI_STATE_CHANGED");


        deathText = (TextView) findViewById(R.id.deathText);
        caseText = (TextView) findViewById(R.id.caseText);
        recoverText = (TextView) findViewById(R.id.recoverText);
        stateReportCard= (Button) findViewById(R.id.stateReport);
        isNetworkConnected();
        //Toast.makeText(getApplicationContext(), "After Network Established...!", Toast.LENGTH_LONG).show();

        stateReportCard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity.this, StateActivity.class);
                startActivity(i);


            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.pre:
                startActivity(new Intent(MainActivity.this,Precaution.class));
                return true;

            case R.id.sym:
                startActivity(new Intent(MainActivity.this,Symtoms.class));
                return true;

            case R.id.govapp:
                startActivity(new Intent(MainActivity.this,GovermentApp.class));
                return true;
            case R.id.aboutme:
                startActivity(new Intent(MainActivity.this,Aboutme.class));

                return true;


            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void hidePDialog() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        hidePDialog();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_items,menu);
            return true;

    }

    public void fetchAllData(){
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading....");
        dialog.show();

        StringRequest request = new StringRequest("https://api.rootnet.in/covid19-in/stats/latest", new Response.Listener<String>() {
            @Override
            public void onResponse(String string) {
                parseJsonData(string);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                MessageDialog dialog = new MessageDialog();
                dialog.show(getSupportFragmentManager(),"error Dialog");

            }
        });

        RequestQueue rQueue = Volley.newRequestQueue(MainActivity.this);
        rQueue.add(request);
        dialog.dismiss();
    }

    public void isNetworkConnected(){
        fetchAllData();
    }

    void parseJsonData(String jsonString) {
        try {
            JSONObject object = new JSONObject(jsonString);
            JSONObject data = object.getJSONObject("data");
            JSONObject summary = data.getJSONObject("summary");
            //set the values
            deathText.setText(summary.getString("deaths"));
            caseText.setText(summary.getString("total"));
            recoverText.setText(summary.getString("discharged"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        dialog.dismiss();
    }
}
