package com.example.ejercicioapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ejercicioapi.model.infoApi;
import com.example.ejercicioapi.services.endPoint.infoEndPoint;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    EditText name, username, password, rol;
    Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.txt_name);
        username = findViewById(R.id.txt_username);
        password = findViewById(R.id.txt_password);
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
       Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        infoEndPoint infoEndPoint = retrofit.create(infoEndPoint.class);
        Call<List<infoApi>> call = infoEndPoint.getInfo();
        call.enqueue(new Callback<List<infoApi>>() {
            @Override
            public void onResponse(Call<List<infoApi>> call, Response<List<infoApi>> response) {
                if(!response.isSuccessful()){
                    name.setText(response.code() + "");
                    return;
                }

                List<infoApi> postList = response.body();

                for(infoApi post: postList){
                    String content = "";
                    content += "name: " + post.getName();
                    name.append(content);
                }

            }

            @Override
            public void onFailure(Call<List<infoApi>> call, Throwable t) {
                name.setText(t.getMessage());
            }
        });
    }

}