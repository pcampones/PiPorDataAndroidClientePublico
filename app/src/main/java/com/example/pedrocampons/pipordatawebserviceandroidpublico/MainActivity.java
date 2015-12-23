package com.example.pedrocampons.pipordatawebserviceandroidpublico;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button botao;
    private String token;
    private String tokenS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        botao = (Button) findViewById(R.id.button2);
        Intent intent = getIntent();
        token = intent.getExtras().getString("token");
        tokenS = token.replace("\"","");
        Toast.makeText(this,  tokenS, Toast.LENGTH_LONG).show();


    }


    public void button_MetodosonClick(View view) {

        Intent intent = new Intent(this, MetodosAnoActivity.class);
        intent.putExtra("token",tokenS);
        startActivity(intent);

    }

}
