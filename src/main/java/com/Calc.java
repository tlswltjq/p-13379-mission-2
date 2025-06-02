package com;

public class Calc {
    public static int run(String exp) {
        String[] expression = exp.split(" ");
        int result = Integer.parseInt(expression[0]);
        for (int i = 1; i < expression.length; i += 2) {
            if (expression[i].equals("+")) {
                result += Integer.parseInt(expression[i + 1]);
            } else if (expression[i].equals("-")) {
                result -= Integer.parseInt(expression[i + 1]);
            } else if (expression[i].equals("*")) {
                result *= Integer.parseInt(expression[i + 1]);
            }
        }
        return result;
    }
}
