package com.example.pedrocampons.pipordatawebserviceandroidpublico;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
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


    private final static String URL_WEBSERVICE = "http://bookstorewebservice2120346.apphb.com/ServiceBookstore.svc";
    private static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metodos_ano);
    }


    TextView dataInicio = (TextView) findViewById(R.id.editText);
    TextView dataFim = (TextView) findViewById(R.id.editText2);



    public void button_media_funcionario_calculo_onClick(View view){



        MediaFuncionarioTask mediaFuncionarioTask = new MediaFuncionarioTask();
        mediaFuncionarioTask.execute(dataInicio.getText().toString(), dataFim.getText().toString());

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




    private class MediaFuncionarioTask extends AsyncTask<String, Void, JSONArray> {

        @Override
        protected JSONArray doInBackground(String... params) {

            try {
                SharedPreferences preferences = MetodosAnoActivity.this.getPreferences(Context.MODE_PRIVATE);
                String token = preferences.getString("token", null);

                String url = URL_WEBSERVICE + "Rest/funcionariosMedia?dataInicio=" + params[0] + "&dataFim=" + params[1] + "&token=" + token;

                HttpURLConnection httpURLConnection = setupHttpURLConnection(url, "GET");

                httpURLConnection.setDoInput(false); //Recebe coisas do webservice?


                httpURLConnection.connect();
                int responseCode = httpURLConnection.getResponseCode();
                Log.i("responseCode", "" + responseCode);

                if (responseCode == 200) {


                    InputStream inputStream = httpURLConnection.getInputStream();
                    String funcionariosEmJSON = readStream(inputStream);
                    try {
                        JSONArray valoresArray = new JSONArray(funcionariosEmJSON);
                        return valoresArray;
                    } catch (JSONException e) {
                        e.printStackTrace();
                        //Mudar
                        Log.e("Jason parser", "Error parsing data " + e.toString());
                    }
                }


            } catch (IOException e) {
                e.printStackTrace();

            }

            return null;
        }


        @Override
        protected void onPostExecute(JSONArray funcionariosArray) {
            try {
                super.onPostExecute(funcionariosArray);

                ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();

                if(funcionariosArray != null){

                    for(int i = 0; i < funcionariosArray.length(); i++){
                        JSONObject jsonFuncionario = funcionariosArray.getJSONObject(i);

                        int ano = jsonFuncionario.getInt("Ano");
                        double valor = jsonFuncionario.getDouble("Valor");


                        Funcionario funcionario = new Funcionario(ano, valor);

                        funcionarios.add(funcionario);
                    }
                }




                ListView listView = (ListView) findViewById(R.id.listView);

                ArrayAdapter<Funcionario> adapter = new ArrayAdapter<Funcionario>(MetodosAnoActivity.this, android.R.layout.simple_list_item_1, funcionarios);

                listView.setAdapter(adapter);
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(MetodosAnoActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }
    }
}
