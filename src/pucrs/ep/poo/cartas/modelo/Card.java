package pucrs.ep.poo.cartas.modelo;

import java.util.*;

public class Card extends Observable{//carta observavél
    private String id;
    private String imageId;//nome da imagem
    private int value;//1,2,3,4,5,6....atuar nesta parte 1lugar, tipo, valor ,força
    private boolean faceUp;//carta virada ou não

    //construtor
    public Card(String anId,String anImageId,int val){
        id = anId;
        imageId = anImageId;
        value = val;
        faceUp = true;
    }
    
    public String getId(){
        return(id);
    }
    
    public String getImageId(){
        return(imageId);
    }
    
    public int getValue(){
        return(value);
    }
    
    public boolean isFacedUp(){
        return(faceUp);
    }//está virada?
    
    public void flip(){//virar a carta
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
}
        

