package subject_4.main.version_3.controller;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import subject_4.main.version_3.model.City;
import subject_4.main.version_3.model.USMap;
import subject_4.main.version_3.model.Vertex;
import subject_4.main.version_3.model.WeightedUSMap;
import util.graph.AbstractGraph;
import util.graph.WeightedEdge;
import util.graph.WeightedGraph;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Author: Zhou Xianghui
 * Time: 2017/12/18 22:03
 * Description:
 */
public class DrawWeighedGraph {

    public WeightedUSMap usMap = new WeightedUSMap();
    public WeightedGraph<City> graph = usMap.graph;
    public List<City> vertices = graph.getVertices();
    public List<City> path = new ArrayList<>();

    public void loadMap(Pane pane){

        // 画顶点
        List<City> vertices = graph.getVertices();

        for (int i = 0; i < graph.getSize(); i++) {
            double x = vertices.get(i).getX();
            double y = vertices.get(i).getY();
            String name = vertices.get(i).getName();

            Circle circle = new Circle(x, y, 6);
            circle.setFill(Color.BLACK);

            Text text = new Text(x - 15, y - 8, name+"");

            pane.getChildren().addAll(circle, text);
        }

        // 画边
        for (int i = 0; i < graph.getSize(); i++) {
            List<Integer> neighbors = graph.getNeighbors(i);
            for (int j = 0; j < neighbors.size(); j++) {
                int v = neighbors.get(j);

                Line line = new Line(graph.getVertex(i).getX(),
                        graph.getVertex(i).getY(),
                        graph.getVertex(v).getX(),
                        graph.getVertex(v).getY());
                pane.getChildren().add(line);
            }
        }

        // 画边的权重
        List<PriorityQueue<WeightedEdge>> queues = ((WeightedGraph<City>)graph).getWeightedEdges();

        for (int i = 0; i < graph.getSize(); i++) {
            ArrayList<WeightedEdge> list = new ArrayList<WeightedEdge>(queues.get(i));

            for (WeightedEdge edge: list) {
                int u = edge.u;
                int v = edge.v;
                int weight = edge.weight;
                double x1 = graph.getVertex(u).getX();
                double y1 = graph.getVertex(u).getY();
                double x2 = graph.getVertex(v).getX();
                double y2 = graph.getVertex(v).getY();

                Text text = new Text((x1 + x2) / 2, (y1 + y2) / 2, weight+"");

                pane.getChildren().add(text);

            }
        }



        // Display the path
        if (path.size() > 0) {

            for (int i = 1; i < path.size(); i++) {

                drawingArrowhead(path.get(i).getX(),
                        path.get(i).getY(),
                        path.get(i - 1).getX(),
                        path.get(i - 1).getY(),
                        pane);
            }
        }
    }

    // 对遍历的路线增加箭头
    protected void drawingArrowhead(double x1, double y1, double x2, double y2, Pane pane){

        Line line = new Line(x1, y1, x2, y2);
        line.setStrokeWidth(3);//设置线的宽度
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


        line1.setStrokeWidth(3);//设置线的宽度
        line1.setStrokeWidth(3);//设置线的宽度
        line1.setStroke(Color.RED);//颜色
        line2.setStroke(Color.RED);
        // 在线上加箭头
        pane.getChildren().add(line1);
        pane.getChildren().add(line2);
    }
}
