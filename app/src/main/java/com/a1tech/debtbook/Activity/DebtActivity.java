package com.a1tech.debtbook.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.a1tech.debtbook.Adapter.ClientAdapter;
import com.a1tech.debtbook.Adapter.DebtAdapter;
import com.a1tech.debtbook.Model.Client;
import com.a1tech.debtbook.Model.Debt;
import com.a1tech.debtbook.R;

import java.util.ArrayList;

public class DebtActivity extends AppCompatActivity {

    private final String TAG = "DebtActivity";
    private ImageView ivBack, ivSearch;
    private ArrayList<Debt> debt = new ArrayList<>();
    private RecyclerView rvDebt;
    private DebtAdapter debtAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        init();
        setData();
        setAdapter();

    }

    private void init() {
        ivBack = findViewById(R.id.ivBack);
        ivSearch = findViewById(R.id.ivSearch);
        rvDebt = findViewById(R.id.rvDebt);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void setData() {
        debt.add(new Debt("Шпаклёвка", 5, 124000, "22.05.2022"));
        debt.add(new Debt("Шпаклёвка", 5, 124000, "22.05.2022"));
        debt.add(new Debt("Шпаклёвка", 5, 124000, "22.05.2022"));
        debt.add(new Debt("Шпаклёвка", 5, 124000, "22.05.2022"));
        debt.add(new Debt("Шпаклёвка", 5, 124000, "22.05.2022"));
        debt.add(new Debt("Шпаклёвка", 5, 124000, "22.05.2022"));
        debt.add(new Debt("Шпаклёвка", 5, 124000, "22.05.2022"));
        debt.add(new Debt("Шпаклёвка", 5, 124000, "22.05.2022"));
        debt.add(new Debt("Шпаклёвка", 5, 124000, "22.05.2022"));
        debt.add(new Debt("Шпаклёвка", 5, 124000, "22.05.2022"));
        debt.add(new Debt("Шпаклёвка", 5, 124000, "22.05.2022"));
        debt.add(new Debt("Шпаклёвка", 5, 124000, "22.05.2022"));
        debt.add(new Debt("Шпаклёвка", 5, 124000, "22.05.2022"));
        debt.add(new Debt("Шпаклёвка", 5, 124000, "22.05.2022"));
        debt.add(new Debt("Шпаклёвка", 5, 124000, "22.05.2022"));
        debt.add(new Debt("Шпаклёвка", 5, 124000, "22.05.2022"));
        debt.add(new Debt("Шпаклёвка", 5, 124000, "22.05.2022"));
        debt.add(new Debt("Шпаклёвка", 5, 124000, "22.05.2022"));
    }

    private void setAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        rvDebt.setLayoutManager(linearLayoutManager);
        debtAdapter = new DebtAdapter(this, debt);
        rvDebt.setAdapter(debtAdapter); // set the Adapter to RecyclerView
    }
}