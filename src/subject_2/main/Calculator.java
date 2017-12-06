package subject_2.main;


import java.util.*;

/**
 * Author: Zhou Xianghui
 * Time: 2017/12/4 21:22
 * Description: 计算表达式类
 */
public class Calculator {


    /**
     * 计算前缀表达式
     * @param expression
     * @return 前缀表达式结果
     */
    public static double calculatePrefix(String expression){
        //创建一个operandStack栈来存储操作数
        LinkedList<Double> operandQueue
                = new LinkedList<>();

        //创建一个operatorStack栈来存储操作符
        LinkedList<Character> operatorQueue
                = new LinkedList<>();

        //字符串分解
        StringTokenizer tokens =
                new StringTokenizer(expression, "()+-/* ", true);

        //扫描被分解后的字符串tokens
        while (tokens.hasMoreTokens()) {

            String token = tokens.nextToken().trim();

            if (token.length() == 0) {
                continue;
            }

            //当操作符为+或者-时
            else if (token.charAt(0) == '+' || token.charAt(0) == '-' || token.charAt(0) == '*' || token.charAt(0) == '/') {
                //将操作符存入栈中
                operatorQueue.offer(token.charAt(0));

            }
            //将操作数推入栈
            else {
                operandQueue.offer(new Double(token));
            }

        }
        System.out.println(operandQueue.toString());
        System.out.println(operatorQueue.toString());
        //处理最后的操作符，并进行计算
        while (!operatorQueue.isEmpty()) {
            processAnOperator(operandQueue, operatorQueue);
        }

//        double answer = operandQueue.get(0);
//        for (int i = 0; i < operatorQueue.size(); i++) {
//            answer = calculateAnOperator(operatorQueue.get(i),operandQueue.get(i+1), answer);
//            System.out.println("answer:" + answer);
//        }


        //返回最后的值
        return operandQueue.poll();

    }

    public static double calculateAnExpression(char[] operators, double[] operands){
        if(operands.length != operands.length - 1){
            return 0;
        }

        double answer = operands[0];
        for (int i = 0; i < operators.length; i++) {
            answer = calculateAnOperator(operators[i], operands[i+1], answer);
            System.out.println("answer:" + answer);
        }

        return answer;
    }

    public static int calculateAnExpression(char[] operators, int[] operands){
        if(operators.length != operands.length - 1){
            return 0;
        }

        int answer = operands[0];
        for (int i = 0; i < operators.length; i++) {
            answer = calculateAnOperator(operators[i], operands[i+1], answer);
//            System.out.println("answer:" + answer);
        }

        return answer;
    }

    /**
     * 计算一个运算符的两个操作数
     * @param op
     * @param op1
     * @param op2
     * @return
     */
    public static double calculateAnOperator(char op, double op1, double op2){
        if (op == '+') {
            return (op2 + op1);
        }

        else if (op == '-') {
            return (op2 - op1);
        }

        else if (op == '*') {
            return (op2 * op1);
        }

        else if (op == '/') {
            return (op2 / op1);
        }

        return 0;
    }

    public static int calculateAnOperator(char op, int op1, int op2){
        if (op == '+') {
            return (op2 + op1);
        }

        else if (op == '-') {
            return (op2 - op1);
        }

        else if (op == '*') {
            return (op2 * op1);
        }

        else if (op == '/') {
            return (op2 / op1);
        }

        return 0;
    }

    private static void processAnOperator(LinkedList<Double> operandQueue, LinkedList<Character> operatorQueue) {
        char op = operatorQueue.poll();
        double op2 = operandQueue.poll();
        double op1 = operandQueue.poll();

        if (op == '+') {
            operandQueue.addFirst(op2 + op1);
        }

        else if (op == '-') {
            operandQueue.addFirst(op2 - op1);
        }

        else if (op == '*') {
            operandQueue.addFirst(op2 * op1);
        }

        else if (op == '/') {
            operandQueue.addFirst(op2 / op1);
        }
    }


    /**
     * 计算中缀表达式
     * @param expression 中缀表达式
     * @return 中缀表达式结果
     */
    public static double calculateInfix(String expression){
        //创建一个operandStack栈来存储操作数
        Stack<Double> operandStack
                = new Stack<>();

        //创建一个operatorStack栈来存储操作符
        Stack<Character> operatorStack
                = new Stack<Character>();

        //字符串分解
        StringTokenizer tokens =
                new StringTokenizer(expression, "()+-/*", true);

        //扫描被分解后的字符串tokens
        while (tokens.hasMoreTokens()) {

            String token = tokens.nextToken().trim();

            if (token.length() == 0) {
                continue;
            }

            //当操作符为+或者-时
            else if (token.charAt(0) == '+' || token.charAt(0) == '-') {

                //将所有的操作符入栈
                while (!operatorStack.isEmpty() &&
                        (operatorStack.peek() == '+' ||
                                operatorStack.peek() == '-' ||
                                operatorStack.peek() == '*' ||
                                operatorStack.peek() == '/')) {

                    //处理运算符和栈顶的两个元素
                    processAnOperator(operandStack, operatorStack);

                }

                //将操作符存入栈中
                operatorStack.push(token.charAt(0));

            }

            //当操作符为*或者/时
            else if (token.charAt(0) == '*' || token.charAt(0) == '/') {

                //将所有的操作符入栈
                while (!operatorStack.isEmpty() &&
                        (operatorStack.peek() == '*' ||
                                operatorStack.peek() == '/')) {
                    //处理运算符和栈顶的两个元素
                    processAnOperator(operandStack, operatorStack);
                }

                //将操作符存入栈中
                operatorStack.push(token.charAt(0));
            }

            //当操作符为(，只将其存入栈中，不计算
            else if (token.trim().charAt(0) == '(') {
                operatorStack.push('(');
            }

            //当操作符为),进行运算
            else if (token.trim().charAt(0) == ')') {

                while (operatorStack.peek() != '(') {
                    processAnOperator(operandStack, operatorStack);
                }

                operatorStack.pop();
            }

            //当字符为操作数时，将其存入operandStack中
            else {
                operandStack.push(new Double(token));
            }
        }


        //处理最后的操作符，并进行计算
        while (!operatorStack.isEmpty()) {
            processAnOperator(operandStack, operatorStack);
        }

        //返回最后的值
        return operandStack.pop();
    }


    //处理运算
    public static void processAnOperator(
            Stack<Double> operandStack,
            Stack<Character> operatorStack) {

        char op = operatorStack.pop();
        double op1 = operandStack.pop();
        double op2 = operandStack.pop();

        if (op == '+') {
            operandStack.push(op2 + op1);
        }

        else if (op == '-') {
            operandStack.push(op2 - op1);
        }

        else if (op == '*') {
            operandStack.push(op2 * op1);
        }

        else if (op == '/') {
            operandStack.push(op2 / op1);
        }
    }

}
