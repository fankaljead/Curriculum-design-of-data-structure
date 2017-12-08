package subject_3.main.version_3.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import subject_3.main.version_1.model.TailGame;
import subject_3.main.version_2.controller.TailGame2Controller;

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

    @FXML
    private JFXButton changeRules;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
    }


    private void changeRulesEvents(){
        System.out.println("改变规则");
        changeRules.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                String source2 = e.getPickResult().getIntersectedNode().getId();
                System.out.println(e);
            }
        });
    }

    @FXML
    protected void selectAction(MouseEvent e){
       super.selectAction(e);
        System.out.println("2333");
        String source2 = e.getPickResult().getIntersectedNode().getId(); //获取点击的节点id
        rulesNum.add(Integer.valueOf(source2));
        System.out.println(rulesNum.toString());
    }
}
