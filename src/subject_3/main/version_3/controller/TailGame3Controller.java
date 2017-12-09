package subject_3.main.version_3.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import subject_3.main.version_1.model.TailGame;
import subject_3.main.version_2.controller.TailGame2Controller;
import subject_3.main.version_2.view.ShowAnswers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Author: Zhou Xianghui
 * Time: 2017/12/8 19:27
 * Description:
 */
public class TailGame3Controller extends TailGame2Controller{

    protected ArrayList<Integer> rulesNum = new ArrayList<>();
    protected ArrayList<TailGame.Rules> rules = new ArrayList<>();
    protected Label rulesLabel = new Label();
    protected boolean isStart = true;

    @FXML
    private VBox vBox;

    @FXML
    private JFXButton changeRules;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        //选择行的事件监听
        selectRowNum.valueProperty().addListener((observable, oldValue, newValue) -> {
            this.rowNum = (int)selectRowNum.getValue();
            this.columnNum = (int)selectColumnNum.getValue();
        });

        //选择列的事件监听
        selectColumnNum.valueProperty().addListener((observable, oldValue, newValue) -> {
            this.rowNum = (int)selectRowNum.getValue();
            this.columnNum = (int)selectColumnNum.getValue();
        });

        randomInitialImages();
        addImages();
        vBox.getChildren().add(rulesLabel);
    }




    //定义规则 点击硬币
    @FXML
    protected void selectAction(MouseEvent e){

        String source2 = e.getPickResult().getIntersectedNode().getId(); //获取点击的节点id returns JUST the id of the object that was clicked

        imageView = (ImageView) (showCoins.getScene().lookup("#" + source2));

        if(!isStart){

            if(inputFormCoins[Integer.valueOf(source2)] == 'H'){
                imageView.setImage(new Image(getClass().getResource(PATH + 'T' + SUFFIX).toExternalForm()));
                inputFormCoins[Integer.valueOf(source2)] = 'T';
            }else {
                imageView.setImage(new Image(getClass().getResource(PATH + 'H' + SUFFIX).toExternalForm()));
                inputFormCoins[Integer.valueOf(source2)] = 'H';
            }

            rulesNum.add(Integer.valueOf(source2));

        }else {
            tailGame = new TailGame(this.rowNum, this.columnNum, rules);

            flippedACoinEvents(Integer.valueOf(source2));

            addImages();

        }



    }


    protected void addImages(){
        showCoins.getChildren().clear();
        for (int i = 0; i < this.rowNum; i++) {
            for (int j = 0; j < this.columnNum; j++) {
                // if(i * this.rowNum + j != Integer.valueOf(source2)) {
                Image image = new Image(getClass().getResource(PATH + inputFormCoins[i * this.rowNum + j] + SUFFIX).toExternalForm());//设置图片
                ImageView imageView = new ImageView(image);
                imageView.setId(String.valueOf(i * this.rowNum + j));
                showCoins.add(imageView, j, i);
                //  }
            }
        }
    }




    protected void flippedACoinEvents(int position){

        tailGame.getFlippedNode(inputFormCoins, position);
    }

    //查看解的情况
    @FXML
    protected void checkAnswersAction(MouseEvent e) throws Exception {

        tailGame = new TailGame(this.rowNum, this.columnNum, rules);

        if(rules.size() >= 1){
            tailGame.setRules(rules);
        }

        ShowAnswers showAnswers = new ShowAnswers(tailGame, inputFormCoins);


        if(rules.size() > 1){
            showAnswers.getTailGame().setRules(rules);
        }
        showAnswers.start(new Stage());
    }


    @Override
    protected void startGame() {
        //initialInput();
        randomInitialImages();
        addImages();
        if(isStart){

            super.startGame();
            rulesLabel.setText("");
        }else {
            rulesLabel.setText("请确定规则");
        }

    }

    //刷新
    @FXML
    protected void startAction(MouseEvent e){
//        initialInput();
        randomInitialImages();
        addImages();
        //startGame();
    }



    //开始定义规则 修改规则按钮
    @FXML
    protected void changeRulesAction(MouseEvent e){
        initialInput();
        addImages();
        if(changeRules.getText().compareTo("修改规则") == 0){
            rulesNum.clear();
            rules.clear();
            changeRules.setText("确定规则");
            rulesLabel.setText("\n提示\n1: 选取一枚硬币作为中心\n2: 选择要翻转的硬币");
            isStart = false;
        }else {
            changeRules.setText("修改规则");
            addImages();
            rulesLabel.setText("");
            isStart = true;

            if(rulesNum.size() >= 1){
                int position = rulesNum.get(0);
//                rules.add(new TailGame.Rules(0, 0));
                for (int i = 0; i < rulesNum.size(); i++) {
                    int r_row = (rulesNum.get(i) - position) / super.rowNum;
                    int r_column = (rulesNum.get(i) - position) % super.columnNum;
                    rules.add(new TailGame.Rules(r_row, r_column));
                }

                //tailGame.setRules(rules);

            }

        }

    }

    @FXML
    protected void initialRulesAction(MouseEvent e){
        initialInput();
        rules.clear();
        rulesNum.clear();
        isStart = true;
        changeRules.setText("修改规则");
        rulesLabel.setText("");
        addImages();
    }



    protected void randomInitialImages(){
        for (int i = 0; i < super.rowNum * super.columnNum; i++) {
            if(Math.random() > 0.5){
                inputFormCoins[i] = TailGame.CHAR_OF_EACH_SIDE[1];
            }
            else {
                inputFormCoins[i] = TailGame.CHAR_OF_EACH_SIDE[0];
            }
        }
    }
}
