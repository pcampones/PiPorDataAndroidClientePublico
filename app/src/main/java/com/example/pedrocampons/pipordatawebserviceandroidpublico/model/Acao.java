package com.example.pedrocampons.pipordatawebserviceandroidpublico.model;


public class Acao {

    private int ano;
    private double valor;



    public Acao(int ano, double valor)
    {
        this.ano = ano;
        this.valor = valor;
    }


    public String acoesCat(){
        return "Ano: " + ano + '\'' +
                ", Número de ações: '" + valor + '\'';
    }


    public String percAcoesCat(){
        return "Ano: " + ano + '\'' +
                ", Percentagem de ações: '" + valor + " %" + '\'';
    }




    public int getAno() {
        return ano;
    }

    public double getValor() {
        return valor;
    }



}
