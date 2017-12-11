package subject_4.main.version_1.stack.controller;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

/**
 * Author: Zhou Xianghui
 * Time: 2017/12/11 19:00
 * Description:
 */
public class ShowStackController implements Initializable {
    public static final int START_X = 200;//head与tail的起始点x坐标
    public static final int START_Y = 20;//head与tail的起始点y坐标
    public static final int WIDTH = 100;//矩形的宽度
    public static final int HEIGHT = 40;//矩形的高度
    public static final String TOP = "Top";



    @FXML
    protected JFXTextField enterValue;//输入的值


    @FXML
    protected Pane showArea;//队列展示的舞台

    @FXML
    protected StackPane main;//队列展示的舞台

    protected Text top;
    protected Line top_mid = new Line();
    protected Line top_left = new Line();
    protected Line top_right = new Line();


    protected Text head = new Text(START_X, START_Y, "The stack is empty");


    protected LinkedList<String> list = new LinkedList<>();//存储输入的值
    protected JFXDialog dialog = new JFXDialog();//对话框


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        dialog.setDialogContainer(main);
        initialInsert();
    }




    //插入
    @FXML
    protected void btInsertAction(){
        //当输入为空时
        if(enterValue.getText().compareTo("") == 0 || enterValue.getText().length() == 0){
            showDialog("输入的值不能为空");
        }


        //插入既有下标也有值时
        else {
            list.push(enterValue.getText());
        }

        insertRectangles();
    }


    //删除
    @FXML
    protected void btDeleteAction(MouseEvent e){

        if(list.size() == 0){
            initialInsert();
            showDialog("栈的元素已经全部出栈");
        }


        //出栈操作
        if(list.pop() == null){
            showDialog("栈的元素已经删除完");
        }

        insertRectangles();
    }


    //Peek操作
    @FXML
    protected void btPeekAction(MouseEvent e){

        String temp = list.peek();

        showDialog("栈顶的元素为" + (temp == null ? "空" : temp));
    }

    protected void initialInsert() {
        showArea.getChildren().clear();

        showArea.getChildren().add(head);
    }


    //插入矩形
    protected void insertRectangles() {
        if(list.size() > 0){
            showArea.getChildren().clear();


            //根据添加
            for (int i = 0; i < list.size(); i++) {
                Rectangle rectangle = new Rectangle(START_X, START_Y + i * HEIGHT, WIDTH, HEIGHT);
                rectangle.setFill(Color.WHITE);
                rectangle.setStroke(Color.BLACK);
                rectangle.setOpacity(0.5);
                Text text = new Text(rectangle.getX() + rectangle.getWidth()/2, rectangle.getY() + rectangle.getHeight()/2, list.get(i));

                if(i == list.size() - 1){
                    top = new Text(START_X - WIDTH, START_Y + HEIGHT/2, TOP);

                    //画箭头
                    top_mid = new Line(START_X - WIDTH/5*3, START_Y + HEIGHT/2,
                            START_X, START_Y + HEIGHT/2);
                    top_left = new Line(top_mid.getEndX(), top_mid.getEndY(),
                            top_mid.getEndX() - HEIGHT/3, top_mid.getEndY() - HEIGHT/2);
                    top_right = new Line(top_mid.getEndX(), top_mid.getEndY(),
                            top_mid.getEndX() - HEIGHT/3, top_mid.getEndY() + HEIGHT/2);

                            showArea.getChildren().addAll(top, top_mid, top_left, top_right);
                }


                showArea.getChildren().addAll(rectangle, text);
            }
        }else {
            initialInsert();
        }
    }


    //展示对话框
    protected void showDialog(String tip){
        dialog.setDialogContainer(main);
        dialog.setContent(new Label(tip));
        dialog.show();
    }
}
