package com.javarush.test.level22.lesson05.task01;


/* Найти подстроку
Метод getPartOfString должен возвращать подстроку начиная с символа после 1-го пробела и до конца слова,
которое следует после 4-го пробела.
Пример: "JavaRush - лучший сервис обучения Java."
Результат: "- лучший сервис обучения"
На некорректные данные бросить исключение TooShortStringException (сделать исключением).
Сигнатуру метода getPartOfString не менять.
Метод main не участвует в тестировании.
*/
public class Solution {
    public static void main(String[] args) throws TooShortStringException {
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java sfdsf lk;l"));
    }
    public static String getPartOfString(String string) throws TooShortStringException {
        try {
            if (string==null) throw new TooShortStringException();
            int start = string.indexOf(' ');
            int finish = start;
            for (int i = 0; i < 4; i++) {
                finish = string.indexOf(' ',finish+1);
            }
            if (finish == start || finish == -1)
                throw new TooShortStringException();


            return string.substring(start+1,finish);
        } catch (Exception e) {
            throw new TooShortStringException();
        }
    }

    public static class TooShortStringException extends Exception {
    }
}
