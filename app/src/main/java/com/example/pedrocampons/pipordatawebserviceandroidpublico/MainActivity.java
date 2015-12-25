package com.example.pedrocampons.pipordatawebserviceandroidpublico;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button botao;
    private String token;
    private String tokenS;
    private Spinner metodos;
    private EditText dataInicio;
    private EditText         dataFim;
    private ArrayAdapter<CharSequence> string;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataInicio = (EditText) findViewById(R.id.editText);
        dataFim = (EditText) findViewById(R.id.editText2);
        dataInicio.setVisibility(View.INVISIBLE);
        dataFim.setVisibility(View.INVISIBLE);
        botao = (Button) findViewById(R.id.button2);
        metodos = (Spinner) findViewById(R.id.spinner);
        Intent intent = getIntent();
        token = intent.getExtras().getString("token");
        tokenS = token.replace("\"", "");
        string =  ArrayAdapter.createFromResource(this,
                R.array.SpinnerList,
                android.R.layout.simple_spinner_dropdown_item);
        string.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        metodos.setAdapter(string);
        metodos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                switch (item){

                    case "Selecione uma opção...":
                        dataInicio.setVisibility(View.INVISIBLE);
                        dataFim.setVisibility(View.INVISIBLE);
                        break;

                    case "GetNumAcoesPorData":

                        dataInicio.setVisibility(View.VISIBLE);
                        dataFim.setVisibility(View.VISIBLE);


                        break;
                    case "GetMediaFuncionarioPorData":
                        Intent intent1 = new Intent(MainActivity.this, MetodosAnoActivity.class);
                      //  Intent intent = new Intent(this, MetodosAnoActivity.class);
                        intent1.putExtra("token", tokenS);
                        startActivity(intent1);
                       // dataInicio.setVisibility(View.INVISIBLE);
                       // dataFim.setVisibility(View.INVISIBLE);
                        break;


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;

            }
        });
        Toast.makeText(this,  tokenS, Toast.LENGTH_LONG).show();


    }
//depois apaga este metodo
    public void button_MetodosonClick() {

        Intent intent = new Intent(this, MetodosAnoActivity.class);
        intent.putExtra("token",tokenS);
        startActivity(intent);

    }


    public void button_MetodosonClick(View view) {

        Intent intent = new Intent(this, MetodosAnoActivity.class);
        intent.putExtra("token",tokenS);
        startActivity(intent);

    }

}
