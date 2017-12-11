package subject_4.main.version_1.arrayList.controller;

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
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Author: Zhou Xianghui
 * Time: 2017/12/11 10:09
 * Description:
 */
public class ShowArrayListController implements Initializable {


    public static int WIDTH = 100;
    public static int HEIGHT = 40;
    public static int START_X = 20;
    public static int START_Y = 20;
    public static int capacitySize = 10;//初始容量

    @FXML
    protected StackPane main;

    @FXML
    protected Label arrayIsEmpty;//线性表是否为空

    @FXML
    protected Label arraySize;//线性表的大小

    @FXML
    protected Label arrayCapacity;//线性表的容量

    @FXML
    protected JFXTextField enterValue;//输入的值

    @FXML
    protected JFXTextField enterIndex;//输入的下标

    @FXML
    protected Pane showList;//展示线性表

    protected ArrayList<String> showArrayList = new ArrayList<>(capacitySize);//存储输入元素的线性表
    protected boolean trim = true;

    protected JFXDialog dialog = new JFXDialog();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initialInsert();
        dialog.setDialogContainer(main);


    }


    //设置有个线性表的信息
    protected void setLabel(){
        arraySize.setText(showArrayList.size() + " ");
        arrayCapacity.setText(capacitySize + "");
        if(showArrayList.size() == 0){
            arrayIsEmpty.setText(" is empty");
        }else {
            arrayIsEmpty.setText("");
        }
    }


    //搜索
    @FXML
    protected void btSearchAction(MouseEvent e){
        //当输入为空时
        if(enterValue.getText().compareTo("") == 0 ||
                enterValue.getText().length() == 0 ||
                showArrayList.indexOf(enterValue.getText()) == -1){//输入的值不在线性表中
            showDialog("这个值不在线性表中");
        }

        //搜索输入的值
        else {
            showDialog(enterValue.getText() + " 在线性表的下标为" + showArrayList.indexOf(enterValue.getText()));
        }

    }

    //插入
    @FXML
    protected void btInsertAction(MouseEvent e){
        //当输入为空时
        if(enterValue.getText().compareTo("") == 0 || enterValue.getText().length() == 0){
            showDialog("输入的值不能为空");
        }

        //当未下标时，在线性表最后添加
        else if(enterIndex.getText().length() == 0){
            showArrayList.add(enterValue.getText());
            if(!trim){
                insertRectanglesTrim();
            }else {
                insertRectangles();
            }
            //showList.getChildren().add(new Rectangle(10,10, 100,100));
        }

        //插入既有下标也有值时
        else {
            try{
                showArrayList.add(Integer.valueOf(enterIndex.getText()), enterValue.getText());
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

        if(showArrayList.size() == 0){
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
            if(showArrayList.remove(enterValue.getText())){
                showDialog("删除成功");
                System.out.println(showArrayList.toString());

            }else {
                showDialog("删除失败");
            }
        }

        //根据下标删除元素
        else {
            try{
                if(showArrayList.remove(Integer.valueOf(enterIndex.getText()))){
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


        if(!trim){
            insertRectanglesTrim();
        }else {
            insertRectangles();
        }
    }

    //Trim To Size
    @FXML
    protected void btTrimToSizeAction(MouseEvent e){
        if(trim){
            insertRectanglesTrim();
            trim = false;
        }else {
            insertRectangles();
            trim = true;
        }

    }

    //展示对话框
    protected void showDialog(String tip){
        dialog.setContent(new Label(tip));
        dialog.show();
    }

    //插入矩形 without trim
    protected void insertRectangles(){
        setLabel();
        if(showArrayList.size() >= 0){
            showList.getChildren().clear();
            if(showArrayList.size() >= capacitySize){
                capacitySize *= 1.5;
            }

            //根据容量添加
            for (int i = 0; i < capacitySize; i++) {

                Rectangle rectangle = new Rectangle(START_X + i * WIDTH, START_Y, WIDTH, HEIGHT);
                rectangle.setFill(Color.WHITE);
                rectangle.setStroke(Color.BLACK);
                rectangle.setOpacity(0.5);
                showList.getChildren().add(rectangle);

                //当有元素时
                if(i < showArrayList.size()) {
                    Text text = new Text(rectangle.getX() + rectangle.getWidth() / 2, rectangle.getY() + rectangle.getHeight() / 2, showArrayList.get(i));
                    showList.getChildren().add(text);
                }
                //当超过线性表大小
                else {
                    Line line = new Line(rectangle.getX(), rectangle.getY() + HEIGHT, rectangle.getX() + WIDTH, rectangle.getY());
                    showList.getChildren().add(line);
                }

            }
        }
    }


    //trim后插入
    protected void insertRectanglesTrim(){
        setLabel();

        if(showArrayList.size() >= 0){
            showList.getChildren().clear();

            //根据线性大小添加
            for (int i = 0; i < showArrayList.size(); i++) {

                //添加矩形
                Rectangle rectangle = new Rectangle(START_X + i * WIDTH, START_Y, WIDTH, HEIGHT);
                rectangle.setFill(Color.WHITE);
                rectangle.setStroke(Color.BLACK);
                rectangle.setOpacity(0.2);
                showList.getChildren().add(rectangle);

                //添加元素值
                Text text = new Text(rectangle.getX() + rectangle.getWidth() / 2, rectangle.getY() + rectangle.getHeight() / 2, showArrayList.get(i));
                showList.getChildren().add(text);
            }
        }
    }


    //初始化插入
    protected void initialInsert(){
        setLabel();

        showList.getChildren().clear();

        for (int i = 0; i < capacitySize; i++) {

            //添加矩形
            Rectangle rectangle = new Rectangle(START_X + i * WIDTH, START_Y, WIDTH, HEIGHT);
            rectangle.setFill(Color.WHITE);
            rectangle.setStroke(Color.BLACK);
            rectangle.setOpacity(0.3);
            showList.getChildren().add(rectangle);

            //添加线
            Line line = new Line(rectangle.getX(), rectangle.getY() + HEIGHT, rectangle.getX() + WIDTH, rectangle.getY());
            showList.getChildren().add(line);
        }
    }
}
