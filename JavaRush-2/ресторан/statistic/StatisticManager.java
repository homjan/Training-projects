package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.*;

public class StatisticManager {
    private static StatisticManager instance = new StatisticManager();

    private StatisticStorage statisticStorage = new StatisticStorage();



    public static StatisticManager getInstance() {
        StatisticManager local = instance;
        if(local == null)
            synchronized (StatisticManager.class) {
                local = instance;
                if(local == null)
                    instance = local = new StatisticManager();
            }

        return local;
    }

    private StatisticManager() {}


    public void register(EventDataRow data) {
        statisticStorage.put(data);
    }



    public Map<LocalDate, Long> getVideos() {
        List<VideoSelectedEventDataRow> videos = new ArrayList(statisticStorage.getSelectedVideo());
        Map<LocalDate, Long> dailyProfit = new TreeMap<>(Collections.reverseOrder());


        for (VideoSelectedEventDataRow video : videos) {
            LocalDate date = video.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            if(!dailyProfit.containsKey(date))
                dailyProfit.put(date, video.getAmount());
            else
                dailyProfit.computeIfPresent(date, (key, value) -> value + video.getAmount());
        }

        return dailyProfit;
    }

    public Map<LocalDate, Map<String, Integer>> getCookWorkloading() {
        List<CookedOrderEventDataRow> cookEvent = new ArrayList(statisticStorage.getCookedOrder());
        Map<LocalDate, Map<String, Integer>> cookData = new TreeMap<>(Collections.reverseOrder());

        for (CookedOrderEventDataRow event : cookEvent) {
            LocalDate localDate = event.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            Cook cook = new Cook(event.getCookName());
         //
            int workTime = (int) Math.ceil((double) event.getTime() / 60);

            if(!cookData.containsKey(localDate)) {
                Map<String, Integer> work = new TreeMap<>();
                work.put(cook.toString(), workTime);
                cookData.put(localDate, work);
            }
            else {
                Map<String, Integer> work = cookData.get(localDate);
                work.merge(cook.toString(), workTime, Integer::sum);
            }
        }
        return cookData;
    }

    private class StatisticStorage {
        private Map<EventType, List<EventDataRow>> storage = new HashMap<>();

        public StatisticStorage() {
            for (EventType type : EventType.values())
                storage.put(type, new ArrayList<>());
        }

        private void put(EventDataRow data) {
            storage.get(data.getType()).add(data);
        }

        private List<EventDataRow> getSelectedVideo() {
            return  storage.get(EventType.SELECTED_VIDEOS);
        }

        private List<EventDataRow> getCookedOrder() {
            return storage.get(EventType.COOKED_ORDER);
        }
    }
}
