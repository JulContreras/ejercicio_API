package com.example.ejercicioapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ejercicioapi.model.Data;
import com.google.gson.Gson;


import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    EditText name, username, id, rol;
    Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        id = findViewById(R.id.txt_id);
        name = findViewById(R.id.txt_name);
        username = findViewById(R.id.txt_username);
        rol = findViewById(R.id.txt_rol);
        send = findViewById(R.id.btn_send);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LeerWS();
            }
        });

    }

    private void LeerWS(){
        String url="https://bd27-181-53-200-20.ngrok.io/api/users/2";

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
        });
        Volley.newRequestQueue(this).add(postRequest);
        }
    }

