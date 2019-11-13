package pucrs.ep.poo.cartas.modelo;

import pucrs.ep.poo.cartas.gui.GameEvent;

import java.util.*;
//quantidade de cartas que tem na mão
//lista de cartas na mão e a que está selecionada no momento
//Card Deck sigintifca cartas na mão

// Esta classe tem de ser um container de cartas observavel ...
public class CardDeck extends Observable{
    public static final int NCARDS = 6;//
    private List<Card> cartas;//cartas na mao
    private Card selected;//selecionada no momento
    
    public CardDeck(){
       cartas = new ArrayList<Card>(NCARDS);//5
       selected = null;
       Random r = new Random();//o Random gera numeros aleatórios
       for(int i=0;i<NCARDS;i++){//sorteia as cartas
           int n = r.nextInt(10)+1;
           Card c = new Card("C"+n,"img"+n,n);//
           c.flip();
           cartas.add(c);
       }
    }
        
    public List<Card> getCards(){
        return(cartas);
    }

    public int getNumberOfCards(){
        return(cartas.size());
    }//quantidade
    
    public void removeSel(){
        if (selected == null){//remove
            return;
        }
        cartas.remove(selected);
        selected = null;//
        GameEvent gameEvent = new GameEvent(GameEvent.Target.DECK,GameEvent.Action.REMOVESEL,"");
        setChanged();
        notifyObservers(gameEvent);//avisou que exemplo "a carta 10 foi tirada"
    }    
        
    public Card getSelectedCard(){
        return(selected);
    }//pergunta se a carta está selecionada
    
    public void setSelectedCard(Card card){
        selected = card;
    }
}

