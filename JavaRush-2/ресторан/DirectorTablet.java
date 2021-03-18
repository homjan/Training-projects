package com.javarush.task.task27.task2712;


import com.javarush.task.task27.task2712.ad.Advertisement;
import com.javarush.task.task27.task2712.ad.StatisticAdvertisementManager;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DirectorTablet {

    private StatisticManager statisticManager = StatisticManager.getInstance();
    private StatisticAdvertisementManager statisticAdvManager = StatisticAdvertisementManager.getInstance();

    public void printAdvertisementProfit() {
        Map<LocalDate, Long> data = statisticManager.getVideos();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyy", Locale.ENGLISH);

        Long totalAmount = 0L;

        for (Map.Entry<LocalDate, Long> entry : data.entrySet())
            if (entry.getValue() > 0) {
                LocalDate date = entry.getKey();
                ConsoleHelper.writeMessage(String.format("%s - %d.%d",
                        date.format(formatter), entry.getValue() / 100, entry.getValue() % 100));

                totalAmount += entry.getValue();
            }

        ConsoleHelper.writeMessage(String.format("Total - %d.%d", totalAmount / 100, totalAmount % 100));
    }

    public void printCookWorkloading() {
        Map<LocalDate, Map<String, Integer>> data = statisticManager.getCookWorkloading();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyy", Locale.ENGLISH);

        for (Map.Entry<LocalDate, Map<String, Integer>> mapEntry : data.entrySet()) {
            LocalDate date = mapEntry.getKey();
            ConsoleHelper.writeMessage(date.format(formatter));
            for (Map.Entry<String, Integer> entry : mapEntry.getValue().entrySet())
                ConsoleHelper.writeMessage(entry.getKey() + " - " + entry.getValue() + " min");

            ConsoleHelper.writeMessage("");
        }
    }

    public void printActiveVideoSet() {
        List<Advertisement> ads = statisticAdvManager.getActiveVideo();
        ads.sort(Comparator.comparing(ad -> ad.getName().toLowerCase()));

        for (Advertisement ad : ads)
            ConsoleHelper.writeMessage(ad.getName() + " - " + ad.getHits());
    }

    public void printArchivedVideoSet() {
        List<Advertisement> ads = statisticAdvManager.getInActiveVideo();
        ads.sort(Comparator.comparing(ad -> ad.getName().toLowerCase()));

        for (Advertisement ad : ads)
            ConsoleHelper.writeMessage(ad.getName());
    }
}
