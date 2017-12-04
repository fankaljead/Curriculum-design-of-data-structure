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
    private Scene scene = new Scene(pane, 1920, 1080);
    private Huffman huffman = new Huffman("the process");
    private final int X = 960;
    private final int Y = 100;
    public final int RADIUS = 50;

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

    public void preorder(Huffman.HuffmanTree.TreeNode root, int x, int y){
        if(root == null){
            return;
        }

        //添加root左边的边
        if(root.left != null){
            pane.getChildren().add(new Line(x - 2*RADIUS + RADIUS/(Math.sqrt(RADIUS)), y + 2*RADIUS - RADIUS/(Math.sqrt(RADIUS)), x + RADIUS/(Math.sqrt(RADIUS)), y + RADIUS/(Math.sqrt(RADIUS))));//画左边的线
            pane.getChildren().add(new Text(x - RADIUS, y + RADIUS, "0"));
        }

        //添加root👉边的边
        if(root.right != null){
            pane.getChildren().add(new Line(x + 2*RADIUS - RADIUS/(Math.sqrt(RADIUS)), y + 2*RADIUS - RADIUS/(Math.sqrt(RADIUS)), x - RADIUS/(Math.sqrt(RADIUS)), y + RADIUS/(Math.sqrt(RADIUS))));//画右边的线
            pane.getChildren().add(new Text(x + RADIUS, y + RADIUS, "1"));

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

            preorder(root.left, x - 2*RADIUS, y + 2*RADIUS);//
            preorder(root.right, x + 2*RADIUS, y + 2*RADIUS);

        }
    }
}
