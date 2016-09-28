package com.javarush.test.level28.lesson15.big01.view;

import com.javarush.test.level28.lesson15.big01.Controller;
import com.javarush.test.level28.lesson15.big01.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.*;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by MVTitov on 26.08.2016.
 */
public class HtmlView implements View {
    private Controller controller;
    private final String filePath = "./src/" + this.getClass().getPackage().getName().replaceAll("\\.", "/") + "/vacancies.html";

    @Override
    public void update(List<Vacancy> vacancies) {
        try {
            updateFile(getUpdatedFileContent(vacancies));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod() {
        try {
            controller.onCitySelect(URLEncoder.encode("Новосибирск", "UTF-8"));
        } catch (UnsupportedEncodingException e) {
        }
    }

    private String getUpdatedFileContent(List<Vacancy> vacancyList) {
        Document doc = null;
        try {
            doc = getDocument();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Some exception occurred");
        }
        Element elementTemplate = doc.getElementsByAttributeValue("class", "vacancy template").first().clone();
        elementTemplate.removeAttr("style");
        elementTemplate.removeClass("template");
        doc.select("tr[class=vacancy]").remove();
        for (Vacancy vacancy : vacancyList) {
            Element newElement = elementTemplate.clone();
            newElement.getElementsByClass("city").html(vacancy.getCity());
            newElement.getElementsByClass("companyName").html(vacancy.getCompanyName());
            newElement.getElementsByClass("salary").html(vacancy.getSalary());
            newElement.getElementsByClass("salary").html(vacancy.getSalary());
            newElement.getElementsByTag("a").html(vacancy.getTitle()).attr("href",vacancy.getUrl());
            doc.select("tr[class=vacancy template]").first().before(newElement.outerHtml());
        }

        return doc.html().toString();
    }

    private void updateFile(String file) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))) {
            bufferedWriter.write(file);
        } catch (IOException e) {}
    }
    protected Document getDocument() throws IOException {
        return Jsoup.parse(new File(filePath), "UTF-8", "");
    }
}
