package pucrs.ep.poo.cartas.modelo;

import java.util.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageFactory{
    private static ImageFactory imgf = new ImageFactory();
    private Map<String,Image> images;
    
    private ImageFactory(){
        images = new HashMap<>();
    }
    
    public static ImageFactory getInstance(){
      return(imgf);
    }

    //trocar estes caminhos para o nosso jogo de pokemon
    private String id2File(String imgId){
        switch(imgId){
            case "img1" : return("file:./images/Um.png");//id da imagem em endereco da imagem
            case "img2" : return("file:./images/Dois.png");// só alterar aqui os nomes/caminhos
            case "img3" : return("file:./images/Tres.png");
            case "img4" : return("file:./images/Quatro.png");
            case "img5" : return("file:./images/Cinco.png");
            case "img6" : return("file:./images/Seis.png");
            case "img7" : return("file:./images/Sete.png");
            case "img8" : return("file:./images/Oito.png");
            case "img9" : return("file:./images/Nove.png");
            case "img10" : return("file:./images/Dez.png");
            case "imgBck" : return("file:./images/Back.png");
            default: throw new IllegalArgumentException("Invalid image Id");
        }
    }
    
    public ImageView createImage(String imgId){
        Image img = images.get(imgId);
        if (img == null){
            img = new Image(id2File(imgId));
            images.put(imgId,img);
        }
 
        //Image img = new Image(id2File(imgId));
        return(new ImageView(img));
    }
}

        
        
    

