package com.copper.ui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import com.copper.actions.Atacar; //Imports de Recursos do nosso programa
import com.copper.actions.Defender;
import com.copper.actions.Urssroullete;

import com.copper.actions.UsarItem;
import com.copper.character.*;
import com.copper.db.*;
import com.copper.main.Frases;

// Una tentativa fracassada de aprender JFrame, então observe Stranger Things happening
// provavelmente o codigo vai estar BEEEEEEEEEEEEM comentado, pois eu me guio melhor assim


public class TelaAtacar extends JDialog {

    Atacar Ataque = new Atacar();
    Defender Defender = new Defender();
    Urssroullete Roletar = new Urssroullete();

    private Jogador player;
    private Inimigo enemy;
    // Status
    private JLabel nomeJogador;
    private JLabel vidaJogador;
    private JLabel defesaJogador;

    private JLabel nomeInimigo;
    private JLabel vidaInimigo;

    // Other
    private JTextArea textoAcao;

    // Botoes hihi
    private JButton btnAtacar;
    private JButton btnDefender;
    private JButton btnRoleta;
    private JButton btnItem;


    public TelaAtacar(Jogador player, Inimigo enemy) {
        this.player = player;
        this.enemy = enemy;

        setModal(true);
        setTitle("Batalha"); // title da janela
        setSize(500, 300); // a tal centralizar div, eu acho
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); // ao apertar X, ele encerra o programa
        setLocationRelativeTo(null); // centraliza na tela
        setResizable(false);

        summonarTela(); // E EU ESCOLHO VOCÊ!!!!!!!!!!1

        setVisible(true); // lembra, tem que por isso aqui por último reynder!!!!!!!111
//        System.out.println("[DEBUG] Janela chamada - Iniciado");
    }

    private void summonarTela(){

        setLayout(new BorderLayout(10,10));

        JPanel painelStatus = new JPanel(new GridLayout(2, 3));

        // HUD do Jogador
        JPanel sidePlayer = new JPanel(new FlowLayout(FlowLayout.LEFT));
        nomeJogador = new JLabel(player.getNome()); //MUDAR DEPOIS!!!!!!!!
        vidaJogador = new JLabel("❤️ Vida: " + player.getVida());
        defesaJogador = new JLabel("🛡️ Defesa: " + player.getDefesaBase());
        sidePlayer.add(nomeJogador);
        sidePlayer.add(vidaJogador);
        sidePlayer.add(defesaJogador);

        // HUD do Inimigo, só dar ctrl c ctrl v na do heroi e mudar os title lol (mentira fiz isso n)
        JPanel sideEnemy = new JPanel (new FlowLayout(FlowLayout.RIGHT));
        nomeInimigo = new JLabel(enemy.getNome());
        vidaInimigo = new JLabel("❤️ Vida " + enemy.getVida());
        sideEnemy.add(nomeInimigo);
        sideEnemy.add(vidaInimigo);

        painelStatus.add(sidePlayer);
        painelStatus.add(sideEnemy);
        add(painelStatus, BorderLayout.NORTH);
//        System.out.println("[DEBUG] Componentes da Janela - Iniciado");


        // agora, o momento que a mãe chora e o filho não vê,
        // vamos fazer a parte que vai mostrar o texto da ação recente
        textoAcao = new JTextArea();
        textoAcao.setEditable(false);
        textoAcao.setLineWrap(true);
        textoAcao.setFont(new Font("Serif", Font.PLAIN, 16));
        textoAcao.setText("Um " + enemy.getNome() + " selvagem apareceu!\nO que você irá fazer?\n");
        textoAcao.append("\n" + enemy.falarAlgo());

        JScrollPane scroll = new JScrollPane(textoAcao);
        add(scroll, BorderLayout.CENTER);


        JPanel painelBotoes = new JPanel(new FlowLayout());

        // Adição de Botões
        System.out.println("[DEBUG] Botões - Iniciado");
        btnAtacar = new JButton("Atacar");
        btnDefender = new JButton("Defender");
        btnRoleta = new JButton("Roleta Russa");
        btnItem = new JButton("Itens");

        btnAtacar.setToolTipText("O inverso de defender");
        btnDefender.setToolTipText("O inverso de atacar");
        btnRoleta.setToolTipText("Por algum motivo você tem um revolver enferrujado...\nO inimigo não atacará");
        btnItem.setToolTipText("O que será que temos aqui?");

        painelBotoes.add(btnAtacar);
        painelBotoes.add(btnDefender);
        painelBotoes.add(btnRoleta);
        painelBotoes.add(btnItem); // pra depois
        add(painelBotoes, BorderLayout.SOUTH);

            // O escutador de ações (ActionListener), ele vai executar coisas quando acontecer tal ação
            // Descobri que se por ele em um laço de Loop, ele fica infinitamente em Loop
            // E se eu criar um metodo para ele verificar o fim da batalha em cada Listener?? - criei - isOver()

//            System.out.println("[DEBUG] ActionListener - Iniciado");
            btnAtacar.addActionListener(e -> {
                String danoJogador = Ataque.executar(player, enemy);
                atualizarTela("\nVocê atacou o " + enemy.getNome() + " com " + danoJogador + " de dano!");
                String acaoDoInimigo = enemy.agir(player);
                textoAcao.append("\n" + acaoDoInimigo);
                textoAcao.append("\n" + enemy.falarAlgo());
                atualizarDados();
                btnItem.setEnabled(true);
                isOver();
            });
            btnDefender.addActionListener(e -> {Defender.executar(player, enemy); // terminar de fazer
                atualizarTela("Você se defendeu");
                isOver();
                String acaoDoInimigo = enemy.agir(player);
                textoAcao.append("\n" + acaoDoInimigo);
                player.setDefesa(player.getDefesaBase()); // pra restaurar a defesa original, que gambiarra :mds:
                textoAcao.append("\n" + enemy.falarAlgo());
                atualizarDados();
                btnItem.setEnabled(true);
                isOver();
            });
            btnRoleta.addActionListener(e -> { // corrigido, ele vai mostrar o resultado da roleta agora!
                isOver();
                atualizarTela("Você girou o tambor...");
                String resultado = Roletar.executar(player, enemy);
                textoAcao.append("\n" + resultado);
                atualizarDados();
                btnItem.setEnabled(true);
                isOver();
            });
            btnItem.addActionListener(e -> { // aqui foi uma brincadeira engraçada
                isOver();
                ArrayList<Item> inventario = player.getInventario(); // ele vai pegar o inventário ja
                if (inventario.isEmpty()) { // se nao tiver nada, vai dar vazio
                    JOptionPane.showMessageDialog(null, "Seu inventário está vazio!");
                    return;
                }
                String[] nomesItens = new String[inventario.size()];
                for (int i = 0; i < inventario.size(); i++) { // um for acorrentado que vai ficar girando em loop até puxar tudo
                    nomesItens[i] = inventario.get(i).getNomeDoItem()
                            + " — "
                            + inventario.get(i).getDescricao(); // ele vai separar em descrição e em nome
                }
                int escolha = JOptionPane.showOptionDialog( // interface simples em JOptionPane
                        null,
                        "Escolha um item:",
                        "Inventário",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        nomesItens,
                        nomesItens[0]
                );

                if (escolha == -1) return; // caso a pessoa clique em fechar janela, ele vai considerar q a pessoa fez nada
                Item itemEscolhido = inventario.get(escolha);
                new UsarItem(player, itemEscolhido, player);
                atualizarTela("Você usou " + itemEscolhido.getNomeDoItem() + "!");
                btnItem.setEnabled(false);
                isOver(); // acabou?
                atualizarDados();
            });
    }


    // quando as ações rolarem, ele vai atualizar a tela, não sei se há alguma coisa melhor pra fazer aqui
    private void atualizarTela(String mensagem) {
        System.out.println("[DEBUG] Tela atualizada!");
        textoAcao.setText(mensagem);
    }

    private void atualizarDados(){ // tipo o atualizarTela, ele vai atualizar os dados aqui
        System.out.println("[DEBUG] Dados atualizados");
        vidaInimigo.setText("❤️ Vida: " + enemy.getVida());
        vidaJogador.setText("❤️ Vida: " + player.getVida());
    }


    // condição de encerramento da luta, ele vai inutilizar os botões
    // aqui da para inserir algo pra ele voltar pra janela anterior (exploração), acho que ja ta no ponto de criar algo

    public void isOver(){
        if (!enemy.stillStanding()) {
            textoAcao.setText("Você derrotou " + enemy.getNome() + "!!!");
            btnAtacar.setEnabled(false);
            btnDefender.setEnabled(false);
            btnRoleta.setEnabled(false);
            btnItem.setEnabled(false);
            JOptionPane.showMessageDialog(this, "O combate terminou.");
            this.dispose();
        } else if (!player.stillStanding()) {
            textoAcao.setText(Frases.playerMorte());
            btnAtacar.setEnabled(false);
            btnDefender.setEnabled(false);
            btnRoleta.setEnabled(false);
            btnItem.setEnabled(false);
            JOptionPane.showMessageDialog(this, "O combate terminou.");
            this.dispose();
        }

    }

//
//    // o executavel desse codigo, meu Deus no que eu me meti?
//    public static void main(String[] args) {
//        Jogador player = new Jogador("Entregador");
//        // TESTE, APAGUE DEPOIS aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
//        player.guardarItem(new PocaoDeCura("Poção Pequena", "Cura 10 de vida", 10));
//        player.guardarItem(new PocaoDeCura("Poção Grande", "Cura 50 de vida", 50));
//        player.guardarItem(new PocaoDeCura("Pedra de Crack", "Cura totalmente sua vida, eu acho", true));
//
//        Inimigo enemy = new Inimigo("Buraco na Pista", 100, 15, 5);
//
//        SwingUtilities.invokeLater(() -> new TelaAtacar(player, enemy));
   }