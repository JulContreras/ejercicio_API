package com.example.ejercicioapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    EditText name, username, id, rol;
    Button send, listar, actualizar, detalles, eliminar;
    MainActivity context = this;
    TextView countTxt;
    int ID;
    String URL = "https://8e74-181-53-200-20.ngrok.io/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        id = findViewById(R.id.txt_id);
        name = findViewById(R.id.txt_name);
        username = findViewById(R.id.txt_username);
        rol = findViewById(R.id.txt_rol);
        countTxt = findViewById(R.id.txt_count);
        send = findViewById(R.id.btn_send);
        listar = findViewById(R.id.btn_listar);
        actualizar = findViewById(R.id.btn_update);
        detalles = findViewById(R.id.btn_detail);
        eliminar = findViewById(R.id.btn_delete);

        if (this.getIntent().getExtras() != null){
            ID = getIntent().getExtras().getInt("ID");
            LeerWS(ID);
            ID = 0;
        }

        //boton send, es para crear un nuevo registro, todo esta bien pero no lo crea
        //TOCA REVISAR
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nuevoWS();
            }
        });

        listar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, listarActivity.class);
                startActivity(intent);
            }
        });

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarWS(Integer.parseInt(id.getText().toString()));
            }
        });

        detalles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LeerWS(Integer.parseInt(id.getText().toString()));
            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setMessage("¿Realmente desea eliminar el usuario:"+ name.getText().toString() + " con el Username: "+username.getText().toString()).setCancelable(false)
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                delete(Integer.parseInt(id.getText().toString()));

                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog titulo = alert.create();
                titulo.setTitle("Eliminar registro");
                titulo.show();


            }
        });

    }

    public void limpiarEdit(){
        id.setText("");
        name.setText("");
        username.setText("");
        rol.setText("");
    }

    private void enviarWS(int a){
        String url = URL+"api/users/"+a;
        StringRequest postRequest = new StringRequest(Request.Method.PUT, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Data data = new Gson().fromJson(jsonObject.get("data").toString(), Data.class);
                    id.setText(String.valueOf(data.getId()));
                    name.setText(data.getNames());
                    username.setText(data.getUsername());
                    rol.setText(data.getRol());
                    AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                    alert.setMessage("Los datos se han actualizado con exito");
                    AlertDialog titulo = alert.create();
                    titulo.setTitle("Actualizacion de datos");
                    titulo.show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog();
            }
        }){
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();
                params.put("names", name.getText().toString());
                params.put("username", username.getText().toString());
                params.put("rol", rol.getText().toString());
                params.put("password", "uwu");
                return params;
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("code-app","2022*01");
                return params;
            }
        };
        Volley.newRequestQueue(this).add(postRequest);
        limpiarEdit();
    }

    private void nuevoWS(){
        String url= URL+"api/users";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Data data = new Gson().fromJson(jsonObject.get("data").toString(), Data.class);
                    AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                    alert.setMessage("el usuario "+data.getUsername()+" ha sido creado correctamente");
                    AlertDialog titulo = alert.create();
                    titulo.setTitle("Creacion de usuario");
                    titulo.show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog();
            }
        }){
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("names", name.getText().toString());
                params.put("username", username.getText().toString());
                params.put("rol", rol.getText().toString());
                params.put("password", id.getText().toString());
                return params;
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("code-app","2022*01");
                return params;
            }
        };
        Volley.newRequestQueue(context).add(postRequest);
        limpiarEdit();
    }

    private void LeerWS(int a){
        String url=URL+"api/users/"+a;
        StringRequest postRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Data data = new Gson().fromJson(jsonObject.get("data").toString(), Data.class);
                    id.setText(String.valueOf(data.getId()));
                    name.setText(data.getNames());
                    username.setText(data.getUsername());
                    rol.setText(data.getRol());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("code-app","2022*01");
                return params;
            }
        };
        Volley.newRequestQueue(this).add(postRequest);
        }

    public void delete(int a){
        String url= URL + "api/users/" + a;
        if (id.getText().toString().equals("")){
            Toast.makeText(this, "Ingrese el id", Toast.LENGTH_LONG);
            return;
        }
        StringRequest postRequest = new StringRequest(Request.Method.DELETE, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Data data = new Gson().fromJson(jsonObject.get("data").toString(), Data.class);
                    id.setText(String.valueOf(data.getId()));
                    name.setText(data.getNames());
                    username.setText(data.getUsername());
                    rol.setText(data.getRol());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("code-app","2022*01");
                return params;
            }
        };
        Volley.newRequestQueue(this).add(postRequest);
        limpiarEdit();
    }

    public void dialog(){
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        alert.setMessage("Ocurrio un error en el programa");
        AlertDialog titulo = alert.create();
        titulo.setTitle("Error");
        titulo.show();
        limpiarEdit();
    }

}

