package util.stringChange;

import java.util.*;

/**
 * Author: Zhou Xianghui
 * Time: 2017/12/4 21:45
 * Description: 前缀、中缀、后缀的相互转化
 */
public class PreToInToPostfix {

    private static boolean reduce = true;

    /**
     * <p>将前缀表达式转化成中缀表达式</p>
     * @param prefix 前缀表达式
     * @return 中缀表达式
     */
    public static String preToInfix(String prefix){
        return "";
    }

    /**
     * <p>将前缀表达式转化成后缀表达式</p>
     * @param prefix 前缀表达式
     * @return 后缀表达式
     */
    public static String preToPostfix(String prefix){
        return "";
    }

    /**
     * <p>将中缀表达式转化成前缀表达式</p>
     * @param infix 中缀表达式
     * @return 前缀表达式
     */
    public static String inToPrefix(String infix){
        Stack<String> operandStack = new Stack<String>();
        Stack<String> operatorStack = new Stack<String>();

        StringTokenizer tokenizer = new StringTokenizer(infix);
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            if (isOperand(token)) {
                operandStack.push(token);
            }

            else if (token.equals("(") || operatorStack.isEmpty()
                    || rank(token) > rank(operatorStack.peek())) {
                operatorStack.push(token);
            }

            else if (token.equals(")")) {
                while (!operatorStack.peek().equals("(")) {
                    operandStack.push(operationCombine(operatorStack, operandStack,reduce));
                }
                operatorStack.pop();
            }

            else if( rank(token) <= rank(operatorStack.peek())){
                while(!operatorStack.isEmpty() && rank(token) <= rank(operatorStack.peek())){
                    operandStack.push(operationCombine(operatorStack, operandStack,reduce));
                }
                operatorStack.push(token);
            }
        }
        while( !operatorStack.isEmpty() ) {
            operandStack.push(operationCombine(operatorStack, operandStack,reduce));
        }
        return (operandStack.peek());
    }


    /**
     * <p>将中缀表达式转化成后缀表达式</p>
     * @param infix 中缀表达式
     * @return 后缀表达式
     */
    public static String inToPostfix(String infix){
        return "";
    }


    /**
     * <p>将后缀表达式转化成前缀表达式</p>
     * @param postfix 后缀表达式
     * @return 前缀表达式
     */
    public static String postToPrefix(String postfix){
        return "";
    }

    /**
     * <p>将后缀表达式转化成中缀表达式</p>
     * @param postfix 后缀表达式
     * @return 中缀表达式
     */
    public static String postToInfix(String postfix){
        return "";
    }



    public static boolean isOperand(String s) {
        return !(s.equals("+") || s.equals("-") || s.equals("/") || s.equals("*") || s.equals("(") || s.equals(")"));
    }

    public static boolean isNumber(String s){
        try {
            Integer.parseInt(s.trim());
        } catch (Exception e){
            return false;
        }
        return true;
    }

    public static String operationCombine(Stack<String> operatorStack, Stack<String> operandStack, boolean reduce){
        String operator = operatorStack.pop();
        String rightOperand = operandStack.pop();
        String leftOperand = operandStack.pop();
        if (reduce && isNumber(rightOperand) && isNumber(leftOperand)){
            int left = Integer.parseInt(leftOperand);
            int right = Integer.parseInt(rightOperand);
            int result = 0;
            if (operator.equals("+")){
                result = left + right;
            }else if (operator.equals("-")){
                result = left - right;
            }else if (operator.equals("*")){
                result = left * right;
            }else if (operator.equals("/")){
                result = left / right;
            }
            return "" + result;

        }
        String operand = "(" + operator + " " + leftOperand + " "+ rightOperand + ")";
        return operand;
    }

    public static int rank(String s) {
        if (s.equals("+") || s.equals("-"))
            return 1;
        else if (s.equals("/") || s.equals("*"))
            return 2;
        else
            return 0;
    }
}
