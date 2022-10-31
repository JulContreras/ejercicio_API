package com.example.ejercicioapi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ejercicioapi.model.Data;

public class adapatadorUsuario extends RecyclerView.Adapter<adapatadorUsuario.UsuarioViewHolder> {

    Data data;

    public adapatadorUsuario(Data data){
        this.data = data;
    }

    @NonNull
    @Override
    public adapatadorUsuario.UsuarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_usuarios,null,false);
        return new UsuarioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapatadorUsuario.UsuarioViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class UsuarioViewHolder extends RecyclerView.ViewHolder {
        public UsuarioViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
