package pucrs.ep.poo.cartas.modelo;

import java.util.*;

public abstract class Card extends Observable{//carta observavél
    private String id;
    private String nomePokemon;//nome da imagem
    private int vida;
    private int ataque;
    public enum Face {UP, DOWN}; //carta virada ou não
    private Face face;
    private boolean onBattle; //carta em campo ou não

    public Card(int n){
        id = "C" + n;
        nomePokemon = "img" + n;
        face = Face.DOWN;
    }
    
    public String getId(){ return id; }
    
    public String getNomePokemon(){
        return nomePokemon;
    }
    
    public int getVida(){ return vida; }

    public int getAtaque(){ return ataque; }

    public void setVida(int v){ vida = v; }

    public void setAtaque(int a){ ataque = a; }

    public boolean isFacedUp(){ //está virada?
        return face == Face.UP ? true : false;
    }
    
    public void flip(Face f){ face = f; setChanged(); notifyObservers(); }

    public void setOnBattle(){
        if (onBattle == true){
            onBattle = false;
        }else{
            onBattle = true;
        }
    }
}
        

