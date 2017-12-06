package subject_2.main.version_1;

import subject_2.main.Calculator;
import subject_2.main.Changer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: Zhou Xianghui
 * Time: 2017/12/5 18:18
 * Description: 24点游戏类
 */
public class Points1 {
    private int cardNumber = 4;//纸牌的张数
    private int[] cards = new int[cardNumber];//存储纸牌数字
    private int[] cardRealNumber = new int[cardNumber];//存储纸牌的文件数字
    private char[] cardsOperator = new char[cardNumber-1];//存储纸牌的操作符
    private int point = 24;//最后凑成的点数
    public static final int NUMBER_OF_TOTAL_CARDS = 52;//纸牌的总张数
    public char[][] operatorsOptions;//操作符的所有选择
    public static final char[] OPERATORS = {'+', '-', '*', '/'};
    public int[][] operandAllOptions;//操作数的所有情况
    private ArrayList<String> answers = new ArrayList<>();//答案

    public char[][] getOperatorsOptions() {
        return operatorsOptions;
    }




    public Points1(int cardNumber, int point) {
        this.cardNumber = cardNumber;
        this.point = point;
        startAGame();
        setOperatorsOptions();
        setOperandAllOptions();
    }

    public Points1(int cardNumber) {
        this.cardNumber = cardNumber;
        startAGame();
        setOperatorsOptions();
        setOperandAllOptions();
    }

    public Points1(){
        startAGame();
        setOperatorsOptions();
        setOperandAllOptions();
    }

    public int[] getCardRealNumber() {
        return cardRealNumber;
    }

    public void setCardRealNumber(int[] cardRealNumber) {
        this.cardRealNumber = cardRealNumber;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getCardNumber() {

        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int[] getCards() {
        return cards;
    }

    public void setCards(int[] cards) {
        this.cards = cards;
    }

    public char[] getCardsOperator() {
        return cardsOperator;
    }

    public void setCardsOperator(char[] cardsOperator) {
        this.cardsOperator = cardsOperator;
    }

    public void startAGame() {
        boolean[] isUsed = new boolean[NUMBER_OF_TOTAL_CARDS];
        int usedNumber = 0;
        for (int i = 0; i < isUsed.length; i++) {
            isUsed[i] = false;
        }

        while (usedNumber < cardNumber) {
            int choose = (int)(Math.random() * NUMBER_OF_TOTAL_CARDS);
            if(!isUsed[choose]){
                cards[usedNumber] = choose/cardNumber + 1;
                cardRealNumber[usedNumber] = choose + 1;
                usedNumber++;
                isUsed[choose] = true;
            }

        }
    }


    /**
     * 穷举法找到答案
     * @return
     */
    public ArrayList<String> getAnswers() {
        for (int i = 0; i < operatorsOptions.length; i++) {
            for (int j = 0; j < operandAllOptions.length; j++) {
                if(Calculator.calculateAnExpression(operatorsOptions[i],  operandAllOptions[j]) == point){
                    answers.add(Changer.changeCharsNumbersToExpression(operatorsOptions[i], operandAllOptions[j]));
                }
            }
        }

        return answers;
    }

    //穷举操作符的种数
    private void setOperatorsOptions(){

        int length = (int)Math.pow(cardNumber, cardNumber -1);
        operatorsOptions = new char[length][cardNumber-1];
        for (int i = 0; i < length; i++) {
            for (int l = 0; l < cardNumber-1; l++) {
                //关键操作
                operatorsOptions[i][l] = OPERATORS[(i) % ((int)Math.pow(cardNumber, cardNumber-1-l)) / ((int)Math.pow(cardNumber, cardNumber-1-l-1))];
            }
        }
//        for (int i = 0; i < cardNumber; i++) {
//            for (int j = 0; j < cardNumber; j++) {
//                for (int k = 0; k < cardNumber; k++) {
//                    for (int l = 0; l < cardNumber-1; l++) {
//                        operatorsOptions[16*i + 4*j + k][l] = OPERATORS[(16*i + 4*j + k) % ((int)Math.pow(cardNumber, cardNumber-1-l)) / ((int)Math.pow(cardNumber, cardNumber-1-l-1))];
////                        if(l == 0)
////                            operatorsOptions[16*i + 4*j + k][l] = OPERATORS[(16*i + 4*j + k)%64/16];
////                        else if(l == 1)
////                            operatorsOptions[16*i + 4*j + k][l] = OPERATORS[(16*i + 4*j + k)%16/4];
////                        else
////                            operatorsOptions[16*i + 4*j + k][l] = OPERATORS[(16*i + 4*j + k)%4/1];
//
//
//                    }
//                }
//            }
//        }
    }



    public void setOperandAllOptions() {
        int length = factorial(cardNumber);
        operandAllOptions = new int[length][cardNumber];
        Arrays.sort(cards);
        for (int i = 0; i < length ; i++) {
            operandAllOptions[i] = cards.clone();
            fullSort(cards, cardNumber);
        }


    }

    public int[][] getOperandAllOptions() {
        return operandAllOptions;
    }

    /**
     * 计算n的阶乘
     *
     * @param num
     * @return
     */
    public int factorial(int num){
        if(num <=0 ){
            return 1;
        }

        else {
            int temp = 1;
            for (int i = 1; i <= num; i++) {
                temp *= i;
            }
            return temp;
        }

    }


    /**
     * 给已排好序的数组组合
     * @param arr
     * @param n
     * @return
     */
    final boolean fullSort(int[] arr, int n) {
        int i = 0, j = 0, k = -1, l, temp;

        for (i = 0; i < n - 1; i++) { // 找最后的升序的位置
            if (arr[i] < arr[i + 1]) {
                k = i;
            }
        }

        if (k >= 0) {
            l = -1;

            for (i = 0; i < n; i++) { // 找到最后一个升序且是最大的数的下标
                if (arr[k] < arr[i]) {
                    l = i;
                }
            }

            temp = arr[k];
            arr[k] = arr[l];
            arr[l] = temp;

            for (i = k + 1; i < n; i++){// 将k+1的元素与末尾对调

                j = n - i + k;
                if (i >= j){
                    break;
                }

                {
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        if (k == -1) {
            return false;
        }

        return true;
    }


    /**
     * 判断输入是否合法
     * @param in
     * @return
     */
    public boolean isEqualCards(String in){
        String[] ins = in.split("[()+-/*]");
        int[] insNumber = new int[cardNumber];
        int[] temp = cards.clone();
        Arrays.sort(temp);
        for (int i = 0 , j = 0; i < ins.length; i++) {
            if(ins[i].length() != 0){
                try {
                    insNumber[j++] = Integer.valueOf(ins[i].trim());
                }catch (Exception e){
                    return false;
                }
            }
        }

        Arrays.sort(insNumber);

        for (int i = 0; i < temp.length; i++) {
            if (temp[i] == insNumber[i]){
                continue;
            }else{
                return false;
            }

        }
        return true;
    }
}
