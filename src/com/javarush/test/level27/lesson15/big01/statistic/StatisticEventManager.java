package com.javarush.test.level27.lesson15.big01.statistic;

import com.javarush.test.level27.lesson15.big01.kitchen.Cook;
import com.javarush.test.level27.lesson15.big01.statistic.event.CookedOrderEventDataRow;
import com.javarush.test.level27.lesson15.big01.statistic.event.EventDataRow;
import com.javarush.test.level27.lesson15.big01.statistic.event.EventType;
import com.javarush.test.level27.lesson15.big01.statistic.event.VideoSelectedEventDataRow;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by MVTitov on 18.08.2016.
 */
public class StatisticEventManager {
    private static StatisticEventManager ourInstance = new StatisticEventManager();
    private StatisticStorage statStorage = new StatisticStorage();

    public static StatisticEventManager getInstance() {
        return ourInstance;
    }

    private StatisticEventManager() {

    }


    public void register(EventDataRow data) {
        statStorage.put(data);
    }
    public Map<String,Long> getProfitByDay() {
        Map<String, Long> map = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String s, String t1) {
                String[] s1 = s.split("-");
                String[] s2 = t1.split("-");
                int res =0;
                switch (s1[1]) {
                    case "Jan": s1[1] = "1"; break;
                    case "Feb": s1[1] = "2"; break;
                    case "Mar": s1[1] = "3"; break;
                    case "Apr": s1[1] = "4"; break;
                    case "May": s1[1] = "5"; break;
                    case "Jun": s1[1] = "6"; break;
                    case "Jul": s1[1] = "7"; break;
                    case "Aug": s1[1] = "8"; break;
                    case "Sep": s1[1] = "9"; break;
                    case "Oct": s1[1] = "10"; break;
                    case "Nov": s1[1] = "11"; break;
                    case "Dec": s1[1] = "12"; break;
                }
                switch (s2[1]) {
                    case "Jan": s2[1] = "1"; break;
                    case "Feb": s2[1] = "2"; break;
                    case "Mar": s2[1] = "3"; break;
                    case "Apr": s2[1] = "4"; break;
                    case "May": s2[1] = "5"; break;
                    case "Jun": s2[1] = "6"; break;
                    case "Jul": s2[1] = "7"; break;
                    case "Aug": s2[1] = "8"; break;
                    case "Sep": s2[1] = "9"; break;
                    case "Oct": s2[1] = "10"; break;
                    case "Nov": s2[1] = "11"; break;
                    case "Dec": s2[1] = "12"; break;
                }
                res = Integer.valueOf(s2[2]) - Integer.valueOf(s1[2]);
                if (res == 0 ) {
                    res = Integer.valueOf(s2[1]) - Integer.valueOf(s1[1]);
                }
                if (res==0) {
                    res = Integer.valueOf(s2[0]) - Integer.valueOf(s1[0]);
                }

                return res;
            }
        });
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        for (EventDataRow eventDataRow : statStorage.get(EventType.SELECTED_VIDEOS)) {
            VideoSelectedEventDataRow event = (VideoSelectedEventDataRow) eventDataRow;
            String date = dateFormat.format(event.getDate());
            if (map.containsKey(date)) map.put(date,event.getAmount()+map.get(date));
            else map.put(date,event.getAmount());
        }
        return map;
    }
    public Map<String,Map<String,Integer>> getCookTimeByDay () {
        Map<String,Map<String,Integer>> map = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String s, String t1) {
                String[] s1 = s.split("-");
                String[] s2 = t1.split("-");
                int res =0;
                switch (s1[1]) {
                    case "Jan": s1[1] = "1"; break;
                    case "Feb": s1[1] = "2"; break;
                    case "Mar": s1[1] = "3"; break;
                    case "Apr": s1[1] = "4"; break;
                    case "May": s1[1] = "5"; break;
                    case "Jun": s1[1] = "6"; break;
                    case "Jul": s1[1] = "7"; break;
                    case "Aug": s1[1] = "8"; break;
                    case "Sep": s1[1] = "9"; break;
                    case "Oct": s1[1] = "10"; break;
                    case "Nov": s1[1] = "11"; break;
                    case "Dec": s1[1] = "12"; break;
                }
                switch (s2[1]) {
                    case "Jan": s2[1] = "1"; break;
                    case "Feb": s2[1] = "2"; break;
                    case "Mar": s2[1] = "3"; break;
                    case "Apr": s2[1] = "4"; break;
                    case "May": s2[1] = "5"; break;
                    case "Jun": s2[1] = "6"; break;
                    case "Jul": s2[1] = "7"; break;
                    case "Aug": s2[1] = "8"; break;
                    case "Sep": s2[1] = "9"; break;
                    case "Oct": s2[1] = "10"; break;
                    case "Nov": s2[1] = "11"; break;
                    case "Dec": s2[1] = "12"; break;
                }
                res = Integer.valueOf(s2[2]) - Integer.valueOf(s1[2]);
                if (res == 0 ) {
                    res = Integer.valueOf(s2[1]) - Integer.valueOf(s1[1]);
                }
                if (res==0) {
                    res = Integer.valueOf(s2[0]) - Integer.valueOf(s1[0]);
                }

                return res;
            }
        });
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        for (EventDataRow eventDataRow : statStorage.get(EventType.COOKED_ORDER)) {
            CookedOrderEventDataRow event = (CookedOrderEventDataRow) eventDataRow;
            String date = dateFormat.format(event.getDate());
            if (map.containsKey(date)) {
                if (map.get(date).containsKey(event.getCookName())) {
                    map.get(date).put(event.getCookName(),(map.get(date).get(event.getCookName()))+event.getTime());
                } else {
                    map.get(date).put(event.getCookName(),event.getTime());
                }
            } else {
                map.put(date,new TreeMap<String, Integer>());
                map.get(date).put(event.getCookName(),event.getTime());
            }
        }
        return map;
    }

    private static class StatisticStorage {
        private Map<EventType, List<EventDataRow>> statMap = new HashMap<>();

        public StatisticStorage() {
            for (EventType eventType : EventType.values()) {
                statMap.put(eventType,new ArrayList<EventDataRow>());
            }
        }
        private void put(EventDataRow data) {
            statMap.get(data.getType()).add(data);
        }
        private List<EventDataRow> get(EventType type) {
            return statMap.get(type);
        }
    }

}
