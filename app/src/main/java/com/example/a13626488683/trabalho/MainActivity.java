package com.example.a13626488683.trabalho;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Button botaoEnviar;
    private EditText post;
    private String idPost;
    RespostaServidor resposta = new RespostaServidor();
    ProgressDialog progress;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        post = (EditText) findViewById(R.id.edittext_id_post);
        String idPost = post.getText().toString();
        botaoEnviar = (Button) findViewById(R.id.button_enviar);

        listenersButtons();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    /**
     * Chama os listeners para os botões
     */
    public void listenersButtons() {

        botaoEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //chama tela da Activity2
                Intent i = new Intent(MainActivity.this, Main2Activity.class);
                i.putExtra("idPost", idPost);
                startActivity(i);
            }
        });
    }
}