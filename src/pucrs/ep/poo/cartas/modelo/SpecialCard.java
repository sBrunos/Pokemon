package pucrs.ep.poo.cartas.modelo;

public class SpecialCard extends Card{

    public SpecialCard(int n) {
        super(n);
        this.setAttributes(n);
    }

    public int getAtr() { return super.getId().equals("C11") ? super.getAtaque() : super.getVida(); }

    public void setEfect(PokemonCard pok) {
        if (super.getId().equals("C11"))
            pok.setAtaque( pok.getVida() + getAtr());
        else
            pok.setVida( pok.getVida() + getAtr());

    }

    private void setAttributes(int id){
        if(id > 10){
            switch (id){
                case 11:
                    super.setAtaque(5);
                    break;
                case 12:
                    super.setVida(10);
                    break;
                default: throw new IllegalArgumentException("Carta veio sem Id.");
            }
        }
    }
}
