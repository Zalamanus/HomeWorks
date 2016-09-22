package com.javarush.test.level34.lesson02.home01;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* Рекурсия для мат.выражения
На вход подается строка - математическое выражение.
Выражение включает целые и дробные числа, скобки (), пробелы, знак отрицания -, возведение в степень ^, sin(x), cos(x), tan(x)
Для sin(x), cos(x), tan(x) выражение внутри скобок считать градусами, например, cos(3 + 19*3)=0.5
Степень задается так: a^(1+3) и так a^4, что эквивалентно a*a*a*a.
С помощью рекурсии вычислить выражение и количество мататичесемких операций. Вывести через пробел результат в консоль.
Результат выводить с точностью до двух знаков, для 0.33333 вывести 0.33, использовать стандартный принцип округления.
Не создавайте статические переменные и поля класса.
Не пишите косвенную рекурсию.
Пример, состоящий из операций sin * - + * +:
sin(2*(-5+1.5*4)+28)
Результат:
0.5 6

sin(2(-5+1.54)+28) expected output 0.5 6 actually 0.5 6
(-2)*(-2) expected output 4 3 actually 4 3
(-2)/(-2) expected output 1 3 actually 1 3
sin(-30) expected output -0.5 2 actually -0.5 2
cos(-30) expected output 0.87 2 actually 0.87 2
tan(-30) expected output -0.58 2 actually -0.58 2
cos(3 + 19*3) expected output 0.5 3 actually 0.5 3
2+8*(9/4-1.5)^(1+1) expected output 6.5 6 actually 6.5 6

0.005 expected output 0.01 0 actually 0.01 0
0.0049 expected output 0 0 actually 0 0
tan(45) expected output 1 1 actually 1 1
0+0.304 expected output 0.3 1 actually 0.3 1
0.3051 expected output 0.31 0 actually 0.31 0
1+(1+(1+1)(1+1))(1+1)+1 expected output 12 8 actually 12 8
tan(44+sin(89-cos(180)^2)) expected output 1 6 actually 1 6
-2+(-2+(-2)-2*(2+2)) expected output -14 8 actually -14 8
sin(80+(2+(1+1))*(1+1)+2) expected output 1 7 actually 1 7
1+4/2/2+2^2+2*2-2^(2-1+1) expected output 6 11 actually 6 11
2^10+2^(5+5) expected output 2048 4 actually 2048 4
1.01+(2.02-1+1/0.5*1.02)/0.1+0.25+41.1 expected output 72.96 8 actually 72.96 8
0.000025+0.000012 expected output 0 1 actually 0 1
-2-(-2-1-(-2)-(-2)-(-2-2-(-2)-2)-2-2) expected output -3 16 actually -3 16
cos(3 + 19*3) expected output 0.5 3 actually 0.5 3
2(589+((24540.1548/0.01(-2+9^2))+((25123.12+45877*25)+25))-547) expected output 8302231.36 14 actually 8302231.36 14
(-1 + (-2)) expected output -3 3 actually -3 3
-sin(2(-5+1.54)+28) expected output -0.5 7 actually -0.5 7
sin(100)-sin(100) expected output 0 3 actually 0 3
-(-22+22*2)expected output -22 4 actually -22 4
-2^(-2)expected output -0.25 3 actually -0.25 3
*/
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
/*        solution.recursion("sin(2*(-5+1.5*4)+28)", 0); //expected output 0.5 6
        String s = "cos(sin(sin(tan(tan(tan(45))))))";
        System.out.print(s + " expected output 1 6 actually ");
        solution.recursion(s, 0);
        s = "0.09*0.008*0.0075/1000/0.0000000001";
        System.out.print(s + " expected output 54 4 actually ");
        solution.recursion(s, 0);
        s = "0.01^5";
        System.out.print(s + " expected output 0 1 actually ");
        solution.recursion(s, 0);
        s = "0.01*0.01+0.001+0.0009+0.009*5";
        System.out.print(s + " expected output 0.05 5 actually ");
        solution.recursion(s, 0);*/
        String s = "(sin(-30)) ^ -2";
        System.out.print(s + " expected output 4 4 actually ");
        solution.recursion(s, 0);
/*        s = "(((0-0)))";
        System.out.print(s + " expected output 0 1 actually ");
        solution.recursion(s, 0);*/
//        s = "-2^-2";
//        System.out.print(s + " expected output -0.25 3 actually ");
//        solution.recursion(s, 0);
/*        s = "(-2)^(-2)^(-2)";
        System.out.print(s + " expected output 16 5 actually ");
        solution.recursion(s, 0);
        s = "-(2^(-2))";
        System.out.print(s + " expected output -0.25 3 actually ");
        solution.recursion(s, 0);
        s = "(-2)^(-2)";
        System.out.print(s + " expected output 0.25 3 actually ");
        solution.recursion(s, 0);
        s = "0.00000000000009^0";
        System.out.print(s + " expected output 1 1 actually ");
        solution.recursion(s, 0);
        s = "0.00000000000009*0.0000000000000000008";
        System.out.print(s + " expected output 0 1 actually ");
        solution.recursion(s, 0);*/
//        s = "       sin (2*(- 5+1.5*4)+43)- cos(2*(-5+1.5*4)+43)";
//        System.out.print(s + " expected output 0 13 actually ");
//        solution.recursion(s, 0);
/*        s = "sin(2*(-5+1.5*4)+28)";
        System.out.print(s + " expected output 0.5 6 actually ");
        solution.recursion(s, 0);
        s = "cos(sin(tan(78) +40)) +40";
        System.out.print(s + " expected output 41 5 actually ");
        solution.recursion(s, 0);
        s = "(-2)*(-2)";
        System.out.print(s + " expected output 4 3 actually ");
        solution.recursion(s, 0);
        s = "(-2)/(-2)";
        System.out.print(s + " expected output 1 3 actually ");
        solution.recursion(s, 0);
        s = "sin(-30)";
        System.out.print(s + " expected output -0.5 2 actually ");
        solution.recursion(s, 0);
        s = "cos(- 30) ";
        System.out.print(s + " expected output 0.87 2 actually ");
        solution.recursion(s, 0);
        s = "tan(-30)";
        System.out.print(s + " expected output -0.58 2 actually ");
        solution.recursion(s, 0);
        s = "cos(3 + 19*3)";
        System.out.print(s + " expected output 0.5 3 actually ");
        solution.recursion(s, 0);
        s = "2+8*(9/4-1.5)^(1+1)";
        System.out.print(s + " expected output 6.5 6 actually ");
        solution.recursion(s, 0);*/
//        s = "0.005 ";
//        System.out.print(s + " expected output 0.01 0 actually ");
//        solution.recursion(s, 0);
/*        s = "0.0049 ";
        System.out.print(s + " expected output 0 0 actually ");
        solution.recursion(s, 0);
        s = "tan(45)";
        System.out.print(s + " expected output 1 1 actually ");
        solution.recursion(s, 0);
        s = "0+0.304";
        System.out.print(s + " expected output 0.3 1 actually ");
        solution.recursion(s, 0);*/
//       s = "- 0.305";
//       System.out.print(s + " expected output -0.31 1 actually ");
//       solution.recursion(s, 0);
 /*       s = "0.3051";
        System.out.print(s + " expected output 0.31 0 actually ");
        solution.recursion(s, 0);
        s = "1+(1+(1+1)*(1+1))*(1+1)+1";
        System.out.print(s + " expected output 12 8 actually ");
        solution.recursion(s, 0);
        s = "tan(44+sin(89-cos(180)^2))";
        System.out.print(s + " expected output 1 6 actually ");
        solution.recursion(s, 0);
        s = "-2+(-2+(-2)-2*(2+2))";
        System.out.print(s + " expected output -14 8 actually ");
        solution.recursion(s, 0);
        s = "sin(80+(2+(1+1))*(1+1)+2)";
        System.out.print(s + " expected output 1 7 actually ");
        solution.recursion(s, 0);
        s = "1+4/2/2+2^2+2*2-2^(2-1+1)";
        System.out.print(s + " expected output 6 11 actually ");
        solution.recursion(s, 0);
        s = "2^10+2^(5+5)";
        System.out.print(s + " expected output 2048 4 actually ");
        solution.recursion(s, 0);
        s = "1.01+(2.02-1+1/0.5*1.02)/0.1+0.25+41.1";
        System.out.print(s + " expected output 72.96 8 actually ");
        solution.recursion(s, 0);
        s = "0.000025+0.000012";
        System.out.print(s + " expected output 0 1 actually ");
        solution.recursion(s, 0);
        s = "-2-(-2-1-(-2)-(-2)-(-2-2-(-2)-2)-2-2)";
        System.out.print(s + " expected output -3 16 actually ");
        solution.recursion(s, 0);
        s = "cos(3 + 19*3)";
        System.out.print(s + " expected output 0.5 3 actually ");
        solution.recursion(s, 0);
        s = "2*(589+((2454*0.1548/0.01*(-2+9^2))+((25*123.12+45877*25)+25))-547)";
        System.out.print(s + " expected output 8302231.36 14 actually ");
        solution.recursion(s, 0);
        s = "(-1 + (-2))";
        System.out.print(s + " expected output -3 3 actually ");
        solution.recursion(s, 0);
        s = "-sin(2*(-5+1.5*4)+28)";
        System.out.print(s + " expected output -0.5 7 actually ");
        solution.recursion(s, 0);
        s = "sin(100)-sin(100)";
        System.out.print(s + " expected output 0 3 actually ");
        solution.recursion(s, 0);
        s = "-(-22+22*2)";
        System.out.print(s + "expected output -22 4 actually ");
        solution.recursion(s,0);
        s = "-2^(-2)";
        System.out.print(s + "expected output -0.25 3 actually ");
        solution.recursion(s,0);
        s = "-sin(-30)";
        System.out.print(s + " expected output 0.5 3 actually ");
        solution.recursion(s, 0);
        s = "0.00000594*0.000000000000000000000000089";
        System.out.print(s + " expected output 0 1 actually ");
        solution.recursion(s, 0);
        s = "1+4/2/2+2^2+2*2-2^(2-1+1)";
        System.out.print(s + " expected output 6 11 actually ");
        solution.recursion(s, 0);
        s = "1 . 50  00 -1* 10^ 0";
        System.out.print(s + "expected output 0.5 3 actually ");
        solution.recursion(s,0);*/
    }

    public void recursion(final String expression, int countOperation) {
        Locale.setDefault(Locale.ENGLISH);
        DecimalFormat df = new DecimalFormat("#.##");
        String value = expression.replaceAll(" ", "");
        int currentCountOperation = countOperation + 1;
        int inside = 0;
        int p1 = -1, p2 = -1, p3 = -1;
        char[] s = value.toCharArray();
        for (int i = s.length - 1; i >= 0; i--) {
            switch (s[i]) {
                case '^':
                    if (inside == 0 && p3 == -1) p3 = i;
                    break;
                case '*':
                case '/':
                    if (inside == 0 && p2 == -1) p2 = i;
                    break;
                case '+':
                case '-':
                    if (inside == 0 && p1 == -1) p1 = i;
                    break;
                case '(':
                    inside++;
                    break;
                case ')':
                    inside--;
                    break;
            }
        }
        if (p1 != -1) p2 = p1;
        if (p2 != -1) p3 = p2;
        if (p3 != -1) {
            PrintStream oldStream = System.out;
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream newStream = new PrintStream(outputStream);
            System.setOut(newStream);
            recursion(value.substring(0, p3), currentCountOperation);
            String[] part1 = outputStream.toString().split("\\s");
            currentCountOperation = currentCountOperation < Integer.parseInt(part1[1]) ? Integer.parseInt(part1[1]) : currentCountOperation;
            outputStream.reset();
            recursion(value.substring(p3 + 1), currentCountOperation);
            String[] part2 = outputStream.toString().split("\\s");
            currentCountOperation = currentCountOperation < Integer.parseInt(part2[1]) ? Integer.parseInt(part2[1]) : currentCountOperation;
            System.setOut(oldStream);
            switch (s[p3]) {
                case '^':
                    customPrint(df, Math.pow(Double.parseDouble(part1[0]), Double.parseDouble(part2[0])), countOperation, currentCountOperation);
                    return;
                case '*':
                    customPrint(df, Double.parseDouble(part1[0]) * Double.parseDouble(part2[0]), countOperation, currentCountOperation);
                    return;
                case '/':
                    customPrint(df, Double.parseDouble(part1[0]) / Double.parseDouble(part2[0]), countOperation, currentCountOperation);
                    return;
                case '+':
                    customPrint(df, Double.parseDouble(part1[0]) + Double.parseDouble(part2[0]), countOperation, currentCountOperation);
                    return;
                case '-':
                    customPrint(df, Double.parseDouble(part1[0]) - Double.parseDouble(part2[0]), countOperation, currentCountOperation);
                    return;
            }
        }
        if (s.length > 0 && s[0] == '(' && s[s.length - 1] == ')') {
            recursion(value.substring(1, s.length - 1), countOperation);
            return;
        }
        if (s.length > 5 && Character.isAlphabetic(s[0]) && s[3] == '(' && s[s.length - 1] == ')') {
            String funcName = value.substring(0, 3);
            PrintStream oldStream = System.out;
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream newStream = new PrintStream(outputStream);
            System.setOut(newStream);
            recursion(value.substring(4, s.length - 1), currentCountOperation);
            String[] part = outputStream.toString().split("\\s");
            currentCountOperation = currentCountOperation < Integer.parseInt(part[1]) ? Integer.parseInt(part[1]) : currentCountOperation;
            System.setOut(oldStream);
            if ("sin".equals(funcName)) {
                customPrint(df, Math.sin(Math.toRadians(Double.parseDouble(part[0]))), countOperation, currentCountOperation);
                return;
            }
            if ("cos".equals(funcName)) {
                customPrint(df, Math.cos(Math.toRadians(Double.parseDouble(part[0]))), countOperation, currentCountOperation);
                return;
            }
            if ("tan".equals(funcName)) {
                customPrint(df, Math.tan(Math.toRadians(Double.parseDouble(part[0]))), countOperation, currentCountOperation);
                return;
            }
        }
        double n = 0d;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length && (Character.isDigit(s[i]) || s[i] == '.'); i++) {
            sb.append(s[i]);
        }
        if (sb.length() > 0) {
            n = Double.parseDouble(sb.toString());
        }
        customPrint(df, n, countOperation, countOperation);
    }
    private void customPrint(DecimalFormat df, double v, int countOperation, int currentCountOperation) {
        if (countOperation == 0) {
            System.out.println(df.format(v) + " " + currentCountOperation);
        } else {
            System.out.println(String.valueOf(v) + " " + currentCountOperation);
        }    }

    public Solution() {
        //don't delete
    }
}
