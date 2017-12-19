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


    Alert alert = new Alert(Alert.AlertType.INFORMATION);   // 弹出框

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadMap();
    }

    @FXML
    private void shortestAction(MouseEvent e){

        //判断输入是否为空
        if(startCity.getText().length() == 0 || endCity.getText().length() == 0){
            alert.setContentText("输入不能为空");
            alert.show();
        }

        else {

            //获取起点城市的下标
            int index1 = drawWeighedGraph.graph.getIndex(drawWeighedGraph.usMap.useNameFindCity.get(startCity.getText()));
            //获取终点城市的下标
            int index2 = drawWeighedGraph.graph.getIndex(drawWeighedGraph.usMap.useNameFindCity.get(endCity.getText()));

            //判断起点城市是否在图中
            if(index1 < 0){
                alert.setContentText(startCity.getText() + "不在地图中");
                alert.show();
                return;
            }
            //判断终点城市是否在图中
            if(index2 < 0){
                alert.setContentText(endCity.getText() + "不在地图中");
                alert.show();
                return;
            }

            //绘制最短路径
            else{
                showMap.getChildren().clear();
                //得到最短路径
                List<City> shortestPath = drawWeighedGraph.graph.getShortestPathTree(index1).getPath(index2);
                //设置图的最短路径
                drawWeighedGraph.path = shortestPath;
                //绘制路线
                drawWeighedGraph.loadMap(showMap);
            }

        }

    }

    //初始化图
    private void loadMap(){
        showMap.getChildren().clear();
        drawWeighedGraph.loadMap(showMap);
    }
}
