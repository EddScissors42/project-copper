package com.copper.db;

import com.copper.character.Personagem;
import java.io.Serializable;

public abstract class Item implements Serializable {
    private String nomeDoItem;
    private String descricao;

    public Item(String nome, String descricao) {
        this.nomeDoItem = nome;
        this.descricao = descricao;
    }

    public String getNomeDoItem() {
        return nomeDoItem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setNomeDoItem(String nomeDoItem) {this.nomeDoItem = nomeDoItem;}
    public void setDescricao(String descricao) {this.descricao = descricao;}

    public abstract void usar(Personagem alvo);
}
