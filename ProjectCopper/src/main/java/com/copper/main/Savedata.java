package com.copper.main;

import com.copper.character.Jogador;
import com.copper.db.Item;
import com.copper.db.PocaoDeCura;
import com.copper.exceptions.SaveException;

import java.io.*;
import java.util.ArrayList;

public class Savedata {

    public static void salvar(Jogador jogador, String arquivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo))) {

            writer.write(jogador.getNome() + "\n");
            writer.write(jogador.getVida() + "\n");

            ArrayList<String> nomesItens = new ArrayList<>();
            for (Item item : jogador.getInventario()) {
                nomesItens.add(item.getNomeDoItem());
            }
            writer.write(String.join(",", nomesItens) + "\n");

        } catch (IOException e) {
            throw new SaveException("Erro ao salvar o arquivo de texto: " + e.getMessage());
        }
    }

    public static Jogador carregar(String arquivo) {
        File arquivoSave = new File(arquivo);
        if (!arquivoSave.exists()) {
            return null;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {

            String nome = reader.readLine();
            int vida = Integer.parseInt(reader.readLine());

            Jogador j = new Jogador(nome);
            j.setVida(vida);

            // inventario
            String linhaItens = reader.readLine();

            if (linhaItens != null && !linhaItens.isEmpty()) {
                String[] itensArray = linhaItens.split(",");

                for (String nomeDoItem : itensArray) {
                    j.guardarItem(new PocaoDeCura(nomeDoItem, "Item recuperado", 30));
                }
            }
            return j;

        } catch (Exception e) {
            throw new SaveException("Não dá pra ler o arquivo! Talvez esteja corrompido?\nDetalhe: " + e.getMessage());
        }
    }
}