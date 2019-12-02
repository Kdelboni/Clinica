package com.example.myapplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.myapplication.model.Agenda;
import com.example.myapplication.model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class AgendamentoDAO {
    private DataBase databaseHelper;
    private SQLiteDatabase sqLiteDatabase;


    public AgendamentoDAO(Context context) {
        databaseHelper = new DataBase(context);
    }


    public SQLiteDatabase getDabase() {
        if (sqLiteDatabase == null) {
            sqLiteDatabase = databaseHelper.getWritableDatabase();
        }
        return sqLiteDatabase;
    }

    public long inserir(Agenda cadastro) {
        ContentValues contentValues = new ContentValues();

//        contentValues.put("id_cliente_agenda", String.valueOf(cadastro.getCliente()));
        contentValues.put("exame_agenda", cadastro.getExame());
        contentValues.put("convenio_agenda", cadastro.getConvenio());
        contentValues.put("medico_agenda", cadastro.getMedico());
        contentValues.put("data_agenda", cadastro.getData());
        contentValues.put("status_agenda", "P");
        contentValues.put("Cliente", cadastro.getCliente());



        Log.e("INSERT", "INSERT " );

        return getDabase().insert("agenda", null, contentValues);
    }

    public long alterar(Agenda agendamento){
        ContentValues values = new ContentValues();
        values.put("exame_agenda", agendamento.getExame());
        values.put("convenio_agenda", agendamento.getConvenio());
        values.put("medico_agenda", agendamento.getMedico() );
        values.put("data_agenda", agendamento.getData());
        values.put("status_agenda", "P");
        Log.e("UPDATE", "UPDATE" );
        return getDabase().update
                ("agenda", values, "_id_consulta= ?",new String[]{String.valueOf(agendamento.getIdAgenda())});

    }

    public void excluir(int idAgenda){
        getDabase().delete("agenda","_id_consulta = ?", new String[]{String.valueOf(idAgenda)});
        Log.e("DELETE", "Delete");

    }

    public List<Agenda> ListarBancoConsulta(){
        List<Agenda> listarTodosOsElementos = new ArrayList<>();
        Cursor cursor = getDabase().rawQuery("SELECT * FROM agenda con ORDER BY _id_consulta",null);
        while(cursor.moveToNext()){
            Agenda listarCadastro = new Agenda();
            listarCadastro.setIdAgenda(cursor.getInt(cursor.getColumnIndex("_id_consulta")));
            listarCadastro.setExame(cursor.getString(cursor.getColumnIndex("exame_agenda")));
            listarCadastro.setConvenio(cursor.getString(cursor.getColumnIndex("convenio_agenda")));
            listarCadastro.setMedico(cursor.getString(cursor.getColumnIndex("medico_agenda")));
            listarCadastro.setData(cursor.getString(cursor.getColumnIndex("data_agenda")));
            listarCadastro.setStatus(cursor.getString(cursor.getColumnIndex("status_agenda")));
            listarTodosOsElementos.add(listarCadastro);
        }

        cursor.close();
        Log.e("listar", "foi "+ listarTodosOsElementos.size() + listarTodosOsElementos.toString());
        return listarTodosOsElementos;
    }

//    public List<Agenda> ListarBancoConsulta2(int idCliente){
//        String[] args = {String.valueOf(idCliente)};
//        List<Agenda> listarTodosOsElementos = new ArrayList<>();
//        Cursor cursor = getDabase().rawQuery("SELECT con.idAgenda ,con.id_cliente_agenda , con.exame , con.convenio , con.medico , con.data , con.status, cli.id_cliente, " +
//                " cli.nome_cliente , cli.rg_cliente , cli.endereco_cliente , cli.telefone_cliente , cli.email_cliente , cli.login_cliente , cli.senha_cliente" +
//                " FROM agenda con" +
//                " INNER JOIN cliente cli ON con.id_cliente_agenda = cli.id_cliente WHERE id_cliente_agenda = ? " +
//                "ORDER BY _id_consulta",args);
//        while(cursor.moveToNext()){
//            Agenda listarCadastro = new Agenda();
//            Cliente listarCliente = new Cliente();
//
//            //lista Cliente
//            Cliente cliente = new Cliente();
//
//            cliente.setId_cliente(cursor.getInt(cursor.getColumnIndex("_id_cliente")));
//            cliente.setNome_cliente((cursor.getString(cursor.getColumnIndex("nome_cliente"))));
//            cliente.setRg_cliente(cursor.getString(cursor.getColumnIndex("rg_cliente")));
//            cliente.setEndereco_cliente(cursor.getString(cursor.getColumnIndex("endereco_cliente")));
//            cliente.setTelefone_cliente(cursor.getString(cursor.getColumnIndex("telefone_cliente")));
//            cliente.setEmail_cliente(cursor.getString(cursor.getColumnIndex("email_cliente")));
//            cliente.setLogin_cliente(cursor.getString(cursor.getColumnIndex("login_cliente")));
//            cliente.setSenha_cliente(cursor.getString(cursor.getColumnIndex("senha_cliente")));
//
//
//            //lista Agenda
//            listarCadastro.setIdAgenda(cursor.getInt(cursor.getColumnIndex("_id_consulta")));
//            listarCadastro.setCliente(listarCliente);
//            listarCadastro.setExame(cursor.getString(cursor.getColumnIndex("exame_agenda")));
//            listarCadastro.setConvenio(cursor.getString(cursor.getColumnIndex("convenio_agenda")));
//            listarCadastro.setMedico(cursor.getString(cursor.getColumnIndex("medico_agenda")));
//            listarCadastro.setData(cursor.getString(cursor.getColumnIndex("data_agenda")));
//            listarCadastro.setStatus(cursor.getString(cursor.getColumnIndex("status_agenda")));
//            listarTodosOsElementos.add(listarCadastro);
//        }
//
//        cursor.close();
//        Log.e("listar", "foi "+ listarTodosOsElementos.size() + listarTodosOsElementos.toString());
//        return listarTodosOsElementos;
//    }



    public void deleTudo() {
        SQLiteDatabase db = getDabase();
        db.execSQL(String.format("DELETE FROM %s", "cliente"));
        db.execSQL("VACUUM");
    }



}
