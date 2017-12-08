package subject_3.main.version_2.controller;

import com.jfoenix.controls.JFXSlider;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import subject_3.main.version_1.model.TailGame;
import subject_3.main.version_2.view.ShowAnswers;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Author: Zhou Xianghui
 * Time: 2017/12/7 20:39
 * Description: 硬币问题 控制类 版本1
 */
public class TailGame2Controller implements Initializable{

    private static final String PATH = "../../coin/";
    private static final String SUFFIX = ".jpg";
    private int rowNum = 4;
    private int columnNum = 4;
    private ImageView imageView;
    private char[] inputFormCoins = new char[rowNum * columnNum];

    @FXML
    private GridPane showCoins;
    @FXML
    private JFXSlider selectColumnNum;
    @FXML
    private JFXSlider selectRowNum;

    private TailGame tailGame;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        selectColumnNum.setValue(4);
        selectRowNum.setValue(4);
        startGame();

        //选择行的事件监听
        selectRowNum.valueProperty().addListener((observable, oldValue, newValue) -> {
            startGame();
        });

        //选择列的事件监听
        selectColumnNum.valueProperty().addListener((observable, oldValue, newValue) -> {
            startGame();
        });


    }

    @FXML
    private void startAction(MouseEvent e){
        startGame();
    }


    //查看解的情况
    @FXML
    private void checkAnswersAction(MouseEvent e) throws Exception {

        tailGame = new TailGame(this.rowNum, this.columnNum);

        ShowAnswers showAnswers = new ShowAnswers(tailGame, inputFormCoins);
        showAnswers.start(new Stage());
    }

    //选取要求解的情况 -> 输入
    @FXML
    private void selectAction(MouseEvent e){
        String source1 = e.getSource().toString(); //yields complete string
        String source2 = e.getPickResult().getIntersectedNode().getId(); //returns JUST the id of the object that was clicked
        System.out.println("Full String: " + source1);
        System.out.println("Just the id: " + source2);
        System.out.println(" " + source2);
        imageView = (ImageView) (showCoins.getScene().lookup("#" + source2));
        if(inputFormCoins[Integer.valueOf(source2)] == 'H'){
            imageView.setImage(new Image(getClass().getResource(PATH + 'T' + SUFFIX).toExternalForm()));
            inputFormCoins[Integer.valueOf(source2)] = 'T';
        }else {
            imageView.setImage(new Image(getClass().getResource(PATH + 'H' + SUFFIX).toExternalForm()));
            inputFormCoins[Integer.valueOf(source2)] = 'H';
        }
        tailGame = new TailGame(this.rowNum, this.columnNum);

    }




    private void startGame(){
//        tailGame = new TailGame(this.rowNum, this.columnNum);
        initialInput();
        this.rowNum = (int)selectRowNum.getValue();
        this.columnNum = (int)selectColumnNum.getValue();
        System.out.println("row: " + this.rowNum + "\tcolumn: " +this.columnNum);


        showCoins.getChildren().clear();
        for (int i = 0; i < this.rowNum; i++) {
            for (int j = 0; j < this.columnNum; j++) {
                Image image = new Image(getClass().getResource(PATH + 'H' + SUFFIX).toExternalForm());//设置图片
                ImageView imageView = new ImageView(image);
                imageView.setId(String.valueOf(i * this.rowNum + j));
                showCoins.add(imageView, j, i);
            }
        }
    }



    //初始化输入
    private void initialInput(){
        for (int i = 0; i < inputFormCoins.length; i++) {
            inputFormCoins[i] = 'H';
        }
    }

}
