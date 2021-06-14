package ru.geekbrains.lesson8;

public class Calculator {

    static String calculate(String s) {
        Expression exp = new Expression(s);
        double result = 0;
        try {
            result = getResult(exp, 0);
        } catch (UnexpectedNumberFormat e) {
            return "Unexpected Number Format";
        } catch (NumberFormatException e) {
            return "Invalid Expression";
        }
        return crop(String.valueOf(result));
    }

    static double getResult(Expression exp, int returnCondition) throws UnexpectedNumberFormat, NumberFormatException {
        double result = 0;
        String s = exp.getNext();
        if (s.equals("-")) result = -1 * getResult(exp, 1);
        else if (s.equals("(")) result = getResult(exp, 2);
        else result = Double.parseDouble(s);
        if (returnCondition == 1) return result;
        while (exp.hasNext()) {
            s = exp.getNext();
            if (s.equals(")") && returnCondition == 2) return result;
        }
        return 0;
    }

    static String calculateSqrt(String s) {
        return crop(String.valueOf(Math.sqrt(Double.parseDouble(calculate(s)))));
    }

    static String crop(String s) {
        return (s.endsWith(".0")) ? s.substring(0, s.length() - 2) : s;
    }

    static class UnexpectedNumberFormat extends Exception {}

    static class Expression {
        private final String text;
        private int position = 0;

        Expression(String text) {
            this.text = text;
        }

        public boolean hasNext() {
            return position < text.length() - 1;
        }

        public String checkNext() {
            return text.substring(position, position + 1);
        }

        public String getNext() throws UnexpectedNumberFormat {
            StringBuilder sb = new StringBuilder();
            int dotsNumber = 0;
            char c = text.charAt(position);
            if (!(c <= '9' && c >= '0' || c == '.')) {
                sb.append(c);
                position++;
            }
            while (c <= '9' && c >= '0' || c == '.') {
                sb.append(c);
                if (c == '.') dotsNumber++;
                if (dotsNumber > 1) throw new UnexpectedNumberFormat();
                if (hasNext()) position++;
                else break;
                c = text.charAt(position);
            }
            return sb.toString();
        }

    }

}
