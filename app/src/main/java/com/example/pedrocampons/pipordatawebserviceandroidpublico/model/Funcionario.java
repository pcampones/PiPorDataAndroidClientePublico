package com.example.pedrocampons.pipordatawebserviceandroidpublico.model;

import java.io.Serializable;

public class Funcionario implements Serializable{

    private int ano;
    private double soma1;
    private double valor;


    public Funcionario(int ano, double soma1,double valor)
    {

        this.ano = ano;
        this.soma1 = soma1;
        this.valor = valor;

    }


    /*public Funcionario(int ano, double valor)
    {

        this.ano = ano;
        this.valor = valor;
    }*/
   /* public String mediaFunc(){
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
    }*/

    public int getAno() {
        return ano;
    }

    public double getSoma1() {
        return soma1;
    }

    public double getValor() {
        return valor;
    }

    @Override
    public String toString() {
        String res= null;
        if(valor == 0){
            res= "Ano : " + ano +  "\nResultado :" + soma1;
        }
        else
        {
            res = "Ano : " + ano + " \nPercentagem : " + valor + "%" ;
        }
            return  res;

    }
}
