package com.example.pedrocampons.pipordatawebserviceandroidpublico;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PorDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.por_data_layout);
    }


    public void button_porData_OnClick(View view){
        Intent intent = new Intent(PorDataActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
