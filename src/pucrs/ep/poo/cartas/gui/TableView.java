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
    private Label vida1,vida2;
    private Table table;
    private static TableView instance = null;
    private Button pane1, pane2;
    private int vidaP1, vidaP2;
    GridPane placar;
    Button butBattle;


    private TableView(){
        Table.getInstance().addObserver(this);
        placar = new GridPane();
        table = Table.getInstance();

        placar.setAlignment(Pos.TOP_CENTER);
        placar.setHgap(10);
        placar.setVgap(10);
        placar.setPadding(new Insets(25, 25, 25, 25));
        //this.setStyle("-fx-background-color: blue");
        Game.getInstance().addObserver(this);
        //Declarar elemento:

        butBattle = new Button("Batalhar");

        butBattle.setPrefSize(100, 40);

        //Adicionar ação:
        if(Game.getInstance().getPlayer() == 3) {
            butBattle.setOnAction(e -> {
                table.battle();
            });
        }
        else {
            butBattle.setOnAction(null);
        }

        vida1 = new Label();
        vida2 = new Label();

        vida1.setText(""+Game.getInstance().getvida1());
        vida2.setText(""+Game.getInstance().getvida2());

        placar.add(vida1,1,0);
        // if (table.getPokJog1() != null && table.getPokJog2() != null)
        placar.add(butBattle,2,0);
        placar.add(vida2,3,0);

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
        this.add(cartas, 0, 1);
    }

    public static TableView getInstance(){
        if(instance == null)
            instance = new TableView();
        return instance;
    }

    public void setImagem(CardView cardV, int jogador){
        Card card = cardV.getCard();
        if (jogador == 1){
            if( card instanceof PokemonCard ) {
                table.setPokJog1((PokemonCard) card);
                pane1.setGraphic(ImageFactory.getInstance().createImage(card.getNomePokemon()));
            }
        }else{
            if( card instanceof PokemonCard ) {
                table.setPokJog2((PokemonCard) card);
                pane2.setGraphic(ImageFactory.getInstance().createImage(card.getNomePokemon()));
            }
        }
    }

    public void setVida1(int vida) {vidaP1 = vida;}

    public void setVida2(int vida) {vidaP2 = vida;}
    //public Table getTable() { return table; }

    @Override
    public void update(Observable o,Object arg){
        vida1.setText("a"+Game.getInstance().getvida1() + vidaP1);
        vida2.setText(""+Game.getInstance().getvida2() + vidaP2);

        if (Table.getInstance().getPokJog1() == null)
            pane1.setGraphic(null);
        if (Table.getInstance().getPokJog2() == null)
            pane2.setGraphic(null);
    }
}

