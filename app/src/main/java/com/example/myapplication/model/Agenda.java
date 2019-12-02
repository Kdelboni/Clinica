package com.example.myapplication.model;

import java.io.Serializable;

public class Agenda implements Serializable {


    private int idAgenda;
    private String exame;
    private String convenio;
    private String medico;
    private String data;
    private String status;
    private String cliente;

    public Agenda() {
    }



    public Agenda(String exame, String convenio, String medico, String dataFinal, String cliente) {
        this.exame = exame;
        this.convenio = convenio;
        this.medico = medico;
        this.data = dataFinal;
        this.cliente = cliente;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getIdAgenda() {
        return idAgenda;
    }

    public void setIdAgenda(int idAgenda) {
        this.idAgenda = idAgenda;
    }

    public String getExame() {
        return exame;
    }

    public void setExame(String exame) {
        this.exame = exame;
    }

    public String getConvenio() {
        return convenio;
    }

    public void setConvenio(String convenio) {
        this.convenio = convenio;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
}
