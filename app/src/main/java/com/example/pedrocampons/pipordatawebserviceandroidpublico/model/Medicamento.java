package com.example.pedrocampons.pipordatawebserviceandroidpublico.model;

/**
 * Created by Proprietario on 22-12-2015.
 */
public class Medicamento {

    private int ano;
    private double valor;


    public Medicamento(int ano, double valor)
    {

        this.ano = ano;
        this.valor = valor;
    }

    public int getAno() {
        return ano;
    }

    public double getValor() {
        return valor;
    }
}
