package pucrs.ep.poo.cartas.modelo;

import java.util.*;

public class Card extends Observable{//carta observavél
    private String id;
    private String nomePokemon;//nome da imagem
    private int vida;
    private int ataque;
    private boolean faceUp;//carta virada ou não
    private enum tipos {POWERUP, POKEMON, HEALTHUP};
    private enum elementos {FOGO, AGUA, TERRA, VOADOR, GRAMA, NORMAL, VENENO, ELETRICO, PEDRA, INSETO}
    private elementos elemento;
    private tipos tipo;
    //construtor
    public Card(String anId,String nome, int pok){
        id = anId;
        setAttributes(pok);
        nomePokemon = nome;
        faceUp = true;

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
    public void setAttributes(int id){
        if(id< 11 && id > 0){
            switch (id){
                case 1: vida = 10;
                        ataque = 5;
                        elemento = elementos.AGUA;
                        tipo = tipos.POKEMON;
                break;
                case 2: vida = 15;
                    ataque = 2;
                    elemento = elementos.GRAMA;
                    tipo = tipos.POKEMON;
                    break;
                case 3: vida = 8;
                    ataque = 8;
                    elemento = elementos.INSETO;
                    tipo = tipos.POKEMON;
                    break;
                case 4: vida = 15;
                    ataque = 7;
                    elemento = elementos.FOGO;
                    tipo = tipos.POKEMON;
                    break;
                case 5: vida = 11;
                    ataque = 4;
                    elemento = elementos.PEDRA;
                    tipo = tipos.POKEMON;
                    break;
                case 6: vida = 9;
                    ataque = 12;
                    elemento = elementos.VOADOR;
                    tipo = tipos.POKEMON;
                    break;
                case 7: vida = 8;
                    ataque = 6;
                    elemento = elementos.ELETRICO;
                    tipo = tipos.POKEMON;
                    break;
                case 8: vida = 15;
                    ataque = 8;
                    elemento = elementos.NORMAL;
                    tipo = tipos.POKEMON;
                    break;
                case 9: vida = 14;
                    ataque = 7;
                    elemento = elementos.TERRA;
                    tipo = tipos.POKEMON;
                    break;
                case 10: vida = 12;
                    ataque = 6;
                    elemento = elementos.VENENO;
                    tipo = tipos.POKEMON;
                    break;
                case 11: vida = 0;
                    ataque = 5;
                    tipo = tipos.POWERUP;
                    break;
                case 12: vida = 10;
                    ataque = 0;
                    tipo = tipos.HEALTHUP;
                    break;

            }
        }
    }

}
        

