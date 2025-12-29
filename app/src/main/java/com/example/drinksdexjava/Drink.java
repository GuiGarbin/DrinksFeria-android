package com.example.drinksdexjava;

public class Drink {
    private String nome;
    private String ingrediente;
    private String teorAlcoolico;
    // Vou assumir que a imagem é um ID drawable por enquanto (ex: R.drawable.drink1)
    private String imagem;

    // Construtor
    public Drink(String nome, String ingrediente, String teorAlcoolico, String imagemResId) {
        this.nome = nome;
        this.ingrediente = ingrediente;
        this.teorAlcoolico = teorAlcoolico;
        this.imagem = imagemResId;
    }

    // Getters (Para o Adapter conseguir ler os dados)
    public String getNome() { return nome; }
    public String getIngrediente() { return ingrediente; }
    public String getTeorAlcoolico() { return teorAlcoolico; }
    public String getImage() { return imagem; }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
