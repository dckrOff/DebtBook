package com.a1tech.debtbook;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";
    private ImageView ivMenu, ivSearch;
    private RecyclerView rvClients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

    }

    private void init() {
        ivMenu = findViewById(R.id.iv_menu);
        ivSearch = findViewById(R.id.iv_search);
        rvClients = findViewById(R.id.rv_clients);
    }
}