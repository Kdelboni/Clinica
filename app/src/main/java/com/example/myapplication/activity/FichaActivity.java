package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.dao.ClienteDAO;
import com.example.myapplication.interfaceHelp.InterfaceHelp;
import com.example.myapplication.model.Agenda;
import com.example.myapplication.model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class FichaActivity extends AppCompatActivity implements InterfaceHelp {
    private TextView ficha_nome, ficha_rg, ficha_email, ficha_telefone, ficha_endereco;
    private Context context = this;
    private List<Cliente>listaCliente = new ArrayList<>();
    ClienteDAO db = new ClienteDAO(context);
    private CardView btn_editar;
    private Cliente cliente;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ficha);
        db = new ClienteDAO(context);
        FindView();
        Onclick();


        listaCliente.addAll(db.listarBancoCliente());

        for (int i = 0 ; i<listaCliente.size();i++) {

            ficha_nome.setText(listaCliente.get(i).getNome_cliente());
            ficha_rg.setText(listaCliente.get(i).getRg_cliente());
            ficha_email.setText(listaCliente.get(i).getEmail_cliente());
            ficha_endereco.setText(listaCliente.get(i).getEndereco_cliente());
            ficha_telefone.setText(listaCliente.get(i).getTelefone_cliente());


        }
    }

    @Override
    public void FindView() {
        ficha_nome = findViewById(R.id.ficha_nome);
        ficha_rg = findViewById(R.id.ficha_rg);
        ficha_email = findViewById(R.id.ficha_email);
        ficha_telefone = findViewById(R.id.ficha_telefone);
        ficha_endereco = findViewById(R.id.ficha_endereco);
        btn_editar = findViewById(R.id.btn_editar);

    }

    @Override
    public void Onclick() {

        btn_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               finish();

            }
        });

    }



}
