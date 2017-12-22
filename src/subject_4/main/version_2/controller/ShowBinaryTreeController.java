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

    public static final double START_X = 450;//根节点所在横坐标
    public static final double START_Y = 70;//根节点所在纵坐标

    protected BinaryTree<Double> tree = new BinaryTree<>();//需要展示的二叉树
    protected JFXDialog dialog = new JFXDialog();//对话框
    private int radius = 30;//节点半径
    private int vPane = 100;//节点垂直距离
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
        preOrder(root, START_X, START_Y, 100);
    }

    public void preOrder(BinaryTree.TreeNode root, double x, double y, double hPane){

        Circle circle = new Circle(x, y, radius);
        circle.setFill(Color.WHITE);
        Text text = new Text(x - 10, y + 4, root.element + "");

        showTree.getChildren().addAll(circle, text);

        //展示左节点的权重
        if (root.left != null) {
            connectLeftChild(showTree, x - hPane, (int)y + vPane, x, (int)y);
            preOrder(root.left, x - hPane, y + vPane, hPane / 2);
        }
//        //展示左节点的元素
//        if (root.left == null) {
//            Text text1 = new Text(x - 4, y + 2 * radius - 5, root.element + "");
//            showTree.getChildren().add(text1);
//        }

        //展示右节点的权重
        if (root.right != null) {
            connectRightChild(showTree, x + hPane, (int)y + vPane, x, (int)y);
            preOrder(root.right, x + hPane, y +vPane, hPane / 2);
        }
        //展示右节点的元素
//        if (root.right == null) {
//            Text text1 = new Text(x - 4, y + 2 * radius - 5, root.element + "");
//            showTree.getChildren().add(text1);
//        }

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
//            Text text = new Text((x11 + x21) / 2 - 6, (y11 + y21) / 2 - 4, "1");

        pane.getChildren().addAll(line);
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
//            Text text = new Text((x11 + x21) / 2 + 6, (y11 + y21) / 2 - 4, "0");

        pane.getChildren().addAll(line);
    }



}
