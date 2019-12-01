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
        player = 1;//o jogador 1 é o primeiro a jogar
    }
    
    public static Game getInstance(){
        return(game);
    }//devolve a mensa instancia
    
    private void nextPlayer(){
        player++;
        if (player == 4){//ou if (player == 3){tem que ver
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
            vida1 += card.getVida();
        }
        return(vida1);
    }

    public int getvida2(){
        int vida2 = 0;
        for(Card card : deckJ1.getCards()){
            vida2 += card.getVida();
        }
        return(vida2);
    }

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
            player = 3;
            deckJ1.flipCards(Card.Face.DOWN);
            deckJ2.flipCards(Card.Face.DOWN);
        } else if(Table.getInstance().getPokJog1() != null){
            player = 2;
            deckJ2.flipCards(Card.Face.UP);
        } else if(Table.getInstance().getPokJog2() != null){
            player = 1;
            deckJ1.flipCards(Card.Face.UP);
        }else{
            player = 1;
            deckJ1.flipCards(Card.Face.UP);
            deckJ2.flipCards(Card.Face.DOWN);
        }

        /*
        if (player == 3){//
                gameEvent = new GameEvent(GameEvent.Target.GWIN,GameEvent.Action.BATTLETIME,"");//
                setChanged();//
                notifyObservers((Object)gameEvent);//
                return;
        }        
        if (deckAcionado == deckJ1){//
            if (player != 1){//não é do jo 1
                gameEvent = new GameEvent(GameEvent.Target.GWIN,GameEvent.Action.INVPLAY,"2");//
               // gameEvent = new GameEvent(GameEvent.Target.GWIN,GameEvent.Action.INVPLAY,"2");//mensagem de erro
                //se tem erro mostra
                setChanged();//
                notifyObservers((Object)gameEvent);//
            }else{
                // Proximo jogador
                nextPlayer();//n jog passa a ser 3
            }
        }else if (deckAcionado == deckJ2){
            if (player != 2){
                gameEvent = new GameEvent(GameEvent.Target.GWIN,GameEvent.Action.INVPLAY,"1");//aqui tem que mexer para o nosso jogo
                setChanged();
                notifyObservers((Object)gameEvent);
            }else{
                // Verifica quem ganhou a rodada
                setChanged();
                notifyObservers((Object)gameEvent);
                // Próximo jogador
                nextPlayer();
            }
        }       */
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

        nextPlayer();
    }
}
