package ru.geekbrains11.lesson8;

public class Calculator {

    static String calculate(String s) {
        Expression exp = new Expression(s);
        double result;
        try {
            result = getResult(exp, 0);
        } catch (InvalidExpressionException e) {
            System.out.println("Invalid Expression");
            return "Error";
        }
        System.out.println();
        return crop(String.valueOf(result));
    }

    static double getResult(Expression exp, int returnCondition) throws InvalidExpressionException {
        double result;
        String s = exp.getNext();
        if (s.equals("-")) result = -1 * getResult(exp, 1);
        else if (s.equals("(")) result = getResult(exp, 2);
        else try {
            result = Double.parseDouble(s);
            } catch (NumberFormatException e) {
                throw new InvalidExpressionException();
            }
        if (returnCondition == 1) return result;
        while (exp.hasNext()) {
            s = exp.checkNext();
            if (s.equals("+")) {
                if (returnCondition == 3) return result;
                else {
                    exp.getNext();
                    result += getResult(exp, 3);
                    continue;
                }
            }
            if (s.equals("-")) {
                if (returnCondition == 3) return result;
                else {
                    exp.getNext();
                    result -= getResult(exp, 3);
                    continue;
                }
            }
            if (s.equals(")")) {
                if (returnCondition != 3) exp.getNext();
                return result;
            }
            s = exp.getNext();
            if (s.equals("*")) result *= getResult(exp, 1);
            if (s.equals("/")) result /= getResult(exp, 1);
            if (s.equals("(")) throw new InvalidExpressionException();
        }
        return result;
    }

    static String calculateSqrt(String s) {
        return crop(String.valueOf(Math.sqrt(Double.parseDouble(calculate(s)))));
    }

    static String crop(String s) {
        return (s.endsWith(".0")) ? s.substring(0, s.length() - 2) : s;
    }

    static class InvalidExpressionException extends Exception {}

    static class Expression {
        private final String text;
        private int position = -1;
        private int brackets = 0;

        Expression(String text) {
            this.text = text;
        }

        public boolean hasNext() {
            return position < text.length() - 1;
        }

        public String checkNext() {
            return text.substring(position + 1, position + 2);
        }

        public String getNext() throws InvalidExpressionException {
            char c;
            if (hasNext()) c = text.charAt(position + 1);
            else {
                System.out.println("Failed getNext request. No characters left");
                return "";
            }
            StringBuilder sb = new StringBuilder();
            if (c <= '9' && c >= '0' || c == '.') {
                int dots = 0;
                do {
                    if (c == '.') dots++;
                    if (dots > 1) throw new InvalidExpressionException();
                    sb.append(c);
                    position++;
                    if (hasNext()) c = text.charAt(position + 1);
                    else break;
                } while (c <= '9' && c >= '0' || c == '.');
            } else {
                if (c == '(') brackets++;
                if (c == ')') brackets--;
                if (brackets < 0) throw new InvalidExpressionException();
                sb.append(c);
                position++;
            }
            if (!hasNext() && brackets > 0) throw new InvalidExpressionException();
            String s = sb.toString();
            System.out.print(s + "|");
            return s;
        }

    }

}
