package com.example.trabalho.Controlers;

import com.example.trabalho.classes.Itens;

import java.util.ArrayList;

public class ControlerItens {
    private static ControlerItens instanciaItem;
    private ArrayList<Itens> listaItens;

    public static ControlerItens getInstanciaItem(){
        if(instanciaItem == null){
            return instanciaItem = new ControlerItens();
        }else{
            return instanciaItem;
        }
    }
    private ControlerItens(){
        listaItens = new ArrayList<>();
    }
    public void salvarItens(Itens itens){
        listaItens.add(itens);
    }
    public ArrayList<Itens> retornarItens(){
        return listaItens;
    }
}
