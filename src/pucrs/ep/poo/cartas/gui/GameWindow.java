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
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.event.*;
import pucrs.ep.poo.cartas.modelo.Game;
import sun.swing.MenuItemLayoutHelper;

import javax.swing.*;


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
   //     if(Game.getInstance().getPlayer() == 2)
   //         deckJ2.flipCards();
        ScrollPane sd1 = new ScrollPane();
        sd1.setPrefWidth(300);
        sd1.setContent(deckJ1);
        grid.add(sd1,1,0);

        TableView placar = TableView.getInstance();
        grid.add(placar,2,0);

      //  table = new GridPane();
      //  table.setAlignment(Pos.CENTER);
      //  table.setPrefSize(950, 150);
      //  table.add(placar, 1, 0);

        DeckView deckJ2 = new DeckView(2);
     //   if(Game.getInstance().getPlayer() == 2)
     //       deckJ2.flipCards();
        ScrollPane sd2 = new ScrollPane();
        sd2.setPrefWidth(300);
        sd2.setContent(deckJ2);
        grid.add(sd2,3,0);
                
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
                   // Table.getInstance().battle();
                    break;                    
                case BATTLETIME:
                    alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Atenção !!");
                    alert.setHeaderText(null);
                    alert.setContentText("Hora de Batalhar!");
                    alert.showAndWait();
                    break;                    
                case ENDGAME:
                    alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Fim de Jogo !!");
                    alert.setHeaderText(null);
                    alert.setContentText("Vencedor: Jogador ");
                    alert.showAndWait();
                    break;
            }
        }
    }
    
}
