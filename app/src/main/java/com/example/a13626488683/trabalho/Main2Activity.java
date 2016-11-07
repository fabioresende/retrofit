package com.example.a13626488683.trabalho;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 13626488683 on 07/11/2016.
 */
public class Main2Activity extends AppCompatActivity {
    Intent intent = getIntent();
    String idPost = intent.getStringExtra("idPost");
    TextView saida;
    RespostaServidor resposta = new RespostaServidor();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        saida = (TextView) findViewById(R.id.textView_resp);
        //chama o retrofit para fazer a requisição no webservice
        retrofitConverter(idPost);
    }

    public void setaValores() {
        saida.setText(resposta.getResult());

    }


    public void retrofitConverter(String idPost) {

        RetrofitService service = ServiceGenerator.createService(RetrofitService.class);

        Call<RespostaServidor> call = service.converterUnidade(idPost);

        call.enqueue(new Callback<RespostaServidor>() {
            @Override
            public void onResponse(Call<RespostaServidor> call, Response<RespostaServidor> response) {

                if (response.isSuccessful()) {

                    RespostaServidor respostaServidor = response.body();

                    //verifica aqui se o corpo da resposta não é nulo
                    if (respostaServidor != null) {

                        if (respostaServidor.isValid()) {

                            resposta.setFrom_type(respostaServidor.getFrom_type());
                            resposta.setFrom_value(respostaServidor.getFrom_value());
                            resposta.setResult(respostaServidor.getResult());
                            resposta.setTo_type(respostaServidor.getTo_type());
                            resposta.setValid(respostaServidor.isValid());

                            progress.dismiss();
                            setaValores();

                        } else {

                            Toast.makeText(getApplicationContext(), "Insira unidade e valores válidos", Toast.LENGTH_SHORT).show();
                        }

                    } else {

                        Toast.makeText(getApplicationContext(), "Resposta nula do servidor", Toast.LENGTH_SHORT).show();
                    }

                } else {

                    Toast.makeText(getApplicationContext(), "Resposta não foi sucesso", Toast.LENGTH_SHORT).show();
                    // segura os erros de requisição
                    ResponseBody errorBody = response.errorBody();
                }

                progress.dismiss();
            }

            @Override
            public void onFailure(Call<RespostaServidor> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "Erro na chamada ao servidor", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.a13626488683.trabalho/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.a13626488683.trabalho/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    public Intent getIntent() {
        return intent;
    }
}
