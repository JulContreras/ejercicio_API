package com.example.ejercicioapi;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class adapatadorUsuario extends RecyclerView.Adapter<adapatadorUsuario.UsuarioViewHolder> {

    ArrayList<Data> lista;

    public adapatadorUsuario(ArrayList<Data> data ){
        this.lista = data;
    }


    @NonNull
    @Override
    public adapatadorUsuario.UsuarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_usuarios,null,false);
        return new UsuarioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapatadorUsuario.UsuarioViewHolder holder, int position) {
        holder.idU.setText(String.valueOf(lista.get(position).getUsername()));
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class UsuarioViewHolder extends RecyclerView.ViewHolder {
        TextView idU;
        public UsuarioViewHolder(@NonNull View itemView) {
            super(itemView);
            idU = itemView.findViewById(R.id.txt_idU);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, MainActivity.class);
                    intent.putExtra("ID", lista.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
