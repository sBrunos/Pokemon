package pucrs.ep.poo.cartas.gui;

import java.util.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

        vida1 = new TextField();
        vida2 = new TextField();

        vida1.setText(""+Game.getInstance().getvida1());
        vida2.setText(""+Game.getInstance().getvida2());

        this.add(new Label("Vida do jogador 1:"),0,0);
        this.add(vida1,1,0);
        this.add(new Label("Vida do jogador 2:"),0,1);
        this.add(vida2,1,1);
    }

    @Override
    public void update(Observable o,Object arg){
        vida1.setText(""+Game.getInstance().getvida1());
        vida2.setText(""+Game.getInstance().getvida2());
    }
}

