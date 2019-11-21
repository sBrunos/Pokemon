package pucrs.ep.poo.cartas.modelo;

import java.util.*;

public class Card extends Observable{//carta observavél
    private String id;
    private String nomePokemon;//nome da imagem
    private int vida;
    private int ataque;
    private boolean faceUp; //carta virada ou não
    private boolean onBattle; //carta em campo ou não

    public Card(int n){
        id = "C" + n;
        nomePokemon = "img" + n;
        faceUp = false;
    }
    
    public String getId(){
        return(id);
    }
    
    public String getNomePokemon(){
        return(nomePokemon);
    }
    
    public int getVida(){
        return(vida);
    }

    public int getAtaque(){
        return(ataque);
    }

    public void setVida(int v){
        vida = v;
    }

    public void setAtaque(int a){ ataque = a; }

    public void setonBattle(boolean a){ onBattle = a; }


    public boolean isFacedUp(){
        return(faceUp);
    }//está virada?
    
    public void select(){//virar a carta
        if (faceUp == true){//fui virada
            faceUp = false;
        }else{
            faceUp = true;
        }
        //semelhança
        setChanged();//
        notifyObservers();//avisa que a carta virou
        //// houve modificação e ve se alguém está interessada
    }
    /*public void flip(){//virar a carta
        if (faceUp == true){//fui virada
            faceUp = false;
        }else{
            faceUp = true;
        }
        //semelhança
        setChanged();//
        notifyObservers();//avisa que a carta virou
        //// houve modificação e ve se alguém está interessada
    }*/


}
        

