package com.example.trabalho.Controlers;

import com.example.trabalho.classes.Clientes;

import java.util.ArrayList;

public class ControlerClientes {
    private static ControlerClientes instanciaCliente;
    private ArrayList<Clientes> listaClientes;

    public static ControlerClientes getInstanciaCliente(){
        if(instanciaCliente == null){
            return instanciaCliente = new ControlerClientes();
        }else{
            return instanciaCliente;
        }
    }
    private ControlerClientes(){
        listaClientes = new ArrayList<>();
    }
    public void salvarClientes(Clientes clientes){
        listaClientes.add(clientes);
    }
    public ArrayList<Clientes> retornarClientes(){
        return listaClientes;
    }
}

