package pucrs.ep.poo.cartas.gui;

import java.util.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import pucrs.ep.poo.cartas.modelo.CardObserver;
import pucrs.ep.poo.cartas.modelo.Game;
import pucrs.ep.poo.cartas.modelo.ImageFactory;

public class TableView extends GridPane implements Observer {
    private Label vida1,vida2;
    private static TableView instance = null;
    private Button pane1;
    private Button pane2;
    private TableView(){
        GridPane placar = new GridPane();

        placar.setAlignment(Pos.TOP_CENTER);
        placar.setHgap(10);
        placar.setVgap(10);
        placar.setPadding(new Insets(25, 25, 25, 25));
        //this.setStyle("-fx-background-color: blue");
        Game.getInstance().addObserver(this);
        //Declarar elemento:
        Button butBattle = new Button("Batalhar");

        butBattle.setPrefSize(100, 40);


        //Adicionar ação:
        butBattle.setOnAction(e -> {
            System.out.println(e.getClass());
            Game.getInstance().removeSelected();
        });

        vida1 = new Label();
        vida2 = new Label();



        vida1.setText(""+Game.getInstance().getvida1());
        vida2.setText(""+Game.getInstance().getvida2());

        placar.add(new Label("Vida do jogador 1:"),0,0);
        placar.add(vida1,1,0);
        placar.add(butBattle,2,0);
        placar.add(new Label("Vida do jogador 2:"),3,0);
        placar.add(vida2,4,0);


        GridPane cartas = new GridPane();
        cartas.setAlignment(Pos.BOTTOM_CENTER);

        //Carta jogador1:
        pane1 = new Button();
        //Carta jogador2:
        pane2 = new Button();


        pane1.setStyle("-fx-background-color: red");
        pane2.setStyle("-fx-background-color: black");

        //Largura da mesa
        pane1.setMinWidth(300);
        pane2.setMinWidth(300);

        //Altura da mesa
        pane1.setMinHeight(500);
        pane2.setMinHeight(500);

        cartas.add(pane1,0,0);
        cartas.add(pane2, 1,0);


        this.add(placar, 0,0);
        this.add(cartas, 0, 1);
    }

    public static TableView getInstance(){
        if(instance == null)
            instance = new TableView();
        return instance;
    }


    public void getImagem(String id){
        pane1.setGraphic(ImageFactory.getInstance().createImage(id));
    }

    @Override
    public void update(Observable o,Object arg){
        vida1.setText(""+Game.getInstance().getvida1());
        vida2.setText(""+Game.getInstance().getvida2());

    }
}

