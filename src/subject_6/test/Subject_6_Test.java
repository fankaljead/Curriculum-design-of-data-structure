package subject_6.test;

import subject_6.main.model.Maze;
import subject_6.main.model.Position;

import java.util.ArrayList;

/**
 * Author: Zhou Xianghui
 * Time: 2017/12/15 10:45
 * Description: 第6题测试类
 */
public class Subject_6_Test {
    public static void main(String[] args) {
       Maze maze = new Maze(4, 4);
       byte[][] x = maze.getMazeData();
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[i].length; j++) {
                System.out.print(x[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println(maze.findPath());
        System.out.println(maze.findPath());
    }
}
