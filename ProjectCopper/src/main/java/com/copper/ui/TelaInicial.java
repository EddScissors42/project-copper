package com.copper.ui;
import com.copper.character.Jogador;
import com.copper.main.*;

import javax.swing.*;

public class TelaInicial {
    public static void main(String[] args) {

        while (true) {

            String[] opcoes = {"Jogar", "Continuar", "Sair"};

            int escolha = JOptionPane.showOptionDialog(
                    null, "Project: Copper\num Mini Basico RPG Textual", "Menu inicial", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]
            );

            if (escolha == 0) {
                String nome = JOptionPane.showInputDialog(null, "Qual é o seu nome, entregador?");
                if (nome == null || nome.isEmpty()) nome = "Entregador";
                Jogador player = new Jogador(nome);
                new TelaMain(player).iniciar();
                break;
            } else if (escolha == 1) {
                String[] slots = {"Slot 1", "Slot 2", "Slot 3", "Voltar"};

                int slotEscolhido = JOptionPane.showOptionDialog(
                        null, "Qual jogo você deseja carregar?", "Carregar Jogo",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, slots, slots[0]
                );

                if (slotEscolhido >= 0 && slotEscolhido <= 2) {
                    String nomeArquivo = "save" + (slotEscolhido + 1) + ".txt";
                    Jogador jogadorCarregado = Savedata.carregar(nomeArquivo);

                    if (jogadorCarregado != null) {
                        JOptionPane.showMessageDialog(null, "Save carregado! Bem-vindo de volta, " + jogadorCarregado.getNome());
                        new TelaMain(jogadorCarregado).iniciar();
                        break; // sai do loop do menu inicial e entra MALDITO JOGO AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
                    } else { // ai ele n achou nada tlgd
                        JOptionPane.showMessageDialog(null, "Nenhum jogo salvo encontrado neste slot!");
                    }
                }

            } else {
                // Caso feche a janela (pq sempre tem algum espertinho)
                System.exit(0);
            }
        }
    }
}