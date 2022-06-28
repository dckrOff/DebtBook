package com.a1tech.debtbook.Adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.a1tech.debtbook.Model.Debt;
import com.a1tech.debtbook.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class DebtsAdapter extends RecyclerView.Adapter<DebtsAdapter.MyViewHolder> {

    private final LayoutInflater inflater;
    private final ArrayList<Debt> debt;
    private final String pattern = "###,###,###.###";
    private final DecimalFormat decimalFormat = new DecimalFormat(pattern);

    public DebtsAdapter(Context context, ArrayList<Debt> debt) {
        this.inflater = LayoutInflater.from(context);
        this.debt = debt;
    }

    @Override
    public DebtsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_debts, parent, false);
        return new DebtsAdapter.MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(DebtsAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        Debt debt = this.debt.get(position);

        // formatter of number(1234567890)-- > (1 234 567 890)
        String formatDebt = decimalFormat.format(Double.valueOf(debt.getItemAmount()));

        holder.itemName.setText(debt.getItemName());
        holder.itemCount.setText(debt.getItemCount() + " шт.");
        holder.itemAmount.setText(formatDebt + " сум");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialog(debt.getItemName(), debt.getItemCount(), debt.getDebtDate(), formatDebt);
            }
        });

    }

    @Override
    public int getItemCount() {
        return debt.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView itemName, itemCount, itemAmount;

        public MyViewHolder(View itemView) {
            super(itemView);

            itemName = itemView.findViewById(R.id.itemName);
            itemCount = itemView.findViewById(R.id.itemCount);
            itemAmount = itemView.findViewById(R.id.itemAmount);
        }
    }

    @SuppressLint("SetTextI18n")
    private void customDialog(String name, int count, String detbDate, String amount) {
        final Dialog dialog = new Dialog(inflater.getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        TextView itemName = dialog.findViewById(R.id.tv_item_name);
        TextView itemCount = dialog.findViewById(R.id.tv_item_count);
        TextView date = dialog.findViewById(R.id.tv_date);
        TextView amountGoods = dialog.findViewById(R.id.tv_amount_goods);

        TextView btnDelete = dialog.findViewById(R.id.tv_delete);
        TextView btnEdit = dialog.findViewById(R.id.tv_edit);
        ImageView btnExit = dialog.findViewById(R.id.iv_exit);

        itemName.setText(name);
        itemCount.setText(String.valueOf(count));
        date.setText(detbDate);
        amountGoods.setText(amount + " сум");


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
