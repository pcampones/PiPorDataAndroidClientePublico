package com.example.pedrocampons.pipordatawebserviceandroidpublico;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button botao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        botao = (Button) findViewById(R.id.button2);
    }


    public void button_media_funcionario_onClick() {

        Intent intent = new Intent(MainActivity.this, MetodosAnoActivity.class);
        startActivity(intent);

    }

}
