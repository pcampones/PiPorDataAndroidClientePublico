package com.example.pedrocampons.pipordatawebserviceandroidpublico;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pedrocampons.pipordatawebserviceandroidpublico.model.Funcionario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MetodosAnoActivity extends AppCompatActivity {


    private final static String URL_WEBSERVICE = "http://projetointerswebservice.apphb.com/Service1.svc";
    private static final int REQUEST_CODE = 1;
    private EditText dataInicio;
    private EditText dataFim;

    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metodos_ano);
        dataInicio = (EditText) findViewById(R.id.dataInicioEditText);
        dataFim = (EditText) findViewById(R.id.dataFimEditText);
        Intent intent = getIntent();
        token= intent.getExtras().getString("token");
         Toast.makeText(this, token, Toast.LENGTH_SHORT).show();




    }


    public void pesquisarBtn_onClick(View view) {
        Puta puta = new Puta();
        puta.execute(dataInicio.getText().toString(), dataFim.getText().toString());



    }

    private String readStream(InputStream is) {
        StringBuilder sb = new StringBuilder(512);
        try {
            Reader r = new InputStreamReader(is, "UTF-8");
            int c = 0;
            while ((c = r.read()) != -1) {
                sb.append((char) c);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }


    @NonNull
    private HttpURLConnection setupHttpURLConnection(String URL, String requestMethod) throws IOException {
        java.net.URL url = new URL(URL);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setReadTimeout(10000);
        httpURLConnection.setConnectTimeout(15000);
        httpURLConnection.setRequestMethod(requestMethod); //GET, PUT, POST ou DELETE
        httpURLConnection.setRequestProperty("Content-Type", "application/json");
        httpURLConnection.setRequestProperty("Accept", "application/json");

        return httpURLConnection;
    }


    private class Puta extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... params) {
            try {
               // SharedPreferences preferences = MetodosAnoActivity.this.getPreferences(Context.MODE_PRIVATE);
                //token = preferences.getString("token", null);

                String url = URL_WEBSERVICE
                        + "/Rest/funcionariosMedia?dataInicio=" + params[0] + "&dataFim=" + params[1] + "&token=" + token;

                HttpURLConnection httpURLConnection = setupHttpURLConnection(url, "GET");

                httpURLConnection.setDoOutput(false); // manda coisas para o webservice??

                httpURLConnection.setDoInput(true); // recebe coisas do webservice?? neste caso sim , recebe o token

                httpURLConnection.connect();
                int responseCode = httpURLConnection.getResponseCode();
                Log.i("responseCode", "" + responseCode);

                // Se tudo correr bem,


                    // ir buscar os livos devolvidos

                    if (responseCode == 200) { // Status OK se tudo correr bem, a response é sempre 200

                        InputStream inputStream = httpURLConnection.getInputStream();
                        String livrosEmJson = readStream(inputStream);




                        return livrosEmJson;



                    } else { // Have Problems
                        return "Erro: " + responseCode;
                    }

            } catch (IOException e) {
                e.printStackTrace();
                return  "Erro : " + e.getMessage();
            }
        }



        @Override
        protected void onPostExecute(String s) {

            try {
                super.onPostExecute(s);
            ArrayList<Funcionario> books = new ArrayList<>();


             JSONArray jsonBooks = new JSONArray(s);


            for (int i = 0; i < jsonBooks.length(); i++) {

                JSONObject jsonBook = jsonBooks.getJSONObject(i);
                int ano = jsonBook.getInt("Ano");
                int soma1 = jsonBook.getInt("Soma1");
                int soma2 = jsonBook.getInt("Soma2");
                int soma3 = jsonBook.getInt("Soma3");
                double soma = jsonBook.getDouble("Valor");
                Funcionario book = new Funcionario(ano,soma1,soma2,soma3,soma);

                books.add(book);


            }
            Toast.makeText(MetodosAnoActivity.this,"Cenas : " + s, Toast.LENGTH_SHORT).show();
            ListView listView = (ListView) findViewById(R.id.listView);
            ArrayAdapter<Funcionario> adapter =
                    new ArrayAdapter<Funcionario>(MetodosAnoActivity.this, android.R.layout.simple_list_item_1, books);

            listView.setAdapter(adapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }




    }

