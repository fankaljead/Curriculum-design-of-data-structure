package subject_4.main.version_2.controller;

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
import util.tree.BinaryTree;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Author: Zhou Xianghui
 * Time: 2017/12/11 20:56
 * Description:
 */
public class ShowBinaryTreeController implements Initializable {

    public static final int RADIUS = 50;//圆的半径
    public static final int START_X = 450;//根节点所在横坐标
    public static final int START_Y = 70;//根节点所在纵坐标

    protected BinaryTree<Double> tree = new BinaryTree<>();//需要展示的二叉树
    protected JFXDialog dialog = new JFXDialog();//对话框

    @FXML
    protected JFXTextField enterKey;//输入

    @FXML
    protected Pane showTree;//展示树的Pane

    @FXML
    protected StackPane main;//所有元素的根Pane

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dialog.setDialogContainer(main);
    }

    //搜索事件
    @FXML
    protected void btSearchAction(MouseEvent e){
        //输入值为空
        if(enterKey.getText().length() != 0 && enterKey.getText() == ""){

            showDialog("输入不能为空");
        }

        //判断元素是否在树中
        try {
            if (tree.search(Double.valueOf(enterKey.getText()))) {
                showDialog(enterKey.getText() + "在树中");
            }else {
                showDialog(enterKey.getText() + "不在树中");
            }
        }catch (NumberFormatException ex){
            showDialog(enterKey.getText() + "不是纯数");
        }

    }

    //插入事件
    @FXML
    protected void btInsertAction(MouseEvent e){
        showTree.getChildren().clear();//清除Pane中的所有元素
        //插入一个值
        if(enterKey.getText().length() != 0 && enterKey.getText() == ""){

            showDialog("输入不能为空");
        }

        //判断元素是否在树中
        try {
            //tree.insert(Double.valueOf(enterKey.getText()));
            if (!tree.insert(Double.valueOf(enterKey.getText()))) {
                showDialog(enterKey.getText() + "已经在树中");
            }
        }catch (NumberFormatException ex){
            showDialog(enterKey.getText() + "不是纯数");
        }

        //向Pane中画树
        preOrder(tree.getRoot());

    }

    //删除事件
    @FXML
    protected void btRemoveAction(MouseEvent e){
        showTree.getChildren().clear();//清除Pane中的所有元素

        //输入值为空
        if(enterKey.getText().length() != 0 && enterKey.getText() == ""){

            showDialog("输入不能为空");
        }

        //判断元素是否在树中
        try {
            //tree.insert(Double.valueOf(enterKey.getText()));
            if (!tree.delete(Double.valueOf(enterKey.getText()))) {
                showDialog("树中不存在" + enterKey.getText());
            }
        }catch (NumberFormatException ex){
            showDialog(enterKey.getText() + "不是纯数");
        }

        preOrder(tree.getRoot());
    }





    //展示对话框
    protected void showDialog(String tip){
        dialog.setContent(new Label(tip));
        dialog.show();
    }



    //前序遍历
    public void preOrder(BinaryTree.TreeNode root){
        preOrder(root, START_X, START_Y);
    }

    public void preOrder(BinaryTree.TreeNode root, int x, int y){
        if(root == null){
            return;
        }

        //添加root左边的边
        if(root.left != null){
            showTree.getChildren().add(new Line(x - 2*RADIUS + RADIUS/(Math.sqrt(RADIUS)), y + 2*RADIUS - RADIUS/(Math.sqrt(RADIUS)), x + RADIUS/(Math.sqrt(RADIUS)), y + RADIUS/(Math.sqrt(RADIUS))));//画左边的线
        }

        //添加root右边的边
        if(root.right != null){
            showTree.getChildren().add(new Line(x + 2*RADIUS - RADIUS/(Math.sqrt(RADIUS)), y + 2*RADIUS - RADIUS/(Math.sqrt(RADIUS)), x - RADIUS/(Math.sqrt(RADIUS)), y + RADIUS/(Math.sqrt(RADIUS))));//画右边的线
        }

        //添加root元素，一个圆形包含字符
        if(root != null){
            Circle c = new Circle(x, y, RADIUS);
            c.setFill(Color.WHITE);
            c.setStroke(Color.BLACK);
            showTree.getChildren().add(c);

            showTree.getChildren().add(new Text(x , y ,  root.element + ""));

            preOrder(root.left, x - 2*RADIUS, y + 2*RADIUS);//
            preOrder(root.right, x + 2*RADIUS, y + 2*RADIUS);

        }
    }

}
