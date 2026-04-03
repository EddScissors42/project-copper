package com.copper.main;

import java.util.Random;

public class Frases { // Frases
    private static String[] playerMorte = { // Frases de derrota
        "Infelizmente, você não tankou...",
        "Com pesar, informamos que você não conseguiu resistir....",
        "Você pereceu"
    };

    private static String[] turnosFrases = {
        "Você viu um buraco suspeito na parede...",
        "Tem alguma coisa se mexendo naquela caixa do canto...",
        "Tem uma sombra te observando ali da esquina..."
    };

//    private static String[] frasesCarteira = {
//            "Olha o banco de horas!",
//            "Uma escala 7x1 não lhe faria mal"
//    };


    private static Random random = new Random();

    public static String playerMorte() {
        int index = random.nextInt(playerMorte.length);
        return playerMorte[index];
    }

    public static String turnosFrases(){
        int index = random.nextInt(turnosFrases.length);
        return turnosFrases[index];
    }
}
