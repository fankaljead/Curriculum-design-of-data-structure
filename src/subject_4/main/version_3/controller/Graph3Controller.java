package subject_4.main.version_3.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import subject_4.main.version_3.model.City;
import util.graph.AbstractGraph;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Author: Zhou Xianghui
 * Time: 2017/12/18 21:26
 * Description:
 */
public class Graph3Controller implements Initializable {

    @FXML
    private Pane showMap;

    @FXML
    private JFXTextField startCity;

    @FXML
    private JFXTextField endCity;

    DrawWeighedGraph drawWeighedGraph = new DrawWeighedGraph();
    AbstractGraph.Tree tree;

    Alert alert = new Alert(Alert.AlertType.INFORMATION);   // 弹出框

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadMap();
    }

    @FXML
    private void shortestAction(MouseEvent e){

        if(startCity.getText().length() == 0 || endCity.getText().length() == 0){
            alert.setContentText("输入不能为空");
            alert.show();
        }else {
            int index1 = drawWeighedGraph.graph.getIndex(new City(startCity.getText(), 0, 0));
            int index2 = drawWeighedGraph.graph.getIndex(new City(endCity.getText(), 0, 0));
            if(index1<0){
                alert.setContentText(startCity.getText() + "不在地图中");
                alert.show();
                return;
            }
            if(index2 < 0){
                alert.setContentText(endCity.getText() + "不在地图中");
                alert.show();
                return;
            }
            else{
                showMap.getChildren().clear();
                //得到最短路径
                List<City> shortestPath = drawWeighedGraph.graph.getShortestPathTree(index1).getPath(index2);
                drawWeighedGraph.load(tree, showMap);
            }

        }

    }

    //初始化图
    private void loadMap(){
        showMap.getChildren().clear();
        drawWeighedGraph.load(tree, showMap);
    }
}
