package com.example.arnau_munoz_dam2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Trainer extends AppCompatActivity {
    Button dietsBtn, trainingsBtn, filterOneBtn, filterTwoBtn;
    TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer);

        tableLayout = findViewById(R.id.tableLayout);
        dietsBtn = findViewById(R.id.getDietsBtn);
        trainingsBtn = findViewById(R.id.getTrainingsBtn);
        filterOneBtn = findViewById(R.id.filterOneBtn);
        filterTwoBtn = findViewById(R.id.filterTwoBtn);

        filterOneBtn.setVisibility(View.INVISIBLE);
        filterTwoBtn.setVisibility(View.INVISIBLE);

        dietsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAllDiets();
                filterOneBtn.setText("DEFINICIÓN");
                filterTwoBtn.setText("VOLUMEN");
                filterOneBtn.setVisibility(View.VISIBLE);
                filterTwoBtn.setVisibility(View.VISIBLE);
            }
        });

        trainingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAllTrainings();
                filterOneBtn.setText("HIPERTROFIA");
                filterTwoBtn.setText("FUERZA");
                filterOneBtn.setVisibility(View.VISIBLE);
                filterTwoBtn.setVisibility(View.VISIBLE);
            }
        });

        filterOneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(filterOneBtn.getText().equals("DEFINICIÓN")){
                    getDietsByType("DEFINITION");
                }
                else if(filterOneBtn.getText().equals("HIPERTROFIA")){
                    getTrainingsByType("HYPERTROPHY");
                }
            }
        });

        filterTwoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(filterTwoBtn.getText().equals("VOLUMEN")){
                    getDietsByType("VOLUME");
                }
                else if(filterTwoBtn.getText().equals("FUERZA")){
                    getTrainingsByType("STRENGTH");
                }
            }
        });

    }

    private void getAllDiets() {
        String URL1 = "http://192.168.1.139/android/getAllDiets.php";
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                URL1,
                new Response.Listener<String>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            tableLayout.removeAllViews();

                            if (success) {
                                JSONArray dietsArray = jsonResponse.getJSONArray("data");

                                for (int i = 0; i < dietsArray.length(); i++) {
                                    JSONObject diet = dietsArray.getJSONObject(i);

                                    // Crear una nueva fila TableRow
                                    TableRow row = new TableRow(Trainer.this);
                                    TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT);
                                    row.setLayoutParams(lp);

                                    // Crear y añadir TextViews a la fila TableRow
                                    addTextViewToRow(row, "Comida 1:", diet.getString("meal1"));
                                    addTextViewToRow(row, "Comida 2:", diet.getString("meal2"));
                                    addTextViewToRow(row, "Comida 3:", diet.getString("meal3"));
                                    addTextViewToRow(row, "Comida 4:", diet.getString("meal4"));
                                    addTextViewToRow(row, "Comida 5:", diet.getString("meal5"));
                                    addTextViewToRow(row, "Pre Entreno:", diet.getString("pre_workout"));
                                    addTextViewToRow(row, "Post Entreno:", diet.getString("post_workout"));
                                    addTextViewToRow(row, "Tipo:", diet.getString("type"));
                                    addTextViewToRow(row, "ID:", diet.getString("diet_id"));

                                    // Añadir la fila TableRow al TableLayout
                                    tableLayout.addView(row);
                                }
                            } else {
                                Toast.makeText(Trainer.this, "Error: No se encontraron dietas", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Trainer.this, "Error en la respuesta JSON", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Trainer.this, "Error de red", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(Trainer.this);
        requestQueue.add(stringRequest);
    }

    private void getAllTrainings() {
        String URL1 = "http://192.168.1.139/android/getAllTrainings.php";
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                URL1,
                new Response.Listener<String>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            tableLayout.removeAllViews();

                            if (success) {
                                JSONArray trainingsArray = jsonResponse.getJSONArray("data");

                                for (int i = 0; i < trainingsArray.length(); i++) {
                                    JSONObject diet = trainingsArray.getJSONObject(i);

                                    // Crear una nueva fila TableRow
                                    TableRow row = new TableRow(Trainer.this);
                                    TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT);
                                    row.setLayoutParams(lp);

                                    // Crear y añadir TextViews a la fila TableRow
                                    addTextViewToRow(row, "Ejercicio 1:", diet.getString("exercise1"));
                                    addTextViewToRow(row, "Ejercicio 2:", diet.getString("exercise2"));
                                    addTextViewToRow(row, "Ejercicio 3:", diet.getString("exercise3"));
                                    addTextViewToRow(row, "Ejercicio 4:", diet.getString("exercise4"));
                                    addTextViewToRow(row, "Ejercicio 5:", diet.getString("exercise5"));
                                    addTextViewToRow(row, "Ejercicio 6:", diet.getString("exercise6"));
                                    addTextViewToRow(row, "Tipo:", diet.getString("type"));
                                    addTextViewToRow(row, "ID:", diet.getString("training_id"));

                                    // Añadir la fila TableRow al TableLayout
                                    tableLayout.addView(row);
                                }
                            } else {
                                Toast.makeText(Trainer.this, "Error: No se encontraron entrenamientos", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Trainer.this, "Error en la respuesta JSON", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Trainer.this, "Error de red", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(Trainer.this);
        requestQueue.add(stringRequest);
    }

    private void getDietsByType(String type) {
        String URL1 = "http://192.168.1.139/android/getDietsByType.php?type="+ type;
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                URL1,
                new Response.Listener<String>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            tableLayout.removeAllViews();

                            if (success) {
                                JSONArray dietsArray = jsonResponse.getJSONArray("data");

                                for (int i = 0; i < dietsArray.length(); i++) {
                                    JSONObject diet = dietsArray.getJSONObject(i);

                                    // Crear una nueva fila TableRow
                                    TableRow row = new TableRow(Trainer.this);
                                    TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT);
                                    row.setLayoutParams(lp);

                                    // Crear y añadir TextViews a la fila TableRow
                                    addTextViewToRow(row, "Comida 1:", diet.getString("meal1"));
                                    addTextViewToRow(row, "Comida 2:", diet.getString("meal2"));
                                    addTextViewToRow(row, "Comida 3:", diet.getString("meal3"));
                                    addTextViewToRow(row, "Comida 4:", diet.getString("meal4"));
                                    addTextViewToRow(row, "Comida 5:", diet.getString("meal5"));
                                    addTextViewToRow(row, "Pre Entreno:", diet.getString("pre_workout"));
                                    addTextViewToRow(row, "Post Entreno:", diet.getString("post_workout"));
                                    addTextViewToRow(row, "Tipo:", diet.getString("type"));
                                    addTextViewToRow(row, "ID:", diet.getString("diet_id"));

                                    // Añadir la fila TableRow al TableLayout
                                    tableLayout.addView(row);
                                }
                            } else {
                                Toast.makeText(Trainer.this, "Error: No se encontraron dietas", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Trainer.this, "Error en la respuesta JSON", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Trainer.this, "Error de red", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(Trainer.this);
        requestQueue.add(stringRequest);
    }

    private void getTrainingsByType(String type) {
        String URL1 = "http://192.168.1.139/android/getTrainingsByType.php?type=" + type;
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                URL1,
                new Response.Listener<String>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            tableLayout.removeAllViews();

                            if (success) {
                                JSONArray trainingsArray = jsonResponse.getJSONArray("data");

                                for (int i = 0; i < trainingsArray.length(); i++) {
                                    JSONObject diet = trainingsArray.getJSONObject(i);

                                    // Crear una nueva fila TableRow
                                    TableRow row = new TableRow(Trainer.this);
                                    TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT);
                                    row.setLayoutParams(lp);

                                    // Crear y añadir TextViews a la fila TableRow
                                    addTextViewToRow(row, "Ejercicio 1:", diet.getString("exercise1"));
                                    addTextViewToRow(row, "Ejercicio 2:", diet.getString("exercise2"));
                                    addTextViewToRow(row, "Ejercicio 3:", diet.getString("exercise3"));
                                    addTextViewToRow(row, "Ejercicio 4:", diet.getString("exercise4"));
                                    addTextViewToRow(row, "Ejercicio 5:", diet.getString("exercise5"));
                                    addTextViewToRow(row, "Ejercicio 6:", diet.getString("exercise6"));
                                    addTextViewToRow(row, "Tipo:", diet.getString("type"));
                                    addTextViewToRow(row, "ID:", diet.getString("training_id"));

                                    // Añadir la fila TableRow al TableLayout
                                    tableLayout.addView(row);
                                }
                            } else {
                                Toast.makeText(Trainer.this, "Error: No se encontraron entrenamientos", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Trainer.this, "Error en la respuesta JSON", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Trainer.this, "Error de red", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(Trainer.this);
        requestQueue.add(stringRequest);
    }

    private void addTextViewToRow(TableRow row, String label, String value) {
        TextView labelView = new TextView(Trainer.this);
        labelView.setText(label);
        labelView.setPadding(5, 5, 10, 0);

        TextView valueView = new TextView(Trainer.this);
        valueView.setText(value);
        valueView.setPadding(5, 5, 10, 0);

        row.addView(labelView);
        row.addView(valueView);
    }
}