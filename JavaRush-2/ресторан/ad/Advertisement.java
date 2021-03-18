package com.javarush.task.task27.task2712.ad;


public class Advertisement {

    private Object content;
    private String name;
    private long initialAmount;
    private long amountPerOneDisplaying;
    private int hits;
    private int duration;

    public Advertisement(Object content, String name, long initialAmount, int hits, int duration) {
        this.content = content;
        this.name = name;
        this.initialAmount = initialAmount;
        this.hits = hits;
        this.duration = duration;

        amountPerOneDisplaying = hits > 0 ? initialAmount / hits : 0;
    }

    public String getName() {
        return name;
    }

    public int getHits() {
        return hits;
    }

    public boolean canHits() {
        return hits > 0;
    }

    public long getAmountPerOneDisplaying() {
        return amountPerOneDisplaying;
    }

    public long getAmountPerSecond() {
        return (long) ( ( (double)amountPerOneDisplaying / duration) * 1000);
    }

    public int getDuration() {
        return duration;
    }

    public void revalidate() throws NoVideoAvailableException {
        if(hits <= 0)
            throw new NoVideoAvailableException();

        hits--;
    }

    @Override
    public String toString() {
        return name + " is displaying... " + getAmountPerOneDisplaying() + ", "
                + getAmountPerSecond();
    }
}
