package org.example;

import java.util.Stack;

public class Calculator {

    public double start (String exp) {
        String prepared = preparingExp(exp);
        String rpn = expressionToRPN(prepared);
        return rpnToAnswer(rpn);
    }

    public String expressionToRPN (String exp) {
        String current = "";
        Stack<Character> stack = new Stack<>();
        int priority;

        for (int i = 0; i < exp.length(); i++) {
            priority = getPriority(exp.charAt(i));

            if (priority == 0) current += exp.charAt(i);
            if (priority == 1) stack.push(exp.charAt(i));
            if (priority > 1) {
                current += " ";
                while (!stack.empty()) {
                    if (getPriority(stack.peek()) >= priority) {
                        current += stack.pop();
                    }
                    else break;
                }
                stack.push(exp.charAt(i));
            }
            if (priority == -1) {
                current += " ";
                while (getPriority(stack.peek()) != 1) {
                    current += stack.pop();
                }
                stack.pop();
            }
        }

        while (!stack.empty()) {
            current += stack.pop();
        }
        return current;
    }

    public double rpnToAnswer (String rpn) {
        String operand;
        Stack<Double> stack = new Stack<>();

        for (int i = 0; i < rpn.length(); i++) {
            if (rpn.charAt(i) == ' ') continue;
            if (getPriority(rpn.charAt(i)) == 0) {
                operand = "";
                while (rpn.charAt(i) != ' ' && getPriority(rpn.charAt(i)) == 0) {
                    operand += rpn.charAt(i++);
                    if (i == rpn.length()) break;
                }
                stack.push(Double.parseDouble(operand));
            }
            if (getPriority(rpn.charAt(i)) > 1) {
                double a = stack.pop();
                double b = stack.pop();

                if (rpn.charAt(i) == '+') stack.push(b + a);
                if (rpn.charAt(i) == '-') stack.push(b - a);
                if (rpn.charAt(i) == '/') stack.push(b / a);
                if (rpn.charAt(i) == '*') stack.push(b * a);
                }
            }
        return stack.pop();
        }

    private int getPriority (char token) {
        if (token == '*' || token == '/') return 3;
        if (token == '+' || token == '-') return 2;
        if (token == '(') return 1;
        if (token == ')') return -1;
        else return 0;
    }

    public String preparingExp (String exp) {
        String prepared = "";
        for (int token = 0; token < exp.length(); token ++) {
            char c = exp.charAt(token);
            if (c == '-') {
                if (token == 0) prepared += '0';
                else if (exp.charAt(token - 1) == '(') prepared += '0';
            }
            prepared += c;
        }
        return prepared;
    }
}
