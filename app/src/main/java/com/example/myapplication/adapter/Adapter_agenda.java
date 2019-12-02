package com.example.myapplication.adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Agenda;

import java.util.List;

public class Adapter_agenda extends RecyclerView.Adapter<Adapter_agenda.AdapterHolde> {

    private Context contexto;
    private List<Agenda> lista;
    private cliqueCard click;

    //construtor
    public Adapter_agenda(Context contexto, List<Agenda> lista, cliqueCard click) {
        this.contexto = contexto;
        this.lista = lista;
        this.click = click;
    }

    @NonNull
    @Override
    public AdapterHolde onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //layout que vai exibir as informações
        View view = LayoutInflater.from(contexto).inflate(R.layout.adapter_agenda, parent, false);
        AdapterHolde holder = new AdapterHolde(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterHolde holder, final int position) {
        //pega as informaçoes que foram mapeadas
        holder.txItem2.setText(String.valueOf(lista.get(position).getExame()));
        holder.txItem3.setText(String.valueOf(lista.get(position).getConvenio()));
        holder.txItem4.setText(String.valueOf(lista.get(position).getMedico()));
        holder.txItem5.setText(String.valueOf(lista.get(position).getData()));

        if (click != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    //possição do card
                    click.clickView(holder.itemView, position);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    click.onLongClick(holder.itemView,position);

                    return true;
                }
            });

        }
    }
    //exibi a lista toda
    @Override
    public int getItemCount() {
        if (lista.size()>0) {
            return lista.size();
        } else {
            return 0;
        }
    }

    public interface cliqueCard {
        @SuppressLint("NewApi")
        void onLongClick(View view, int i);
        void clickView(View view, int index);
    }

    public static class AdapterHolde extends RecyclerView.ViewHolder {
        View view;
        TextView  txItem2, txItem3, txItem4, txItem5;


        public AdapterHolde(View itemView) {
            super(itemView);

            this.view = itemView;

            txItem2 = view.findViewById(R.id.txtEspecialidade);
            txItem3 = view.findViewById(R.id.txtConvenio);
            txItem4 = view.findViewById(R.id.txtMedico);
            txItem5 = view.findViewById(R.id.txtData);



        }
    }
}
