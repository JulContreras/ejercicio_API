package com.example.ejercicioapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ejercicioapi.model.Data;
import com.example.ejercicioapi.model.InfoData;
import com.example.ejercicioapi.model.JsonPlaceHolderApi;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class listarActivity extends AppCompatActivity {

    RecyclerView listaUsuarios;
    int count=0;
    Context context = this;
    String URL = "https://4912-201-228-154-179.ngrok.io/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_listar);
        listaUsuarios = findViewById(R.id.listarUsuario);

        getPost();
    }

    private void getPost(){
        ArrayList<Data> listaPersonas = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<InfoData> call = jsonPlaceHolderApi.getData();

        call.enqueue(new Callback<InfoData>() {
            @Override
            public void onResponse(@NonNull Call<InfoData> call, @NonNull retrofit2.Response<InfoData> response) {
                if (!response.isSuccessful()){
                    AlertDialog.Builder alert = new AlertDialog.Builder(listarActivity.this);
                    alert.setMessage("Error: "+response.code());
                    AlertDialog titulo = alert.create();
                    titulo.setTitle("Error");
                    titulo.show();
                    return;
                }

                InfoData postList = response.body();

                Data data;

                for (int i=0; i< postList.data.size(); i++){
                    data = new Data();
                    data.setId(postList.data.get(i).getId());
                    data.setNames(postList.data.get(i).getNames());
                    data.setUsername(postList.data.get(i).getUsername());
                    data.setRol(postList.data.get(i).getRol());
                    listaPersonas.add(data);
                }

                listaUsuarios.setLayoutManager(new LinearLayoutManager(context));
                adapatadorUsuario adapter = new adapatadorUsuario(listaPersonas);
                listaUsuarios.setAdapter(adapter);

                //name.setText(postList.data.get(1).getNames());

            }

            @Override
            public void onFailure(Call<InfoData> call, Throwable t) {
                AlertDialog.Builder alert = new AlertDialog.Builder(listarActivity.this);
                alert.setMessage("Error: "+t.getMessage());
                AlertDialog titulo = alert.create();
                titulo.setTitle("Error1");
                titulo.show();
            }
        });
    }


}