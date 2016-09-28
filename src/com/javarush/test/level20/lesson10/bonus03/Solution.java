package com.javarush.test.level20.lesson10.bonus03;

import java.util.ArrayList;
import java.util.List;

/* Кроссворд
1. Дан двумерный массив, который содержит буквы английского алфавита в нижнем регистре.
2. Метод detectAllWords должен найти все слова из words в массиве crossword.
3. Элемент(startX, startY) должен соответствовать первой букве слова, элемент(endX, endY) - последней.
text - это само слово, располагается между начальным и конечным элементами
4. Все слова есть в массиве.
5. Слова могут быть расположены горизонтально, вертикально и по диагонали как в нормальном, так и в обратном порядке.
6. Метод main не участвует в тестировании
*/
public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };
        List<Word> list = detectAllWords(crossword, "gsf");
        for (Word word : list) {
            System.out.println(word);
        }
        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        List<Word> list = new ArrayList<>();
        for (String word : words) {
            int startX=-1,startY=-1,finishX=-1,finishY=-1;
            char c = word.charAt(0);
            boolean found =false;
            outer:
            for (int j = 0; j < crossword.length; j++) {
                for (int k = 0; k < crossword[0].length; k++) {
                    if (crossword[j][k] == c) {
                        startX = k;
                        startY = j;
                        finishX = k;
                        finishY = j;
                        for (int i = 1; i < word.length(); i++) {
                            found = false;
                            char ch = word.charAt(i);
                            try {
                                if (ch!=crossword[startY+i][startX]) {
                                    break;
                                }
                            } catch (ArrayIndexOutOfBoundsException e) {
                                break;
                            }
                            if (i == word.length()-1) {
                                found = true;
                                finishY = startY+i;
                            }

                        }
                        if (!found) for (int i = 1; i < word.length(); i++) {
                            found = false;
                            char ch = word.charAt(i);
                            try {
                                if (ch!=crossword[startY][startX+i]) {
                                    break;
                                }
                            } catch (ArrayIndexOutOfBoundsException e) {
                                break;
                            }
                            if (i == word.length()-1) {
                                found = true;
                                finishX = startX+i;
                            }
                        }
                        if (!found) for (int i = 1; i < word.length(); i++) {
                            found = false;
                            char ch = word.charAt(i);
                            try {
                                if (ch!=crossword[startY+i][startX+i]) {
                                    break;
                                }
                            } catch (ArrayIndexOutOfBoundsException e) {
                                break;
                            }
                            if (i == word.length()-1) {
                                found = true;
                                finishX = startX+i;
                                finishY = startY+i;
                            }
                        }
                        if (!found) for (int i = 1; i < word.length(); i++) {
                            found = false;
                            char ch = word.charAt(i);
                            try {
                                if (ch!=crossword[startY-i][startX+i]) {
                                    break;
                                }
                            } catch (ArrayIndexOutOfBoundsException e) {
                                break;
                            }
                            if (i == word.length()-1) {
                                found = true;
                                finishX = startX+i;
                                finishY = startY-i;
                            }
                        }
                        if (!found) for (int i = 1; i < word.length(); i++) {
                            found = false;
                            char ch = word.charAt(i);
                            try {
                                if (ch!=crossword[startY+i][startX-i]) {
                                    break;
                                }
                            } catch (ArrayIndexOutOfBoundsException e) {
                                break;
                            }
                            if (i == word.length()-1) {
                                found = true;
                                finishX = startX-i;
                                finishY = startY+i;
                            }
                        }
                        if (!found) for (int i = 1; i < word.length(); i++) {
                            found = false;
                            char ch = word.charAt(i);
                            try {
                                if (ch!=crossword[startY-i][startX]) {
                                    break;
                                }
                            } catch (ArrayIndexOutOfBoundsException e) {
                                break;
                            }
                            if (i == word.length()-1) {
                                found = true;
                                finishY = startY-i;
                            }
                        }
                        if (!found) for (int i = 1; i < word.length(); i++) {
                            found = false;
                            char ch = word.charAt(i);
                            try {
                                if (ch!=crossword[startY][startX-i]) {
                                    break;
                                }
                            } catch (ArrayIndexOutOfBoundsException e) {
                                break;
                            }
                            if (i == word.length()-1) {
                                found = true;
                                finishX = startX-i;
                            }
                        }
                        if (!found) for (int i = 1; i < word.length(); i++) {
                            found = false;
                            char ch = word.charAt(i);
                            try {
                                if (ch!=crossword[startY-i][startX-i]) {
                                    break;
                                }
                            } catch (ArrayIndexOutOfBoundsException e) {
                                break;
                            }
                            if (i == word.length()-1) {
                                found = true;
                                finishX = startX-i;
                                finishY = startY-i;
                            }
                        }
                        if (found) break outer;

                    }

                }
            }
            Word w = new Word(word);
            w.setStartPoint(startX,startY);
            w.setEndPoint(finishX,finishY);
            list.add(w);
        }
        return list;
    }

    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}
