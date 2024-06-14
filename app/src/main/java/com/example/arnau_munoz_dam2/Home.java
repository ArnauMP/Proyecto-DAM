package com.example.arnau_munoz_dam2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();
        String savedEmail = intent.getStringExtra("savedEmail");
        String savedUsername = intent.getStringExtra("savedUsername");

        Button vipButton = findViewById(R.id.vipButton);
        Button contactButton = findViewById(R.id.contactButton);
        TextView usernameText = findViewById(R.id.usernameText);
        usernameText.setText(savedUsername);

        ImageView userImg = findViewById(R.id.userImg);

        vipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Vip.class);
                intent.putExtra("savedEmail", savedEmail);
                intent.putExtra("savedUsername", savedUsername);
                startActivity(intent);
            }
        });

        contactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Contact.class);
                intent.putExtra("savedEmail", savedEmail);
                intent.putExtra("savedUsername", savedUsername);
                startActivity(intent);
            }
        });

        userImg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Home.this, User.class);
                intent.putExtra("savedEmail", savedEmail);
                intent.putExtra("savedUsername", savedUsername);
                startActivity(intent);
            }
        });
    }
}