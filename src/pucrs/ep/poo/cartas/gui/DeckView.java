package pucrs.ep.poo.cartas.gui;

import java.util.*;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pucrs.ep.poo.cartas.modelo.*;


public class DeckView extends VBox implements CardObserver,Observer{
    private int jogador;
    private CardDeck cDeck;
    private Card selectedCard;


    public DeckView(int nroJog){
        //Cria uma Horizontal Box, com 4 intens
        super(4);
        
        //Posiciona no centro
        this.setAlignment(Pos.CENTER);
        
        //Declara quem é o jogador e nenhuma carta selecionada
        jogador = nroJog;
        selectedCard = null;
        
        cDeck = null;

        //Dependendo do jogador escolhe o Deck
        if (jogador == 1){
            cDeck = Game.getInstance().getDeckJ1();

        }else{
            cDeck = Game.getInstance().getDeckJ2();
        }

        cDeck.addObserver(this);
        
        for(Card card:cDeck.getCards()){
            CardView cv = new CardView(card, jogador);
            cv.setCardObserver(this);
            this.getChildren().add(cv);
        }
        if (jogador == 1) {
            cDeck.flipCards(Card.Face.UP);
        }
    }

    public void cardSelected(CardView cv){
            cDeck.setSelectedCard(cv.getCard());

            Table.getInstance().setCard(cv, jogador);

            selectedCard = cv.getCard();

            selectedCard.setOnBattle();

            removeSel();
            Game.getInstance().play();
    }

    private void removeSel(){
        List cards = getChildren();
        for(int i=0;i<cards.size();i++){
            CardView cv = (CardView)cards.get(i);
            if (cv.getCard() == selectedCard){

                getChildren().remove(cv);
                selectedCard = null;
            }
        }
    }
    
    @Override
    public void update(Observable o,Object arg){
        if (arg == null){
            return;
        }
        GameEvent ge = (GameEvent)arg;
        if (ge.getTarget() != GameEvent.Target.DECK){
            return;
        }
        if (ge.getAction() == GameEvent.Action.REMOVESEL){
            removeSel();
        }
        if(ge.getAction() == GameEvent.Action.SELECTCARD){

        }
    }
}



