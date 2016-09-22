package com.javarush.test.level33.lesson10.bonus01;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* Комментарий внутри xml
Реализовать метод toXmlWithComment, который должен возвращать строку - xml представление объекта obj.
В строке перед каждым тэгом tagName должен быть вставлен комментарий comment.
Сериализация obj в xml может содержать CDATA с искомым тегом. Перед ним вставлять комментарий не нужно.

Пример вызова:  toXmlWithComment(firstSecondObject, "second", "it's a comment")
Пример результата:
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<first>
    <!--it's a comment-->
    <second>some string</second>
    <!--it's a comment-->
    <second>some string</second>
    <!--it's a comment-->
    <second><![CDATA[need CDATA because of < and >]]></second>
    <!--it's a comment-->
    <second/>
</first>
*/
public class Solution {
    public static String toXmlWithComment(Object obj, String tagName, String comment) {

        try
        {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            StringWriter writer = new StringWriter();

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            marshaller.marshal(obj, writer);

            String xml = writer.toString();
            String commentPut = "<!--"+comment+"-->";
            String tag = "<"+tagName+">";

            //Создаю регулярку для поиска CDATA
            Pattern pattern = Pattern.compile("(<!\\[CDATA\\[(.|\n)*?]]>)");
            Matcher matcher = pattern.matcher(xml);
            //Все найденные CDATA кладу в лист
            List<String> cdatas = new ArrayList<>();
            while (matcher.find()){
                cdatas.add(matcher.group());
            }
            //Заменяю все CDATA на другой тег
            xml = (xml.replaceAll(pattern.pattern(),"<URURU/>"));

            //ставлю комментарии в xml в котором нету CDATA и есть URURU teg
            List<String> list = new ArrayList<>(Arrays.asList(xml.split("\n")));
            List<String> copy = new ArrayList<>(list);
            for(int i =0;i<copy.size();i++){
                String s = copy.get(i);
                if(s.contains(tag)){
                    int count = spaceCount(s);
                    for(int j =0;j<count;j++){
                        commentPut = " "+commentPut;
                    }
                    list.set(i,commentPut+"\n"+s);
                    commentPut = commentPut.trim();
                }

            }
            //делаю новый xml который уже с комментами
            StringBuilder comments = new StringBuilder();
            for(String elem : list){
                comments.append(elem+"\n");
            }
            xml = comments.toString();


            //заменяю все URURU обратно на CDATA
            List<String> ururu = new ArrayList<>(Arrays.asList(xml.split("<URURU/>")));
            for(int i =0;i<cdatas.size();i++){
                String s = ururu.get(i);
                s+=cdatas.get(i);
                ururu.set(i,s);
            }

            //Делаю финальный xml
            StringBuilder result = new StringBuilder();
            for(String cdata : ururu)
                result.append(cdata);


            return result.toString().replace("standalone=\"yes\"","standalone=\"no\"");

        }
        catch (JAXBException e)
        {
            e.printStackTrace();
            return "";
        }
    }
    //штука для счета пробелов
    public static int spaceCount(String word){
        word = word.substring(0,word.indexOf("<"));
        int count = 0;
        char[] array = word.toCharArray();
        for(Character charc : array){
            if(charc == ' ')count++;
        }
        return count;
    }

    public static void main(String[] args) {
        String result = Solution.toXmlWithComment(new AnExample(), "needCDAT", "it's a comment - <needCDATA>");
        System.out.println(result);
    }
}


