package com.example.ejercicioapi;

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
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class listarActivity extends AppCompatActivity {

    RecyclerView listaUsuarios;
    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_listar);
        listaUsuarios = findViewById(R.id.listarUsuario);
        if (getIntent()!=null){
            count = getIntent().getExtras().getInt("count");
        }
        visualizarUsuarios();
    }

    public void visualizarUsuarios(){
        listaUsuarios.setLayoutManager(new LinearLayoutManager(this));
        adapatadorUsuario adapter = new adapatadorUsuario(count);
        listaUsuarios.setAdapter(adapter);
    }




}