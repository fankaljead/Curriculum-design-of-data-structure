package subject_1;


import com.jfoenix.controls.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import util.fileInOut.FileInOut;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Author: Zhou Xianghui
 * Time: 2017/11/29 10:46
 * Description: 数据压缩 解压控制层
 */
public class HuffmanController implements Initializable{


    private Huffman huffman;
    private FileInOut<Huffman> fileInOut = new FileInOut<>();

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
    private JFXTextArea textArea;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bindHboxEvents();
        bindBtInitialEvents();//初始化
        bindBtCodeEvents();//编码
        bindBtDecodeEvents();//解码
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
                        fileInOut.writeObjectToFile(huffman, "hfmtree.dat");
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
                        huffman = fileInOut.readObjectFromFile("hfmtree.dat");
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    fileInOut.writeToFile("codefile.txt", huffman.getHuffmanCodes());
                } catch (FileNotFoundException e) {
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
                        huffman = fileInOut.readObjectFromFile("hfmtree.dat");
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("IOException");
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }

                String temp = "";
            }
        });
    }

}
