package com.example.chatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {

    private Button nRegBtn;
    private Button nLogBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        nLogBtn = (Button) findViewById(R.id.reg_log_btn);
        nRegBtn = (Button) findViewById(R.id.start_reg_button);
        nRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent reg_intent = new Intent(StartActivity.this,RegisterActivity.class);
                startActivity(reg_intent);

            }
        });
        nLogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent log_intent = new Intent(StartActivity.this,LoginActivity.class);
                startActivity(log_intent);

            }
        });



    }
}
