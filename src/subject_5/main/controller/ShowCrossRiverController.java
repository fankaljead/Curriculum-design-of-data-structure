package subject_5.main.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import subject_5.main.model.CrossRiver;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Author: Zhou Xianghui
 * Time: 2017/12/15 9:59
 * Description:
 */
public class ShowCrossRiverController implements Initializable {

    private static final long serialVersionUID = 1L;
    private CrossRiver cr = new CrossRiver();
    private int[] route = cr.getRoute();
    private int[] route2 = cr.getRoute2();
    private Object[][] dataRow = initData();



    @FXML
    protected GridPane show;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showAnswer();
    }


    //展示答案
    protected void showAnswer(){

        show.setPadding(new Insets(20));
        show.setHgap(100);
        show.setVgap(20);

        show.add(new Label("步骤"), 0, 0);
        show.add(new Label("南岸"), 1, 0);
        show.add(new Label("北岸"), 2, 0);

        for (int i = 0; i < dataRow.length; i++) {
            for (int j = 0; j < dataRow[i].length; j++) {
                Label label = new Label(String.valueOf(dataRow[i][j]).replaceAll("[]]", "").replaceAll("\\[", ""));
                label.setBorder(new Border(new BorderStroke(Color.YELLOW, BorderStrokeStyle.SOLID, null, new BorderWidths(5))));


                show.add(label, j, i + 1);
            }
        }
    }


    //找到答案
    private Object[][] initData() {
        for (int i = 0; i < route.length; i++) {
            System.out.print(route[i] + "  ");
        }
        String[] item = {"农夫 ", "狼", "羊", "白菜"};
        Object[][] dataRow = new Object[8][3];
        int count = 0;
        dataRow[count][0] = "0";
        dataRow[count][1] = "[农夫,狼,羊,白菜]";
        dataRow[count++][2] = "";
        int index = 0;

        while (route[index] != -1) {
            List<String> south = new ArrayList<String>();//南岸
            List<String> north = new ArrayList<String>();//北岸

            @SuppressWarnings("static-access")
            int[] stateArray = cr.getEachState(route[index]); // 获得状态数组
            int i = 0;
            for (; i < stateArray.length; i++) {
                if (stateArray[i] == 0) {
                    south.add(item[i]);
                } else {
                    north.add(item[i]);
                }
            }
            // 装载数据
            dataRow[count][0] = count;
            // 如果south不为空
            if (!south.toString().equals("[]")) {
                dataRow[count][1] = south.toString();
            } else {
                dataRow[count][1] = "";
            }
            dataRow[count++][2] = north.toString();
            index = route[index];
        }

        for (int i = 0; i < dataRow.length; i++) {
            for (int j = 0; j < dataRow[i].length; j++) {
                System.out.print("dataRow[" + i + ", " + j + "]: " + dataRow[i][j] + "\t");
            }
            System.out.println();
        }

        return dataRow;
    }

}
