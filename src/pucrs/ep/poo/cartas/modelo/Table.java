package pucrs.ep.poo.cartas.modelo;

import java.util.ArrayList;

public class Table {
    private PokemonCard pokJog1;
    private PokemonCard pokJog2;
    private SpecialCard esc1;
    private SpecialCard esc2;

    public PokemonCard getPokJog1() {
        return pokJog1;
    }

    public PokemonCard getPokJog2() {
        return pokJog2;
    }

    public SpecialCard getEsc1() {
        return esc1;
    }

    public SpecialCard getEsc2() {
        return esc2;
    }

    public void setEsc1(SpecialCard esc1) {
        this.esc1 = esc1;
    }

    public void setEsc2(SpecialCard esc2) {
        this.esc2 = esc2;
    }

    public void setPokJog1(PokemonCard pokJog1) {
        this.pokJog1 = pokJog1;
    }

    public void setPokJog2(PokemonCard pokJog2) {
        this.pokJog2 = pokJog2;
    }

    public void battle(){
        int atak1 = pokJog1.getAtaque();
        int atak2 = pokJog2.getAtaque();
        int vida1 = pokJog1.getVida();
        int vida2 = pokJog2.getVida();
        int vidaAtual1;
        int vidaAtual2;


        //Verifica se tem alguma carta especial:
        if (esc1 != null || esc2 != null){
            atak1 += esc1.getId() == "11" ? esc1.getAtaque() : esc2.getId() == "12" ? esc2.getVida() : 0;
            atak2 += esc2.getId() == "11" ? esc2.getAtaque() : esc1.getId() == "12" ? esc1.getVida() : 0;
        }

        //Adiciona ataque no pokemon que tem vantagem:
        setVantagem();

        vidaAtual1 = vida1 - atak2;
        vidaAtual2 = vida2 - atak1;
        pokJog1.setVida(vidaAtual1);
        pokJog2.setVida(vidaAtual2);

        if(vidaAtual1 <= 0)pokJog1.setMorto(true);

        if(vidaAtual2 <= 0)pokJog2.setMorto(true);

    }

    public void setVantagem(){

        pokJog1.getVantagem().forEach(elemento -> {
                    if (elemento == pokJog2.getTipo())
                        pokJog1.addAtaque();
                });
        pokJog2.getVantagem().forEach(elemento -> {
                if(elemento == pokJog1.getTipo())
                    pokJog2.addAtaque();
        });
    }
}
