package com.example.arnau_munoz_dam2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Vip extends AppCompatActivity {

    EditText etTelf, etBirthday, etWeight;
    RequestQueue requestQueue;

    private Spinner sportFrecuencySpinner;
    private String selectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vip);

        Intent intent = getIntent();
        String savedEmail = intent.getStringExtra("savedEmail");

        Button vipFormButton = findViewById(R.id.vipFormButton);

        etTelf = findViewById(R.id.etTelf);
        etBirthday = findViewById(R.id.etBirthday);
        etWeight = findViewById(R.id.etWeight);

        // SPINNER:
        sportFrecuencySpinner = findViewById(R.id.sportFrecuencySpinner);

        String[] opciones = {"Nunca", "Mensualmente", "Semanalmente", "Diariamente"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opciones);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sportFrecuencySpinner.setAdapter(adapter);

        sportFrecuencySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if(parentView.getItemAtPosition(position).toString().equals("Nunca")){
                    selectedItem = "NEVER";
                }
                else if(parentView.getItemAtPosition(position).toString().equals("Mensualmente")){
                    selectedItem = "MONTHLY";
                }
                else if(parentView.getItemAtPosition(position).toString().equals("Semanalmente")){
                    selectedItem = "WEEKLY";
                }
                else if(parentView.getItemAtPosition(position).toString().equals("Diariamente")){
                    selectedItem = "DAILY";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });


        vipFormButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String telf = etTelf.getText().toString();
                String birthday = etBirthday.getText().toString();
                String weight = etWeight.getText().toString();

                if (!telf.isEmpty() && !birthday.isEmpty() && !weight.isEmpty() && !selectedItem.isEmpty()) {
                    makeVip(telf, birthday, weight, selectedItem, savedEmail);
                } else {
                    Toast.makeText(Vip.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void makeVip(String telf, String birthday, String weight, String selectedItem, String email) {
        String URL1 = "http://192.168.56.1/android/vip.php";

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URL1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            String message = jsonResponse.getString("message");

                            if (success) {
                                Toast.makeText(Vip.this, "Correcto: " + message, Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Vip.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Vip.this, "Error en la respuesta JSON", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Vip.this, "Error de red", Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("telf", telf);
                params.put("birthday", birthday);
                params.put("weight", weight);
                params.put("sportFrecuency", selectedItem);
                params.put("email", email);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(Vip.this);
        requestQueue.add(stringRequest);
    }
}