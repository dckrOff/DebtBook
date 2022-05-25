package com.a1tech.debtbook.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.a1tech.debtbook.Model.Debt;
import com.a1tech.debtbook.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class DebtAdapter extends RecyclerView.Adapter<DebtAdapter.MyViewHolder> {

    private final LayoutInflater inflater;
    private final ArrayList<Debt> debt;
    private final String pattern = "###,###,###.###";
    private final DecimalFormat decimalFormat = new DecimalFormat(pattern);

    public DebtAdapter(Context context, ArrayList<Debt> debt) {
        this.inflater = LayoutInflater.from(context);
        this.debt = debt;
    }

    @Override
    public DebtAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_debts, parent, false);
        return new DebtAdapter.MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(DebtAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        Debt debt = this.debt.get(position);

        // formatter of number(1234567890)-- > (1 234 567 890)
        String formatDebt = decimalFormat.format(Double.valueOf(debt.getItemAmount()));

        holder.itemName.setText(debt.getItemName());
        holder.itemCount.setText(debt.getItemCount() + " шт.");
        holder.itemAmount.setText(formatDebt + " сум");

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
}
