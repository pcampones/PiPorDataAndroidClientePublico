package com.example.pedrocampons.pipordatawebserviceandroidpublico.model;

import java.io.Serializable;

public class Funcionario implements Serializable{

    private int ano;
    private double media;
    private double numFunc;
    private double percenPessoal;
    private double percenFun;


    public Funcionario(int ano,double media, double numFun,double percenPessoal, double percenFun)
    {

        this.ano = ano;
        this.media = media;
        this.numFunc = numFun;
        this.percenPessoal = percenPessoal;
        this.percenFun = percenFun;

    }




    public int getAno() {
        return ano;
    }

    public double getMedia() {
        return media;
    }

    public double getNumFunc() {
        return numFunc;
    }

    public double getPercenFun() {
        return percenFun;
    }

    public double getPercenPessoal() {
        return percenPessoal;
    }

    @Override
    public String toString() {
        String res= null;
        if(percenPessoal == 0 && numFunc ==0 && percenFun == 0){
            res= "Ano : " + ano +  "\nMédia : " + media + " €";
        }
        else if(percenPessoal == 0 && percenFun ==0 && media == 0)
        {
            res = "Ano : " + ano +
                    " \nNúmero de Funcionários : " + numFunc + " "  ;
        }else if(numFunc == 0 && percenFun ==0 && media == 0){
            res = "Ano : " + ano +
                    " \nPercentagem de Pessoal : " + percenPessoal + " %" ;
        }else if(numFunc == 0 && percenPessoal ==0 && media == 0){
            res = "Ano : " + ano +
                    " \nPercentagem de funcionários : " + percenFun + " %" ;
        }
            return  res;

    }
}
