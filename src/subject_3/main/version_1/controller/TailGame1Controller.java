package subject_3.main.version_1.controller;

import com.jfoenix.controls.JFXSlider;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import subject_3.main.version_1.model.TailGame;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Author: Zhou Xianghui
 * Time: 2017/12/7 20:39
 * Description: 硬币问题 控制类 版本1
 */
public class TailGame1Controller implements Initializable{

    private static final String PATH = "../../coin/";
    private static final String SUFFIX = ".jpg";
    private int rowNum = 3;
    private int columnNum = 3;

    @FXML
    private GridPane showCoins;
    @FXML
    private JFXSlider selectColumnNum;
    @FXML
    private JFXSlider selectRowNum;

    private TailGame tailGame;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void startAction(MouseEvent e){
        startGame();
    }

    @FXML
    private void rowSelected(MouseEvent e){
        System.out.println((int)selectColumnNum.getValue());
        this.rowNum = (int)selectRowNum.getValue();
        System.out.println("row: " + this.rowNum + "\ncolumn: " +this.columnNum);
        startGame();
    }

    @FXML
    private void columnSelected(MouseEvent e){
        System.out.println((int)selectColumnNum.getValue());
        this.columnNum = (int)selectColumnNum.getValue();
        System.out.println("row: " + this.rowNum + "\ncolumn: " +this.columnNum);
        startGame();

    }


    private void startGame(){
        tailGame = new TailGame(this.rowNum, this.columnNum);
        showCoins.getChildren().clear();
        for (int i = 0; i < tailGame.getRows(); i++) {
            for (int j = 0; j < tailGame.getColumns(); j++) {
                Image image = new Image(getClass().getResource(PATH + 'H' + SUFFIX).toExternalForm());//设置图片
                ImageView imageView = new ImageView(image);
                imageView.setId(String.valueOf(i * tailGame.getRows() + j));
                showCoins.add(imageView, j, i);
            }
        }
    }


}
