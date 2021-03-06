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

import com.example.pedrocampons.pipordatawebserviceandroidpublico.model.Acao;
import com.example.pedrocampons.pipordatawebserviceandroidpublico.model.Cama;
import com.example.pedrocampons.pipordatawebserviceandroidpublico.model.Funcionario;
import com.example.pedrocampons.pipordatawebserviceandroidpublico.model.Medicamento;

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
    private Spinner cat;
    private EditText dataInicio;
    private EditText dataFim;
    private ArrayAdapter<CharSequence> string;
    private ArrayAdapter<CharSequence> categorias;

    private Spinner acao ;
    private ArrayAdapter<CharSequence> acoes;
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

        acao = (Spinner) findViewById(R.id.spinner3);

        cat = (Spinner) findViewById(R.id.spinner2);
        string =  ArrayAdapter.createFromResource(this,
                R.array.SpinnerList,
                android.R.layout.simple_spinner_dropdown_item);
        categorias =  ArrayAdapter.createFromResource(this,
                R.array.SpinnerCategorias,
                android.R.layout.simple_spinner_dropdown_item);
        acoes = ArrayAdapter.createFromResource(this,
                R.array.SpinnerAcoes,
                android.R.layout.simple_spinner_dropdown_item);
        string.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorias.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        acoes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        metodos.setAdapter(string);
        cat.setAdapter(categorias);
        acao.setAdapter(acoes);
        metodos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override

        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String item = parent.getItemAtPosition(position).toString();

                switch (item){

                    case "Selecione uma opção...":
                        cat.setVisibility(View.GONE);
                        dataInicio.setVisibility(View.GONE);
                        dataFim.setVisibility(View.GONE);
                        acao.setVisibility(View.GONE);
                        break;
                    case "Custo Médio de um Funcionário":
                        dataInicio.setVisibility(View.VISIBLE);
                        dataFim.setVisibility(View.VISIBLE);
                        cat.setVisibility(View.GONE);
                        acao.setVisibility(View.GONE);
                        dataInicio.setEnabled(true);
                        dataFim.setEnabled(true);
                        break;
                    case "Número de Funcionários":
                         dataInicio.setVisibility(View.VISIBLE);
                        dataFim.setVisibility(View.VISIBLE);
                        dataInicio.setEnabled(true);
                        dataFim.setEnabled(true);
                        cat.setVisibility(View.GONE);
                        acao.setVisibility(View.GONE);
                        break;
                    case "Número de Funcionários Por Categoria":
                        dataInicio.setVisibility(View.VISIBLE);
                        dataFim.setVisibility(View.VISIBLE);
                        dataInicio.setEnabled(true);
                        dataFim.setEnabled(true);
                        acao.setVisibility(View.GONE);
                        cat.setVisibility(View.VISIBLE);
                        break;
                    case "Percentagem dos Custos com Medicamentos":
                        dataInicio.setVisibility(View.VISIBLE);
                        dataFim.setVisibility(View.VISIBLE);
                        dataInicio.setEnabled(true);
                        dataFim.setEnabled(true);
                        cat.setVisibility(View.GONE);
                        acao.setVisibility(View.GONE);
                        break;
                    case "Percentagem dos Custos com Pessoal":
                        dataInicio.setVisibility(View.VISIBLE);
                        dataFim.setVisibility(View.VISIBLE);
                        dataInicio.setEnabled(true);
                        dataFim.setEnabled(true);
                        cat.setVisibility(View.GONE);
                        acao.setVisibility(View.GONE);
                        break;
                    case "Número de Consultas, Internamentos e Urgências em Hospitais":
                        dataInicio.setVisibility(View.VISIBLE);
                        dataFim.setVisibility(View.VISIBLE);
                        dataInicio.setEnabled(true);
                        dataFim.setEnabled(true);
                        cat.setVisibility(View.GONE);
                        acao.setVisibility(View.VISIBLE);
                        break;
                    case "Percentagem de Consultas, Internamentos e Urgências em Centros de Saúde":
                        dataInicio.setVisibility(View.VISIBLE);
                        dataFim.setVisibility(View.VISIBLE);
                        dataInicio.setEnabled(true);
                        dataFim.setEnabled(true);
                        cat.setVisibility(View.GONE);
                        acao.setVisibility(View.VISIBLE);
                        break;
                    case "Média do Número de Camas Disponíveis nos Hospitais":
                        dataInicio.setVisibility(View.VISIBLE);
                        dataFim.setVisibility(View.VISIBLE);
                        dataInicio.setEnabled(true);
                        dataFim.setEnabled(true);
                        cat.setVisibility(View.GONE);
                        acao.setVisibility(View.GONE);
                        break;
                    case "Rácio entre o número de Funcionários e número de Estabelecimentos":

                        dataInicio.setVisibility(View.VISIBLE);
                        dataFim.setVisibility(View.VISIBLE);
                        dataInicio.setEnabled(true);
                        dataFim.setEnabled(true);
                        cat.setVisibility(View.GONE);
                        acao.setVisibility(View.GONE);
                      break;



                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;

            }
        });
      //  Toast.makeText(this,  tokenS, Toast.LENGTH_LONG).show();


    }

  public void button_MetodosonClick(View view) {

      int a = 0;

      String text = metodos.getSelectedItem().toString();

      if (dataInicio.getText().toString().trim().equals("") && dataFim.getText().toString().trim().equals("")) {

          Toast.makeText(this, "Insira uma data em cada espaço!", Toast.LENGTH_LONG).show();

      } else {
          a = verificaDatas(dataInicio.getText().toString(), dataFim.getText().toString());

          if (a == 1) {

              Toast.makeText(this, "A executar...", Toast.LENGTH_SHORT).show();
              switch (text) {

                  case "Selecione uma opção...":
                      Toast.makeText(this, "Selecione uma opção!", Toast.LENGTH_LONG).show();
                      break;

                  case "Custo Médio de um Funcionário":
                      GetMediaFunc getMediaFunc = new GetMediaFunc();
                      getMediaFunc.execute(
                              dataInicio.getText().toString(),
                              dataFim.getText().toString());
                      break;

                  case "Número de Funcionários":
                      GetNumFunc getNumFunc = new GetNumFunc();
                      getNumFunc.execute(
                              dataInicio.getText().toString(),
                              dataFim.getText().toString());
                      break;

                  case "Número de Funcionários Por Categoria":
                      GetNumFuncCategoria getNumFuncCat = new GetNumFuncCategoria();
                      getNumFuncCat.execute(
                              dataInicio.getText().toString(),
                              dataFim.getText().toString(),
                              cat.getSelectedItem().toString());
                      break;


                  case "Percentagem dos Custos com Medicamentos":
                      GetPercentagemMedicamentos getPercentagemMed = new GetPercentagemMedicamentos();
                      getPercentagemMed.execute(
                              dataInicio.getText().toString(),
                              dataFim.getText().toString());
                      break;

                  case "Percentagem dos Custos com Pessoal":
                      GetPercentagemPessoal getPercentagemPessoal = new GetPercentagemPessoal();
                      getPercentagemPessoal.execute(
                              dataInicio.getText().toString(),
                              dataFim.getText().toString());
                      break;


                  case "Número de Consultas, Internamentos e Urgências em Hospitais":
                      GetAcoesCategoria getAcoesCategoria = new GetAcoesCategoria();
                      getAcoesCategoria.execute(
                              dataInicio.getText().toString(),
                              dataFim.getText().toString(),
                              acao.getSelectedItem().toString());
                      break;

                  case "Percentagem de Consultas, Internamentos e Urgências em Centros de Saúde":
                      GetPerAcoesCat getPerAcoesCat = new GetPerAcoesCat();
                      getPerAcoesCat.execute(
                              dataInicio.getText().toString(),
                              dataFim.getText().toString(),
                              acao.getSelectedItem().toString());
                      break;

                  case "Média do Número de Camas Disponíveis nos Hospitais":
                      GetMediaCamas getMediaCamas = new GetMediaCamas();
                      getMediaCamas.execute(
                              dataInicio.getText().toString(),
                              dataFim.getText().toString());
                      break;

                  case "Rácio entre o número de Funcionários e número de Estabelecimentos":
                      GetRacioFuncionarios getRacioFunc = new GetRacioFuncionarios();
                      getRacioFunc.execute(
                              dataInicio.getText().toString(),
                              dataFim.getText().toString());
                      break;
              }
          } else {
              Toast.makeText(this, "Data de Inicio tem que ser menor que a Data de Fim!", Toast.LENGTH_LONG).show();
          }

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

    private class GetMediaFunc extends AsyncTask<String, Void, String> {

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
                    double media = jsonFuncionario.getDouble("Media");
                    double numFun = jsonFuncionario.getDouble("NumFun");
                    double percenPessoal = jsonFuncionario.getDouble("PercenPessoal");
                    double percenFun = jsonFuncionario.getDouble("PercenFun");


                    Funcionario funcionario = new Funcionario(ano,media,numFun,percenPessoal,percenFun);
                    funcionarioArrayList.add(funcionario);
                }

                ListView listView = (ListView) findViewById(R.id.listView2);
                ArrayAdapter<Funcionario> adapter =
                        new ArrayAdapter<Funcionario>(MainActivity.this, android.R.layout.simple_list_item_1, funcionarioArrayList);

                listView.setAdapter(adapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private class GetNumFunc extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... params) {
            try{

                String url = URL_WEBSERVICE
                        + "/Rest/funcionarios?dataInicio=" + params[0] + "&dataFim=" + params[1] + "&token=" + tokenS;

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
            }
            catch (IOException e){
                e.printStackTrace();
                return  "Erro : " + e.getMessage();
            }

        }

        @Override
        protected void onPostExecute(String s) {
            try{
                super.onPostExecute(s);
                ArrayList<Funcionario> funcionarioArrayList = new ArrayList<>();



                JSONArray jsonFuncionariosArray = new JSONArray(s);


                for (int i = 0; i < jsonFuncionariosArray.length(); i++) {
                    JSONObject jsonFuncionario = jsonFuncionariosArray.getJSONObject(i);
                    int ano = jsonFuncionario.getInt("Ano");
                    double media = jsonFuncionario.getDouble("Media");
                    double numFun = jsonFuncionario.getDouble("NumFun");
                    double percenPessoal = jsonFuncionario.getDouble("PercenPessoal");
                    double percenFun = jsonFuncionario.getDouble("PercenFun");


                    Funcionario funcionario = new Funcionario(ano,media,numFun,percenPessoal,percenFun);

                    funcionarioArrayList.add(funcionario);



                }
                ListView listView = (ListView) findViewById(R.id.listView2);
                ArrayAdapter<Funcionario> adapter =
                        new ArrayAdapter<Funcionario>(MainActivity.this, android.R.layout.simple_list_item_1, funcionarioArrayList);

                listView.setAdapter(adapter);
            }catch (JSONException e){
                e.printStackTrace();
            }

        }
    }

    private class GetNumFuncCategoria extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                // SharedPreferences preferences = MetodosAnoActivity.this.getPreferences(Context.MODE_PRIVATE);
                //token = preferences.getString("token", null);

                String url = URL_WEBSERVICE
                        + "/Rest/funcionariosCategoriaS?dataInicio=" + params[0] + "&dataFim=" + params[1]
                        + "&categoria=" +  params[2].replace(" ", "")  + "&token=" + tokenS;

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
                    String funcionariosCatEmJson = readStream(inputStream);




                    return funcionariosCatEmJson;



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
                ArrayList<Funcionario> funcionarioCatArrayList = new ArrayList<>();


                JSONArray jsonFuncionariosCatArray = new JSONArray(s);


                for (int i = 0; i < jsonFuncionariosCatArray.length(); i++) {

                    JSONObject jsonFuncionario = jsonFuncionariosCatArray.getJSONObject(i);
                    int ano = jsonFuncionario.getInt("Ano");
                    double media = jsonFuncionario.getDouble("Media");
                    double numFun = jsonFuncionario.getDouble("NumFun");
                    double percenPessoal = jsonFuncionario.getDouble("PercenPessoal");
                    double percenFun = jsonFuncionario.getDouble("PercenFun");


                    Funcionario funcionario = new Funcionario(ano,media,numFun,percenPessoal,percenFun);

                    funcionarioCatArrayList.add(funcionario);



                }

                ListView listView = (ListView) findViewById(R.id.listView2);
                ArrayAdapter<Funcionario> adapter =
                        new ArrayAdapter<Funcionario>(MainActivity.this, android.R.layout.simple_list_item_1, funcionarioCatArrayList);

                listView.setAdapter(adapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private class GetPercentagemMedicamentos extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                // SharedPreferences preferences = MetodosAnoActivity.this.getPreferences(Context.MODE_PRIVATE);
                //token = preferences.getString("token", null);

                String url = URL_WEBSERVICE
                        + "/Rest/percentagemMedicamentos?dataInicio=" + params[0] + "&dataFim=" + params[1] + "&token=" + tokenS;

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
                    String medicamentosEmJson = readStream(inputStream);




                    return medicamentosEmJson;



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
                ArrayList<Medicamento> medicamentosArrayList = new ArrayList<>();


                JSONArray jsonMedicamentosArray = new JSONArray(s);


                for (int i = 0; i < jsonMedicamentosArray.length(); i++) {

                    JSONObject jsonMedicamento = jsonMedicamentosArray.getJSONObject(i);
                    int ano = jsonMedicamento.getInt("Ano");
                    double valor = jsonMedicamento.getDouble("Valor");
                    Medicamento medicamento = new Medicamento(ano, valor);

                    medicamentosArrayList.add(medicamento);


                }

                ListView listView = (ListView) findViewById(R.id.listView2);
                ArrayAdapter<Medicamento> adapter =
                        new ArrayAdapter<Medicamento>(MainActivity.this, android.R.layout.simple_list_item_1, medicamentosArrayList);

                listView.setAdapter(adapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private class GetPercentagemPessoal extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                // SharedPreferences preferences = MetodosAnoActivity.this.getPreferences(Context.MODE_PRIVATE);
                //token = preferences.getString("token", null);

                String url = URL_WEBSERVICE
                        + "/Rest/percentagemPessoal?dataInicio=" + params[0] + "&dataFim=" + params[1] + "&token=" + tokenS;

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
                    String pessoalEmJson = readStream(inputStream);




                    return pessoalEmJson;



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
                ArrayList<Funcionario> pessoalArrayList = new ArrayList<>();


                JSONArray jsonPessoalArray = new JSONArray(s);


                for (int i = 0; i < jsonPessoalArray.length(); i++) {


                    JSONObject jsonFuncionario = jsonPessoalArray.getJSONObject(i);
                    int ano = jsonFuncionario.getInt("Ano");
                    double media = jsonFuncionario.getDouble("Media");
                    double numFun = jsonFuncionario.getDouble("NumFun");
                    double percenPessoal = jsonFuncionario.getDouble("PercenPessoal");
                    double percenFun = jsonFuncionario.getDouble("PercenFun");


                    Funcionario funcionario = new Funcionario(ano,media,numFun,percenPessoal,percenFun);

                    pessoalArrayList.add(funcionario);




                }

                ListView listView = (ListView) findViewById(R.id.listView2);
                ArrayAdapter<Funcionario> adapter =
                        new ArrayAdapter<Funcionario>(MainActivity.this, android.R.layout.simple_list_item_1, pessoalArrayList);

                listView.setAdapter(adapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private class GetAcoesCategoria extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                // SharedPreferences preferences = MetodosAnoActivity.this.getPreferences(Context.MODE_PRIVATE);
                //token = preferences.getString("token", null);

                String url = URL_WEBSERVICE
                        + "/Rest/acoesCategoria?dataInicio=" + params[0] + "&dataFim=" + params[1] + "&categoria=" + params[2].replace(" ", "") + "&token=" + tokenS;

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
                    String AcoesCatEmJson = readStream(inputStream);




                    return AcoesCatEmJson;



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
                ArrayList<Acao> acoesCatArrayList = new ArrayList<>();


                JSONArray jsonAcoesCatArray = new JSONArray(s);


                for (int i = 0; i < jsonAcoesCatArray.length(); i++) {

                    JSONObject jsonAcaoCat = jsonAcoesCatArray.getJSONObject(i);
                    int ano = jsonAcaoCat.getInt("Ano");
                    double valor = jsonAcaoCat.getDouble("Valor");
                    double soma = jsonAcaoCat.getDouble("Soma1");
                    Acao acaoCat = new Acao(ano,valor, soma);

                    acoesCatArrayList.add(acaoCat);


                }

                ListView listView = (ListView) findViewById(R.id.listView2);
                ArrayAdapter<Acao> adapter =
                        new ArrayAdapter<Acao>(MainActivity.this, android.R.layout.simple_list_item_1, acoesCatArrayList);

                listView.setAdapter(adapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private class GetPerAcoesCat extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                // SharedPreferences preferences = MetodosAnoActivity.this.getPreferences(Context.MODE_PRIVATE);
                //token = preferences.getString("token", null);

                String url = URL_WEBSERVICE
                        + "/Rest/percentagemAcoes?dataInicio=" + params[0] + "&dataFim=" + params[1] +
                        "&categoria=" + params[2].replace(" ", "") + "&token=" + tokenS;

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
                    String acoesPerEmJson = readStream(inputStream);




                    return acoesPerEmJson;



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
                ArrayList<Acao> acaoPerArrayList = new ArrayList<>();


                JSONArray jsonAcaoPerArray = new JSONArray(s);


                for (int i = 0; i < jsonAcaoPerArray.length(); i++) {

                    JSONObject jsonAcaoCat = jsonAcaoPerArray.getJSONObject(i);
                    int ano = jsonAcaoCat.getInt("Ano");
                    double valor = jsonAcaoCat.getDouble("Valor");
                    double soma = jsonAcaoCat.getInt("Soma1");
                    Acao acaoCat = new Acao(ano,valor, soma);


                    acaoPerArrayList.add(acaoCat);


                }

                ListView listView = (ListView) findViewById(R.id.listView2);
                ArrayAdapter<Acao> adapter =
                        new ArrayAdapter<Acao>(MainActivity.this, android.R.layout.simple_list_item_1, acaoPerArrayList);

                listView.setAdapter(adapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private class GetMediaCamas extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                // SharedPreferences preferences = MetodosAnoActivity.this.getPreferences(Context.MODE_PRIVATE);
                //token = preferences.getString("token", null);

                String url = URL_WEBSERVICE
                        + "/Rest/mediaCamas?dataInicio=" + params[0] + "&dataFim=" + params[1] + "&token=" + tokenS;

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
                    String camaEmJson = readStream(inputStream);




                    return camaEmJson;



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
                ArrayList<Cama> camaArrayList = new ArrayList<>();


                JSONArray jsonCamaArray = new JSONArray(s);


                for (int i = 0; i < jsonCamaArray.length(); i++) {

                    JSONObject jsonCama = jsonCamaArray.getJSONObject(i);
                    int ano = jsonCama.getInt("Ano");
                    double valor = jsonCama.getDouble("Valor");
                    Cama cama = new Cama(ano, valor);
                    camaArrayList.add(cama);


                }

                ListView listView = (ListView) findViewById(R.id.listView2);
                ArrayAdapter<Cama> adapter =
                        new ArrayAdapter<Cama>(MainActivity.this, android.R.layout.simple_list_item_1, camaArrayList);

                listView.setAdapter(adapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private class GetRacioFuncionarios extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                // SharedPreferences preferences = MetodosAnoActivity.this.getPreferences(Context.MODE_PRIVATE);
                //token = preferences.getString("token", null);

                String url = URL_WEBSERVICE
                        + "/Rest/racioFuncionarios?dataInicio=" + params[0] + "&dataFim=" + params[1] + "&token=" + tokenS;

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
                    String funcionarioEmJson = readStream(inputStream);




                    return funcionarioEmJson;



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


                JSONArray jsonFuncionarioArray = new JSONArray(s);


                for (int i = 0; i < jsonFuncionarioArray.length(); i++) {

                    JSONObject jsonFuncionario = jsonFuncionarioArray.getJSONObject(i);
                    int ano = jsonFuncionario.getInt("Ano");
                    double media = jsonFuncionario.getDouble("Media");
                    double numFun = jsonFuncionario.getDouble("NumFun");
                    double percenPessoal = jsonFuncionario.getDouble("PercenPessoal");
                    double percenFun = jsonFuncionario.getDouble("PercenFun");


                    Funcionario funcionario = new Funcionario(ano,media,numFun,percenPessoal,percenFun);
                    funcionarioArrayList.add(funcionario);




                }

                ListView listView = (ListView) findViewById(R.id.listView2);
                ArrayAdapter<Funcionario> adapter =
                        new ArrayAdapter<Funcionario>(MainActivity.this, android.R.layout.simple_list_item_1, funcionarioArrayList);

                listView.setAdapter(adapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public int verificaDatas(String dataInicio, String dataFim){

        int a = dataInicio.compareTo(dataFim);
        if (a > 0){
            return -1;
        }else{
            return 1;
        }
    }


}
