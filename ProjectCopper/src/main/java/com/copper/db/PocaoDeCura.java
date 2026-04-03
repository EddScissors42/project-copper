package com.copper.db;

import com.copper.character.Personagem;

public class PocaoDeCura extends Item {

    private int quantidadeDeCura;
    private boolean curaTotal;

    public PocaoDeCura(String nome, String descricao, int quantidadeDeCura) {
        super(nome, descricao);
        this.quantidadeDeCura = quantidadeDeCura;
        this.curaTotal = false;
    }

    public PocaoDeCura(String nome, String descricao, boolean curaTotal) {
        super(nome, descricao);
        this.curaTotal = curaTotal;
    }

    public void usar(Personagem alvo) {
        if(curaTotal) {
            alvo.receberCura(99999999);
        } else {
            alvo.receberCura(quantidadeDeCura);
        }
    }
}
