package pucrs.ep.poo.cartas.gui;

import java.util.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import pucrs.ep.poo.cartas.modelo.Game;

public class TableView extends GridPane implements Observer{
    private TextField vida1,vida2;

    public TableView(){
        this.setAlignment(Pos.CENTER);
        this.setHgap(10);
        this.setVgap(10);
        this.setPadding(new Insets(25, 25, 25, 25));

        Game.getInstance().addObserver(this);
        //Declarar elemento:
        Button butBattle = new Button("Batalhar");

        butBattle.setStyle("-fx-background-color: black");

        butBattle.setPrefSize(100, 40);


        //Adicionar ação:
        butBattle.setOnAction(e -> {
            System.out.println(e.getClass());
            Game.getInstance().removeSelected();
        });

        vida1 = new TextField();
        vida2 = new TextField();

        vida1.setText(""+Game.getInstance().getvida1());
        vida2.setText(""+Game.getInstance().getvida2());

        this.add(new Label("Vida do jogador 1:"),0,0);
        this.add(vida1,1,0);
        this.add(butBattle,2,0);
        this.add(new Label("Vida do jogador 2:"),3,0);
        this.add(vida2,4,0);
    }

    @Override
    public void update(Observable o,Object arg){
        vida1.setText(""+Game.getInstance().getvida1());
        vida2.setText(""+Game.getInstance().getvida2());
    }
}

