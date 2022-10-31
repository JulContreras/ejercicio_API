package com.example.ejercicioapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class listarActivity extends AppCompatActivity {

    RecyclerView listaUsuarios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);
        listaUsuarios = findViewById(R.id.listarUsuario);
    }

    public void visualizarUsuarios(){
        listaUsuarios.setLayoutManager(new LinearLayoutManager(this));

    }

}