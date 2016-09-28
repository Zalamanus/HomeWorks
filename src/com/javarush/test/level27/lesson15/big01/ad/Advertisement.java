package com.javarush.test.level27.lesson15.big01.ad;

/**
 * Created by MVTitov on 17.08.2016.
 */
public class Advertisement implements Comparable<Advertisement> {
    private Object content;
    private String name;
    private long initialAmount;
    private int hits;
    private int duration;
    private long amountPerOneDisplaying;

    public Advertisement(Object content, String name, long initialAmount, int hits, int duration) {
        this.content = content;
        this.name = name;
        this.initialAmount = initialAmount;
        this.hits = hits;
        this.duration = duration;
        if (hits > 0) this.amountPerOneDisplaying = initialAmount / hits;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public long getAmountPerOneDisplaying() {
        return amountPerOneDisplaying;
    }

    public int getHits() {
        return hits;
    }

    public void revalidate() {
        if (hits <= 0) throw new UnsupportedOperationException();
        hits--;
        if (hits == 1) { amountPerOneDisplaying += initialAmount % amountPerOneDisplaying; }
    }

    @Override
    public String toString() {
        return name + " is displaying... " + amountPerOneDisplaying + ", " + (int) (amountPerOneDisplaying / (double) duration * 1000);
    }

    @Override
    public int compareTo(Advertisement advertisement) {
        int diff = Long.compare(advertisement.getAmountPerOneDisplaying(),amountPerOneDisplaying);
        if (diff == 0)
            diff = Double.compare((amountPerOneDisplaying / (double) duration * 1000),(advertisement.getAmountPerOneDisplaying() / (double) advertisement.getDuration() * 1000));
        return diff;
    }
}
