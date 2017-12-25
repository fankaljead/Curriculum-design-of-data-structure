package subject_2.main;

import java.util.*;

/**
 * Author: Zhou Xianghui
 * Time: 2017/12/5 10:08
 * Description:
 */
public class Changer {
    private String inFixToPostFix(String input) {//Sample input {(a+b) * (c-d)/e} = ab+cd-e/*
        final StringBuilder builder = new StringBuilder();
        final LinkedList<String> stack = new LinkedList();
        for(int i=0; i<input.length(); i++) {
            char c = input.charAt(i);
            if(c==' ') continue;
            if(isOperand(c)){
                builder.append(c);
            }else{
                if(stack.isEmpty() && !isClosingBraces(c)){
                    stack.add(String.valueOf(c));
                }else {
                    while(!stack.isEmpty()){
                        if(isClosingBraces(c)){
                            builder.append(popTillOpeningBrace(stack));
                            break;
                        }else if(isGreaterOrEqual(String.valueOf(c),stack.peekLast())){
                            builder.append(stack.pollLast());
                        }else{
                            break;
                        }
                    }
                    if(!(isClosingBraces(c) || isOpeningBraces(c)))
                        stack.add(String.valueOf(c));
                }
            }
        }
        while(!stack.isEmpty()){
            builder.append(stack.pollLast());
        }
        return builder.toString();
    }

    private String popTillOpeningBrace(LinkedList<String> stack) {
        StringBuilder builder = new StringBuilder();
        while(!stack.isEmpty()){
            if(isOpeningBraces(stack.peekLast().charAt(0))){
                stack.pollLast();
                break;
            }else{
                builder.append(stack.pollLast());
            }
        }
        return builder.toString();
    }

    private boolean isGreaterOrEqual(String toCompare, String inList) {
        switch(toCompare){
            case "+":
            case "-":
                return inList.equals("+") || inList.equals("-") || inList.equals("*") || inList.equals("/");
            case "*":
            case "/":
                return inList.equals("*") || inList.equals("/");
        }
        return false;
    }

    private String inFixToPreFix(String input) {
        return new StringBuilder(inFixToPostFix(new StringBuilder(input).reverse().toString())).reverse().toString();
    }

    private String postFixToInFix(String input) {
        LinkedList<String> stack = new LinkedList<>();
        for(int i=0; i<input.length(); i++) {
            char c = input.charAt(i);
            if(isOperand(c)){
                stack.add(String.valueOf(c));
            }else{
                if (isOperator(c) && stack.size()>=2){
                    String str2 = stack.pollLast();
                    String str1 = stack.pollLast();
                    stack.add(str1 + String.valueOf(c) + str2);
                }
            }
        }
        return stack.pollLast();
    }

    private String preFixToInFix(String input) {
        return new StringBuilder(postFixToInFix(new StringBuilder(input).reverse().toString())).reverse().toString();
    }

    private boolean isOpeningBraces(final char brace) {
        return brace == '[' || brace == '{' || brace == '(';
    }

    private boolean isClosingBraces(final char brace) {
        return brace == ']' || brace == '}' || brace == ')';
    }

    private boolean isOperator(final char operator) {
        return operator == '+' || operator == '-' || operator == '*' || operator == '/' || operator == '^';
    }

    private boolean isOperand(final char operator){
        return !(isOperator(operator) || isOpeningBraces(operator) || isClosingBraces(operator)) ;
    }



    public static String
    changeCharsNumbersToExpression(char[] operators, int[] operands){
        String expression = "";//最终的表达式

        LinkedList<String> queue = new LinkedList<>();
        queue.offer(operands[0] + " ");
        for (int i = 0; i < operators.length; i++) {
            queue.offer(operators[i] + " ");
            queue.offer(operands[i+1] + " ");
            if(i != operators.length-1){
                queue.addFirst("(");
                queue.addLast(")");
            }

        }
        for (int i = 0; i < queue.size(); i++) {
            expression += queue.get(i);
        }
        return expression;
    }

    public static void main(String[] args) {
        char[] chars = {'+', '-', '*'};
        int[] ints = {1, 2, 3, 4};
        System.out.println(changeCharsNumbersToExpression(chars, ints));
    }
}
