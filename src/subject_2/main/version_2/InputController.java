package subject_2.main.version_2;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import subject_2.main.version_1.Points1;


/**
 * Author: Zhou Xianghui
 * Time: 2017/12/18 14:22
 * Description:
 */
public class InputController {
    @FXML
    private JFXTextField input1;
    @FXML
    private JFXTextField input2;
    @FXML
    private JFXTextField input3;
    @FXML
    private JFXTextField input4;
    @FXML
    private Text answerText;

    @FXML
    private void answerAction(MouseEvent e){

        int[] num = new int[4];

        try {
            num[0] = Integer.valueOf(input1.getText());
            num[1] = Integer.valueOf(input2.getText());
            num[2] = Integer.valueOf(input3.getText());
            num[3] = Integer.valueOf(input4.getText());
        }catch (NumberFormatException ee){
            answerText.setText("输入错误");
        }

        Points1 points1 = new Points1(num);
        if(points1.getAnswers().size() == 0){
            answerText.setText("无解");
        }
        else {
            answerText.setText(points1.getAnswers().get(0));
        }


    }
}
