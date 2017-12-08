package subject_3.main.version_2.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
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
        HBox hBox = new HBox();
        hBox.setSpacing(50);
        hBox.setPadding(new Insets(10));

        Scene scene = new Scene(hBox);
        primaryStage.setHeight(280);

        List<Integer> path = tailGame.getShortestPath(tailGame.getIndex(inputFormCoins));

        //当大小为1时，无解
        if(path.size() == 1){
            hBox.getChildren().add(new Label("无解"));
        }
        else {

            for (int k = 0; k < path.size(); k++) {

                GridPane showCoins = new GridPane();
                char[] tempCoins = tailGame.getNode(path.get(k).intValue());
                showCoins.getChildren().clear();
                for (int i = 0; i < tailGame.getRows(); i++) {
                    for (int j = 0; j < tailGame.getColumns(); j++) {
                        Image image = new Image(getClass().getResource(PATH + tempCoins[i * tailGame.getRows() + j] + SUFFIX).toExternalForm());//设置图片
                        ImageView imageView = new ImageView(image);
                        showCoins.add(imageView, j, i);
                    }
                }
                hBox.getChildren().add(showCoins);
            }
        }




        primaryStage.setTitle("硬币问题答案");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
