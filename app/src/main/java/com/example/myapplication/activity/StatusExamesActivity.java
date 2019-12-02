package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.adapter.Adapter_agenda;
import com.example.myapplication.adapter.Adapter_exame;
import com.example.myapplication.dao.ExameDAO;
import com.example.myapplication.interfaceHelp.InterfaceHelp;

public class StatusExamesActivity extends AppCompatActivity implements InterfaceHelp {

    private RecyclerView recyclerViewExame;
    private Context context = this;
    ExameDAO db = new ExameDAO(context);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_exames);
        db = new ExameDAO(context);

        FindView();
        Onclick();

        if (db.ListarBancoExame().isEmpty()) {
            Toast.makeText(context, "vazio", Toast.LENGTH_SHORT).show();

        } else {
            db.ListarBancoExame();
            recyclerViewExame.setLayoutManager(new LinearLayoutManager(context));
            recyclerViewExame.setAdapter(new Adapter_exame(context, db.ListarBancoExame(), clickListner()));
        }

    }

    @Override
    public void FindView() {
        recyclerViewExame = findViewById(R.id.recyclerViewExame);
    }

    @Override
    public void Onclick() {

    }



    @Override
    public void onResume() {
        db.ListarBancoExame();
        //setLayoutManager para exibir o recyclerView
        recyclerViewExame.setLayoutManager(new LinearLayoutManager(context));
        recyclerViewExame.setAdapter(new Adapter_exame(context, db.ListarBancoExame(), clickListner()));


        super.onResume();
    }


    private Adapter_exame.cliqueCard clickListner() {
        return new Adapter_exame.cliqueCard() {
            @Override
            public void onLongClick(View view, final int indxx) {

            }

            @SuppressLint("RestrictedApi")
            @Override
            public void clickView(View view, final int index) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(context);

                alerta.setTitle("Atenção");
                alerta.setMessage("Deseja excluir essa exame ");
                alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        db.excluir(db.ListarBancoExame().get(index).getIdExame());

                        db.ListarBancoExame();
                        recyclerViewExame.setLayoutManager(new LinearLayoutManager(context));
                        recyclerViewExame.setAdapter(new Adapter_exame(context, db.ListarBancoExame(), clickListner()));
                    }
                });
                alerta.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                alerta.show();


            }




        };
    }
}
