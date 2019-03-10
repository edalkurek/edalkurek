package com.edalkurek.edalkurek;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class GirisEkrani extends AppCompatActivity {

    private Toolbar actionBarGiris;
    private EditText tvEmail, tvPassword;
    private Button btnLogin;
    private FirebaseAuth auth;
    private FirebaseUser currentUser;

    public void init(){

        actionBarGiris=(Toolbar)findViewById(R.id.actionBarGiris);
        setSupportActionBar(actionBarGiris);
        getSupportActionBar().setTitle("Giriş Yap");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        auth=FirebaseAuth.getInstance();
        currentUser=auth.getCurrentUser();

        tvEmail=(EditText)findViewById(R.id.Ltv_username);
        tvPassword=(EditText)findViewById(R.id.tv_LuserSifre);
        btnLogin=(Button) findViewById(R.id.btnGiris);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);
        init();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loginUser();

            }
        });


    }

    private void loginUser() {

        String email = tvEmail.getText().toString();
        String password= tvPassword.getText().toString();

        if(TextUtils.isEmpty(email)){

            Toast.makeText(this, "E-mail Alanı Boş!", Toast.LENGTH_SHORT).show();
        }

        else if (TextUtils.isEmpty(password)){

            Toast.makeText(this, "Şifre Alanı Boş!", Toast.LENGTH_SHORT).show();
        }

        else {

            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful()){

                        Toast.makeText(GirisEkrani.this, "Giriş Yapıldı !", Toast.LENGTH_SHORT).show();
                        Intent mainIntent = new Intent(GirisEkrani.this,MainActivity.class);
                        startActivity(mainIntent);
                        finish();

                    }

                    else {
                        Toast.makeText(GirisEkrani.this, "Giriş Yapılamadı!", Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }

    }
}
