package pucrs.ep.poo.cartas.modelo;

public class PokemonCard extends Card{
    private int ataque;
    private int vida;
    private enum elemento {FOGO, AGUA, TERRA, VOADOR, GRAMA, NORMAL, VENENO, ELETRICO, PEDRA, INSETO};
    private elemento e;


    public PokemonCard(int n) {
        super(n);
        setAttributes(n);
    }

    public int getAtaque() { return super.getAtaque(); }

    public int getVida() { return super.getVida(); }

    public elemento getTipo() { return e; }

    public void setAttributes(int id){
        if(id< 11 && id > 0){
            switch (id){
                case 1: super.setVida(10);
                    super.setAtaque(5);
                    e = elemento.AGUA;
                    break;
                case 2: super.setVida(15);
                    super.setAtaque(2);
                    e = elemento.GRAMA;
                    break;
                case 3: super.setVida(8);
                    super.setAtaque(8);
                    e = elemento.INSETO;
                    break;
                case 4: super.setVida(15);
                    super.setAtaque(7);
                    e = elemento.FOGO;
                    break;
                case 5: super.setVida(11);
                    super.setAtaque(4);
                    e = elemento.PEDRA;

                    break;
                case 6: super.setVida(9);
                    super.setAtaque(12);
                    e = elemento.VOADOR;
                    break;
                case 7: super.setVida(8);
                    super.setAtaque(6);
                    e = elemento.ELETRICO;
                    break;
                case 8: super.setVida(15);
                    super.setAtaque(8);
                    e = elemento.NORMAL;
                    break;
                case 9: super.setVida(14);
                    super.setAtaque(7);
                    e = elemento.TERRA;
                    break;
                case 10: super.setVida(12);
                    super.setAtaque(6);
                    e = elemento.VENENO;
                    break;
            }
        }
    }
}