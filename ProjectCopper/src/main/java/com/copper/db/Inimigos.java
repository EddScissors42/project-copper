package com.copper.db;

import com.copper.character.Inimigo;

import java.util.ArrayList;
import java.util.Random;

public class Inimigos {

    private static ArrayList<Inimigo> listaInimigos = new ArrayList<>();
    private static ArrayList<Inimigo> listaBosses = new ArrayList<>();

    private static Random random = new Random();

    static {
        String[] frasesDoBuraco = {
                "crick crack, eu sou o buraco",
                "*Silêncio profundo*",
                "Nothing ever Happens - Disse buraco na pista frustrado com nossa politica"
        };

        Inimigo buraco = new Inimigo("Buraco na Pista", 80, 10, 5);
        buraco.setFrasesDeBatalha(frasesDoBuraco);
        listaInimigos.add(buraco);

        String[] frasesBalaio = {
                "Pop Corn and ice cream sellers, sentenced for coup d'etat in brazil",
                "cof cof"
        };

        Inimigo balaio = new Inimigo("Balaio", 30, 15, 20);
        balaio.setFrasesDeBatalha(frasesBalaio);
        listaInimigos.add(balaio);

        String[] frasesCobra = {
                "Eu comia calango",
                "Cata o calanguinho!"
        };

        Inimigo cobra = new Inimigo("Cobra", 40, 20, 0);
        cobra.setFrasesDeBatalha(frasesCobra);
        listaInimigos.add(cobra);

        String[] frasesCao = {
                "Grrr.... AU AU!",
                "*Rosna enquanto te xinga brutalmente*",
                "AU AU AUUUUU! - O cão disse louvado seja, pois ele é cristão"
        };

        Inimigo cao = new Inimigo("Cão raivoso", 50, 5, 5);
        cao.setFrasesDeBatalha(frasesCao);
        listaInimigos.add(cao);
    }

    static {
        String[] frasesCarteira = {
                "Sempre consulte seu banco de horas!",
                "Depois passa no RH pra gente bater um papo, tá?",
                "Pode ficar até mais tarde hoje?",
                "A call terminando com você assumindo muitas responsabilidades",
                "Desista, persista!"
        };
        Inimigo Carteira = new Inimigo("Carteira de trabalho", 30, 15, 10);
        Carteira.setFrasesDeBatalha(frasesCarteira);
        listaBosses.add(Carteira); // boss
    }

    // retorna um inimigo aleatório da lista ooooooooow, os inimigos criativos
    public static Inimigo aleatorio() {
        return listaInimigos.get(random.nextInt(listaInimigos.size()));
    }

    // essa aqui vai servir pra buscar os bosses, melhor do que fazer algo especifico pra bosses

    public static Inimigo buscar(String nome) {
        for (Inimigo i : listaBosses) {
            if (i.getNome().equals(nome)) {
                return i;
            }
        }
        return null; // não encontrou
    }
}