package com.example.trabalho.classes;

public class Itens {

    private String nome;
    private int Codigo;
    private Double Valor;
    private String Descricao;
    private int Quantidade;
    private Double valorTotal;
    public Itens(){}

    public int getQuantidade() {return Quantidade;}

    public void setQuantidade(int Quantidade) {this.Quantidade = Quantidade;}

    public String getNome() {return nome; }

    public void setNome(String nome) {this.nome = nome;}

    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int codigo) {
        Codigo = codigo;
    }

    public Double getValor() {
        return Valor;
    }

    public void setValor(Double valor) {
        Valor = valor;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    public Double getValorTotal() {return valorTotal;}

    public void setValorTotal(Double valorTotal) { this.valorTotal = valorTotal;}

}
