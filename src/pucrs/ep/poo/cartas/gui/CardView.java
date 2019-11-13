package pucrs.ep.poo.cartas.gui;

import javafx.scene.control.Button;

import java.util.*;
import javafx.event.*;
import pucrs.ep.poo.cartas.modelo.*;

public class CardView extends Button implements Observer{
    private Card card;
    private CardView thisCard;
    private CardObserver observer;
    
    public CardView(Card aCard){
        super("",ImageFactory.getInstance().createImage("imgBck"));
        card = aCard;
        card.addObserver(this);
        thisCard = this;
        
        this.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (observer != null){
                    observer.cardSelected(thisCard);
                }
            }
        });
    }
    
    @Override
    public void update(Observable o,Object args){
        if (card.isFacedUp()){
            this.setGraphic(ImageFactory.getInstance().createImage(card.getImageId()));
        }else{
            this.setGraphic(ImageFactory.getInstance().createImage("imgBck"));
        }   
    }
    
    public void setCardObserver(CardObserver obs){
        observer = obs;
    }
    
    public Card getCard(){
        return(card);
    }
}

