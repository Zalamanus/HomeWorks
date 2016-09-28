package com.javarush.test.level27.lesson15.big01.ad;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MVTitov on 17.08.2016.
 */
class AdvertisementStorage {
    private static final AdvertisementStorage ourInstance = new AdvertisementStorage();
    private final List<Advertisement> videos = new ArrayList<>();

    public static AdvertisementStorage getInstance() {
        return ourInstance;
    }

    private AdvertisementStorage() {
        Object someContent = new Object();
/*
        videos.add(new Advertisement(someContent, "First Video", 5000, 100, 25 * 60)); // 3 min
        videos.add(new Advertisement(someContent, "Second Video", 100, 10, 15 * 60)); //15 min
        videos.add(new Advertisement(someContent, "Third Video", 400, 1, 10 * 60));   //10 min
*/
        videos.add(new Advertisement(someContent, "First", 5000, 100, 3 * 60));
        videos.add(new Advertisement(someContent, "Second", 100, 100, 15 * 60));
        videos.add(new Advertisement(someContent, "second", 400, 200, 10 * 60));
        videos.add(new Advertisement(someContent, "seconD", 400, 20, 20 * 60));
        videos.add(new Advertisement(someContent, "Пятый", 400, 2, 40 * 60));
        videos.add(new Advertisement(someContent, "пятый", 400, 2, 30 * 60));
        videos.add(new Advertisement(someContent, "Седьмой", 400, 200, 50 * 60));
        videos.add(new Advertisement(someContent, "Восьмой", 150, 20, 20 * 60));
        videos.add(new Advertisement(someContent, "Nineth", 7000, 100, 10 * 60));
    }

    public List<Advertisement> list() {
        return videos;
    }

    public void add(Advertisement advertisement) {
        videos.add(advertisement);
    }
}
