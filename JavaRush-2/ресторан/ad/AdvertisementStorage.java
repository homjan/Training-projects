package com.javarush.task.task27.task2712.ad;

import java.util.ArrayList;
import java.util.List;

public class AdvertisementStorage {

    private static AdvertisementStorage instance;
    private final List<Advertisement> videos = new ArrayList<>();


    private AdvertisementStorage(){
        Object someContent = new Object();
        videos.add(new Advertisement(someContent, "First Video", 5000, 100, 3 * 60)); // 3 min
        videos.add(new Advertisement(someContent, "Second Video", 100, 10, 15 * 60)); //15 min
        videos.add(new Advertisement(someContent, "Third Video", 400, 2, 10 * 60)); //10 min
        videos.add(new Advertisement(someContent, "Четвертое видео", 150, 2, 1 * 60));//1 min
        videos.add(new Advertisement(someContent, "Fifth Video", 350, 1, 2 * 60));//2 min
        videos.add(new Advertisement(someContent, "Sixth Video", 400, 2, 10 * 60));//10 min
        videos.add(new Advertisement(someContent, "Седьмое видео", 100, 2, 5 * 60));//5 min
        videos.add(new Advertisement(someContent, "Eighth Video", 300, 1, 7 * 60));//7 min
        videos.add(new Advertisement(someContent, "Ninth Video", 3000, 2, 20 * 60));//20 min
        videos.add(new Advertisement(someContent, "Tenth Video", 900, 2, 15 * 60));//15 min
        videos.add(new Advertisement(someContent, "Eleventh Video", 350, 5, 3 * 60));//3 min
        videos.add(new Advertisement(someContent, "Двенадцатое видео", 350, 5, 3 * 60));//3 min
    }

    public static AdvertisementStorage getInstance() {
        AdvertisementStorage local = instance;
        if(local == null) {
            synchronized (AdvertisementStorage.class) {
                local = instance;
                if(local == null)
                    instance = local = new AdvertisementStorage();
            }
        }
        return local;
    }

    public List<Advertisement> list() {
        return videos;
    }

    public void add(Advertisement advertisement) {
        videos.add(advertisement);
    }
}
