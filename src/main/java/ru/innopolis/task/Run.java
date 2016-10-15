package ru.innopolis.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.innopolis.task.DAO.impl.DataDAOFileImpl;
import ru.innopolis.task.entity.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * Created by innopolis on 06.10.16.
 */
public class Run {

    private static Logger logger = LoggerFactory.getLogger(Run.class);

    private volatile Map<Integer, Data> globalMap = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        //Проверка на ресурсы
        if (args.length == 0) {
            System.out.println("Нет ресурсов для загрузки в кеш");
            logger.warn("is't resources for download cache");
            return;
        }
        Run run = new Run();
        ExecutorService exec = Executors.newFixedThreadPool(args.length);
        for (String src : args) {
            //Запуск потока под каждый ресурс
            exec.execute(new ResourceThread(run.globalMap, src));
        }
        exec.shutdown();
        while (true) {
            if (exec.isTerminated()) {
                if (run.globalMap.isEmpty()) {
                    System.out.println("Кеш пуст");
                    logger.info("cache is empty");
                } else {
                    System.out.println("Данные успешно загружены в кеш");
                    logger.info("download cache complete");
                    new DataDAOFileImpl().write(run.globalMap);
                }
                break;
            }
        }
    }

}

