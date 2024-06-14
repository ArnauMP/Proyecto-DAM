package com.example.arnau_munoz_dam2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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

public class User extends AppCompatActivity {

    TextView nameTv, telfTv, emailTv, roleTv;
    Button trainerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Intent intent = getIntent();
        String savedEmail = intent.getStringExtra("savedEmail");

        nameTv = findViewById(R.id.nameTv);
        telfTv = findViewById(R.id.telfTv);
        emailTv = findViewById(R.id.emailTv);
        roleTv = findViewById(R.id.roleTv);
        trainerBtn = findViewById(R.id.trainerBtn);

        getUserData(savedEmail);

        if(trainerBtn.getVisibility() == View.VISIBLE){
            trainerBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(User.this, Trainer.class);
                    intent.putExtra("savedEmail", savedEmail);
                    startActivity(intent);
                }
            });
        }


    }

    private void getUserData(String email) {
        String URL1 = "http://192.168.56.1/android/getUserByEmail.php?email=" + email;
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                URL1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                JSONObject userData = jsonResponse.getJSONArray("data").getJSONObject(0);
                                nameTv.setText(userData.getString("name"));
                                telfTv.setText(userData.getString("telf"));
                                emailTv.setText(userData.getString("email"));
                                roleTv.setText(userData.getString("role"));
                                if(roleTv.getText().equals("TRAINER")){
                                    trainerBtn.setVisibility(View.VISIBLE);
                                }
                                else{
                                    trainerBtn.setVisibility(View.INVISIBLE);
                                }
                            } else {
                                Toast.makeText(User.this, "Error: ", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(User.this, "Error en la respuesta JSON", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(User.this, "Error de red", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(User.this);
        requestQueue.add(stringRequest);
    }
}