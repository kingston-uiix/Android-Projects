package com.example.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    private TextInputLayout nDisplayName;
    private TextInputLayout nEmail;
    private TextInputLayout nPasssword;
    private Button nCreateBtn;
    private FirebaseAuth mAuth;

    private ProgressDialog mRegProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        mToolbar = (Toolbar) findViewById(R.id.register_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Create Account");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRegProgress = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();


        nDisplayName = (TextInputLayout) findViewById(R.id.reg_display_name);
        nEmail = (TextInputLayout) findViewById(R.id.reg_email);
        nPasssword = (TextInputLayout) findViewById(R.id.reg_password);
        nCreateBtn = (Button) findViewById(R.id.reg_create_btn);


        nCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String display_name = nDisplayName.getEditText().getText().toString();
                String email = nEmail.getEditText().getText().toString();
                String password = nPasssword.getEditText().getText().toString();

                if(!TextUtils.isEmpty(display_name)|| !TextUtils.isEmpty(email) || !TextUtils.isEmpty(password)){
                    mRegProgress.setTitle("Registering Process");
                    mRegProgress.setMessage("please wait while we create!...");
                    mRegProgress.setCanceledOnTouchOutside(false);
                    mRegProgress.show();
                    register_user(display_name,email,password);

                }


            }
        });
    }

    private void register_user(String display_name, String email, String password) {

      mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
          @Override
          public void onComplete(@NonNull Task<AuthResult> task) {
              if(task.isSuccessful()){
                    mRegProgress.dismiss();
                  Intent mainIntent = new Intent(RegisterActivity.this,MainActivity.class);

                  startActivity(mainIntent);
                  finish();

                  }else{
                    mRegProgress.hide();
                  Toast.makeText(RegisterActivity.this,"cant sign in please try again!..",Toast.LENGTH_LONG).show();

                  }

          }
      });
    }
}
