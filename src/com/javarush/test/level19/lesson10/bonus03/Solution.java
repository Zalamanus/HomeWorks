package com.javarush.test.level19.lesson10.bonus03;

/* Знакомство с тегами
Считайте с консоли имя файла, который имеет HTML-формат
Пример:
Info about Leela <span xml:lang="en" lang="en"><b><span>Turanga Leela
</span></b></span><span>Super</span><span>girl</span>
Первым параметром в метод main приходит тег. Например, "span"
Вывести на консоль все теги, которые соответствуют заданному тегу
Каждый тег на новой строке, порядок должен соответствовать порядку следования в файле
Количество пробелов, \n, \r не влияют на результат
Файл не содержит тег CDATA, для всех открывающих тегов имеется отдельный закрывающий тег, одиночных тегов нету
Тег может содержать вложенные теги
Пример вывода:
<span xml:lang="en" lang="en"><b><span>Turanga Leela</span></b></span>
<span>Turanga Leela</span>
<span>Super</span>
<span>girl</span>

Шаблон тега:
<tag>text1</tag>
<tag text2>text1</tag>
<tag
text2>text1</tag>

text1, text2 могут быть пустыми
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    private static ArrayList<Integer> openTags = new ArrayList<>();
    private static ArrayList<Integer> closeTags = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        String s = args[0];
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        TreeMap<Integer, Integer> map = new TreeMap<>();
        BufferedReader f = new BufferedReader(new FileReader(reader.readLine()));
        reader.close();
        StringBuilder sb = new StringBuilder();
        while (f.ready()) {
            sb.append(f.readLine());
        }
        f.close();
        Pattern popen = Pattern.compile("<"+s+"\\b");
        Pattern pclose = Pattern.compile("</"+s+">");
        s = sb.toString();
        Matcher start = popen.matcher(s);
        Matcher finish = pclose.matcher(s);
        while (start.find()) {

            openTags.add(start.start());
        }
        while (finish.find()) {
            closeTags.add(finish.end());
        }
        for (Integer i : closeTags){
            int index = 0;
            int openIndex = 0;
            int closeIndex = 0;
            while (i > openTags.get(index)){
                openIndex = openTags.get(index);
                closeIndex = i;
                index++;
                if (index >= openTags.size()) break;
            }
            openTags.remove(index-1);
            map.put(openIndex, closeIndex);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println(s.substring(entry.getKey(),entry.getValue()));
        }

    }
}
