package pucrs.ep.poo.cartas.modelo;

import pucrs.ep.poo.cartas.gui.GameEvent;

import java.util.*;
//classe teste
//um jogo em si
//
public class Game extends Observable{
    private static Game game = new Game();//coleções/simbotol()instancia da classe
    private int ptsJ1,ptsJ2;//
    private CardDeck deckJ1,deckJ2;//2 pessoas
    private int player;//que tem na vez
    private int jogadas;//qtas já jogou
    
    private Game(){//constru privat
        ptsJ1 = 0;
        ptsJ2 = 0;
        deckJ1 = new CardDeck();//distribui as cartas deckJ1 = new CardDeck(baralho 1);
        deckJ2 = new CardDeck();//baralho 2 deckJ2 = new CardDeck(baralho 2);
        player = 1;//o jogador 1 é o primeiro a jogar
        jogadas = CardDeck.NCARDS;//10 rodadas
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
        
    public int getPtsJ1(){
        return(ptsJ1);
    }

    public int getPtsJ2(){
        return(ptsJ2);
    }
    
    public CardDeck getDeckJ1(){
        return(deckJ1);
    }
    
    public CardDeck getDeckJ2(){
        return(deckJ2);
    }
    
    public void play(CardDeck deckAcionado){//fazer a jogada
        GameEvent gameEvent = null;

        if (player == 3){//
                gameEvent = new GameEvent(GameEvent.Target.GWIN,GameEvent.Action.MUSTCLEAN,"");//
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
                deckJ1.getSelectedCard().flip();//carta selecionda, flip virou ela
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
                deckJ2.getSelectedCard().flip();
                // Verifica quem ganhou a rodada
                if (deckJ1.getSelectedCard().getValue() > deckJ2.getSelectedCard().getValue()){//mudar aqui a lógica
                    ptsJ1++;
                }else if (deckJ1.getSelectedCard().getValue() < deckJ2.getSelectedCard().getValue()){
                    ptsJ2++;//soma os pontos do jogador
                }
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
