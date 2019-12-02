package com.example.myapplication.model;

import java.io.Serializable;

public class Cliente implements Serializable {
    private int id_cliente;
    private String nome_cliente;
    private String rg_cliente;
    private String endereco_cliente;
    private String telefone_cliente;
    private String email_cliente;
    private String login_cliente;
    private String senha_cliente;

    public Cliente() {
    }

    public Cliente(String nome_cliente, String rg_cliente, String endereco_cliente, String telefone_cliente, String email_cliente, String login_cliente, String senha_cliente) {
        this.nome_cliente = nome_cliente;
        this.rg_cliente = rg_cliente;
        this.endereco_cliente = endereco_cliente;
        this.telefone_cliente = telefone_cliente;
        this.email_cliente = email_cliente;
        this.login_cliente = login_cliente;
        this.senha_cliente = senha_cliente;
    }

    public String getLogin_cliente() {
        return login_cliente;
    }

    public void setLogin_cliente(String login_cliente) {
        this.login_cliente = login_cliente;
    }

    public String getSenha_cliente() {
        return senha_cliente;
    }

    public void setSenha_cliente(String senha_cliente) {
        this.senha_cliente = senha_cliente;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNome_cliente() {
        return nome_cliente;
    }

    public void setNome_cliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
    }

    public String getRg_cliente() {
        return rg_cliente;
    }

    public void setRg_cliente(String rg_cliente) {
        this.rg_cliente = rg_cliente;
    }

    public String getEndereco_cliente() {
        return endereco_cliente;
    }

    public void setEndereco_cliente(String endereco_cliente) {
        this.endereco_cliente = endereco_cliente;
    }

    public String getTelefone_cliente() {
        return telefone_cliente;
    }

    public void setTelefone_cliente(String telefone_cliente) {
        this.telefone_cliente = telefone_cliente;
    }

    public String getEmail_cliente() {
        return email_cliente;
    }

    public void setEmail_cliente(String email_cliente) {
        this.email_cliente = email_cliente;
    }
}
