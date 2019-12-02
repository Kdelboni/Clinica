package com.example.myapplication.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.conexao.LoginWs;
import com.example.myapplication.dao.ClienteDAO;
import com.example.myapplication.interfaceHelp.InterfaceHelp;
import com.example.myapplication.model.Cliente;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements InterfaceHelp {
    private EditText edt_login, edt_senha;
    private CardView btn_logar;
    private Context contexto = this;
    private ProgressBar progressBar;
    private TextView cadastrarUsuario;
    private Cliente clienteExtra;
    ClienteDAO db = new ClienteDAO(contexto);
    private Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FindView();
        Onclick();
    }

    @Override
    public void FindView() {
        edt_login = findViewById(R.id.edt_login);
        edt_senha = findViewById(R.id.edt_senha);
        btn_logar = findViewById(R.id.btn_logar);
        progressBar = findViewById(R.id.progressBar);
        cadastrarUsuario = findViewById(R.id.cadastrarUsuario);
        handler = new Handler();

    }

    @Override
    public void Onclick() {

        // para Teste
//        btn_logar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(contexto, Home.class);
//                startActivity(intent);
//            }
//        });


        btn_logar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String login = edt_login.getText().toString();
                String senha = edt_senha.getText().toString();
                int achou = 0;
                if (db.listarBancoCliente().isEmpty()) {
                    alertaNaoTemUsuario();
                } else {
                    if (login.isEmpty() || senha.isEmpty()) {
                        Toast.makeText(contexto, "Preencha todos os campos", Toast.LENGTH_LONG).show();
                    } else {
                        for ( int i = 0; i < db.listarBancoCliente().size(); i++) {

                            if (login.equals(db.listarBancoCliente().get(i).getLogin_cliente()) && senha.equals(db.listarBancoCliente().get(i).getSenha_cliente())) {
                                achou = 1;
                            }
                            if (achou == 1) {
                                progressBar.setVisibility(View.VISIBLE);
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        progressBar.setVisibility(View.GONE);
                                        Intent intent = new Intent(contexto, Home.class);
                                        login(edt_login.getText().toString(),edt_senha.getText().toString());
                                        startActivity(intent);
                                        clean();
                                    }
                                }, 2000);
                            } else {
                                achou = 2;
                            }
                        }
                        if (achou >= 2) {
                            alertaIncorreta();
                        }

                    }
                }
            }
        });

        cadastrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(contexto, CadastroClienteActivity.class);
                intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP | intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

    }

    public void clean() {
        edt_login.setText("");
        edt_senha.setText("");
    }

    public void alertaIncorreta() {
        AlertDialog.Builder alerta = new AlertDialog.Builder(contexto);
        alerta.setView(R.layout.alertasenhaincorreta);

        alerta.setPositiveButton("Cadastrar usario", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(contexto, CadastroClienteActivity.class);
                startActivity(intent);
            }
        });
        alerta.setNegativeButton("Tentar novamente", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                clean();

            }
        });
        alerta.show();

    }

    public void alertaNaoTemUsuario() {

        AlertDialog.Builder alertaNaoTemUsuario = new AlertDialog.Builder(contexto);
        alertaNaoTemUsuario.setView(R.layout.alertanaotemusuario);
        alertaNaoTemUsuario.setPositiveButton("Cadastrar usuario", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(contexto, CadastroClienteActivity.class);
                startActivity(intent);
            }
        });
        alertaNaoTemUsuario.show();
    }

    private void login(String login, String senha) {
        Map<String, String> map = new HashMap<>();
        map.put("login", login);
        map.put("senha", senha);
        LoginWs.logar(contexto,"usuario/login", map);
        Log.e("chamou", "chamou o metodo" + "map" + map);
    }

}
