package com.example.pedrocampons.pipordatawebserviceandroidpublico.model;


public class Cama {


    private int ano;
    private double valor;


    public Cama(int ano, double valor)
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


    @Override
    public String toString() {
        return
                "Ano : " + ano +
                "\nMédia : " + valor ;
    }



}
