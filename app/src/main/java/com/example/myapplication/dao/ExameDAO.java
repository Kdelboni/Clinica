package com.example.myapplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.myapplication.model.Agenda;
import com.example.myapplication.model.Exame;

import java.util.ArrayList;
import java.util.List;

public class ExameDAO {
    private DataBase databaseHelper;
    private SQLiteDatabase sqLiteDatabase;


    public ExameDAO(Context context) {
        databaseHelper = new DataBase(context);
    }


    public SQLiteDatabase getDabase() {
        if (sqLiteDatabase == null) {
            sqLiteDatabase = databaseHelper.getWritableDatabase();
        }
        return sqLiteDatabase;
    }
    public long inserir(Exame cadastro) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("tipo_exame", cadastro.getTipoExame());
        contentValues.put("convenio_exame", cadastro.getConvenioExame());
        contentValues.put("medico_exame", cadastro.getMedicoExame());
        contentValues.put("data", cadastro.getData());


        Log.e("INSERT", "INSERT " );

        return getDabase().insert("exame", null, contentValues);
    }

    public long alterar(Exame agendamento){
        ContentValues values = new ContentValues();
        values.put("tipo_exame", agendamento.getTipoExame());
        values.put("convenio_exame", agendamento.getConvenioExame());
        values.put("medico_exame", agendamento.getMedicoExame() );
        values.put("data", agendamento.getData());
        Log.e("UPDATE", "UPDATE" );
        return getDabase().update
                ("exame", values, "_id_exame= ?",new String[]{String.valueOf(agendamento.getIdExame())});

    }

    public void excluir(int idAgenda){
        getDabase().delete("exame","_id_exame = ?", new String[]{String.valueOf(idAgenda)});
        Log.e("DELETE", "Delete");

    }

    public List<Exame> ListarBancoExame(){
        List<Exame> listarTodosOsElementos = new ArrayList<>();
        Cursor cursor = getDabase().rawQuery("SELECT * FROM exame ORDER BY _id_exame",null);
        while(cursor.moveToNext()){
            Exame listarCadastro = new Exame();
            listarCadastro.setIdExame(cursor.getInt(cursor.getColumnIndex("_id_exame")));
            listarCadastro.setTipoExame(cursor.getString(cursor.getColumnIndex("tipo_exame")));
            listarCadastro.setConvenioExame(cursor.getString(cursor.getColumnIndex("convenio_exame")));
            listarCadastro.setMedicoExame(cursor.getString(cursor.getColumnIndex("medico_exame")));
            listarCadastro.setData(cursor.getString(cursor.getColumnIndex("data")));
            listarTodosOsElementos.add(listarCadastro);
        }

        cursor.close();
        Log.e("listar", "foi "+ listarTodosOsElementos.size() + listarTodosOsElementos.toString());
        return listarTodosOsElementos;
    }


    public void deleTudo() {
        SQLiteDatabase db = getDabase();
        db.execSQL(String.format("DELETE FROM %s", "cliente"));
        db.execSQL("VACUUM");
    }
}
