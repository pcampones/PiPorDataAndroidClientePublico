package com.example.pedrocampons.pipordatawebserviceandroidpublico.model;

/**
 * Created by Proprietario on 29-12-2015.
 */
public class Cama {


    private int ano;
    private double valor;


    public Cama(int ano, double valor)
    {

        this.ano = ano;
        this.valor = valor;

    }


    public String mediaCamas(){
        return "Ano: " + ano + '\'' +
                ", Média: '" + valor + '\'';
    }


    public int getAno() {
        return ano;
    }

    public double getValor() {
        return valor;
    }


}
