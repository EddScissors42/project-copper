package com.copper.actions;

import com.copper.character.Personagem;

public class Defender implements Acao {

    @Override
    public String executar(Personagem jogador, Personagem inimigo){

        jogador.setDefesa(jogador.getDefesaBase() + 90);
        return "";
    }

    public void executar() {
    }
}
