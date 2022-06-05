package com.a1tech.debtbook.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListPopupWindow;
import android.widget.TextView;

import com.a1tech.debtbook.R;

public class AddDebtActivity extends AppCompatActivity {

    private final String[] amount = {"шт", "кг", "литр"};
    private final String[] currency = {"SUM", "$", "₽"};
    private final String TAG = "AddDebtActivity";
    private ImageView ivBack;
    private TextView tvDebterPhoto;
    private EditText etDebterName, etDebterPhone;
    private EditText etDebtItemName, edItemAmount, etItemPrice;
    private TextView tvDDItemAmount, tvDDPrice;
    private Button btnDebtorSave, btnDebtSave;
    private ListPopupWindow mListPopupWindowAmount, mListPopupWindowPrice;
    private ConstraintLayout addDebtor, addDebt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_debt);

        init();
        setOnClicks();
        setDropDownForAmount();
        setDropDownForPrice();
        setViewByStatus();

    }

    private void setViewByStatus() {
        Bundle arguments = getIntent().getExtras();
        int status = arguments.getInt("add");

        if (status == 0) {
            addDebt.setVisibility(View.GONE);
            addDebtor.setVisibility(View.VISIBLE);
        } else if (status == 1) {
            addDebt.setVisibility(View.VISIBLE);
            addDebtor.setVisibility(View.GONE);
        }
    }

    private void init() {
        ivBack = findViewById(R.id.ivBack);
        tvDebterPhoto = findViewById(R.id.tvDebterPhoto);
        etDebterName = findViewById(R.id.etDebterName);
        etDebterPhone = findViewById(R.id.etDebterPhone);
        etDebtItemName = findViewById(R.id.etDebtItemName);
        edItemAmount = findViewById(R.id.edItemAmount);
        etItemPrice = findViewById(R.id.etItemPrice);
        tvDDPrice = findViewById(R.id.tvDDPrice);
        tvDDItemAmount = findViewById(R.id.tvDDItemAmount);
        btnDebtorSave = findViewById(R.id.btnDebtorSave);
        btnDebtSave = findViewById(R.id.btnDebtSave);
        mListPopupWindowAmount = new ListPopupWindow(this);
        mListPopupWindowPrice = new ListPopupWindow(this);
        addDebt = findViewById(R.id.clAddDebt);
        addDebtor = findViewById(R.id.clAddDebtor);
    }

    private void setOnClicks() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        tvDebterPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // set intent to take photo from gallery
            }
        });
        btnDebtSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btnDebtorSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void setDropDownForAmount() {
        mListPopupWindowAmount.setAnchorView(tvDDItemAmount);
        mListPopupWindowAmount.setAdapter(new ArrayAdapter(getBaseContext(), R.layout.list_popup_window, amount));

        mListPopupWindowAmount.setModal(true);
        mListPopupWindowAmount.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tvDDItemAmount.setText(amount[position]);
                mListPopupWindowAmount.dismiss();
            }
        });

        tvDDItemAmount.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mListPopupWindowAmount.show();
            }
        });
    }

    private void setDropDownForPrice() {
        mListPopupWindowPrice.setAnchorView(tvDDPrice);
        mListPopupWindowPrice.setAdapter(new ArrayAdapter(getBaseContext(), R.layout.list_popup_window, currency));

        mListPopupWindowPrice.setModal(true);
        mListPopupWindowPrice.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tvDDPrice.setText(currency[position]);
                mListPopupWindowPrice.dismiss();
            }
        });

        tvDDPrice.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mListPopupWindowPrice.show();
            }
        });
    }

}