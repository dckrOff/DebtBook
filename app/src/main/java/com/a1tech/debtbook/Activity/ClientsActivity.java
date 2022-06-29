package com.a1tech.debtbook.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.a1tech.debtbook.Adapter.ClientAdapter;
import com.a1tech.debtbook.Model.Client;
import com.a1tech.debtbook.R;

import java.util.ArrayList;

public class ClientsActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";
    private ImageView ivMenu, ivSearch;
    private TextView tvAddDebtor;
    private ArrayList<Client> clientList = new ArrayList<>();
    private RecyclerView rvClients;
    private ClientAdapter clientAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO); // off dark mode
        setContentView(R.layout.activity_clients);

        init();
        setData();
        setAdapter();
    }

    private void init() {
        ivMenu = findViewById(R.id.iv_menu);
        ivSearch = findViewById(R.id.iv_search);
        rvClients = findViewById(R.id.rv_clients);
        tvAddDebtor = findViewById(R.id.tvAddDebtor);

        tvAddDebtor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClientsActivity.this, AddDebtActivity.class);
                intent.putExtra("add",0);
                startActivity(intent);
            }
        });
    }

    private void setData() {
        clientList.add(new Client("Отабоев Азамат", 12540000, "21/05/2022"));
        clientList.add(new Client("Авезов Рустам", 24520000, "21.05.2022"));
        clientList.add(new Client("Отабоев Азамат", 1254000, "21-05-2022"));
        clientList.add(new Client("Авезов Рустам", 3120000, "21.05.2022"));
        clientList.add(new Client("Отабоев Азамат", 1254000, "21/05/2022"));
        clientList.add(new Client("Отабоев Азамат", 99999999, "21/05/2022"));
    }

    private void setAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        rvClients.setLayoutManager(linearLayoutManager);
        clientAdapter = new ClientAdapter(this, clientList);
        rvClients.setAdapter(clientAdapter); // set the Adapter to RecyclerView
    }
}