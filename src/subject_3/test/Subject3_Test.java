package subject_3.test;

import subject_3.main.version_1.model.TailGame;

import java.util.*;


/**
 * Author: Zhou Xianghui
 * Time: 2017/12/6 20:10
 * Description: 16枚硬币的反面问题的测试类
 */
public class Subject3_Test {
    public static void main(String[] args) {
//        System.out.print("Enter an initial nine coin H's and T's: ");
//        Scanner input = new Scanner(System.in);
//        String s = input.nextLine();
//        char[] initialNode = s.toCharArray();

        TailGame model = new TailGame(100, 100);
//        System.out.println(model.getNode(12));
//        char[] tail = {'T', 'H', 'H', 'H',
//                        'H', 'H', 'H', 'H',
//                        'H', 'H', 'H', 'H',
//                        'H', 'H', 'H', 'T'};
//        System.out.println(model.getIndex(tail));
//        List<Integer> path = model.getShortestPath(model.getIndex(initialNode));

//        System.out.println("The steps to flip the coins are ");
//        System.out.println(path.size());
//        for (int i = 0; i < path.size(); i++) {
//            model.printNode(model.getNode(path.get(i).intValue()));
//        }
        System.out.println(model.toString());

    }
}
