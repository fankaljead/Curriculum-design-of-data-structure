package subject_4.main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Author: Zhou Xianghui
 * Time: 2017/12/12 9:52
 * Description:
 */
public class Subject_4_Controller implements Initializable {
    protected Parent arrayListRoot = FXMLLoader.load(getClass().getResource("version_1/arrayList/view/ShowArrayListView.fxml"));
    protected Parent linkedListRoot = FXMLLoader.load(getClass().getResource("version_1/linkedList/view/ShowLinkedListView.fxml"));
    protected Parent stackRoot = FXMLLoader.load(getClass().getResource("version_1/stack/view/ShowStackView.fxml"));
    protected Parent queueRoot = FXMLLoader.load(getClass().getResource("version_1/queue/view/ShowQueueView.fxml"));
    protected Parent treeRoot = FXMLLoader.load(getClass().getResource("version_2/view/ShowBinaryTreeView.fxml"));



    @FXML
    protected Pane option_view;

    public Subject_4_Controller() throws IOException {
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        option_view.getChildren().add(arrayListRoot);
    }

    //展示线性表
    @FXML
    protected void show_array_action(MouseEvent e){
        option_view.getChildren().clear();
        option_view.getChildren().add(arrayListRoot);
    }

    //展示链表
    @FXML
    protected void show_linked_action(MouseEvent e){
        option_view.getChildren().clear();
        option_view.getChildren().add(linkedListRoot);
    }

    //展示栈
    @FXML
    protected void show_stack_action(MouseEvent e){
        option_view.getChildren().clear();
        option_view.getChildren().add(stackRoot);
    }

    //展示队列
    @FXML
    protected void show_queue_action(MouseEvent e){
        option_view.getChildren().clear();
        option_view.getChildren().add(queueRoot);
    }

    //展示二叉树
    @FXML
    protected void show_tree_action(MouseEvent e){
        option_view.getChildren().clear();
        option_view.getChildren().add(treeRoot);
    }
}
