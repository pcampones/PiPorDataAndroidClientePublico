package com.example.pedrocampons.pipordatawebserviceandroidpublico;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {

    private final static String TOKEN = "token";
    private String token;
    private final static String URL_WEBSERVICE = "http://projetointerswebservice.apphb.com/Service1.svc";
    //  private  String user = "";
    //  private  String pass ="";
    private EditText user;
    private EditText pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user= (EditText)findViewById(R.id.userEditText);
        pass = (EditText)findViewById(R.id.passEditText);

    }
    public void button_login_onClick(View view){
        LoginTask login = new LoginTask();
        login.execute(user.getText().toString(), pass.getText().toString());
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


    private class LoginTask extends AsyncTask<String,Void,String> {


        @Override
        protected String doInBackground(String... params) {
          /*  editText =  (EditText) findViewById(R.id.editTextPassword);
            editNome = (EditText) findViewById(R.id.editTextUsername);*/
            //Todas as instruções de uma thread paralela
            //Todas as operações de rede
            //Chamada ao método login no webservice
            //A parte a verde tem depois de ser a nossa, eu coloquei o do prof porque não consegui fazer o meu
            try {
                String url = URL_WEBSERVICE + "/Rest/login?username=" + params[0] + "&password=" + params[1];

                HttpURLConnection httpURLConnection = setupHttpURLConnection(url, "POST");

                httpURLConnection.setDoInput(true); //Recebe coisas do webservice?
               httpURLConnection.setDoOutput(false); //Manda coisas para o webservice?

                httpURLConnection.connect();
                int responseCode = httpURLConnection.getResponseCode();
                Log.i("responseCode", "" + responseCode);

                if (responseCode == 200) { //status OK
                    InputStream inputStream = httpURLConnection.getInputStream();
                     token = readStream(inputStream);

                    return token;

                } else { //Houve problemas
                    return "ERRO: " + responseCode;

                }
            } catch (IOException e) {
                e.printStackTrace();
                return "ERRO: " + e.getMessage();
            }

        }

        protected void onPostExecute(String s) {


            //Guardar e mostrar o token

            Toast.makeText(LoginActivity.this, s, Toast.LENGTH_SHORT).show();

            SharedPreferences preferences = LoginActivity.this.getSharedPreferences("token",Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = preferences.edit();

            edit.putString("token", s.replace("\"", ""));
            edit.commit();
            if (!s.startsWith("Erro:")) {
                Toast.makeText(LoginActivity.this, "Login Succecfull", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
              //  startActivityForResult(intent, 1);
                intent.putExtra("token", token);
                startActivityForResult(intent, 1);


            } else {

                Toast.makeText(LoginActivity.this, "Error login. Username and Password are incorrect!! " + s, Toast.LENGTH_SHORT).show();

            }


        }
    }
}
