package subject_4.main.version_1.arrayList.controller;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import subject_4.main.version_1.arrayList.model.ShowArrayList;

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

    protected ArrayList<String> showArrayList = new ArrayList<>();//存储输入元素的线性表
    protected ArrayList<Rectangle> showArrayListRec = new ArrayList<>();//存储输入元素的线性表的矩形

    protected JFXDialog dialog = new JFXDialog();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dialog.setDialogContainer(main);
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
            insertRectangles();
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

    }

    //Trim To Size
    @FXML
    protected void btTrimToSizeAction(MouseEvent e){

    }

    //展示对话框
    protected void showDialog(String tip){
        dialog.setContent(new Label(tip));
        dialog.show();
    }

    //插入矩形
    protected void insertRectangles(){
        if(showArrayList.size() != 0){
            showList.getChildren().clear();

            for (int i = 0; i < showArrayList.size(); i++) {

                Rectangle rectangle = new Rectangle(START_X + i * WIDTH, START_Y, WIDTH, HEIGHT);
                rectangle.setFill(Color.WHITE);
                rectangle.setStroke(Color.BLACK);

                Text text = new Text(rectangle.getX() + rectangle.getWidth()/ 2, rectangle.getY() + rectangle.getHeight() /2, showArrayList.get(i));
                System.out.println(showArrayList.get(i));
                showList.getChildren().add(text);
                showList.getChildren().add(rectangle);
            }
        }
    }
}
