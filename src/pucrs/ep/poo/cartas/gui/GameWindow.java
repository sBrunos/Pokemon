package pucrs.ep.poo.cartas.gui;

import java.util.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.event.*;
import pucrs.ep.poo.cartas.modelo.Game;


public class GameWindow extends Application implements Observer{
   
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Game.getInstance().addObserver(this);
        
        primaryStage.setTitle("POKÉMON-CARDS");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
       
        DeckView deckJ1 = new DeckView(1);
        ScrollPane sd1 = new ScrollPane();
        sd1.setPrefHeight(300);
        sd1.setContent(deckJ1);
        grid.add(sd1,0,0);

        TableView placar = new TableView();
        grid.add(placar,0,2);

      //  table = new GridPane();
      //  table.setAlignment(Pos.CENTER);
      //  table.setPrefSize(950, 150);
      //  table.add(placar, 1, 0);

        DeckView deckJ2 = new DeckView(2);
        ScrollPane sd2 = new ScrollPane();
        sd2.setPrefHeight(300);
        sd2.setContent(deckJ2);
        grid.add(sd2,0,3);
                
        Scene scene = new Scene(grid);
        primaryStage.setScene(scene);        
        primaryStage.show();
    }

    @Override
    public void update(Observable o,Object arg){
        Alert alert;
        
        if (arg == null){
            return;
        }
        GameEvent eg = (GameEvent)arg;
        if (eg.getTarget() == GameEvent.Target.GWIN){
            switch(eg.getAction()){
                case INVPLAY:
                    alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Atenção !!");
                    alert.setHeaderText("Jogada inválida!!");
                    alert.setContentText("Era a vez do jogador "+eg.getArg());
                    alert.showAndWait();
                    break;                    
                case MUSTCLEAN:
                    alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Atenção !!");
                    alert.setHeaderText(null);
                    alert.setContentText("Utilize o botao \"Clean\"");
                    alert.showAndWait();
                    break;                    
                case ENDGAME:
                    alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Atenção !!");
                    alert.setHeaderText(null);
                    alert.setContentText("Fim de Jogo !!");
                    alert.showAndWait();
                    break;                    
            }
        }
    }
    
}
