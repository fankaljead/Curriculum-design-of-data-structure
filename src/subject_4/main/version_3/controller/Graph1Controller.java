package subject_4.main.version_3.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.text.Text;
import subject_4.main.version_3.model.City;
import subject_4.main.version_3.model.USMap;
import util.graph.AbstractGraph;
import util.graph.Graph;


import java.net.URL;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Author: Zhou Xianghui
 * Time: 2017/12/18 15:09
 * Description:
 */
public class Graph1Controller implements Initializable{

    @FXML
    private Pane showMap;

    @FXML
    private JFXTextField startCity;

    DrawWeightGraph drawWeightGraph = new DrawWeightGraph();
    AbstractGraph.Tree tree;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loadMap(tree);
    }

    //加载地图
    public void loadMap(AbstractGraph.Tree tree){
        drawWeightGraph.loadMap(tree, showMap);
    }

    //广度优先
    @FXML
    private void dfsAction(MouseEvent event){
        showMap.getChildren().clear();

        try{
            tree = drawWeightGraph.usMap.graph.dfs(getRoot(startCity.getText()));
            loadMap(tree);


        }catch (Exception ee){

        }

    }

    //得到某个城市的顶点
    private int getRoot(String text) {
        for (int i = 0; i < drawWeightGraph.usMap.vertices.length; i++) {
            if(text.compareTo(drawWeightGraph.usMap.vertices[i].getName()) == 0){
                return i;
            }
        }

        return -1;
    }

    //深度优先
    @FXML
    private void bfsAction(MouseEvent event){
        showMap.getChildren().clear();

        try{
            tree = drawWeightGraph.usMap.graph.bfs(getRoot(startCity.getText()));
            loadMap(tree);


        }catch (Exception ee){

        }
    }
}
