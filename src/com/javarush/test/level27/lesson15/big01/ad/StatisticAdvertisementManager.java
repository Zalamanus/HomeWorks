package com.javarush.test.level27.lesson15.big01.ad;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by MVTitov on 20.08.2016.
 */
public class StatisticAdvertisementManager {
    private static StatisticAdvertisementManager ourInstance = new StatisticAdvertisementManager();
    private AdvertisementStorage adStorage = AdvertisementStorage.getInstance();
    public static StatisticAdvertisementManager getInstance() {
        return ourInstance;
    }

    private StatisticAdvertisementManager() {
    }
    public Set<Advertisement> getAdList (boolean active) {
        Set<Advertisement> roliki = new TreeSet<>(new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement advertisement, Advertisement t1) {
                return advertisement.getName().compareToIgnoreCase(t1.getName());
            }
        });
        for (Advertisement ad : adStorage.list()) {
            if (active) { if (ad.getHits()>0) roliki.add(ad); }
            else if (ad.getHits()==0) roliki.add(ad);
        }
        return roliki;
    }
}
