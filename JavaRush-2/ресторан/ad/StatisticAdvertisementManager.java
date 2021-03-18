package com.javarush.task.task27.task2712.ad;

import java.util.List;
import java.util.stream.Collectors;

public class StatisticAdvertisementManager {

    private static StatisticAdvertisementManager instance = new StatisticAdvertisementManager();
    private AdvertisementStorage storage = AdvertisementStorage.getInstance();

    private StatisticAdvertisementManager() {}

    public static StatisticAdvertisementManager getInstance() {
        StatisticAdvertisementManager local = instance;
        if(local == null)
            synchronized (StatisticAdvertisementManager.class) {
                local = instance;
                if(local == null)
                    instance = local = new StatisticAdvertisementManager();
            }

        return local;
    }

    public List<Advertisement> getActiveVideo() {
        return storage.list().stream().filter(Advertisement::canHits).collect(Collectors.toList());
    }

    public List<Advertisement> getInActiveVideo() {
        return storage.list().stream().filter(adv -> !adv.canHits()).collect(Collectors.toList());
    }
}
