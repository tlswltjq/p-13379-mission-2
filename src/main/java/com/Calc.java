package com;

import java.util.ArrayList;
import java.util.List;

public class Calc {
    public static int run(String exp) {
        List<String> expression = toList(exp.replaceAll("-\\(", " -1 * ("));

        for (int i = 0; i < expression.size(); i++) {
            String token = expression.get(i);
            if (token.equals("(")) {
                int closingIndex = -1;
                int depth = 0;
                for (int j = i + 1; j < expression.size(); j++) {
                    if (expression.get(j).equals("(")) {
                        depth++;
                    } else if (expression.get(j).equals(")")) {
                        if (depth == 0) {
                            closingIndex = j;
                            break;
                        } else {
                            depth--;
                        }
                    }
                }
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