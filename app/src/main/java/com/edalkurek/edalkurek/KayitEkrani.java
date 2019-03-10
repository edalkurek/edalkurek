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

public class KayitEkrani extends AppCompatActivity {

    private Toolbar actionBarKayit;
    private EditText tvUsername,tvEmail,tvPassword;
    private Button btnRegister;
    private FirebaseAuth auth;



    public void init() {

        actionBarKayit = (Toolbar) findViewById(R.id.actionBarKayit);
        setSupportActionBar(actionBarKayit);
        getSupportActionBar().setTitle("Kayıt Ol");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        auth=FirebaseAuth.getInstance();

        tvUsername=(EditText)findViewById(R.id.tv_username);
        tvEmail=(EditText)findViewById(R.id.tv_userEmail);
        tvPassword=(EditText)findViewById(R.id.tv_userpassword);
        btnRegister=(Button)findViewById(R.id.btnKayit);






    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit);
        init();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                createNewAccount();

            }
        });
    }

    private void createNewAccount() {
        String username =tvUsername.getText().toString();
        String email=tvEmail.getText().toString();
        String password= tvPassword.getText().toString();

        if(TextUtils.isEmpty(email)){

            Toast.makeText(this, "E-mail Alanı Boş Olamaz", Toast.LENGTH_SHORT).show();
        }

        else if(TextUtils.isEmpty(password))

            Toast.makeText(this, "Şifre Alanı Boş Olamaz", Toast.LENGTH_SHORT).show();

        else {

            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()){

                        Toast.makeText(KayitEkrani.this, "Kayıt Oluşturuldu !", Toast.LENGTH_SHORT).show();

                        Intent loginIntent= new Intent(KayitEkrani.this,GirisEkrani.class);
                        startActivity(loginIntent);
                        finish();
                    }
                    else{

                        Toast.makeText(KayitEkrani.this, "HATA!", Toast.LENGTH_SHORT).show();

                    }

                }
            });

        }

    }
}