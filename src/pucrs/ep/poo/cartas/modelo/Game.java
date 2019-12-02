package pucrs.ep.poo.cartas.modelo;

import pucrs.ep.poo.cartas.gui.GameEvent;
import pucrs.ep.poo.cartas.gui.TableView;

import java.util.*;
//classe teste
//um jogo em si
//
public class Game extends Observable{
    private static Game game = new Game();
    private CardDeck deckJ1, deckJ2;//2 pessoas
    private int player;//que tem a vez
    private Table table;
    
    private Game(){//constru privat
        deckJ1 = new CardDeck();//distribui as cartas deckJ1 = new CardDeck(baralho 1);
        deckJ2 = new CardDeck();//baralho 2 deckJ2 = new CardDeck(baralho 2);
    }
    
    public static Game getInstance(){
        return(game);
    }//devolve a mensa instancia
    
    private void nextPlayer(){
        player++;
        if (player == 3){//ou if (player == 3){tem que ver
            player = 1;
        }
        if (player == 1)
            deckJ1.flipCards(Card.Face.UP);

        if (player == 2)
            deckJ2.flipCards(Card.Face.UP);
    }
    
    public CardDeck getDeckJ1(){
        return(deckJ1);
    }

    public int getvida1(){
        int vida1 = 0;
        for(Card card : deckJ1.getCards()){
            if (card instanceof PokemonCard)
                if(deckJ1.getSelectedCard() != card){
                    vida1 += card.getVida();
                }

        }
        vida1 += Table.getInstance().getVida1();
        return(vida1 < 0 ? 0 : vida1);
    }

    public int getvida2(){
        int vida2 = 0;
        for(Card card : deckJ2.getCards()){
            if (card instanceof PokemonCard)
                if(deckJ2.getSelectedCard() != card){
                    vida2 += card.getVida();
                }

        }
        vida2 += Table.getInstance().getVida2();
        return(vida2 < 0 ? 0 : vida2);
    }

    public boolean allPokDead1() {
        System.out.print("Vida 1: " + getvida1() + " Vida 2: " + getvida2() );

        return getvida1() == 0 ? true : false;  }

    public boolean allPokDead2(){ return getvida2() == 0 ? true : false;  }

    public int getDeck1Size() {return deckJ1.getCards().size();}

    public int getDeck2Size() {return deckJ2.getCards().size();}

    public int getPlayer() {return player;}

    public void setPlayer() {player = 1;}

    public void setDeck1(CardDeck deck){
        deckJ1 = deck;
    }

    public void setDeck2(CardDeck deck){
        deckJ2 = deck;
    }

    public CardDeck getDeckJ2(){
        return(deckJ2);
    }
    
    public void play(){//fazer a jogada
        GameEvent gameEvent = null;

        if(Table.getInstance().getPokJog1() != null && Table.getInstance().getPokJog2() != null){
            Table.getInstance().setBattleMode(Table.BattleMode.ON);
            deckJ1.flipCards(Card.Face.DOWN);
            deckJ2.flipCards(Card.Face.DOWN);
        } else if(Table.getInstance().getPokJog1() != null){
            player = 2;
            deckJ2.flipCards(Card.Face.UP);
            deckJ1.flipCards(Card.Face.DOWN);
        } else if(Table.getInstance().getPokJog2() != null){
            player = 1;
            deckJ1.flipCards(Card.Face.UP);;
            deckJ2.flipCards(Card.Face.DOWN);
        }else{
            player = player == 1 ? 2 : 1;
            deckJ1.flipCards(Card.Face.UP);
            deckJ2.flipCards(Card.Face.DOWN);

        }

        if ( allPokDead1() || allPokDead2() ){//
            gameEvent = new GameEvent(GameEvent.Target.GWIN,GameEvent.Action.ENDGAME, "");//
            setChanged();//
            notifyObservers((Object)gameEvent);//
            deckJ1.flipCards(Card.Face.UP);
            deckJ2.flipCards(Card.Face.UP);
            return;
        }

        setChanged();
        notifyObservers();
    }

    // Acionada pelo botao de limpar
    public void removeSelected(){
        GameEvent gameEvent = null;

        if ((Table.getInstance().getVida1() == 0 && deckJ2.getCards().size() > 0 ) ||
                (Table.getInstance().getVida2() == 0 && deckJ1.getCards().size() > 0 )){
            gameEvent = new GameEvent(GameEvent.Target.GWIN,GameEvent.Action.ENDGAME,"");
            setChanged();
            notifyObservers((Object)gameEvent);
        }

        deckJ1.removeSel();
        deckJ2.removeSel();
    }
}
