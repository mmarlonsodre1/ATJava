package com.example.atjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.atjava.models.Json;
import com.example.atjava.models.ListJson;
import com.example.atjava.web.APIClient;
import com.example.atjava.web.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PosLogin extends AppCompatActivity {
    private Call<Json> call;
    ListView list;
    List<Json> jsonss;
    private APIInterface apiService;
    private Json json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pos_login);
        list = findViewById(R.id.listview);
        jsonss = new ArrayList<>();

        requestInfo();
    }

    private void requestInfo(){
        apiService = APIClient.getService().create(APIInterface.class);
        call = apiService.getInfo();

        call.enqueue(new Callback<Json>() {
            @Override
            public void onResponse(Call<Json> call, Response<Json> response) {

                json = (Json) response.body();
                Json jsons = new Json(json.getMsg(), json.getMsf());
                jsonss.add(jsons);

                ListJson adapter = new ListJson(PosLogin.this, jsonss);
                list.setAdapter(adapter);

                Toast.makeText(PosLogin.this, "Deu bom", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<Json> call, Throwable t) {
                Log.e("Networking", t.toString());
                Toast.makeText(PosLogin.this, "Deu ruim ( Internet )", Toast.LENGTH_LONG).show();

            }
        });

    }
}
