package com.example.pedrocampons.pipordatawebserviceandroidpublico.model;


public class Acao {

    private int ano;
    private double soma1;
    private double soma2;
    private double soma3;



    public Acao(int ano, double soma1)
    {
        this.ano = ano;
        this.soma1 = soma1;
    }


    public Acao(int ano, double soma1, double soma2, double soma3)
    {

        this.ano = ano;
        this.soma1 = soma1;
        this.soma2 = soma2;
        this.soma3 = soma3;

    }

    public int getAno() {
        return ano;
    }
    public double getSoma1() {
        return soma1;
    }

    public double getSoma2() {
        return soma2;
    }

    public double getSoma3() {
        return soma3;
    }

}
