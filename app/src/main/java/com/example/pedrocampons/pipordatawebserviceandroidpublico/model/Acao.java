package com.example.pedrocampons.pipordatawebserviceandroidpublico.model;


public class Acao {

    private int ano;
    private double valor;
    private double soma;



    public Acao(int ano, double valor, double soma)
    {
        this.ano = ano;
        this.valor = valor;
        this.soma = soma;
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

    @Override
    public String toString() {

        String res = null;
        if(valor == 0){
            res = "Ano : " + ano + "\nNúmero de Ações : " + soma ;
        }else {
            res = "Ano : " + ano + "\nPercentagem de Ações : " + valor + " %";
        }

        return  res;
    }
}
