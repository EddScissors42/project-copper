package com.copper.character;
import com.copper.actions.Atacar;
import java.util.Random;

public class Inimigo extends Personagem {

    private Random random = new Random();
    private Atacar acaoAtacar = new Atacar();

    public Inimigo(String nome, int vida, int dano, int defesa){
        super(nome, vida, dano, defesa);
    }

    public Inimigo clone() { // ELe vai clonar o inimigo, sem alterar os dados originais dele, isso é pra um lance ai, meio q evitar merda
        Inimigo cloneInimigo = new Inimigo (this.getNome(), this.getvidaMax(), this.getDano(), this.getDefesaBase());
        cloneInimigo.setFrasesDeBatalha(this.getFrasesDeBatalha());
        return cloneInimigo;
    }

    public String agir(Personagem jogador) {
        this.setDefesa(this.getDefesaBase()); // vai resetar a defesa caso ele ja tenha defendido antes

        int chance = random.nextInt(100);

        if (chance < 60) {
            // 60% de chance do inimgio atacar!
            String dano = acaoAtacar.executar(this, jogador);
            return this.getNome() + " atacou você causando " + dano + " de dano!";

        } else if (chance < 85) {
            // 25% de chance dele realizar o inverso de atacar
            this.setDefesa(this.getDefesaBase() + 50);
            return this.getNome() + " se encurvou e colocou os braços na frente se defendendo";

        } else {
            // 15% de chance dele se curar
            int cura = 20; // Ele cura 20 de vida, eu acho q fica balanceado
            this.receberCura(cura);
            return this.getNome() + " deu um mortal e recuperou " + cura + " de vida!";
        }
    }

}



