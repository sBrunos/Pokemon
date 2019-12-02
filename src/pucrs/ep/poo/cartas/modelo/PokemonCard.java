package pucrs.ep.poo.cartas.modelo;

import java.util.ArrayList;

public class PokemonCard extends Card{
    public enum elemento {FOGO, AGUA, TERRA, VOADOR, GRAMA, NORMAL, VENENO, ELETRICO, PEDRA, INSETO};
    private elemento e;
    private ArrayList<elemento> vantagem ;
    private boolean morto = false;

    public PokemonCard(int n) {
        super(n);
        this.setAttributes(n);
    }

    public int getAtaque() { return super.getAtaque(); }

    public int getVida() { return super.getVida(); }

    public elemento getTipo() { return e; }

    public void setAttributes(int id){
        vantagem = new ArrayList<>();
        if(id< 11 && id > 0){
            switch (id){
                case 1:
                    super.setVida(15);
                    super.setAtaque(5);
                    e = elemento.AGUA;
                    vantagem.add(elemento.FOGO);
                    break;
                case 2:
                    super.setVida(15);
                    super.setAtaque(8);
                    e = elemento.GRAMA;
                    vantagem.add(elemento.PEDRA);
                    vantagem.add(elemento.TERRA);
                    break;
                case 3:
                    super.setVida(8);
                    super.setAtaque(2);
                    e = elemento.INSETO;
                    vantagem.add(elemento.GRAMA);

                    break;
                case 4:
                    super.setVida(15);
                    super.setAtaque(7);
                    e = elemento.FOGO;
                    vantagem.add(elemento.GRAMA);
                    vantagem.add(elemento.INSETO);
                    vantagem.add(elemento.VENENO);
                    break;
                case 5:
                    super.setVida(10);
                    super.setAtaque(9);
                    e = elemento.PEDRA;
                    vantagem.add(elemento.VOADOR);
                    vantagem.add(elemento.ELETRICO);
                    break;
                case 6:
                    super.setVida(6);
                    super.setAtaque(12);
                    e = elemento.VOADOR;
                    vantagem.add(elemento.INSETO);
                    vantagem.add(elemento.GRAMA);
                    vantagem.add(elemento.TERRA);
                    break;
                case 7:
                    super.setVida(9);
                    super.setAtaque(4);
                    e = elemento.ELETRICO;
                    vantagem.add(elemento.VOADOR);
                    break;
                case 8:
                    super.setVida(15);
                    super.setAtaque(8);
                    e = elemento.NORMAL;
                    break;
                case 9:
                    super.setVida(14);
                    super.setAtaque(7);
                    e = elemento.TERRA;
                    vantagem.add(elemento.ELETRICO);
                    break;
                case 10:
                    super.setVida(12);
                    super.setAtaque(6);
                    e = elemento.VENENO;
                    vantagem.add(elemento.GRAMA);
                    break;
                default: throw new IllegalArgumentException("Carta veio sem Id.");
            }
        }
    }

    public ArrayList<elemento> getVantagem() {
        return vantagem;
    }

    public void setVantagem(ArrayList vantagem) {
        this.vantagem = vantagem;
    }

    public void addVantagem(elemento tipo){
        vantagem.add(tipo);
    }

    public void setMorto(boolean morto) {
        this.morto = morto;
    }

    public boolean getMorto() {
        return morto;
    }
}