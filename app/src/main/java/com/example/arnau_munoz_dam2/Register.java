package com.example.arnau_munoz_dam2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    EditText etName, etPassword, etPassword2, etEmail;
    RequestQueue requestQueue;

    private static final String URL1 = "http://192.168.1.139/android/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button registerButton = findViewById(R.id.btnRegister);
        TextView loginButton = findViewById(R.id.textView3);

        //Edit Texts user info
        etName = findViewById(R.id.etName);
        etPassword = findViewById(R.id.etPassword);
        etPassword2 = findViewById(R.id.etPassword2);
        etEmail = findViewById(R.id.etEmail);

        requestQueue = Volley.newRequestQueue(this);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString();
                String password = etPassword.getText().toString();
                String password2 = etPassword2.getText().toString();
                String email = etEmail.getText().toString();

                if (!name.isEmpty() && !password.isEmpty() && !password2.isEmpty() && !email.isEmpty()) {
                    if(password.equals(password2)){
                        createUser(name, password, email);
                    }
                    else{
                        Toast.makeText(Register.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Register.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                }



            }
            private void createUser(String name, String password, String email) {
                String URL1 = "http://192.168.1.139/android/register.php";

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
                                        Toast.makeText(Register.this, "Correcto: " + message, Toast.LENGTH_SHORT).show();
                                        // Realizar alguna acción adicional si es necesario
                                        showVerificationDialog(email);

                                    } else {
                                        Toast.makeText(Register.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(Register.this, "Error en la respuesta JSON", Toast.LENGTH_SHORT).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(Register.this, "Error de red", Toast.LENGTH_SHORT).show();
                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("name", name);
                        params.put("password", password);
                        params.put("email", email);
                        return params;
                    }
                };

                // Obtener el contexto correcto para RequestQueue
                RequestQueue requestQueue = Volley.newRequestQueue(Register.this);
                requestQueue.add(stringRequest);
            }
        });


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });
    }
    private void showVerificationDialog(final String email) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter Verification Code");

        // Configurar el layout del diálogo
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(input);

        // Configurar botones del diálogo
        builder.setPositiveButton("Verify", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String verificationCode = input.getText().toString();
                verifyCode(email, verificationCode);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    private void verifyCode(final String email, String verificationCode) {
        String URL = "http://192.168.1.139/android/verify.php";

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            String message = jsonResponse.getString("message");

                            Toast.makeText(Register.this, message, Toast.LENGTH_LONG).show();

                            if (success) {
                                // Redirigir al usuario a la pantalla de inicio de sesión o página principal
                                Intent intent = new Intent(Register.this, Login.class);
                                startActivity(intent);
                                finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Register.this, "Error en la respuesta JSON", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(Register.this, "Error de red: " + error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("verification_code", verificationCode);
                return params;
            }
        };

        // Obtener el contexto correcto para RequestQueue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}