package com.copper.ui;

import javax.swing.*;
import com.copper.character.Inimigo;
import com.copper.character.Jogador;
import com.copper.db.Inimigos;
import com.copper.db.PocaoDeCura;
import com.copper.exceptions.SaveException;
import com.copper.main.*;
import com.copper.ui.TelaAtacar;

import java.util.Random;

import static com.copper.main.Frases.turnosFrases;

public class TelaMain {

    private Jogador player;
    private Random random = new Random();
    private int totalTurnos = 6;
    public int sorteio = random.nextInt(3);

    public TelaMain(Jogador player) {
        this.player = player;
    }


    public void iniciar(){

        for (int turno = 1; turno <= totalTurnos; turno++) {

            int vidaAntes = player.getVida(); // vai ficar salvando os dados de vida mais recente do jogador para usar depois em algo ai




            String[] opcoes = {"Olhar", "Atacar", "Evitar", "(Salvar Jogo)"};
            String[] eventos = {"BATALHA", "ITEM", "ITEM", "SHAME", "SHAME", "ITEM", "BATALHA", "BATALHA", "BATALHA"};
            String evento = eventos[random.nextInt(eventos.length)]; // vai sortear um evento aleatorio lol

            //Batalha inicializa combate, Item dá um item, Shame dá dano no jogador

            if (turno == totalTurnos) { // INVOCANDO O GIGANTE, O BOSS!!!!!!!!!!!
                JOptionPane.showMessageDialog(null,
                        "Turno " + turno + " — Algo grande está vindo...\nCom medo, você saca sua espada e seu revolver...");
                executarBoss();
                if (player.stillStanding()) {
                    JOptionPane.showMessageDialog(null, "Parabéns, " + player.getNome() + "! Você derrotou a CLT e finalizou a entrega!");
                }
                return;
            }

            int escolha = JOptionPane.showOptionDialog(
                    null, turnosFrases() + "\nO que fazer?", "Exploração, Turno Atual: " + turno, JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]
            );

            if (escolha == 0 || escolha == 1) { // OLHAR
                switch (evento) {
                    case "BATALHA":
                        Inimigo inimigoSorteado = Inimigos.aleatorio().clone();
                        new TelaAtacar(player, inimigoSorteado);
                        if (!player.stillStanding()){
                            JOptionPane.showMessageDialog(null, "Você foi derrotado...");
                        }
                        break;
                    case "ITEM":
                        PocaoDeCura pocao = new PocaoDeCura("Vinho Pequeno", "Restaura 45 de vida", 45);
                        player.guardarItem(pocao);
                        JOptionPane.showMessageDialog(null, "Após fuçar demais, você encontrou: " + pocao.getNomeDoItem());
                        break;
                    case "SHAME":
                        int danoSofrido = random.nextInt(15) + 5; // Dano entre 5 e 19
                        player.recebeuDano(danoSofrido);
                        JOptionPane.showMessageDialog(null, "Você levou um tiro e a bala atravessou.\nVocê sofreu " + danoSofrido + " de dano.\nVida atual: " + player.getVida());
                        if (!player.stillStanding()) {
                            JOptionPane.showMessageDialog(null, "As feridas não cicatrizaram... Infelizmente você veio a obito");
                            return;
                        }
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "[ERRO] Evento sorteado não reconhecido: '" + evento + "'");
                        break;
                }
            } else if (escolha == 2) {
                JOptionPane.showMessageDialog(null, "Você como sabio ou tolo, escolheu fugir...");

            } else if (escolha == 3) {
                String[] slots = {"Slot 1", "Slot 2", "Slot 3", "Cancelar"};

                int slotEscolhido = JOptionPane.showOptionDialog(
                        null, "Escolha um slot para chumbar seu jogo! \n(Por que eu disse isso?):", "Salvar Jogo",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, slots, slots[0]
                );

                if (slotEscolhido >= 0 && slotEscolhido <= 2) {
                    // aqui ele vai criar um arquivo dinamico tipo save1.dat, save2.dat
                    String nomeArquivo = "save" + (slotEscolhido + 1) + ".txt";

                    try {
                        Savedata.salvar(player, nomeArquivo);
                        JOptionPane.showMessageDialog(null, "Jogo salvo com sucesso no " + slots[slotEscolhido] + "!");
                    } catch (SaveException e) { //TE PEGUEI ERRO MALDITO!!!!!!!!!!!!!!!!!1
                        JOptionPane.showMessageDialog(null, e.getMessage(), "Erro de Save\n", JOptionPane.ERROR_MESSAGE);
                    }

                }

                // o turno é subtraído independente de ele ter salvo ou cancelado,
                // para garantir q ele n perca um turno por ter saido
                turno--;
            } else {
                // Caso feche a janela (pq sempre tem algum espertinho), ele vai fechar o jogo
                System.exit(0);
            }
        }
    }

    private void executarBoss() {
        Inimigo boss = Inimigos.buscar("Carteira de trabalho");
        if (boss != null) {
            new TelaAtacar(player, boss);
        }
    }

}