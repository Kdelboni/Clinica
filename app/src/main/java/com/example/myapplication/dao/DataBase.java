package com.example.myapplication.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper {
    private static final int VERSAO_BANCO = 1;
    private static final String NOME_BANCO = "bancoClinica";


    public DataBase(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        FOREIGN KEY(id_) REFERENCES tabela(_id_da_tabela)
        createTable(sqLiteDatabase);
//        insertMedico(sqLiteDatabase);
    }


    public void createTable(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE cliente(_id_cliente INTEGER PRIMARY KEY, nome_cliente TEXT,  rg_cliente TEXT, endereco_cliente TEXT," +
                " telefone_cliente TEXT, email_cliente TEXT, login_cliente TEXT, senha_cliente TEXT)");

//        sqLiteDatabase.execSQL("CREATE TABLE agenda(_id_consulta INTEGER PRIMARY KEY,id_cliente_agenda INTEGER, exame_agenda TEXT, convenio_agenda TEXT, medico_agenda TEXT, data_agenda TEXT, status_agenda TEXT,"+
//                "FOREIGN KEY(id_cliente_agenda) REFERENCES cliente(_id_cliente))");

                sqLiteDatabase.execSQL("CREATE TABLE agenda(_id_consulta INTEGER PRIMARY KEY,exame_agenda TEXT, convenio_agenda TEXT, medico_agenda TEXT, data_agenda TEXT, status_agenda TEXT, Cliente TEXT)");


        sqLiteDatabase.execSQL("CREATE TABLE exame(_id_exame INTEGER PRIMARY KEY, tipo_exame TEXT, convenio_exame TEXT, medico_exame TEXT, data TEXT)");



    }

    public void insertMedico(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("INSERT INTO NOMEDATABELA (CAMPO1, CAMPO2, CAMPO3, CAMPO4) VALUES " + "(valor1, valor2, valor3, valor4)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }

}
