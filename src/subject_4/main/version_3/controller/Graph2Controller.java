package subject_4.main.version_3.controller;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import subject_4.main.version_3.model.Vertex;
import util.graph.AbstractGraph;
import util.graph.WeightedEdge;
import util.graph.WeightedGraph;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Author: Zhou Xianghui
 * Time: 2017/12/18 17:18
 * Description:
 */
public class Graph2Controller implements Initializable{

    public static final double RADIUS = 5;

    @FXML
    private JFXTextField tfVertexName;//新建顶点的名字

    @FXML
    private JFXTextField tfVertexX;//新建顶点的x坐标

    @FXML
    private JFXTextField tfVertexY;//新建顶点的x坐标

    @FXML
    private JFXTextField tfEdgeStart;//新建边的x起点

    @FXML
    private JFXTextField tfEdgeEnd;//新建边的x终点

    @FXML
    private JFXTextField tfEdgeWeight;//新建边的x权值

    @FXML
    private StackPane stackPane;

    @FXML
    private Pane graphArea;


    List<WeightedEdge> edges = new ArrayList<>();//存储边
    List<Vertex> vertices = new ArrayList<>();//存储顶点
    private WeightedGraph<Vertex> graph;//加权图


    private JFXDialog dialog = new JFXDialog();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dialog.setDialogContainer(stackPane);
    }


    //新建顶点
    @FXML
    private void btNewVertexAction(MouseEvent e){

        try{
            String name = tfVertexName.getText();
            double x = Double.valueOf(tfVertexX.getText());
            double y = Double.valueOf(tfVertexY.getText());

            Vertex vertex = new Vertex(name, x, y);
            vertices.add(vertex);

            Circle circle = new Circle(x, y, RADIUS);
            Text text = new Text(x + RADIUS, y + RADIUS, name);

            graphArea.getChildren().addAll(circle, text);

            graph = new WeightedGraph<Vertex>(edges, vertices);

        }catch (NumberFormatException ee){
            showTip("您的输入不正确，请重新输入");
        }


        clearInput();

    }

    //新建边
    @FXML
    private void btNewEdgeAction(MouseEvent e){
        try {
            int start = Integer.valueOf(tfEdgeStart.getText());
            int end = Integer.valueOf(tfEdgeEnd.getText());
            int weight = Integer.valueOf(tfEdgeWeight.getText());

            if(!checkStartEnd(start, end, vertices.size())){
                showTip("输入的顶点下标不正确");
            }

            WeightedEdge edge = new WeightedEdge(start, end, weight);
            edges.add(edge);
            graph = new WeightedGraph<Vertex>(edges, vertices);


            //当边的起点和终点不同时
            if(start != end){
                Line line = new Line(vertices.get(start).getX(),
                        vertices.get(start).getY(),
                        vertices.get(end).getX(),
                        vertices.get(end).getY());
                Text text = new Text((vertices.get(start).getX() + vertices.get(end).getX())/2 + RADIUS,
                        (vertices.get(start).getY() + vertices.get(end).getY())/2 + RADIUS,
                        weight + "");
                graphArea.getChildren().addAll(line, text);
            }
            //当顶点与终点为同一个时
            else {
                Circle circle = new Circle(vertices.get(start).getX(), vertices.get(start).getY(), RADIUS*RADIUS);
                circle.setStroke(Color.BLACK);
                circle.setFill(Color.WHITE);
                circle.setOpacity(0.5);

                Text text = new Text(vertices.get(start).getX() + RADIUS*RADIUS + RADIUS,
                        vertices.get(start).getY(),
                        weight + "");

                graphArea.getChildren().addAll(circle, text);
            }




        }catch (NumberFormatException ee){
            showTip("您的输入不正确，请重新输入");
        }

        clearInput();

    }

    //重新开始
    @FXML
    private void newGraphAction(MouseEvent e){

        if(vertices.size() != 0 || edges.size() != 0){
            graphArea.getChildren().clear();
            vertices = new ArrayList<>();
            edges = new ArrayList<>();
            //graph = new WeightedGraph<Vertex>(edges, vertices);
        }

    }


    //提示
    private void showTip(String tip){
        dialog.setContent(new Label(tip));
        dialog.show();
    }

    //清除输入
    private void clearInput(){
        tfEdgeEnd.setText("");
        tfEdgeStart.setText("");
        tfEdgeWeight.setText("");
        tfVertexName.setText("");
        tfVertexX.setText("");
        tfVertexY.setText("");
    }

    //检验用户输入顶点下标是否正确
    private boolean checkStartEnd(int start, int end, int size){
        if(size <= end || size <= start){
            return false;
        }

        return false;
    }
}
