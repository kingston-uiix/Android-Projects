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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {


    private Toolbar mToolbar;

    private TextInputLayout mloginMail;
    private TextInputLayout mloginPass;
    private Button mLoginBtn;
    private FirebaseAuth mAuth;
    private ProgressDialog mloginProgress;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mToolbar = (Toolbar) findViewById(R.id.login_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Login");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAuth = FirebaseAuth.getInstance();
        mloginProgress = new ProgressDialog(this);

        mloginMail = (TextInputLayout) findViewById(R.id.log_email);
        mloginPass = (TextInputLayout) findViewById(R.id.log_password);

        mLoginBtn = (Button) findViewById(R.id.log_btn);

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = mloginMail.getEditText().getText().toString();
                String password = mloginPass.getEditText().getText().toString();

                if(!TextUtils.isEmpty(email) || !TextUtils.isEmpty(password)) {
                    mloginProgress.setTitle("Logging in");
                    mloginProgress.setMessage("Please wait while wee log in!..");
                    mloginProgress.setCanceledOnTouchOutside(false);
                    mloginProgress.show();

                    loginUser(email,password);
                }

            }
        });

    }

    private void loginUser(String email, String password) {

        mAuth.signInWithEmailAndPassword(email, password)
.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {

        if(task.isSuccessful()){

            mloginProgress.dismiss();
            Intent mainIntent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(mainIntent);
            finish();

        } else {

            mloginProgress.hide();
            Toast.makeText(LoginActivity.this,"cant Login in please try again!..",Toast.LENGTH_LONG).show();
        }

    }


}) ;

    }
}
