package com.example.coronago;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DistrictActivity extends AppCompatActivity {

    TextView t1 ;
    List<DistrictItem> districtItems;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter dAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private static final String URL_DATA = "https://api.covid19india.org/state_district_wise.json";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_district);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        districtItems = new ArrayList<>();
        t1 = (TextView) findViewById(R.id.t1);
        Bundle bundle = getIntent().getExtras();
        t1.setText(bundle.get("stateName").toString());
        loadStateData();
    }

    private void loadStateData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait, fetching Data");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject data = jsonObject.getJSONObject(t1.getText().toString());
                            JSONObject districtData = data.getJSONObject("districtData");
                            Iterator<String> keysStr = districtData.keys();
                            while(keysStr.hasNext()){
                                String key = keysStr.next();
                                JSONObject value = (JSONObject) districtData.get(key);
                                DistrictItem districtItem = new DistrictItem(
                                        key,
                                        "Total : "+value.getString("confirmed"),
                                        "Recovered  : "+value.getString("recovered"),
                                        "Death(s)  : "+value.getString("deceased")
                                );
                                Log.d("Key--->",key);
                                Log.d("Total :--->",value.getString("confirmed"));
                                Log.d("Cured :--->",value.getString("recovered"));
                                Log.d("Death :--->",value.getString("deceased"));
                                districtItems.add(districtItem);
                            }

                            dAdapter = new DistrictAdapter(districtItems,getApplicationContext());
                            recyclerView.setAdapter(dAdapter);


                            //Log.d(" before DISTRICT DATA ",districtData.toString());
//                            for (Map.Entry pair : (Iterable<Map.Entry>) disdata.entrySet()) {
//                                System.out.println(pair.getKey() + " : " + pair.getValue());
//                                Log.d("District " + pair.getKey(), " :" + pair.getKey());
//
//
//                            }
//                            for(int i=0;i<districtData.length();i++){
//                                JSONObject obj = districtData.getJSONObject(i);
//                                Log.d("District "+i,obj.toString());
//
//                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                MessageDialog dialog = new MessageDialog();
                dialog.show(getSupportFragmentManager(),"error Dialog");
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        progressDialog.dismiss();
    }

}
