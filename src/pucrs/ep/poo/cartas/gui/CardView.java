package pucrs.ep.poo.cartas.gui;

import javafx.scene.control.Button;

import java.util.*;
import javafx.event.*;
import pucrs.ep.poo.cartas.modelo.*;

public class CardView extends Button implements Observer{
    private Card card;
    private CardView thisCard;
    private CardObserver observer;
    private int owner;
    
    public CardView(Card aCard, int jogador){

        //cria botão sem texto e com imagem:
        super("",ImageFactory.getInstance().createImage("imgBck"));
        card = aCard;
        card.addObserver(this);
        thisCard = this;
        owner = jogador;

        this.setOnAction(null);
    }

    public void setCardObserver(CardObserver obs){
        observer = obs;
    }

    public Card getCard(){
        return(card);
    }

    @Override
    public void update(Observable o,Object targs){
        if (card.isFacedUp()){
            boolean emptySpace = Game.getInstance().getPlayer() != 0 && Game.getInstance().getPlayer() != owner ? false : true;
            this.setGraphic(ImageFactory.getInstance().createImage(card.getNomePokemon()));
            this.setOnAction(e  -> {
                        if (observer != null){
                            if(emptySpace)
                            observer.cardSelected(thisCard);
                        }
                    }
            );
        }else{
            this.setGraphic(ImageFactory.getInstance().createImage("imgBck"));
            this.setOnAction(null);
        }
    }
}

