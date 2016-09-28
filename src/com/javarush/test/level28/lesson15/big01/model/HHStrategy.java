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
 * Created by MVTitov on 23.08.2016.
 */
public class HHStrategy implements Strategy {
    private static final String URL_FORMAT = "http://hh.ru/search/vacancy?text=java+%s&page=%d";
    private static final String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36";
    private static final String referrer = "http://hh.ru/";


    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> vacancyList = new ArrayList<>();
        Document doc;
        int pages=0;
        try {
            while (true) {
                doc = getDocument(searchString,pages++);
                Elements vacancies = doc.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy vacancy-serp__vacancy_premium");
                vacancies.addAll(doc.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy"));
                if (!vacancies.isEmpty()) {
                    for (Element e : vacancies) {
                        Vacancy vacancy = new Vacancy();
                        vacancy.setTitle(e.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title").text());
                        vacancy.setSalary(e.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-compensation").text());
                        vacancy.setCity(e.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-address").text());
                        vacancy.setCompanyName(e.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-employer").text());
                        vacancy.setSiteName(doc.title());
                        vacancy.setUrl(e.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title").attr("href"));
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
        //Document doc = Jsoup.connect("http://javarush.ru/testdata/big28data.html").userAgent(userAgent).referrer(referrer).get();
        return doc;
    }
}
