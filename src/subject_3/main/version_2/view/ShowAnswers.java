package subject_3.main.version_2.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import subject_3.main.version_1.model.TailGame;

import java.util.*;

/**
 * Author: Zhou Xianghui
 * Time: 2017/12/8 18:58
 * Description:
 */
public class ShowAnswers extends Application{

    private static final String PATH = "../../coin/";
    private static final String SUFFIX = ".jpg";

    private TailGame tailGame;
    private char[] inputFormCoins;

    //构造方法
    public ShowAnswers() {
    }

    public ShowAnswers(char[] inputFormCoins) {
        this.inputFormCoins = inputFormCoins;
    }

    public ShowAnswers(TailGame tailGame) {
        this.tailGame = tailGame;
    }

    public ShowAnswers(TailGame tailGame, char[] inputFormCoins) {
        this.tailGame = tailGame;
        this.inputFormCoins = inputFormCoins;
    }



    //Get 与 Set方法
    public char[] getInputFormCoins() {
        return inputFormCoins;
    }

    public void setInputFormCoins(char[] inputFormCoins) {
        this.inputFormCoins = inputFormCoins;
    }


    public TailGame getTailGame() {
        return tailGame;
    }

    public void setTailGame(TailGame tailGame) {
        this.tailGame = tailGame;
    }


    //start方法
    @Override
    public void start(Stage primaryStage) throws Exception {

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(20));


        Scene scene = new Scene(vBox);

//        primaryStage.setResizable(false);
        tailGame.getIndex(inputFormCoins);
        List<Integer> path = tailGame.getShortestPath(tailGame.getIndex(inputFormCoins));

        //当大小为1时，无解
        if(path.size() == 1 || path == null){
            vBox.getChildren().add(new Label("无解"));
        }
        else {
            int hBoxNum = (int)Math.ceil((double) path.size() / 5);
            HBox[] hBox = new HBox[hBoxNum];
            primaryStage.setHeight(hBox.length * tailGame.getRows() * 75);
            for (int i = 0; i < hBoxNum; i++) {
                hBox[i] = new HBox();
                vBox.getChildren().add(hBox[i]);
            }

            for (int k = 0; k < path.size(); k++) {

                hBox[k/5].setSpacing(50);
                hBox[k/5].setPadding(new Insets(10));


                GridPane showCoins = new GridPane();
                showCoins.setPadding(new Insets(5));
                showCoins.setBorder(new Border(new BorderStroke(Color.YELLOW, BorderStrokeStyle.SOLID, null, new BorderWidths(5))));

                char[] tempCoins = tailGame.getNode(path.get(k).intValue());
                showCoins.getChildren().clear();
                for (int i = 0; i < tailGame.getRows(); i++) {
                    for (int j = 0; j < tailGame.getColumns(); j++) {
                        Image image = new Image(getClass().getResource(PATH + tempCoins[i * tailGame.getRows() + j] + SUFFIX).toExternalForm());//设置图片
                        ImageView imageView = new ImageView(image);

                        showCoins.add(imageView, j, i);
                    }
                }
                hBox[k/5].getChildren().add(showCoins);
            }
        }




        primaryStage.setTitle("硬币问题答案");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
