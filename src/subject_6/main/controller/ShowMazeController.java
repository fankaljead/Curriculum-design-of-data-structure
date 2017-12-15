package subject_6.main.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import subject_6.main.model.Maze;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Author: Zhou Xianghui
 * Time: 2017/12/15 16:17
 * Description:
 */
public class ShowMazeController implements Initializable {

    private Maze maze = new Maze(10, 10);
    private int rows = maze.getRows();
    private int columns = maze.getColumns();

    @FXML
    private GridPane mazeArea;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mazeArea.setVgap(4);
        mazeArea.setHgap(4);
        initialMazeArea();
    }

    //初始化迷宫
    private void initialMazeArea(){
        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < columns; j++) {
                if(maze.getMazeData()[i][j] == 0){
                    Image image = new Image(getClass().getResource("../images/back.png").toExternalForm());
                    ImageView imageView = new ImageView(image);
                    mazeArea.add(imageView, j, i);
                }else {
                    Image image = new Image(getClass().getResource("../images/wall.jpg").toExternalForm());
                    ImageView imageView = new ImageView(image);
                    mazeArea.add(imageView, j, i);
                }

            }
        }


    }


    @FXML
    private void okAction(MouseEvent e){

    }

    @FXML
    private void cancelAction(MouseEvent e){

    }

    @FXML
    private void randomAction(MouseEvent e){

    }

    @FXML
    private void saveAction(MouseEvent e){

    }

    @FXML
    private void getAction(MouseEvent e){

    }
}
