package pucrs.ep.poo.cartas.modelo;

import pucrs.ep.poo.cartas.gui.GameEvent;
import pucrs.ep.poo.cartas.gui.TableView;

import java.util.*;
//classe teste
//um jogo em si
//
public class Game extends Observable{
    private static Game game = new Game();//coleções/simbotol()instancia da classe
    private CardDeck deckJ1, deckJ2;//2 pessoas
    private int player;//que tem na vez
    private int jogadas;//qtas já jogou
    private int vida1;
    private int vida2;
    private Table table;
    
    private Game(){//constru privat
        deckJ1 = new CardDeck();//distribui as cartas deckJ1 = new CardDeck(baralho 1);
        deckJ2 = new CardDeck();//baralho 2 deckJ2 = new CardDeck(baralho 2);
        player = 1;//o jogador 1 é o primeiro a jogar
        jogadas = CardDeck.NCARDS;//10 rodadas
        table = new Table();
    }
    
    public static Game getInstance(){
        return(game);
    }//devolve a mensa instancia
    
    private void nextPlayer(){
        player++;
        if (player == 4){//ou if (player == 3){tem que ver
            player = 1;
        }
    }
    
    public CardDeck getDeckJ1(){
        return(deckJ1);
    }

    public int getvida1(){
        for (Card card: deckJ1.getCards()) {
            vida1 += card.getId() == "11" ? 0 : card.getVida();
        }

        deckJ1.getCards().forEach(card -> {
            vida1 += card.getVida();
        });
        return(vida1);
    }

    public int getvida2(){
        for (Card card: deckJ2.getCards()) {
            vida2 += card.getId() == "11" ? 0 : card.getVida();
        }
        return(vida2);
    }

    public void setvida1(int vida){
        vida1 = vida;
    }

    public void setvida2(int vida){
        vida2 = vida;
    }


    public CardDeck getDeckJ2(){
        return(deckJ2);
    }
    
    public void play(CardDeck deckAcionado){//fazer a jogada
        GameEvent gameEvent = null;

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
                // Vira a carta
                deckJ1.getSelectedCard().select();//carta selecionda, flip virou ela
                // Proximo jogador
                nextPlayer();//n jog passa a ser 3
            }
        }else if (deckAcionado == deckJ2){
            if (player != 2){
                gameEvent = new GameEvent(GameEvent.Target.GWIN,GameEvent.Action.INVPLAY,"1");//aqui tem que mexer para o nosso jogo
                setChanged();
                notifyObservers((Object)gameEvent);
            }else{
                // Vira a carta
                deckJ2.getSelectedCard().select();
                // Verifica quem ganhou a rodada
                setChanged();
                notifyObservers((Object)gameEvent);
                // Próximo jogador
                nextPlayer();
            }
        }          
    }

    // Acionada pelo botao de limpar    
    public void removeSelected(){
        GameEvent gameEvent = null;
        
        if (player != 3){
            return;
        }
        jogadas--;
        if (jogadas == 0){
            gameEvent = new GameEvent(GameEvent.Target.GWIN,GameEvent.Action.ENDGAME,"");
            setChanged();
            notifyObservers((Object)gameEvent);
            //return;
        }
        deckJ1.removeSel();
        deckJ2.removeSel();
        nextPlayer();
    }
}
