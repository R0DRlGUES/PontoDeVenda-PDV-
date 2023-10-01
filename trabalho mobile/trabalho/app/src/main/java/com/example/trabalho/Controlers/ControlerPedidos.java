package com.example.trabalho.Controlers;

import com.example.trabalho.classes.Clientes;
import com.example.trabalho.classes.Pedidos;

import java.util.ArrayList;

public class ControlerPedidos {
    private static ControlerPedidos instanciaPedidos;
    private ArrayList<Pedidos> listaPedidos;

    public static ControlerPedidos getControlerPedidos(){
        if(instanciaPedidos == null){
            return instanciaPedidos = new ControlerPedidos();
        }else{
            return instanciaPedidos;
        }
    }
    private ControlerPedidos(){
        listaPedidos = new ArrayList<>();
    }
    public void salvarPedidos(Pedidos pedidos){
        listaPedidos.add(pedidos);
    }
    public ArrayList<Pedidos> retornarPedidos(){
        return listaPedidos;
    }
}
