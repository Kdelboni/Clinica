package com.example.myapplication.conexao;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.activity.Home;
import com.example.myapplication.dao.ClienteDAO;
import com.example.myapplication.dao.ExameDAO;
import com.example.myapplication.model.Cliente;
import com.example.myapplication.model.Exame;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.Map;

public class ExameWs {
    public static void logar(final Context contexto, String url, final Map<String, String> params) {
        RequestQueue queue = Volley.newRequestQueue(contexto);
        JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, Conexao.getUrl() + url,
                new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                ExameDAO dbUsuario = new ExameDAO(contexto);

                Exame cadastro = new Gson().fromJson(response.toString(), Exame.class);

                if (cadastro != null) {
                    dbUsuario.deleTudo();
                    dbUsuario.inserir(cadastro);

                } else {
                    Toast.makeText(contexto, "erro", Toast.LENGTH_LONG).show();
                }
                try {
                    Intent intent = new Intent(contexto, Home.class);
                    contexto.startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("erro_login", error.getMessage());
                AlertDialog.Builder alerta = new AlertDialog.Builder(contexto);
                alerta.setTitle("Aviso!!");
                alerta.setMessage("Login ou Senha inválidos...");
                alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                alerta.show();
            }
        });
        postRequest.setRetryPolicy(new DefaultRetryPolicy(10000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(postRequest);
    }
}
