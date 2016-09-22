package com.javarush.test.level33.lesson10.home01;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MVTitov on 13.09.2016.
 */

@XmlType(name = "shop")
@XmlRootElement
public class Shop {
    @XmlElementWrapper(name="goods", nillable = true)
    public List<String> names = new ArrayList<>();
    public int count;
    public double profit;
    public List<String> secretData = new ArrayList<>();

    @Override
    public String toString() {
        return names+"\n"
                +count+"\n"
                +profit+"\n"+
                secretData;
    }
}
