package com.a1tech.debtbook.Activity;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.a1tech.debtbook.Adapter.ClientAdapter;
import com.a1tech.debtbook.Model.Client;
import com.a1tech.debtbook.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";
    private ImageView ivMenu, ivSearch;
    private ArrayList<Client> clientList = new ArrayList<>();
    private RecyclerView rvClients;
    private ClientAdapter clientAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO); // off dark mode
        setContentView(R.layout.activity_main);

        init();
        setData();
        setAdapter();
    }

    private void init() {
        ivMenu = findViewById(R.id.iv_menu);
        ivSearch = findViewById(R.id.iv_search);
        rvClients = findViewById(R.id.rv_clients);
    }

    private void setData() {
        clientList.add(new Client("Отабоев Азамат", 1254000, "21/05/2022"));
        clientList.add(new Client("Авезов Рустам", 2450000, "21.05.2022"));
        clientList.add(new Client("Отабоев Азамат", 1254000, "21-05-2022"));
        clientList.add(new Client("Авезов Рустам", 3120000, "21.05.2022"));
        clientList.add(new Client("Отабоев Азамат", 1254000, "21/05/2022"));
    }

    private void setAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        rvClients.setLayoutManager(linearLayoutManager);
        clientAdapter = new ClientAdapter(this, clientList);
        rvClients.setAdapter(clientAdapter); // set the Adapter to RecyclerView
    }
}