package com.example.pedrocampons.pipordatawebserviceandroidpublico.model;

import java.io.Serializable;

public class Funcionario implements Serializable{

    private int ano;
    private int soma1;
    private double valor;


    public Funcionario(int ano, int soma1)
    {

        this.ano = ano;
        this.soma1 = soma1;

    }


    public Funcionario(int ano, double valor)
    {

        this.ano = ano;
        this.valor = valor;
    }

    public String mediaFunc(){
        return "Ano: " + ano + '\'' +
                ", Média: '" + valor + '\'';
    }

    public String funcionarios(){
        return "Ano: " + ano + '\'' +
                ", Número de funcionários: '" + soma1 + '\'';
    }

    public String percPessoal(){
        return "Ano: " + ano + '\'' +
                ", Percentagem de pessoal: '" + valor + " %" + '\'';
    }

    public String racioFuncionarios(){
        return "Ano: " + ano + '\'' +
                ", Percentagem de funcionários: '" + valor + " %" + '\'';
    }
















    public int getAno() {
        return ano;
    }

    public int getSoma1() {
        return soma1;
    }

    public double getValor() {
        return valor;
    }


}
