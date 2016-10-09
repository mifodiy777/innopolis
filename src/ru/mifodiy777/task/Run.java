package ru.mifodiy777.task;

import ru.mifodiy777.task.entity.Data;
import ru.mifodiy777.task.service.DataService;
import ru.mifodiy777.task.service.impl.DataServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by innopolis on 06.10.16.
 */
public class Run {

    Cache cache;

    public static void main(String[] args) {
        Run run = new Run();
        DataService dataService = new DataServiceImpl();
        dataService.setConfig();
        int minutes = 5;
        int timeLive = 1000 * 60 * minutes;
        try {
            run.cache = new Cache(timeLive);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Thread thread = new Thread() {
            @Override
            public void run() {
                dataService.chaching(run.cache,dataService.readingObj());
            }
        };
        thread.start();
        dataService.writeObj(dataService.getChachingObj(run.cache));

    }
}
