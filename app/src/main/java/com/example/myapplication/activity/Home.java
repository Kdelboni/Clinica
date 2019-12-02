package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.dao.ClienteDAO;
import com.example.myapplication.interfaceHelp.InterfaceHelp;
import com.example.myapplication.model.Cliente;

public class Home extends AppCompatActivity implements InterfaceHelp {
    private TextView txt_usuario;
    private CardView btn_exame, btn_ficha, btn_exame_status, btn_sair, btn_consulta;
    private Context contexto = this;
    private ClienteDAO cliente;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        cliente = new ClienteDAO(contexto);
        FindView();
        Onclick();
        Intent intent = getIntent();
        String nomeUsuario = intent.getStringExtra("nomeUsuario");

        for (Cliente cliente : cliente.listarBancoCliente()) {
            txt_usuario.setText(cliente.getNome_cliente());
        }
    }

    @Override
    public void FindView() {
        txt_usuario = findViewById(R.id.txt_usuario);
        btn_consulta = findViewById(R.id.btn_consulta);
        btn_exame = findViewById(R.id.btn_exame);
        btn_ficha = findViewById(R.id.btn_ficha);
        btn_exame_status = findViewById(R.id.btn_exame_status);
        btn_sair = findViewById(R.id.btn_sair);
    }

    @Override
    public void Onclick() {
        btn_consulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(contexto, ConsultaActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

            }
        });
        btn_exame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(contexto, ExameActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);


            }
        });

        btn_sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });
        btn_ficha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(contexto, FichaActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        btn_exame_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(contexto, StatusExamesActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

            }
        });
    }
}
