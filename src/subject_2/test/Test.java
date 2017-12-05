package subject_2.test;

import subject_2.main.Calculator;
import subject_2.main.version_1.Points1;
import util.stringChange.PreToInToPostfix;

import java.util.HashMap;

/**
 * Author: Zhou Xianghui
 * Time: 2017/12/4 21:36
 * Description: 测试类
 */
public class Test {
    public static void main(String[] args) {
        Points1 points1 = new Points1();
        for (int i = 0; i < points1.getCards().length; i++) {
            System.out.print(points1.getCards()[i] + "\t");
        }
        System.out.println();

        System.out.println();

        char[] chars = {'+', '*', '+'};
        double[] doubles = {1, 2, 3, 4};

        HashMap<Character[], Double[]> hashMap = points1.findAnswers();
//        for (int i = 0; i < points1.getOperatorsOptions().length; i++) {
//            for (int j = 0; j < points1.getOperatorsOptions()[i].length; j++) {
//                System.out.print(points1.getOperatorsOptions()[i][j] + "\t");
//
//            }
//            System.out.println();
//            if((i+1) %4 == 0)
//                System.out.println();
//        }

        for (int i = 0; i < points1.getOperandAllOptions().length; i++) {
            for (int j = 0; j < points1.getOperandAllOptions()[i].length; j++) {
                System.out.print(points1.getOperandAllOptions()[i][j] + "\t");

            }
            System.out.println();
            if((i+1) %6 == 0)
                System.out.println();
        }
    }
}
