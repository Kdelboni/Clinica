 package com.example.myapplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.myapplication.model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    private DataBase databaseHelper;
    private SQLiteDatabase sqLiteDatabase;

    public ClienteDAO(Context contexto) {
        databaseHelper = new DataBase(contexto);
    }

    public SQLiteDatabase getDabase() {
        if (sqLiteDatabase == null) {
            sqLiteDatabase = databaseHelper.getWritableDatabase();
        }
        return sqLiteDatabase;
    }


    public long inserir(Cliente cliente) {
        ContentValues contentValues = new ContentValues();

        contentValues.put("nome_cliente", cliente.getNome_cliente());
        contentValues.put("rg_cliente", cliente.getRg_cliente());
        contentValues.put("endereco_cliente", cliente.getEndereco_cliente());
        contentValues.put("telefone_cliente", cliente.getTelefone_cliente());
        contentValues.put("email_cliente", cliente.getEmail_cliente());
        contentValues.put("login_cliente", cliente.getLogin_cliente());
        contentValues.put("senha_cliente", cliente.getSenha_cliente());
        Log.e("insert","inserido");


        return getDabase().insert("cliente", null, contentValues);
    }


    public List<Cliente> listarBancoCliente() {
        List<Cliente> listarTodosCliente = new ArrayList<>();
        Cursor cursor = getDabase().rawQuery("SELECT * FROM cliente order by _id_cliente", null);
        while (cursor.moveToNext()) {
            Cliente cliente = new Cliente();

            cliente.setId_cliente(cursor.getInt(cursor.getColumnIndex("_id_cliente")));
            cliente.setNome_cliente((cursor.getString(cursor.getColumnIndex("nome_cliente"))));
            cliente.setRg_cliente(cursor.getString(cursor.getColumnIndex("rg_cliente")));
            cliente.setEndereco_cliente(cursor.getString(cursor.getColumnIndex("endereco_cliente")));
            cliente.setTelefone_cliente(cursor.getString(cursor.getColumnIndex("telefone_cliente")));
            cliente.setEmail_cliente(cursor.getString(cursor.getColumnIndex("email_cliente")));
            cliente.setLogin_cliente(cursor.getString(cursor.getColumnIndex("login_cliente")));
            cliente.setSenha_cliente(cursor.getString(cursor.getColumnIndex("senha_cliente")));


            listarTodosCliente.add(cliente);
        }
        cursor.close();
        return listarTodosCliente;
    }

    public void deleTudo() {
        SQLiteDatabase db = getDabase();
        db.execSQL(String.format("DELETE FROM %s", "cliente"));
        db.execSQL("VACUUM");
    }

//    public void excluir(String nome){
//        getDabase().delete("cadproduto","nome = ?", new String[]{nome});
//    }

//    public long alterar(CadProduto usuario){
//        ContentValues values = new ContentValues();
//        values.put("nome", usuario.getNome());
//        values.put("preco", usuario.getPreco());
//        values.put("marca", usuario.getMarca());
//        values.put("quantidade",usuario.getQuantidade());
//        return getDabase().update
//                ("cadproduto", values, "_id= ?",new String[]{String.valueOf(usuario.getId())});
//    }





}
