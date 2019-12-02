package pucrs.ep.poo.cartas.modelo;

import javafx.scene.image.ImageView;
import pucrs.ep.poo.cartas.gui.CardView;
import pucrs.ep.poo.cartas.gui.TableView;

import java.util.ArrayList;
import java.util.Observable;

public class Table extends Observable {
    private static Table instance = null;
    private PokemonCard pokJog1, pokJog2;
    private SpecialCard espJ1, espJ2;
    public enum BattleMode {ON, OFF};
    private BattleMode battleMode;
    private int pokStart;

    public Table(){battleMode  = BattleMode.OFF; pokStart = 1;}

    public PokemonCard getPokJog1() { return pokJog1; }

    public PokemonCard getPokJog2() { return pokJog2; }

    public SpecialCard getEspJ1() {  return espJ1; }

    public SpecialCard getEspJ2() { return espJ2; }

    //public int getVida1() { return pokJog1 != null ? pokJog1.getVida() : 0; }

    public int getVida1() { return pokJog1 != null ? pokJog1.getVida() : 0; }

    //public int getVida2() { return pokJog2 != null ? pokJog2.getVida() : 0; }

    public int getVida2() { return pokJog2 != null ? pokJog2.getVida() : 0; }

    public int getAtaque1() { return pokJog1 != null ? pokJog1.getAtaque() : 0; }

    public int getAtaque2() { return pokJog2 != null ? pokJog2.getAtaque() : 0; }

    public void setCard(CardView cv, int jogador){
        Card card = cv.getCard();
        if( card instanceof PokemonCard ){
            if (jogador == 1){
                pokJog1 = (PokemonCard) card;
                if (espJ1 != null){
                        espJ1.setEfect(pokJog1);
                }
            } else{
                pokJog2 = (PokemonCard) card;
                if (espJ2 != null){
                        espJ2.setEfect(pokJog2);
                }
            }
        } else {
            if (jogador == 1)
                espJ1 = (SpecialCard) card;
            else
                espJ2 = (SpecialCard) card;
        }
        if(card instanceof PokemonCard)
            TableView.getInstance().setImagem(cv, jogador);

        setChanged(); notifyObservers();
    }

    public void setPokJog1(PokemonCard pokJog) { pokJog1 = pokJog; }

    public void setPokJog2(PokemonCard pokJog) { pokJog2 = pokJog; }

    public void setBattleMode(BattleMode m) { battleMode = m; }

    public boolean inBattleMode() { return battleMode == BattleMode.ON ? true : false; }

    public int getVencedor() {
        return pokJog1.getMorto() && Game.getInstance().getDeck2Size() > 0 ? 2 :
                pokJog2.getMorto() && Game.getInstance().getDeck1Size() == 0 ? 1 : 0;
    }

    public static Table getInstance(){
        if(instance == null)
            instance = new Table();
        return instance;
    }

    public void battle(){
        if (inBattleMode()) {

            int atak1 = pokJog1.getAtaque();
            int atak2 = pokJog2.getAtaque();
            int vida1 = pokJog1.getVida();
            int vida2 = pokJog2.getVida();

            int vidaAtual1;
            int vidaAtual2;
            int[] vantagens = getVantagens();

            //Adiciona ataque no pokemon que tem vantagem:
            atak1 += vantagens[0] * 5;
            atak2 += vantagens[1] * 5;

            //setVantagem();
            if (pokStart == 1) {
                vidaAtual2 = vida2 - atak1;
                vidaAtual1 = vidaAtual2 > 0 ? vida1 - atak2 : vida1;
            }
            else {
                if(vida1 < atak2){
                    vidaAtual1 = 0;
                }else{

                    vidaAtual1 = vida1 - atak2;
                }

                if(vidaAtual1 > 0){
                    if(vida2 < atak1){
                        vidaAtual2 = 0;
                    }
                    else{
                        vidaAtual2 = vida2 - atak1;

                    }
                }
                else{
                    vidaAtual2 =  vida2;

                }
            }

            pokJog1.setVida(vidaAtual1);
            pokJog2.setVida(vidaAtual2);

            if (vidaAtual1 <= 0){
                pokJog1 = null;
                battleMode = BattleMode.OFF;
            }
            if (vidaAtual2 <= 0) {
                pokJog2 = null;
                battleMode = BattleMode.OFF;
            }

            //Define o pokemon a atacar primeiro no próximo battle
            pokStart = vidaAtual1 <= 0 ? 1 : vidaAtual2 <= 0 ? 2 : pokStart == 1 ? 2 : 1;

            if (vidaAtual1 <= 0)
                espJ1 = null;

            if (vidaAtual2 <= 0)
                espJ2 = null;

            Game.getInstance().play();

            setChanged();
            notifyObservers();

        }

    }

    public int[] getVantagens(){
        int[] vantagens = new int[2];

        pokJog1.getVantagem().forEach(elemento -> {
                    if (elemento == pokJog2.getTipo())
                        vantagens[0]++;
                });
        pokJog2.getVantagem().forEach(elemento -> {
                if(elemento == pokJog1.getTipo())
                    vantagens[1]++;
        });
        return vantagens;
    }

}
