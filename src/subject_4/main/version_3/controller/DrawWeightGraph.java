package subject_4.main.version_3.controller;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import subject_4.main.version_3.model.City;
import subject_4.main.version_3.model.USMap;
import util.graph.AbstractGraph;
import util.graph.Graph;
import util.graph.UnweightedGraph;
import util.graph.WeightedGraph;

import java.util.List;

/**
 * Author: Zhou Xianghui
 * Time: 2017/12/18 21:34
 * Description:
 */
public class DrawWeightGraph {

    public USMap usMap = new USMap();
    public AbstractGraph<City> graph = usMap.graph;
    public List<City> vertices = graph.getVertices();


    //加载地图
    public void loadMap(AbstractGraph.Tree tree, Pane showMap){


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

    // 对遍历的路线增加箭头
    protected void drawingArrowhead(double x1, double y1, double x2, double y2, Pane pane){

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
