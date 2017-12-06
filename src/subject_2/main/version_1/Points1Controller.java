package subject_2.main.version_1;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import subject_2.main.Calculator;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Author: Zhou Xianghui
 * Time: 2017/12/5 10:17
 * Description:
 */
public class Points1Controller implements Initializable {

    private static final String PATH = "../cards/";
    private static final String SUFFIX = ".jpg";

    @FXML
    private JFXButton btStart;

    @FXML
    private JFXButton btEnter;

    @FXML
    private HBox showCards;

    @FXML
    private TextField text;

    private Points1 points1;
    private ImageView[] imageView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        bindBtStartEvents();
    }

    private void bindBtStartEvents() {
        btStart.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btStart.setText("刷新");
                showCards.getChildren().clear();
                points1 = new Points1();
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
                int yourAnswer = (int)Calculator.calculateInfix(yourAnswerString);
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


    private void btEndAction(MouseEvent e){
        btStart.getScene().getWindow();
    }


}
