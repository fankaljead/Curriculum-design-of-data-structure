package subject_1;


import com.jfoenix.controls.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import util.fileInOut.FileInOut;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * Author: Zhou Xianghui
 * Time: 2017/11/29 10:46
 * Description: 数据压缩 解压控制层
 */
public class HuffmanController implements Initializable{


    private static String FILE_TO_SAVE_HUFFMAN_CODE = "codefile.txt";//保存编码后的文件名
    private static String FILE_TO_SAVE_HUFFMAN_CODE_PRINT = "codeprint.txt";//保存打印的哈夫曼编码 每行50个
    private static String FILE_TO_SAVE_HUFFMAN_OBJECT = "hfmtree.dat";//保存存储哈夫曼对象的文件名

    private Huffman huffman;
    private FileInOut<Huffman> fileInOut = new FileInOut<>();



    @FXML
    private MenuItem fileOpen;
    @FXML
    private Pane pane;

    @FXML
    private HBox hbox;

    @FXML
    private JFXButton btInitial;

    @FXML
    private JFXButton btCode;

    @FXML
    private JFXButton btDecode;

    @FXML
    private JFXButton btPrint;

    @FXML
    private JFXButton btPrintTree;

    @FXML
    private JFXButton filePick;

    @FXML
    private JFXTextArea textArea;




    @Override
    public void initialize(URL location, ResourceBundle resources) {

        bindHboxEvents();
        bindBtInitialEvents();//初始化
        bindBtCodeEvents();//编码
        bindBtDecodeEvents();//解码
        bindBtPrintEvents();//打印哈夫曼编码
        bindFilePickEvents();
    }


    public void bindHboxEvents(){

        hbox.setOnScroll(new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {

                if(hbox.getLayoutX() >= -850)
                    hbox.setLayoutX(hbox.getLayoutX() - 100);
                else{
                    hbox.setLayoutX(50);
                }
                System.out.println(hbox.getLayoutX());
            }
        });
    }


    //初始化功能，读取字符串，并建立哈夫曼树，并存入文件中
    public void bindBtInitialEvents(){
        btInitial.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {

                //当输入框不为空时
                if(textArea.getText().length() != 0){
                    huffman = new Huffman(textArea.getText());
                    textArea.setText(huffman.getHuffmanCodes());
                    try {
                        fileInOut.writeObjectToFile(huffman, FILE_TO_SAVE_HUFFMAN_OBJECT);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
    }

    //编码功能 将哈夫曼编码存入文件中
    public void bindBtCodeEvents(){
        btCode.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //当内存中不存在时，从文件中读取创建哈夫曼树
                if(huffman == null){
                    try {
                        huffman = fileInOut.readObjectFromFile(FILE_TO_SAVE_HUFFMAN_OBJECT);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                try {
//                    huffman.encode(FILE_TO_SAVE_HUFFMAN_CODE);
                    fileInOut.writeToFile(FILE_TO_SAVE_HUFFMAN_CODE, huffman.getHuffmanCodes());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });
    }


    //解码功能 利用已经建立好的哈夫曼树将codefile.txt中的文件解码
    public void bindBtDecodeEvents(){
        btDecode.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //当内存中不存在时，从文件中读取创建哈夫曼树
                if(huffman == null){
                    try {
                        huffman = fileInOut.readObjectFromFile(FILE_TO_SAVE_HUFFMAN_OBJECT);
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("IOException");
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }

                String temp = "";
                try {
                    temp = fileInOut.readFromFile(FILE_TO_SAVE_HUFFMAN_CODE);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                //解码并输出到屏幕
                temp = huffman.decode(new EncodeResult(temp, huffman.getCodesWithKey()));


                System.out.println(temp);
                System.out.println(temp);
                textArea.setText(temp);
            }
        });
    }

    //打印代码文件 每行打印50个代码 并保存到文件codefile中
    public void bindBtPrintEvents(){
        btPrint.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String codes = "";
                try {
                    codes = fileInOut.readFromFile(FILE_TO_SAVE_HUFFMAN_CODE);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                String temp = "";
                for (int i = 0; i < codes.length(); i = i + 50) {
                    if(i + 50 > codes.length()){
                        temp += codes.substring(i, codes.length());
                        temp += "\n";
                    }
                    else {
                        temp += codes.substring(i, i + 50);
                        temp += "\n";

                    }
                }

                textArea.setText(temp);
                try {
                    fileInOut.writeToFile(FILE_TO_SAVE_HUFFMAN_CODE_PRINT, temp);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
        });
    }


    public void bindFilePickEvents(){
        filePick.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("文件选择");
                FileChooser fileChooser = new FileChooser();//文件选择器
                fileChooser.setTitle("Open Resource File");
//                fileChooser.showOpenDialog(filePick.getScene().getWindow());

                File file = fileChooser.showOpenDialog(filePick.getScene().getWindow());//通过按钮获取父stage
//                System.out.println(file.getPath().replaceAll("\\\\", "\\\\\\\\"));

                try {
                    huffman = new Huffman(fileInOut.readFromFile(file.getPath()));//有文件创建哈夫曼树获取
                    System.out.println(huffman.getText());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
