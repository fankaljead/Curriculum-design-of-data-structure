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

    public static final double RADIUS = 3;


    @FXML
    private Pane showMap;



    @FXML
    private JFXTextField startCity;

    private USMap usMap = new USMap();
    private Graph<City> graph = usMap.graph;
    private List<City> vertices = graph.getVertices();
    AbstractGraph.Tree tree;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loadMap(tree);
    }

    //加载地图
    private void loadMap(AbstractGraph.Tree tree){

//        //加载城市
//        for (int i = 0; i < usMap.vertices.length; i++) {
//
//            Circle circle = new Circle(usMap.vertices[i].getX(), usMap.vertices[i].getY(), RADIUS);
//            Text text = new Text(usMap.vertices[i].getX() + RADIUS, usMap.vertices[i].getY() + RADIUS, usMap.vertices[i].getName());
//
//            showMap.getChildren().addAll(circle, text);
//        }
//
//        //加载边
//        for (int i = 0; i < usMap.edges.length; i++) {
//            Line line = new Line(usMap.vertices[usMap.edges[i][0]].getX(),
//                    usMap.vertices[usMap.edges[i][0]].getY(),
//                    usMap.vertices[usMap.edges[i][1]].getX(),
//                    usMap.vertices[usMap.edges[i][1]].getY());
//
//            showMap.getChildren().add(line);
//        }
        for (int i = 0; i < graph.getSize(); i++) {
            double x = vertices.get(i).getX();
            double y = vertices.get(i).getY();
            String name = vertices.get(i).getName();

            Circle circle = new Circle(x, y, 6);
            circle.setFill(Color.BLACK);

            Text text = new Text(x - 15, y - 15, name+"");

            showMap.getChildren().addAll(circle, text);

        }

        // 画出边
        for (int i = 0; i < graph.getSize(); i++) {
            List<Integer> edges = graph.getNeighbors(i);
            for (int j = 0; j < edges.size(); j++) {
                // 获取到相邻节点
                int v = edges.get(j);

                // 分别获取两个点的横坐标和纵坐标
                double x1 = graph.getVertex(i).getX();
                double y1 = graph.getVertex(i).getY();
                double x2 = graph.getVertex(v).getX();
                double y2 = graph.getVertex(v).getY();

                // 连接直线
                Line line = new Line(x1, y1, x2, y2);
                showMap.getChildren().add(line);
            }
        }

        // 搜索过的树进行高亮操作
        if (tree != null) {
            for(int i = 0; i < graph.getSize(); i++) {
                if (tree.getParent(i) != -1) {
                    // 进行图形的重绘
                    int v = tree.getParent(i);
                    double x1 = graph.getVertex(i).getX();
                    double y1 = graph.getVertex(i).getY();
                    double x2 = graph.getVertex(v).getX();
                    double y2 = graph.getVertex(v).getY();
                    // 划线
                    drawingArrowhead(x2, y2, x1, y1, showMap);
                }
            }

        }
    }

    //广度优先
    @FXML
    private void dfsAction(MouseEvent event){
        showMap.getChildren().clear();

        try{
            tree = usMap.graph.dfs(getRoot(startCity.getText()));
            loadMap(tree);


        }catch (Exception ee){

        }

    }

    //得到某个城市的顶点
    private int getRoot(String text) {
        for (int i = 0; i < usMap.vertices.length; i++) {
            if(text.compareTo(usMap.vertices[i].getName()) == 0){
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
            tree = usMap.graph.bfs(getRoot(startCity.getText()));
            loadMap(tree);


        }catch (Exception ee){

        }
    }






    // 对遍历的路线增加箭头
    private void drawingArrowhead(double x1, double y1, double x2, double y2, Pane pane){

        Line line = new Line(x1, y1, x2, y2);
        line.setFill(Color.RED);
        line.setStroke(Color.RED);
        pane.getChildren().add(line);

        // 斜率
        double slope = ((((double) y1) - (double) y2)) / (((double) x1) - (((double) x2)));

        double arctan = Math.atan(slope);

        // This will flip the arrow 45 off of a
        // perpendicular line at pt x2
        double set45 = 1.57 / 2;

        // 箭头永远指向i 而不是 i+1
        if (x1 < x2) {
            // add 90 degrees to arrow lines
            set45 = -1.57 * 1.5;
        }

        // set length of arrows
        int arrlen = 15;

        Line line1 = new Line(x2, y2, (x2 + (Math.cos(arctan + set45) * arrlen)), ((y2)) + (Math.sin(arctan + set45) * arrlen));
        Line line2 = new Line(x2, y2, (x2 + (Math.cos(arctan - set45) * arrlen)), ((y2)) + (Math.sin(arctan - set45) * arrlen));

        line1.setStroke(Color.RED);
        line2.setStroke(Color.RED);
        // 在线上加箭头
        pane.getChildren().add(line1);
        pane.getChildren().add(line2);
    }


}
