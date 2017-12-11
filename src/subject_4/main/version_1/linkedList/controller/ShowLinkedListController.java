package subject_4.main.version_1.linkedList.controller;

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
 * Time: 2017/12/11 15:29
 * Description:
 */
public class ShowLinkedListController implements Initializable {

    public static final int START_X = 20;//head与tail的起始点x坐标
    public static final int START_Y = 20;//head与tail的起始点y坐标
    public static final int WIDTH = 100;//矩形的宽度
    public static final int HEIGHT = 40;//矩形的高度
    public static final String HEAD = "Head";
    public static final String TAIL = "Tail";
    public static final String NULL = ": null";


    @FXML
    protected JFXTextField enterValue;//输入的值

    @FXML
    protected JFXTextField enterIndex;//输入的下标

    @FXML
    protected Pane showArea;//链表展示的舞台

    @FXML
    protected StackPane main;//链表展示的舞台

    protected Text head = new Text(START_X, START_Y,HEAD + NULL);
    protected Line head_mid = new Line();
    protected Line head_left = new Line();
    protected Line head_right = new Line();



    protected Text tail = new Text(START_X, START_Y*2,TAIL + NULL);
    protected Line tail_mid = new Line();
    protected Line tail_left = new Line();
    protected Line tail_right = new Line();

    protected LinkedList<String> list = new LinkedList<>();//存储输入的值
    protected JFXDialog dialog = new JFXDialog();//对话框


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        dialog.setDialogContainer(main);
        initialInsert();
    }

    //查找
    @FXML
    protected void btSearchAction(){
        //当输入为空时
        if(enterValue.getText().compareTo("") == 0 ||
                enterValue.getText().length() == 0 ||
                list.indexOf(enterValue.getText()) == -1){//输入的值不在线性表中
            showDialog("这个值不在线性表中");
        }

        //搜索输入的值
        else {
            showDialog(enterValue.getText() + " 在线性表的下标为" + list.indexOf(enterValue.getText()));
        }
    }

    //插入
    @FXML
    protected void btInsertAction(){
        //当输入为空时
        if(enterValue.getText().compareTo("") == 0 || enterValue.getText().length() == 0){
            showDialog("输入的值不能为空");
        }

        //当未下标时，在线性表最后添加
        else if(enterIndex.getText().length() == 0){
            list.add(enterValue.getText());

            insertRectangles();

        }

        //插入既有下标也有值时
        else {
            try{
                list.add(Integer.valueOf(enterIndex.getText()), enterValue.getText());
            }catch (NumberFormatException exception){
                showDialog("请正确输入下标");
            }catch (IndexOutOfBoundsException exception){
                showDialog("请输入下标以内的");
            }
        }
    }


    //删除
    @FXML
    protected void btDeleteAction(MouseEvent e){

        if(list.size() == 0){
            initialInsert();
        }

        //当输入为空时
        if(enterValue.getText().compareTo("") == 0 &&
                enterValue.getText().length() == 0 &&
                enterIndex.getText().length() == 0
                ){
            showDialog("输入的值不能为空");
        }

        //当下标为输入空时，根据元素值删除线性表
        else if(enterIndex.getText().length() == 0){
            if(list.remove(enterValue.getText())){
                showDialog("删除成功");
                System.out.println(list.toString());

            }else {
                showDialog("删除失败");
            }
        }

        //根据下标删除元素
        else {
            try{

                if(list.remove(list.get(Integer.valueOf(enterIndex.getText())))){
                    showDialog("删除成功");
                }else {
                    showDialog("删除失败");
                }
            }catch (NumberFormatException exception){
                showDialog("请正确输入下标");
            }catch (IndexOutOfBoundsException exception){
                showDialog("请输入下标以内的");
            }

        }


        insertRectangles();
    }

    protected void initialInsert() {
        head = new Text(START_X, START_Y,HEAD + NULL);
        tail = new Text(START_X, START_Y*2,TAIL + NULL);
        showArea.getChildren().addAll(head, tail);
    }


    //插入矩形
    protected void insertRectangles() {
        if(list.size() >= 0){
            showArea.getChildren().clear();


            //根据添加
            for (int i = 0; i < list.size(); i++) {

                //值所在的矩形框
                Rectangle rectangleMain = new Rectangle(START_X + i * 2.5 * WIDTH, START_Y*6, WIDTH , HEIGHT);
                rectangleMain.setFill(Color.WHITE);
                rectangleMain.setStroke(Color.BLACK);
                rectangleMain.setOpacity(0.5);

                //添加的元素值
                Text text = new Text(rectangleMain.getX() + rectangleMain.getWidth() / 2, rectangleMain.getY() + rectangleMain.getHeight() / 2, list.get(i));

                //值所在的矩形框的tail
                Rectangle rectangleTail = new Rectangle(rectangleMain.getX() + rectangleMain.getWidth(), rectangleMain.getY(), 0.5 * WIDTH, HEIGHT);
                rectangleTail.setFill(Color.WHITE);
                rectangleTail.setStroke(Color.BLACK);
                rectangleTail.setOpacity(0.5);

                if(i != list.size() -1){
                    Line rec_mid = new Line(rectangleTail.getX() + rectangleTail.getWidth(),
                            rectangleTail.getY() + rectangleTail.getHeight()/2,
                            rectangleTail.getX() + 1.5 * WIDTH,
                            rectangleTail.getY() + rectangleTail.getHeight()/2);

                    Line rec_left = new Line(rectangleTail.getX() + 1.3 * WIDTH,
                            rectangleTail.getY() ,
                            rectangleTail.getX() + 1.5 * WIDTH,
                            rectangleTail.getY() + rectangleTail.getHeight()/2);

                    Line rec_right = new Line(rectangleTail.getX() + 1.3 * WIDTH,
                            rectangleTail.getY() + rectangleTail.getHeight() ,
                            rectangleTail.getX() + 1.5 * WIDTH,
                            rectangleTail.getY() + rectangleTail.getHeight()/2);

                    showArea.getChildren().addAll( rec_mid, rec_left, rec_right);
                }


                //头节点head
                if(i == 0){
                    head.setText(HEAD);
                    head_mid = new Line(START_X + WIDTH/2, START_Y,
                            START_X + WIDTH/2,
                            START_Y + WIDTH);
                    head_left = new Line(WIDTH/2, WIDTH,
                            START_X + WIDTH/2,
                            START_Y + WIDTH);
                    head_right = new Line(START_X * 2 + WIDTH/2, WIDTH,
                            START_X + WIDTH/2,
                            START_Y + WIDTH);

                    showArea.getChildren().addAll(head_mid, head, head_left, head_right);
                }

                //尾节点tail
                if(i == list.size() -1){
                    tail.setText(TAIL);
                    tail.setX(rectangleTail.getX()-WIDTH/2);
                    tail.setY(head.getY());
                    tail_mid = new Line(rectangleTail.getX()- WIDTH/2 + 10, START_Y,
                            rectangleTail.getX()- WIDTH/2  + 10,
                            START_Y + WIDTH);
                    tail_left = new Line(rectangleTail.getX() - WIDTH/4*3 + 10, WIDTH,
                            rectangleTail.getX()- WIDTH/2 + 10,
                            START_Y + WIDTH);
                    tail_right = new Line(rectangleTail.getX() - WIDTH/4 + 10, WIDTH,
                            rectangleTail.getX()- WIDTH/2 + 10,
                            START_Y + WIDTH);

                    showArea.getChildren().addAll(tail_mid, tail, tail_left, tail_right);
                }






                showArea.getChildren().addAll(rectangleMain,  text, rectangleTail);
            }
        }
    }


    //展示对话框
    protected void showDialog(String tip){
        dialog.setContent(new Label(tip));
        dialog.show();
    }


}
