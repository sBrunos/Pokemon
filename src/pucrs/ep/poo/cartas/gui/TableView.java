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
import pucrs.ep.poo.cartas.modelo.*;

public class TableView extends GridPane implements Observer {
    private Label vida1,vida2, vidapk1, vidapk2, atkpk1, atkpk2;
    private Table table;
    private static TableView instance = null;
    private Button pane1, pane2;
    private int vidaP1, vidaP2;
    GridPane placar;
    Button butBattle;


    private TableView(){
        table = Table.getInstance();

        Table.getInstance().addObserver(this);

        placar = new GridPane();

        placar.setAlignment(Pos.TOP_CENTER);
        placar.setHgap(10);
        placar.setVgap(10);
        placar.setPadding(new Insets(25, 25, 25, 25));
        //this.setStyle("-fx-background-color: blue");
        Game.getInstance().addObserver(this);
        //Declarar elemento:

        butBattle = new Button("Batalhar");

        butBattle.setPrefSize(100, 40);

        //Adicionar ação: if (Table.getInstance().inBattleMode())
        //            butBattle.setOnAction(e -> {
        //                table.battle();
        //            });

        butBattle.setOnAction(e -> {
            table.battle();
        });

        vida1 = new Label();
        vida2 = new Label();
        vidapk1 = new Label();
        vidapk2 = new Label();
        atkpk1 = new Label();
        atkpk2 = new Label();

        vida1.setText(""+Game.getInstance().getvida1());
        vida2.setText(""+Game.getInstance().getvida2());

        vidapk1.setText(""+0);
        vidapk2.setText(""+0);
        atkpk1.setText(""+0);
        atkpk2.setText(""+0);

        placar.add(vidapk1,0,0);
        //placar.add(new Label("<--- vida"), 1, 0);
        placar.add(atkpk1,0,1);
        //placar.add(new Label("<- ataque"), 1, 1);
        placar.add(vida1,6,0);
        placar.add(butBattle,7,0);
        placar.add(vida2,8,0);
        //placar.add(new Label("vida ---> "), 12, 0);
        placar.add(vidapk2,13,0);
        //placar.add(new Label("ataque -> "), 12, 1);
        placar.add(atkpk2,13,1);

        GridPane cartas = new GridPane();
        cartas.setAlignment(Pos.BOTTOM_CENTER);

        //Carta jogador1:
        pane1 = new Button();
        //Carta jogador2:
        pane2 = new Button();


        pane1.setStyle("-fx-background-color: #dedccc");
        pane2.setStyle("-fx-background-color: #ceccbc");

        //Largura da mesa
        pane1.setMinWidth(300);
        pane2.setMinWidth(300);

        //Altura da mesa
        pane1.setMinHeight(500);
        pane2.setMinHeight(500);

        cartas.add(pane1,0,0);
        cartas.add(pane2, 1,0);


        this.add(placar, 0,0);
        //this.add(placar2, 0,0);
        this.add(cartas, 0, 1);
    }

    public static TableView getInstance(){
        if(instance == null)
            instance = new TableView();
        return instance;
    }

    public void setImagem(CardView cardV, int jogador){
        Card card = cardV.getCard();
        if (card instanceof SpecialCard)
            throw new IllegalArgumentException("Foi recebido uma SpecialCard quando se esperava uma PokemonCard.");

        if (jogador == 1){
                pane1.setGraphic(ImageFactory.getInstance().createImage(card.getNomePokemon()));
        }else{
                pane2.setGraphic(ImageFactory.getInstance().createImage(card.getNomePokemon()));
        }
    }

    public void setVida1(int vida) {vidaP1 = vida;}

    public void setVida2(int vida) {vidaP2 = vida;}
    //public Table getTable() { return table; }

    @Override
    public void update(Observable o,Object arg){
        vida1.setText(""+Game.getInstance().getvida1());
        vida2.setText(""+Game.getInstance().getvida2());

        vidapk1.setText(""+Table.getInstance().getVida1());
        vidapk2.setText(""+Table.getInstance().getVida2());
        atkpk1.setText(""+Table.getInstance().getAtaque1());
        atkpk2.setText(""+Table.getInstance().getAtaque2());

        if (Table.getInstance().getPokJog1() == null)
            pane1.setGraphic(null);
        if (Table.getInstance().getPokJog2() == null)
            pane2.setGraphic(null);

        SpecialCard s1 = Table.getInstance().getEspJ1();
        SpecialCard s2 = Table.getInstance().getEspJ2();

        if(s1 != null) {
            if ( s1.getId().equals("C11") )
                pane1.setStyle("-fx-background-color: #fa4b4b");
            else
                pane1.setStyle("-fx-background-color: #a5f097");
        }
        else
            pane1.setStyle("-fx-background-color: #dedccc");

        if(s2 != null) {
            if ( s2.getId().equals("C11") )
                pane2.setStyle("-fx-background-color: #fa4b4b");
            else
                pane2.setStyle("-fx-background-color: #a5f097");
        }
        else
            pane2.setStyle("-fx-background-color: #ceccbc");
    }
}

