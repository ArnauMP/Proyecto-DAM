package com.example.arnau_munoz_dam2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class Contact extends AppCompatActivity {

    EditText etTelf, etSubject, etMessage;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        Intent intent = getIntent();
        String savedEmail = intent.getStringExtra("savedEmail");
        String savedUsername = intent.getStringExtra("savedUsername");

        Button sendContactBtn = findViewById(R.id.sencContactBtn);

        etTelf = findViewById(R.id.etTelf);
        etSubject = findViewById(R.id.etSubject);
        etMessage = findViewById(R.id.etMessage);
        requestQueue = Volley.newRequestQueue(this);

        sendContactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String telf = etTelf.getText().toString();
                String subject = etSubject.getText().toString();
                String message = etMessage.getText().toString();

                if (!savedUsername.isEmpty() && !savedEmail.isEmpty() && !telf.isEmpty() && !subject.isEmpty()) {
                    sendContact(savedUsername, savedEmail, telf, subject, message);
                } else {
                    Toast.makeText(Contact.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

        private void sendContact(String username, String email, String telf, String subject, String message) {
            String URL1 = "http://192.168.56.1/android/contactForm.php";

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
                                    Toast.makeText(Contact.this, "Correcto: " + message, Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(Contact.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(Contact.this, "Error en la respuesta JSON", Toast.LENGTH_SHORT).show();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Contact.this, "Error de red", Toast.LENGTH_SHORT).show();
                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("name", username);
                    params.put("email", email);
                    params.put("telf", telf);
                    params.put("subject", subject);
                    params.put("message", message);
                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(Contact.this);
            requestQueue.add(stringRequest);
        }

}