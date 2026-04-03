package com.copper.actions;

import com.copper.character.Personagem;
import java.util.Random;


public class Urssroullete implements Acao {

    private Random random = new Random();



    public String executar(Personagem jogador, Personagem inimigo){

        int n = random.nextInt(6);
        int Dano = 40;

        if (n == 2){
            inimigo.recebeuDano(Dano);
            return "A Bala saiu e atingiu " + inimigo.getNome();
        } else if (n == 5){
            jogador.recebeuDano(Dano);
            return "A bala saiu e atingiu você!";
        } else {
            return ".... mas nada aconteceu";
        }
    }


    public void executar() {
    }
}
