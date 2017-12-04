package subject_1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import util.tree.HuffmanCode;

import java.util.ArrayList;

/**
 * Author: Zhou Xianghui
 * Time: 2017/12/4 16:52
 * Description:
 */
public class DrawHuffmanCode extends Application{

    private Pane pane = new Pane();
    private Scene scene = new Scene(pane, 1000, 1000);
    private Huffman huffman = new Huffman("the process");

    @Override
    public void start(Stage primaryStage) throws Exception {

        PaintHuffmanNode p1 = new PaintHuffmanNode(500, 100);
        huffman.getTree();



        pane.getChildren().add(p1.circle);


        primaryStage.setScene(scene);
        primaryStage.show();

    }

    //前序遍历
    public void preorder(Huffman.HuffmanTree.TreeNode root){
        preorder(root);
    }

    public void preorder(Huffman.HuffmanTree.TreeNode root, ArrayList<Character> temp){
        if(root == null){
            return;
        }
        System.out.print(root.element + " ");

        if(root.left != null){

        }

        temp.add(root.element);

        preorder(root.left, temp);
        preorder(root.right, temp);

    }

    // Inner class PaintTree for displaying a tree on a panel
    class PaintHuffmanNode{
        private int x;
        private int y;
        public final int RADIUS = 50;
        public Circle circle;

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public Circle getCircle() {
            return circle;
        }

        public void setCircle(Circle circle) {
            this.circle = circle;
        }

        public PaintHuffmanNode(int x, int y) {
            this.x = x;
            this.y = y;
            this.circle = new Circle(x, y, RADIUS);
        }
    }
}
