package edu.stevens.cs570.assignments;

import java.util.ArrayList;
import java.util.Stack;

public class ExpressionEvaluator {

    private StringBuilder postfix = new StringBuilder();
    private Stack<String> operatorStack = new Stack<>();
    private Stack<Double> operandStack = new Stack();
    private Stack tempStack = new Stack<>();
    private ArrayList<String> postfix1 = new ArrayList();
    private String[] postfix2;
    private String s;
    private String popped_operators;

    public ExpressionEvaluator(String expr) throws IllegalArgumentException {
        System.out.println(expr);
        expr = expr.replaceAll("\\s+", "");
        System.out.println(expr);
        String expr2 = expr.replace("**", "^");
        System.out.println(expr2);
        // Call validate string function.
        String[] parts = expr2.split("(?<=[-+*/^\\\\(\\\\)])|(?=[-+*/^\\\\(\\\\)])");
        try {
            postfix = convert(parts);
        }
        catch (Exception e){
            throw e;
        }
    }



    public StringBuilder convert(String[] infix) {
        String poped_item;
        for (String a : infix) {
            if (isDigit(a)) {
                postfix.append(a);
                postfix1.add(a);
            }
            else if (a.charAt(0) == ')'){
                while (!operatorStack.isEmpty()){
                    poped_item = operatorStack.pop();
                    if (poped_item.charAt(0)=='(') break;
                    postfix1.add(poped_item);
                }
            }
            else {
                if (operatorStack.isEmpty() || a.charAt(0) =='(') {
                    operatorStack.push(a);
                } else {
                    int current = getPrecedence(a.charAt(0));
                    String top = operatorStack.peek();
                    int top_precedence = getPrecedence(top.charAt(0));



                    if (current > top_precedence) {
                        operatorStack.push(a);
                    } else {

                        while (current <= top_precedence) {
                            poped_item = operatorStack.pop();

                            if (poped_item.charAt(0)!='(')
                                postfix1.add(poped_item);
                            if(operatorStack.isEmpty()) break;
                            top = operatorStack.peek();
                            top_precedence = getPrecedence(top.charAt(0));
                        }
                        operatorStack.push(a);
                    }
                }
            }

        }

        while (!operatorStack.isEmpty()) {
            popped_operators = operatorStack.pop();
            try {
                if (popped_operators.charAt(0) == '(') {
                    throw new IllegalArgumentException("Expression is invalid: Please enter a valid exprression");
                }
                else{
                    postfix1.add(popped_operators);
                }
            }
            catch (Exception e){
                throw e;
            }
        }
        return postfix;
    }

    private boolean isDigit(String a){
            try {
                Double.parseDouble(a);
                return true;
            } catch(NumberFormatException e){
                return false;
            }
        }


    public int getPrecedence(char op) {
        int precedence = 0;
        switch (op) {
            case '+':
                precedence = 1;
                break;
            case '-':
                precedence = 1;
                break;
            case '*':
                precedence = 2;
                break;
            case '/':
                precedence = 2;
                break;
            case '^':
                precedence = 4;
                break;
            case '(':
                precedence = 3;
                break;

        }

        return precedence;
    }

    public static double calculate(char operator, double a, double b) {

        if (operator == '+')
            return a + b;

        else if (operator == '-')
            return a - b;

        else if (operator == '*')
            return a * b;

        else if (operator == '/')
            return a / b;
        else if(operator == '^')
            return (int) Math.pow(a,b);

        else {
            return 0;
        }
    }


    public double evaluate(){
        double op2, op1, sum, final_ans;
        for (String x : postfix1){
            //System.out.println(x);
            if (isDigit(x)) {
                operandStack.push(Double.parseDouble(x));
            }
            else{
                op2 = operandStack.pop();
                op1 = operandStack.pop();
                sum = calculate(x.charAt(0),op1,op2);
                operandStack.push(sum);
            }
        }
        //System.out.println(operandStack);
        final_ans = operandStack.pop();
        return final_ans;
    }
    public static void main(String[] args){

        ExpressionEvaluator exp = new ExpressionEvaluator("2*3");
        double sum = exp.evaluate();
        System. out.println("The Expression evaluates to: +"+ sum);
    }


}