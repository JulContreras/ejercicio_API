package com.example.ejercicioapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText name, username, id, rol;
    Button send, listar;
    MainActivity context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        id = findViewById(R.id.txt_id);
        name = findViewById(R.id.txt_name);
        username = findViewById(R.id.txt_username);
        rol = findViewById(R.id.txt_rol);
        send = findViewById(R.id.btn_send);
        listar = findViewById(R.id.btn_listar);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nuevoWS();
            }
        });

        listar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(MainActivity.this, listarActivity.class);
                //startActivity(intent);
            }
        });

    }

    private void enviarWS(){
        String url="https://24d3-201-228-154-179.ngrok.io/api/users/0";

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
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

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
    }

    private void nuevoWS(){
        String url="https://24d3-201-228-154-179.ngrok.io/api/users";

        StringRequest postRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Data data = new Gson().fromJson(jsonObject.get("data").toString(), Data.class);
                    /*id.setText(String.valueOf(data.getId()));
                    name.setText(data.getNames());
                    username.setText(data.getUsername());
                    rol.setText(data.getRol());*/
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<String, String>();
                params.put("names", name.getText().toString());
                params.put("username", username.getText().toString());
                params.put("rol", rol.getText().toString());
                params.put("password", id.getText().toString());

                //params.put("updated_at", getDate());
                //params.put("created_at", getDate());
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
    }

    public String getDate(){
        Calendar fecha = Calendar.getInstance();
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int hora = fecha.get(Calendar.HOUR_OF_DAY);
        int minuto = fecha.get(Calendar.MINUTE);
        int segundo = fecha.get(Calendar.SECOND);
        return año+"-"+mes+"-"+dia+" "+hora+":"+minuto+":"+segundo;
    }

    private void LeerWS(){
        String url="https://24d3-201-228-154-179.ngrok.io/api/users/"+id.getText().toString();

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

}

