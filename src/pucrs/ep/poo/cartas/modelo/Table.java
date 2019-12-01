package pucrs.ep.poo.cartas.modelo;

import javafx.scene.image.ImageView;
import pucrs.ep.poo.cartas.gui.TableView;

import java.util.ArrayList;
import java.util.Observable;

public class Table extends Observable {
    private static Table instance = null;
    private PokemonCard pokJog1, pokJog2;
    private SpecialCard esc1, esc2;

    public Table(){}

    public PokemonCard getPokJog1() { return pokJog1; }

    public PokemonCard getPokJog2() { return pokJog2; }

    public SpecialCard getEsc1() {  return esc1; }

    public SpecialCard getEsc2() {
        return esc2;
    }

    public int getVida1() {
        return pokJog1 != null ? pokJog1.getVida() : 0;
    }

    public int getVida2() {
        return pokJog2 != null ? pokJog2.getVida() : 0;
    }

    public void setEsc1(SpecialCard esc1) {
        this.esc1 = esc1;
    }

    public void setEsc2(SpecialCard esc2) {
        this.esc2 = esc2;
    }

    public void setPokJog1(PokemonCard pokJog) { pokJog1 = pokJog; }

    public void setPokJog2(PokemonCard pokJog) { pokJog2 = pokJog; }

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
        int atak1 = pokJog1.getAtaque();
        int atak2 = pokJog2.getAtaque();
        int vida1 = pokJog1.getVida();
        int vida2 = pokJog2.getVida();

        int vidaAtual1;
        int vidaAtual2;
        int[] vantagens = getVantagens();

        //Verifica se tem alguma carta especial:
        if (esc1 != null || esc2 != null){
            atak1 += esc1.getId() == "11" ? esc1.getAtaque() : esc2.getId() == "12" ? esc2.getVida() : 0;
            atak2 += esc2.getId() == "11" ? esc2.getAtaque() : esc1.getId() == "12" ? esc1.getVida() : 0;
        }
        else if (esc1 != null ){
            atak1 += esc1.getId() == "11" ? esc1.getAtaque() : 0;
            atak2 += esc1.getId() == "12" ? esc1.getVida() : 0;
        }
        else if (esc2 != null){
            atak1 += esc2.getId() == "12" ? esc2.getVida() : 0;
            atak2 += esc2.getId() == "11" ? esc2.getAtaque() : 0;
        }

        //Adiciona ataque no pokemon que tem vantagem:
        atak1 += vantagens[0]*5;
        atak2 += vantagens[1]*5;

        //setVantagem();

        vidaAtual1 = vida1 - atak2;
        vidaAtual2 = vida2 - atak1;

        pokJog1.setVida(vidaAtual1);
        pokJog2.setVida(vidaAtual2);

        if(vidaAtual1 <= 0)
            pokJog1 = null;
        if(vidaAtual2 <= 0)
            pokJog2 = null;
        //if(vidaAtual1 <= 0)pokJog1.setMorto(true);
System.out.println("BATTLE");
        //if(vidaAtual2 <= 0)pokJog2.setMorto(true);
        Game.getInstance().play();
        TableView.getInstance().setVida1(vidaAtual1);
        TableView.getInstance().setVida2(vidaAtual2);

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
