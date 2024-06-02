package com.example.arnau_munoz_dam2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;

public class Vip extends AppCompatActivity {

    EditText etTelf, etBirthday, etWeight;
    RequestQueue requestQueue;

    private Spinner sportFrecuencySpinner;
    private static final String URL1 = "http://192.168.1.139/android/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vip);
        Button vipFormButton = findViewById(R.id.vipFormButton);

        // SPINNER:
        sportFrecuencySpinner = findViewById(R.id.sportFrecuencySpinner);

        String[] opciones = {"Nunca", "Mensualmente", "Semanalmente", "Diariamente"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opciones);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sportFrecuencySpinner.setAdapter(adapter);

        sportFrecuencySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedItem = parentView.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }



}