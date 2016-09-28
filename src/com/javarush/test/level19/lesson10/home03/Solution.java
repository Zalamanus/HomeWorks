package com.javarush.test.level19.lesson10.home03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* Хуан Хуанович
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя день месяц год
где [имя] - может состоять из нескольких слов, разделенных пробелами, и имеет тип String
[день] - int, [месяц] - int, [год] - int
данные разделены пробелами

Заполнить список PEOPLE импользуя данные из файла
Закрыть потоки. Не использовать try-with-resources

Пример входного файла:
Иванов Иван Иванович 31 12 1987
Вася 15 5 2013
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) throws ParseException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        Pattern p = Pattern.compile("^(.*)\\s(\\d+\\s\\d+\\s\\d+)");
        SimpleDateFormat date = new SimpleDateFormat("d MM y");
        Matcher m;
        while (reader.ready()) {
            m = p.matcher(reader.readLine());
            m.find();
            PEOPLE.add(new Person(m.group(1), date.parse(m.group(2))));

        }
        reader.close();
    }

}
