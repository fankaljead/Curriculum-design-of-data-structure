package subject_1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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

    private Pane pane = new Pane();
    private final int WIDTH = 900;
    private final int HEIGHT = 700;
    private Scene scene = new Scene(pane, WIDTH, HEIGHT);
    private Huffman huffman = new Huffman("the process");
    private final int X = WIDTH/2;
    private final int Y = 50;
    public final int RADIUS = 50;
    public final static double REAGAN_THREE = Math.sqrt(3);
    public final static double REAGAN_TWO = Math.sqrt(2);

    public DrawHuffmanCode(Huffman huffman) {
        this.huffman = huffman;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {


        preorder(huffman.getTree().root);
        primaryStage.setTitle("打印哈夫曼树");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    //前序遍历
    public void preorder(Huffman.HuffmanTree.TreeNode root){

        preorder(root, X, Y);
    }

    public void preorder(Huffman.HuffmanTree.TreeNode root, double x, double y){
        if(root == null){
            return;
        }

//        if(flag){
//            //添加root左边的边
//            if(root.left != null){
//                pane.getChildren().add(new Line(x - 1.5*RADIUS, y + 1.5 * RADIUS * REAGAN_THREE,
//                        x - 0.5 * RADIUS, y + 0.5 * RADIUS * REAGAN_THREE));//画左边的线
//                pane.getChildren().add(new Text(x - RADIUS, y + REAGAN_THREE * RADIUS, "0"));
//            }
//
//            //添加root右边的边
//            if(root.right != null){
////            pane.getChildren().add(new Line(x + 2*RADIUS - RADIUS/(Math.sqrt(RADIUS)), y + 2*RADIUS - RADIUS/(Math.sqrt(RADIUS)), x - RADIUS/(Math.sqrt(RADIUS)), y + RADIUS/(Math.sqrt(RADIUS))));//画右边的线
////            pane.getChildren().add(new Text(x + RADIUS, y + RADIUS, "1"));
//
//                pane.getChildren().add(new Line(x + RADIUS/REAGAN_TWO, y + RADIUS/REAGAN_TWO,
//                        x + 2 * RADIUS * REAGAN_THREE - RADIUS/REAGAN_TWO, y + 2 * RADIUS * REAGAN_THREE - RADIUS/REAGAN_TWO));//画左边的线
//                pane.getChildren().add(new Text(x + REAGAN_THREE*RADIUS, y + REAGAN_THREE * RADIUS, "1"));
//
//            }
//
//            flag = false;
//        }else {
//            //添加root左边的边
//            if(root.left != null){
//                pane.getChildren().add(new Line(x - RADIUS/REAGAN_TWO, y + RADIUS/REAGAN_TWO,
//                        x - 2 * RADIUS * REAGAN_THREE + RADIUS/REAGAN_TWO, y + 2 * RADIUS * REAGAN_THREE - RADIUS/REAGAN_TWO));//画左边的线
//                pane.getChildren().add(new Text(x - REAGAN_THREE*RADIUS, y + REAGAN_THREE * RADIUS, "0"));
//            }
//
//            //添加root右边的边
//            if(root.right != null){
////            pane.getChildren().add(new Line(x + 2*RADIUS - RADIUS/(Math.sqrt(RADIUS)), y + 2*RADIUS - RADIUS/(Math.sqrt(RADIUS)), x - RADIUS/(Math.sqrt(RADIUS)), y + RADIUS/(Math.sqrt(RADIUS))));//画右边的线
////            pane.getChildren().add(new Text(x + RADIUS, y + RADIUS, "1"));
//
//                pane.getChildren().add(new Line(x + 1.5*RADIUS, y + 1.5 * RADIUS * REAGAN_THREE,
//                        x + 0.5 * RADIUS, y + 0.5 * RADIUS * REAGAN_THREE));//画左边的线
//                pane.getChildren().add(new Text(x + RADIUS, y + REAGAN_THREE * RADIUS, "1"));
//
//            }
//
//            flag = true;
//        }

        //添加root左边的边
        if(root.left != null){
            pane.getChildren().add(new Line(x - 1.5*RADIUS, y + 1.5 * RADIUS * REAGAN_THREE,
                    x - 0.5 * RADIUS, y + 0.5 * RADIUS * REAGAN_THREE));//画左边的线
            pane.getChildren().add(new Text(x - RADIUS, y + REAGAN_THREE * RADIUS, "0"));
        }

        //添加root右边的边
        if(root.right != null){
//            pane.getChildren().add(new Line(x + 2*RADIUS - RADIUS/(Math.sqrt(RADIUS)), y + 2*RADIUS - RADIUS/(Math.sqrt(RADIUS)), x - RADIUS/(Math.sqrt(RADIUS)), y + RADIUS/(Math.sqrt(RADIUS))));//画右边的线
//            pane.getChildren().add(new Text(x + RADIUS, y + RADIUS, "1"));

            pane.getChildren().add(new Line(x + 1.5*RADIUS, y + 1.5 * RADIUS * REAGAN_THREE,
                    x + 0.5 * RADIUS, y + 0.5 * RADIUS * REAGAN_THREE));//画左边的线
            pane.getChildren().add(new Text(x + RADIUS, y + REAGAN_THREE * RADIUS, "1"));

        }

        //添加root元素，一个圆形包含字符
        if(root != null){
            Circle c = new Circle(x, y, RADIUS);
            c.setFill(Color.WHITE);
            c.setStroke(Color.BLACK);
            pane.getChildren().add(c);//画右边的线
            String temp;
            if(root.element == '\0'){
                temp = root.weight + "";
            }
            else {
                temp = root.element + "";
            }

            pane.getChildren().add(new Text(x , y , temp));

            preorder(root.left, x - 2*RADIUS, y + 2*RADIUS * REAGAN_THREE);//
            preorder(root.right, x + 2*RADIUS, y + 2*RADIUS * REAGAN_THREE);

        }
    }
}
