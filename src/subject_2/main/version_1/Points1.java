package subject_2.main.version_1;

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
    private char[] cardsOperator = new char[cardNumber-1];//存储纸牌的操作符
    private int point = 24;//最后凑成的点数
    public static final int NUMBER_OF_TOTAL_CARDS = 52;//纸牌的总张数
    public char[][] operatorsOptions;//操作符的所有选择
    public static final char[] OPERATORS = {'+', '-', '*', '/'};
    public int[][] operandAllOptions;//操作数的所有情况

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
                usedNumber++;
                isUsed[choose] = true;
            }

        }
    }


    /**
     * 穷举法找到答案
     * @return
     */
    public HashMap<Character[], Double[]> findAnswers() {
        HashMap<Character[], Double[]> answers = new HashMap<>();

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
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < cardNumber; j++) {
//                if(j == 0)
                    operandAllOptions[i][j] = cards[(i) / (factorial(cardNumber-1))];
//                else if(j == 1)
//                    operandAllOptions[i][j] = cards[i / (factorial(cardNumber-1))];
//                else if(j == 2)
//                    operandAllOptions[i][j] = cards[i / (factorial(cardNumber-1))];
//                else if(j == 3)
//                    operandAllOptions[i][j] = cards[i / (factorial(cardNumber-1))];

            }
        }
    }

    public int[][] getOperandAllOptions() {
        return operandAllOptions;
    }

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
}
