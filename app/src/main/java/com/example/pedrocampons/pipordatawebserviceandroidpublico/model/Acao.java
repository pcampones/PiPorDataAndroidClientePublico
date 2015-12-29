package com.example.pedrocampons.pipordatawebserviceandroidpublico.model;


public class Acao {

    private int ano;
    private double valor;
    private int soma;



    public Acao(int ano, double valor)
    {
        this.ano = ano;
        this.valor = valor;
    }

    public Acao(int ano, int soma)
    {
        this.ano = ano;
        this.soma = soma;
    }


    public String acoesCat(){
        return "Ano: " + ano + '\'' +
                ", Número de ações: '" + soma + '\'';
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

    public double getSoma() {
        return soma;
    }



}
