package com.a1tech.debtbook.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.a1tech.debtbook.Adapter.DebtAdapter;
import com.a1tech.debtbook.Model.Debt;
import com.a1tech.debtbook.R;

import java.util.ArrayList;

public class DebtActivity extends AppCompatActivity {

    private final String TAG = "DebtActivity";
    private ImageView ivBack, ivSearch;
    private ArrayList<Debt> debt = new ArrayList<>();
    private RecyclerView rvDebt;
    private TextView tvAddDebtor;
    private DebtAdapter debtAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        init();
        setData();
        setAdapter();
        setOnClicks();
    }

    private void init() {
        ivBack = findViewById(R.id.iv_back);
        ivSearch = findViewById(R.id.ivSearch);
        rvDebt = findViewById(R.id.rvDebt);
        tvAddDebtor = findViewById(R.id.tvAddDebt);


    }

    private void setOnClicks() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        tvAddDebtor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DebtActivity.this, AddDebtActivity.class);
                intent.putExtra("add", 1);
                startActivity(intent);
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