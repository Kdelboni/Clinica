package com.example.myapplication.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.dao.ClienteDAO;
import com.example.myapplication.interfaceHelp.InterfaceHelp;
import com.example.myapplication.interfaceHelp.MaskEditUtil;
import com.example.myapplication.model.Cliente;

public class CadastroClienteActivity extends AppCompatActivity implements InterfaceHelp {
    private EditText nome_cliente, rg_cliente, endereco_cliente, telefone_cliente, email_cliente, login_cliente, senha_cliente;
    private CardView btn_cadastrarUsuario;
    private Context contexto = this;
    private ClienteDAO db;
    private Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);

        db = new ClienteDAO(contexto);

        FindView();
        Onclick();
    }

    @Override
    public void FindView() {
        nome_cliente = findViewById(R.id.nome_cliente);
        rg_cliente = findViewById(R.id.rg_cliente);
        endereco_cliente = findViewById(R.id.endereco_cliente);
        telefone_cliente = findViewById(R.id.telefone_cliente);
        email_cliente = findViewById(R.id.email_cliente);
        login_cliente = findViewById(R.id.login_cliente);
        senha_cliente = findViewById(R.id.senha_cliente);
        btn_cadastrarUsuario = findViewById(R.id.btn_cadastrarUsuario);
        handler = new Handler();

        rg_cliente.addTextChangedListener(MaskEditUtil.mask(rg_cliente, MaskEditUtil.FORMAT_RG));
        telefone_cliente.addTextChangedListener(MaskEditUtil.mask(telefone_cliente, MaskEditUtil.FORMAT_FONE));


    }

    @Override
    public void Onclick() {
        btn_cadastrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nome = nome_cliente.getText().toString();
                String rg = rg_cliente.getText().toString();
                String endereco = endereco_cliente.getText().toString();
                String telefone = telefone_cliente.getText().toString();
                String email = email_cliente.getText().toString();
                String login = login_cliente.getText().toString();
                String senha = senha_cliente.getText().toString();
                Boolean achou = false;
                Boolean validar = nome.isEmpty() || rg.isEmpty() || endereco.isEmpty() || telefone.isEmpty() || email.isEmpty() || login.isEmpty() || senha.isEmpty();
                if (validar) {
                    Toast.makeText(contexto, "Preencha todos os campos", Toast.LENGTH_LONG).show();
                } else {
                    for (int i = 0; i < db.listarBancoCliente().size(); i++) {
                        if (login.equals(db.listarBancoCliente().get(i).getLogin_cliente())) {
                            achou = true;

                        }
                    }
                    if (achou) {
                        alertaUsuario();
                    } else {
                        db.inserir(new Cliente(nome,rg,endereco,telefone,email,login,senha));
                        Toast.makeText(contexto, "Usuario Adicionado", Toast.LENGTH_LONG).show();

                        handler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    finish();
                                                    startActivity(new Intent(contexto, LoginActivity.class));
                                                }
                                            }, 1000
                        );
                    }

                }


            }
        });

    }

    public void alertaUsuario() {
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Informação");
        alerta.setIcon(R.drawable.ic_addcliente24dp);
        alerta.setMessage("Esse usuario ja se encontra cadastrado no sistema, volte e tente fazer Login Novamente ou cadastre um novo usuario");
        alerta.setPositiveButton("Login", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(contexto, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        alerta.setNegativeButton("Novo Cadastro", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });


        alerta.show();

    }
}
