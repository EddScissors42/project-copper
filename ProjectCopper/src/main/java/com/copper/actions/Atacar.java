package com.copper.actions;

import com.copper.character.Personagem;
import java.util.Random;

public class Atacar implements Acao {

    private Random random = new Random();

    @Override
    public String executar(Personagem jogador, Personagem inimigo){

        int Dano = jogador.getDano() + random.nextInt(5);
        inimigo.recebeuDano(Dano);

        return Integer.toString(Dano);
    }

    public void executar() {
    }
}
