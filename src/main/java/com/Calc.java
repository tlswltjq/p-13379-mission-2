package com;

import java.util.ArrayList;
import java.util.List;

public class Calc {
    public static int run(String exp) {
        List<String> expression = toList(exp);
        for (int i = 0; i < expression.size(); i++) {
            String token = expression.get(i);
            if (token.equals("(")) {

                int closingIndex = expression.subList(expression.indexOf(")") + 1, expression.size()).indexOf(")") + expression.indexOf(")") + 1;
                String subExp = String.join(" ", expression.subList(i + 1, closingIndex));

                int midResult = run(subExp);

                expression.subList(i, closingIndex + 1).clear();
                expression.add(i, String.valueOf(midResult));

                i = -1;
            }
        }

        for (int i = 1; i < expression.size(); i++) {
            String token = expression.get(i);

            if (token.equals("*") || token.equals("/")) {
                int left = Integer.parseInt(expression.get(i - 1));
                int right = Integer.parseInt(expression.get(i + 1));
                int tmpResult = mulOrDiv(left, right, token);
                expression.subList(i - 1, i + 2).clear();
                expression.add(i - 1, String.valueOf(tmpResult));
                i = 0;
            }
        }

        int result = Integer.parseInt(expression.get(0));

        for (int i = 1; i < expression.size(); i += 2) {
            String token = expression.get(i);
            int right = Integer.parseInt(expression.get(i + 1));
            if (token.equals("+")) {
                result += right;
            } else if (token.equals("-")) {
                result -= right;
            }
        }
        return result;
    }

    public static List<String> toList(String exp) {
        exp = exp.replaceAll("\\(", " ( ").replaceAll("\\)", " ) ");
        return new ArrayList<>(List.of(exp.trim().split("\\s+")));
    }

    private static int mulOrDiv(int left, int right, String operator) {
        return operator.equals("*") ? left * right : left / right;
    }
}