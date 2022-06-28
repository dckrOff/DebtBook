package com.a1tech.debtbook.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.a1tech.debtbook.Activity.DebtsActivity;
import com.a1tech.debtbook.Model.Client;
import com.a1tech.debtbook.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.MyViewHolder> {

    private final LayoutInflater inflater;
    private final ArrayList<Client> clientList;
    private final String pattern = "###,###,###.###";
    private final DecimalFormat decimalFormat = new DecimalFormat(pattern);

    public ClientAdapter(Context context, ArrayList<Client> clientList) {
        this.inflater = LayoutInflater.from(context);
        this.clientList = clientList;
    }

    @Override
    public ClientAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_client, parent, false);
        return new ClientAdapter.MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ClientAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        Client client = this.clientList.get(position);

        // formatter of number(1234567890)-- > (1 234 567 890)
        String formatDebt = decimalFormat.format(Double.valueOf(client.getDebtAmount()));

        holder.clientName.setText(client.getClientName());
        holder.debtAmount.setText(formatDebt + " UZS");
        holder.debtDate.setText(client.getDebtDate());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DebtsActivity.class);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return clientList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView clientName, debtAmount, debtDate, btnDetails;

        public MyViewHolder(View itemView) {
            super(itemView);

            clientName = itemView.findViewById(R.id.tvClientName);
            debtAmount = itemView.findViewById(R.id.tvDebtAmount);
            debtDate = itemView.findViewById(R.id.tvDebtDate);
            btnDetails = itemView.findViewById(R.id.btnDetail);

        }
    }
}
