package com.example.pedrocampons.pipordatawebserviceandroidpublico.model;

/**
 * Created by Proprietario on 22-12-2015.
 */
public class Funcionario {

    private int ano;
    private int soma1;
    private int soma2;
    private int soma3;
    private double valor;


    public Funcionario(int ano, int soma1)
    {

        this.ano = ano;
        this.soma1 = soma1;
    }


    public Funcionario(int ano, int soma1, int soma2, int soma3)
    {

        this.ano = ano;
        this.soma1 = soma1;
        this.soma2 = soma2;
        this.soma3 = soma3;
    }


    public Funcionario(int ano, double valor)
    {

        this.ano = ano;
        this.valor = valor;
    }



    public int getAno() {
        return ano;
    }

    public int getSoma1() {
        return soma1;
    }


    public int getSoma2() {
        return soma2;
    }

    public int getSoma3() {
        return soma3;
    }


    public double getValor() {
        return valor;
    }

}
