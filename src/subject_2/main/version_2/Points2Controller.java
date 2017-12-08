package subject_2.main.version_2;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import subject_2.main.Calculator;
import subject_2.main.version_1.Points1;

import java.net.URL;
import java.util.AbstractList;
import java.util.ResourceBundle;

/**
 * Author: Zhou Xianghui
 * Time: 2017/12/6 18:34
 * Description:
 */
public class Points2Controller implements Initializable {

    private static final String PATH = "../cards/";
    private static final String SUFFIX = ".jpg";

    @FXML
    private JFXButton btStart;

    @FXML
    private JFXButton btEnter;

    @FXML
    private Label answer;

    private int i = 0;
    private AbstractList<String> answers;

    @FXML
    private HBox showCards;

    @FXML
    private TextField text;

    private Points1 points1;
    private ImageView[] imageView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        startGame();
        bindTextEvents();
        bindBtStartEvents();
    }

    private void bindBtStartEvents() {
        btStart.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                startGame();
            }
        });
    }

    @FXML
    private void btEnterAction(MouseEvent e){
        if(text.getText() == "" || text.getText().compareTo("") == 0 || points1 == null){
            System.out.println();
        }
        else {
            String yourAnswerString = text.getText();
            if(points1.isEqualCards(yourAnswerString)){
                int yourAnswer = (int) Calculator.calculateInfix(yourAnswerString);
                if(yourAnswer == points1.getPoint()){
                    text.setText("你的答案正确");

                }
                else {
                    text.setText("你的答案不正确");


                }
            }else {
                text.setText("你的输入不正确");
            }

        }
    }

    @FXML
    private void btEndAction(MouseEvent e){
        btStart.getScene().getWindow().hide();
    }

    @FXML
    private void btFindAnswerAction(MouseEvent e){
        if(points1 != null){
            if(points1.getAnswers().size() == 0){
                answer.setText("无解");
            }else {
                answer.setText(answers.get(0));
            }
        }
    }

    //查看上一个答案
    @FXML
    private void preAction(MouseEvent e){
        if(i > 0){
            answer.setText(answers.get(--i));
            System.out.println(i);
        }
        else if( i == 0){
            answer.setText(answer.getText() + "\n已经到了第一个");
        }
    }

    //查看下一个答案
    @FXML
    private void nextAction(MouseEvent e){
        if(i >= 0 && i < answers.size() -1){
            answer.setText(answers.get(++i));
            System.out.println(i);

        }
        else if( i == answers.size()){
            answer.setText(answer.getText() + "\n已经到了最后一个");
        }
    }

    private void bindTextEvents(){
        text.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(text.isFocused()){
                    text.setText("");
                    System.out.println(text.isFocused());
                }
            }
        });
    }



    //开始游戏
    private void startGame(){
        btStart.setText("刷新");
        answer.setText("");
        showCards.getChildren().clear();
        points1 = new Points1();
        answers = points1.getAnswers();
        imageView = new ImageView[points1.getCardNumber()];
        for (int i = 0; i < imageView.length; i++) {
            imageView[i] = new ImageView();
        }

        //关键
//                Image image = new Image(getClass().getResource("../cards/2.jpg").toExternalForm());//设置图片
//                card2.setImage(image);//设置图片

        for (int i = 0; i < points1.getCardRealNumber().length; i++) {
            Image image = new Image(getClass().getResource(PATH + points1.getCardRealNumber()[i] + SUFFIX).toExternalForm());//设置图片
            imageView[i] = new ImageView(image);
            showCards.getChildren().add(imageView[i]);
        }

    }



}
