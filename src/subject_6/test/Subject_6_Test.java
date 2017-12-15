package subject_6.test;

import subject_6.main.model.Maze;

/**
 * Author: Zhou Xianghui
 * Time: 2017/12/15 10:45
 * Description: 第6题测试类
 */
public class Subject_6_Test {
    public static void main(String[] args) {
        int r = 12;
        int c = 12;
        Maze maze = new Maze(r, c);
        byte[][] x = maze.getMazeData();

        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[i].length; j++) {
                System.out.print(x[i][j] + " ");
            }
            System.out.println();
        }
    }
}
