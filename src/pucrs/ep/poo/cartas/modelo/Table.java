package pucrs.ep.poo.cartas.modelo;

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

        if (esc1 != null || esc2 != null){
            atak1 += esc1.getId() == "11" ? esc1.getAtaque() : esc2.getId() == "12" ? esc2.getVida() : 0;
            atak2 += esc2.getId() == "11" ? esc2.getAtaque() : esc1.getId() == "12" ? esc1.getVida() : 0;
        }

    }
}
