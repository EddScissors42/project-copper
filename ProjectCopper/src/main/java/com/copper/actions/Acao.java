package com.copper.actions;

import com.copper.character.Personagem;

public interface Acao {
    String executar(Personagem jogador, Personagem inimigo);
}
