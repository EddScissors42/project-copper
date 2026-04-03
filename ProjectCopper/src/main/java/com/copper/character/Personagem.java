package com.copper.character;

import com.copper.db.Item;

import java.util.ArrayList;
import java.util.Random;
import java.io.Serializable;

public class Personagem implements Serializable {

    private String nome;
    private int vida;
    private int dano;
    private int defesa;
    private int vidaMax;
    private String[] frasesDeBatalha;
    private Random random = new Random();
    private ArrayList<Item> inventario = new ArrayList<>();

    private int defesaBase; // correção da defesa, guardando defesa originale

    public void setDefesa(int defesa) { this.defesa = defesa; }
    public int getDefesaBase() { return defesaBase; }

    public void setFrasesDeBatalha(String[] frases) { // isso também é pra frases, meu lindo setter de frases pra usar no TelaAtacar
        this.frasesDeBatalha = frases;
    }

    public String falarAlgo() { // isso aqui é pra frases
        if (frasesDeBatalha == null || frasesDeBatalha.length == 0) {
            return "..."; // caso não haja falas para serem usadas, inimigos silenciosos
        }
        return frasesDeBatalha[random.nextInt(frasesDeBatalha.length)]; //aleatorizando as frases
    }

    public Personagem(String nome, int vida, int dano, int defesa) {
        this.nome = nome;
        this.vida = vida;
        this.dano = dano;
        this.defesaBase = defesa;
        this.vidaMax = vida;
    }

    public void recebeuDano(int dano){ //Ao receber dano
        double danoReduzido = dano  * (1 - (defesa / 100.0)); // calculo para diminuir o dano de acordo com a defesa
        vida -= (int) danoReduzido;

        if (vida < 0){
            vida = 0;
        }
//        System.out.println("[DEBUG] Dano reduzido: " + danoReduzido);
//        System.out.println("[DEBUG] Dano original: " + dano);
    }

    public void receberCura(int cura) {
        this.vida += cura;
        if (this.vida >= this.vidaMax){
            this.vida = this.vidaMax;
        }
        System.out.println("Personagem curado e agora possui: " + this.vida + " pontos de Vida!");
    }

    public void guardarItem(Item item) {
        this.inventario.add(item);
    }

    public void removerItem(Item item) {
        this.inventario.remove(item);
    }

    public boolean stillStanding(){ //Sim, esse ''sillStanding'' é uma referência a música do Elton John
        return vida > 0; // esse bloco serve como condicional da batalha, para saber quem é que sobreviveu ou não
    }

    public String getNome() {
        return nome;
    }
    public int getVida() {
        return vida;
    }
    public int getDano() { return dano; }
    public int getDefesa() { return defesa; }
    public int getvidaMax() { return vidaMax; }
    public ArrayList<Item> getInventario(){
        return this.inventario;
    }
    public String[] getFrasesDeBatalha() {
        return this.frasesDeBatalha;
    }
    public void setInventario(ArrayList<Item> inventario) {
        this.inventario = inventario;
    }

    public void setVida(int vida) { this.vida = vida; }


}
