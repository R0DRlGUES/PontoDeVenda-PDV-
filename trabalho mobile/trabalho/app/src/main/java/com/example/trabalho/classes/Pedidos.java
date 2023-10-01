package com.example.trabalho.classes;

public class Pedidos {
    private String nomeItem;
    private int quantidade;
    private Double valor;

    public Pedidos(){}

    public Double getValor() {return valor;}

    public void setValor(Double valor) {this.valor = valor;}

    public String getNomeItem() {return nomeItem;}

    public void setNomeItem(String nomeItem) {this.nomeItem = nomeItem;}

    public int getQuantidade() {return quantidade;}

    public void setQuantidade(int Quantidade) {quantidade = Quantidade;}
}
