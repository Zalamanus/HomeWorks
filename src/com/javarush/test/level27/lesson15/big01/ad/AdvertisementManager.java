package com.javarush.test.level27.lesson15.big01.ad;


import com.javarush.test.level27.lesson15.big01.ConsoleHelper;
import com.javarush.test.level27.lesson15.big01.statistic.StatisticEventManager;
import com.javarush.test.level27.lesson15.big01.statistic.event.VideoSelectedEventDataRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by MVTitov on 17.08.2016.
 */
public class AdvertisementManager {
    private final AdvertisementStorage storage;
    private int timeSeconds;
    private int numVideo = 0;
    private int saveTime;
    private List<Advertisement> selectedAdv = new ArrayList<>();

    public AdvertisementManager(int timeSeconds) {
        storage = AdvertisementStorage.getInstance();
        this.timeSeconds = timeSeconds;
        saveTime = timeSeconds;
    }

    public List<Advertisement> selectAdv() throws NoVideoAvailableException{
        List<Advertisement> temp = new ArrayList<>();
        timeSeconds = saveTime;
        for (int i = numVideo; i < storage.list().size(); i++){
            if (!temp.contains(storage.list().get(i)) && storage.list().get(i).getHits() > 0 && storage.list().get(i).getDuration() <= timeSeconds){
                temp.add(storage.list().get(i));
                timeSeconds -= storage.list().get(i).getDuration();
            } else continue;
            for (int j = 0; j < storage.list().size(); j++){
                if (storage.list().get(i).getHits() > 0 && i != j && !temp.contains(storage.list().get(i)) && storage.list().get(j).getDuration() <= timeSeconds){
                    temp.add(storage.list().get(i));
                    timeSeconds -= storage.list().get(i).getDuration();
                }
            }
        }
        if (selectedAdv.isEmpty() && storage.list().isEmpty())
        {
            throw new NoVideoAvailableException();
        }
        if (selectedAdv.isEmpty() && temp.isEmpty()){
            throw new NoVideoAvailableException();
        }else if (selectedAdv.isEmpty() && !temp.isEmpty()){
            selectedAdv = new ArrayList<>(temp);
            numVideo++;
            return selectAdv();
        } else {
            int sumSelected = 0, durSelected = 0, sumTemp = 0, durTemp = 0;
            for (Advertisement adv : selectedAdv){
                sumSelected += adv.getAmountPerOneDisplaying();
                durSelected += adv.getDuration();
            }
            for (Advertisement adv : temp){
                sumTemp += adv.getAmountPerOneDisplaying();
                durTemp += adv.getDuration();
            }
            if (sumTemp > sumSelected) {
                selectedAdv = new ArrayList<>(temp);
            } else if (sumTemp == sumSelected) {
                if (durTemp > durSelected) {
                    selectedAdv = new ArrayList<>(temp);
                } else if (durTemp == durSelected){
                    if (temp.size() < selectedAdv.size()){
                        selectedAdv = new ArrayList<>(temp);
                    }
                }
            }
        }
        if (numVideo >= storage.list().size() - 1) return selectedAdv;
        else
        {
            numVideo++;
            return selectAdv();
        }
    }
    public void processVideos() throws NoVideoAvailableException{
        selectedAdv = selectAdv();
        if (selectedAdv.isEmpty()) throw new NoVideoAvailableException();
        Collections.sort(selectedAdv, new Comparator<Advertisement>()
        {
            @Override
            public int compare(Advertisement o1, Advertisement o2)
            {
                int result = (int)(o2.getAmountPerOneDisplaying() - o1.getAmountPerOneDisplaying());
                if (result == 0) return  (int)(o1.getAmountPerOneDisplaying() * 1000/o1.getDuration() - o2.getAmountPerOneDisplaying() * 1000/o2.getDuration());
                else return result;
            }
        });
        long sumSelected = 0;
        int durSelected = 0;
        for (Advertisement adv : selectedAdv){
            sumSelected += adv.getAmountPerOneDisplaying();
            durSelected += adv.getDuration();
        }
        StatisticEventManager.getInstance().register(new VideoSelectedEventDataRow(selectedAdv,sumSelected,durSelected));
        for (Advertisement adv : selectedAdv){
            ConsoleHelper.writeMessage(
                    String.format(
                            "%s is displaying... %d, %d",
                            adv.getName(),
                            adv.getAmountPerOneDisplaying(), adv.getAmountPerOneDisplaying() * 1000/adv.getDuration()
                    )
            );
            adv.revalidate();
        }
    }
}
