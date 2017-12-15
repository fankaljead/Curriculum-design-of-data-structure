package subject_6.main.controller;

import com.jfoenix.controls.JFXDialog;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import subject_6.main.model.Maze;
import subject_6.main.model.Position;
import util.fileInOut.FileInOut;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Author: Zhou Xianghui
 * Time: 2017/12/15 16:17
 * Description:
 */
public class ShowMazeController implements Initializable {


    private int rows = 8;
    private int columns = 8;
    private Maze maze = new Maze(rows, columns);
    private JFXDialog dialog = new JFXDialog();
    private boolean findAccess = false; // 是否找到迷宫通路
    private Position[][] labyrinth = maze.getLabyrinth();
    private FileInOut out = new FileInOut();
            @FXML
            private GridPane mazeArea;

            @FXML
            private StackPane stackPane;
            private boolean findWay;

            @Override
            public void initialize(URL location, ResourceBundle resources) {
                dialog.setDialogContainer(stackPane);
                mazeArea.setVgap(4);
                mazeArea.setHgap(4);
                initialMazeArea();
            }

            //初始化迷宫
        private void initialMazeArea(){
            mazeArea.getChildren().clear();
            for (int i = 0; i < rows; i++) {

                for (int j = 0; j < columns; j++) {
                    if(maze.getMazeData()[i][j] == 0){
                        Image image = new Image(getClass().getResource("../images/back.png").toExternalForm());
                        ImageView imageView = new ImageView(image);
                        imageView.setId(String.valueOf(i*rows + j));
                        mazeArea.add(imageView, j, i);
                    }else {
                        Image image = new Image(getClass().getResource("../images/wall.jpg").toExternalForm());
                        ImageView imageView = new ImageView(image);
                        imageView.setId(String.valueOf(i*rows + j));
                        mazeArea.add(imageView, j, i);
                    }

                }
            }


        }


        @FXML
        private void okAction(MouseEvent e){
//            findWay = false;
//            byte[][] temp = maze.mazeDataCopy();
//
//            if(maze.findPath()){
                findAccess(labyrinth, 0, 0);
                findAccess = false;
//        }
//        else{
//            showDialog("该迷宫无法找出出路");
//        }
    }

    //取消事件
    @FXML
    private void cancelAction(MouseEvent e){
       randomAction(e);
    }

    //随机生成
    @FXML
    private void randomAction(MouseEvent e){
        maze = new Maze(rows, columns);
        labyrinth = maze.getLabyrinth();
        findAccess = false;
        findWay = false;
        initialMazeArea();
    }

    //保存数据
    @FXML
    private void saveAction(MouseEvent e) throws IOException {
        out.writeObjectToFile(maze, "maze.dat");
    }

    //取得数据
    @FXML
    private void getAction(MouseEvent e){
        try{
            maze = (Maze)out.readObjectFromFile("maze.dat");
            labyrinth = maze.getLabyrinth();
        }catch (IOException ee){
            showDialog("文件里没有对象");
        }catch (ClassNotFoundException ee){
            showDialog("文件里没有对象");
        }
    }


    //对话窗口
    private void showDialog(String tip){
        dialog.setContent(new Label(tip));
        dialog.show();
    }



    //找到路径
    public void findAccess(Position[][] labyrinth, int x, int y) {
        labyrinth[x][y].setAttribute(-1);
        if (x == labyrinth.length - 1 && y == labyrinth[labyrinth.length - 1].length - 1) {
            setImages(x, y, "red");
            findAccess = true;
            return;
        }

        if (!findAccess && y + 1 < labyrinth[x].length && x < labyrinth.length
                && labyrinth[x][y + 1].getAttribute() == 0) {
            labyrinth[x][y].setDirection(Position.RIGHT);

            setImages(x, y, "right");
            findAccess(labyrinth, x, y + 1);
        }
        if (!findAccess && x + 1 < labyrinth.length && y < labyrinth[x + 1].length
                && labyrinth[x + 1][y].getAttribute() == 0) {
            labyrinth[x][y].setDirection(Position.DOWN);
            setImages(x, y, "down");
            findAccess(labyrinth, x + 1, y);
        }
        if (!findAccess && y > 0 && y - 1 < labyrinth[x].length && x < labyrinth.length
                && labyrinth[x][y - 1].getAttribute() == 0) {
            labyrinth[x][y].setDirection(Position.LEFT);
            setImages(x, y, "left");
            findAccess(labyrinth, x, y - 1);

        }
        if (!findAccess && x - 1 < labyrinth.length && x > 0 && y < labyrinth[x - 1].length
                && labyrinth[x - 1][y].getAttribute() == 0) {
            labyrinth[x][y].setDirection(Position.UP);
            setImages(x, y, "up");
            findAccess(labyrinth, x - 1, y);
        }

        if (!findAccess) {
            labyrinth[x][y].setDirection("");
            setImages(x, y, "flush");
        }

        if (x == 0 && y == 0 && !findAccess) {
            showDialog("此迷宫没有通路");
        }


    }


    //改变图片
    public void setImages(int x, int y, String name){
        Image image = new Image(getClass().getResource("../images/" + name + ".jpg").toExternalForm());
        ImageView imageView = (ImageView)stackPane.getScene().lookup("#" + String.valueOf(x*columns + y));
        imageView.setImage(image);
    }



}
