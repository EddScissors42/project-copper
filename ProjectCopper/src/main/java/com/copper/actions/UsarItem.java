package com.copper.actions;

import com.copper.character.Personagem;
import com.copper.db.Item;
import com.copper.exceptions.*;

public class UsarItem {
    public UsarItem(Personagem quemUsar, Item itemUsado, Personagem alvo){
        if(quemUsar.getInventario().contains(itemUsado)){
            itemUsado.usar(alvo);
            quemUsar.getInventario().remove(itemUsado);
            System.out.println("O " + quemUsar.getNome() + " gastou o item " +
                    itemUsado.getNomeDoItem() + ".");
        } else {
            throw new ItemIndisponivelException("Affs, algo deu errado: O " + quemUsar.getNome() + " não possui " + itemUsado.getNomeDoItem() + " no inventário!");
        }
    }
}
