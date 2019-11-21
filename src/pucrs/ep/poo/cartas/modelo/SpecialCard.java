package pucrs.ep.poo.cartas.modelo;

public class SpecialCard extends Card{

    public SpecialCard(int n) {
        super(n);
    }

    public void setAttributes(int id){
        if(id< 11 && id > 0){
            switch (id){
                case 11:
                    super.setAtaque(5);
                    break;
                case 12: super.setVida(10);
                    break;
            }
        }
    }
}
