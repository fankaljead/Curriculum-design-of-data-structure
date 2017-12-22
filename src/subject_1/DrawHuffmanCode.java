package subject_1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * Author: Zhou Xianghui
 * Time: 2017/12/4 16:52
 * Description:
 */
public class DrawHuffmanCode extends Application{
    private int radius = 30;//节点半径
    private int vPane = 100;//节点垂直距离
    private Pane pane = new Pane();
    private final int WIDTH = 1500;
    private final int HEIGHT = 700;
    private Scene scene = new Scene(pane, WIDTH, HEIGHT);
    private Huffman huffman = new Huffman("the process");
    private final double X = WIDTH/2;
    private final double Y = 50;
    public final int RADIUS = 25;
    public final static double REAGAN_THREE = Math.sqrt(3);
    public final static double REAGAN_TWO = Math.sqrt(2);

    public DrawHuffmanCode(Huffman huffman) {
        this.huffman = huffman;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {


        if(huffman != null){
            preorder(huffman.getTree().root);
        }
        primaryStage.setTitle("打印哈夫曼树");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    //前序遍历
    public void preorder(Huffman.HuffmanTree.TreeNode root){
        preorder(root, X, Y, 300);

    }



    public void preorder(Huffman.HuffmanTree.TreeNode root, double x, double y, double hPane){

        Circle circle = new Circle(x, y, radius);
        circle.setFill(Color.WHITE);
        Text text = new Text(x - 10, y + 4, root.weight + "");

        pane.getChildren().addAll(circle, text);

        //展示左节点的权重
        if (root.left != null) {
            connectLeftChild(pane, x - hPane, (int)y + vPane, x, (int)y);
            preorder(root.left, x - hPane, y + vPane, hPane / 2);
        }
        //展示左节点的元素
        if (root.left == null) {
            Text text1 = new Text(x - 4, y + 2 * radius - 5, root.element + "");
            pane.getChildren().add(text1);
        }

        //展示右节点的权重
        if (root.right != null) {
            connectRightChild(pane, x + hPane, (int)y + vPane, x, (int)y);
            preorder(root.right, x + hPane, y +vPane, hPane / 2);
        }
        //展示右节点的元素
        if (root.right == null) {
            Text text1 = new Text(x - 4, y + 2 * radius - 5, root.element + "");
            pane.getChildren().add(text1);
        }

    }

    //连接右孩子
    private void connectRightChild(Pane pane, double x1, int y1, double x2, int y2) {
        double d = Math.sqrt(vPane * vPane + (x2 - x1) * (x2 - x1));
        //起始坐标
        int x11 = (int)(x1 + radius * (x2 - x1) / d);
        int y11 = (int)(y1 - radius * vPane / d);
        //结束坐标
        int x21 = (int)(x2 - radius * (x2 - x1) / d);
        int y21 = (int)(y2 + radius * vPane / d);

        Line line = new Line(x11, y11, x21, y21);
        Text text = new Text((x11 + x21) / 2 , (y11 + y21) / 2 + 2, "1");

        pane.getChildren().addAll(line, text);
    }

    //连接左孩子
    private void connectLeftChild(Pane pane, double x1, int y1, double x2, int y2) {
        double d = Math.sqrt(vPane * vPane + (x2 - x1) * (x2 - x1));
        //起始坐标
        int x11 = (int)(x1 - radius * (x1 - x2) / d);
        int y11 = (int)(y1 - radius * vPane / d);
        //结束坐标
        int x21 = (int)(x2 + radius * (x1 - x2) / d);
        int y21 = (int)(y2 + radius * vPane / d);
        Line line = new Line(x11, y11, x21, y21);
        Text text = new Text((x11 + x21) / 2 , (y11 + y21) / 2 + 2, "0");

        pane.getChildren().addAll(line, text);
    }

}
