package com.edalkurek.edalkurek;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AnaEkran extends AppCompatActivity {

    private Button btn_kayit,btn_giris;

    public void init(){

        btn_giris=(Button)findViewById(R.id.btn_giris);
        btn_kayit=(Button)findViewById(R.id.btn_kayit);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ana);
        init();

        btn_giris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent_giris = new Intent(AnaEkran.this,GirisEkrani.class);
                startActivity(intent_giris);
                finish();
            }
        });

        btn_kayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_kayit = new Intent(AnaEkran.this,KayitEkrani.class);
                startActivity(intent_kayit);
                finish();
            }
        });

    }
}
