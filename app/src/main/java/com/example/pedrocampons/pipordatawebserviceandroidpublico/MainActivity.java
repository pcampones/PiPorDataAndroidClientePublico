package com.example.pedrocampons.pipordatawebserviceandroidpublico;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
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

public class MainActivity extends AppCompatActivity {

    private Button botao;
    private String token;
    private String tokenS;
    private Spinner metodos;
    private EditText dataInicio;
    private EditText dataFim;
    private ArrayAdapter<CharSequence> string;

    private final static String URL_WEBSERVICE = "http://projetointerswebservice.apphb.com/Service1.svc";
    private static final int REQUEST_CODE = 1;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataInicio = (EditText) findViewById(R.id.editText);
        dataFim = (EditText) findViewById(R.id.editText2);
        dataInicio.setEnabled(false);
        dataFim.setEnabled(false);
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

                        dataInicio.setEnabled(false);
                        dataFim.setEnabled(false);

                        break;

                    case "GetNumAcoesPorData":

                        dataInicio.setVisibility(View.VISIBLE);
                        dataFim.setVisibility(View.VISIBLE);


                        break;
                    case "GetMediaFuncionarioPorData":

                        dataInicio.setEnabled(true);
                        dataFim.setEnabled(true);
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



    public void button_MetodosonClick(View view) {

        String text = metodos.getSelectedItem().toString();


        if (text.equals("Selecione uma opção...")){
            Toast.makeText(this,"Selecione uma opção",Toast.LENGTH_LONG).show();

        } else if (text.equals("GetMediaFuncionarioPorData")){

                Puta puta = new Puta();
                puta.execute(dataInicio.getText().toString(), dataFim.getText().toString());



        }else {
            Toast.makeText(this,"Selecione uma opção",Toast.LENGTH_LONG).show();

        }

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


    private class Puta extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                // SharedPreferences preferences = MetodosAnoActivity.this.getPreferences(Context.MODE_PRIVATE);
                //token = preferences.getString("token", null);

                String url = URL_WEBSERVICE
                        + "/Rest/funcionariosMedia?dataInicio=" + params[0] + "&dataFim=" + params[1] + "&token=" + tokenS;

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
                    String funcionariosEmJson = readStream(inputStream);




                    return funcionariosEmJson;



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
                ArrayList<Funcionario> funcionarioArrayList = new ArrayList<>();


                JSONArray jsonFuncionariosArray = new JSONArray(s);


                for (int i = 0; i < jsonFuncionariosArray.length(); i++) {

                    JSONObject jsonFuncionario = jsonFuncionariosArray.getJSONObject(i);
                    int ano = jsonFuncionario.getInt("Ano");
                    int soma1 = jsonFuncionario.getInt("Soma1");
                    int soma2 = jsonFuncionario.getInt("Soma2");
                    int soma3 = jsonFuncionario.getInt("Soma3");
                    double soma = jsonFuncionario.getDouble("Valor");
                    Funcionario funcionario = new Funcionario(ano,soma1,soma2,soma3,soma);

                    funcionarioArrayList.add(funcionario);


                }
                Toast.makeText(MainActivity.this,"Cenas : " + s, Toast.LENGTH_SHORT).show();
                ListView listView = (ListView) findViewById(R.id.listView2);
                ArrayAdapter<Funcionario> adapter =
                        new ArrayAdapter<Funcionario>(MainActivity.this, android.R.layout.simple_list_item_1, funcionarioArrayList);

                listView.setAdapter(adapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
