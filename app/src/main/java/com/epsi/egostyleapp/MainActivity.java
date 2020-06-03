package com.epsi.egostyleapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public ArrayList<Pair<String,String>> pairs = new ArrayList<>();
    String description = null;
    String date_limite = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = (TextView) findViewById(R.id.textView2);
        final RecyclerView rv = findViewById(R.id.ListBon);

        /******************************************************/
       RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://192.168.56.1/android_connect/api_all_coupons.php"; // on doit mettre l'adresse ip privée en dur car localhost ne fonctionne pas

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        JSONObject coupon = null;
                        JSONArray jsonArray = null;

                        try {
                            jsonArray = new JSONArray(response);
                            for(int i=0; i<jsonArray.length(); i++){
                                coupon = (JSONObject) jsonArray.get(i);
                                description = coupon.getString("description");
                                date_limite = coupon.getString("date_limite");
                                Pair<String,String> pair = Pair.create(description, date_limite);
                                pairs.add(pair);
                            }
                            rv.setAdapter(new ListeAdapter(pairs)); //contenu de la liste
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("That didn't work!");
            }

        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        /******************************************************/
        rv.setLayoutManager(new LinearLayoutManager(this)); //positionnement des éléments



        /****************Navigation bar***************/
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.mesbons :
                        startActivity(new Intent(getApplicationContext(),
                                BonActivity.class));
                        finish();
                                overridePendingTransition(0,0);
                                return true;
                    case R.id.scanner :
                        startActivity(new Intent(getApplicationContext(),
                                ScanActivity.class));
                        finish();
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.home :
                        return true;
                }
                return false;
            }
        });
    }
}
