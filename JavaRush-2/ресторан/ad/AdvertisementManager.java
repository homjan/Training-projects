package com.javarush.task.task27.task2712.ad;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.NoAvailableVideoEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.*;
import java.util.stream.Collectors;

public class AdvertisementManager {

    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private final int timeSeconds;

    private StatisticManager statisticManager = StatisticManager.getInstance();

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() throws NoVideoAvailableException {
        if (storage.list().isEmpty())
            throw new NoVideoAvailableException();

        //Filter advertisement which can hits and acceptable duration
        List<Advertisement> bestSelect = storage.list()
                .stream().filter(ad -> ad.canHits() && ad.getDuration() <= timeSeconds).collect(Collectors.toList());

        //Makes powerSet
        List<List<Advertisement>> allCombinations = powerSet(bestSelect);

        //We reject combinations that are required duration
        allCombinations = allCombinations
                .stream().filter(comb -> getTotalDuration(comb) <= timeSeconds).collect(Collectors.toList());

        //Max income
        long maxIncome = allCombinations.stream().mapToLong(this::getTotalAmount).max().getAsLong();

        //Discard combinations that are less than the maximum income
        allCombinations = allCombinations
                .stream().filter(comb -> getTotalAmount(comb) >= maxIncome).collect(Collectors.toList());

        //Max duration
        int maxDuration = allCombinations.stream().mapToInt(this::getTotalDuration).max().getAsInt();

        //Discard combinations that are less than the maximum duration
        allCombinations = allCombinations
                .stream().filter(comb -> getTotalDuration(comb) >= maxDuration).collect(Collectors.toList());

        //Choose the option with the least amount of advertising
        allCombinations.sort(Comparator.comparingInt(List::size));
        bestSelect = allCombinations.get(0);

        //sorting part 2.4
        bestSelect.sort(Comparator.comparingLong(Advertisement::getAmountPerOneDisplaying).reversed()
                .thenComparingLong(Advertisement::getAmountPerSecond));

//        System.out.println("\n>>DEBUG: cooking time " + timeSeconds + " ms, Adv_duration " +
//                + getTotalDuration(bestSelect) + " ms, amount " + getTotalAmount(bestSelect) + " cent\n");

        if(bestSelect.isEmpty()) {
            NoAvailableVideoEventDataRow noAvailableVideoEventDataRow = new NoAvailableVideoEventDataRow(timeSeconds);
            statisticManager.register(noAvailableVideoEventDataRow);
        }
        else
            statisticManager.register(
                    new VideoSelectedEventDataRow(bestSelect, getTotalAmount(bestSelect), getTotalDuration(bestSelect)));

        for (Advertisement adv : bestSelect) {
            adv.revalidate();
            ConsoleHelper.writeMessage(adv.toString());
        }

    }

    private int getTotalDuration(List<Advertisement> list) {
        return list.stream().mapToInt(Advertisement::getDuration).sum();
    }

    private long getTotalAmount(List<Advertisement> list) {
        return list.stream().mapToLong(Advertisement::getAmountPerOneDisplaying).sum();
    }

    private List<List<Advertisement>> powerSet(List<Advertisement> videos) {
        List<List<Advertisement>> result = new ArrayList<>();
        if (videos.isEmpty()) {
            result.add(new ArrayList<>());
            return result;
        }

        List<Advertisement> copy = new ArrayList<>(videos);
        Advertisement head = copy.get(0);

        List<Advertisement> rest = copy.subList(1, copy.size());
        for (List<Advertisement> set : powerSet(rest)) {
            List<Advertisement> newSet = new ArrayList<>();
            newSet.add(head);
            newSet.addAll(set);

            result.add(newSet);
            result.add(set);
        }

        return result;
    }
}