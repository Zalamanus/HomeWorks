package com.javarush.test.level28.lesson15.big01.model;

import com.javarush.test.level28.lesson15.big01.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MVTitov on 29.08.2016.
 */
public class MoikrugStrategy implements Strategy {
    private static final String URL_FORMAT = "https://moikrug.ru/vacancies?q=java+%s&page=%d";
    private static final String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36";
    private static final String referrer = "https://moikrug.ru";

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> vacancyList = new ArrayList<>();
        Document doc;
        int pages = 1;
        try {
            while (true) {
                doc = getDocument(searchString,pages++);
                Elements vacancies = doc.getElementsByAttributeValueMatching("class","job\\s.*");
                if (!vacancies.isEmpty()) {
                    for (Element e : vacancies) {
                        Vacancy vacancy = new Vacancy();
                        vacancy.setTitle(e.getElementsByClass("title").text());
                        vacancy.setSalary(e.getElementsByClass("salary").text());
                        vacancy.setCity(e.getElementsByClass("location").text());
                        vacancy.setCompanyName(e.getElementsByClass("company_name").select("a").text());
                        vacancy.setSiteName(doc.title());
                        vacancy.setUrl(referrer+e.getElementsByClass("title").select("a").attr("href"));
                        vacancyList.add(vacancy);
                    }
                } else break;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return vacancyList;
    }
    protected Document getDocument(String searchString, int page) throws IOException {
        Document doc = Jsoup.connect(String.format(URL_FORMAT,searchString,page)).userAgent(userAgent).referrer(referrer).get();
//        Document doc = Jsoup.connect("http://javarush.ru/testdata/big28data2.html").userAgent(userAgent).referrer(referrer).get();

        return doc;
    }
}
