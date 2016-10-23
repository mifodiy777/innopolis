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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * Created by innopolis on 06.10.16.
 */
public class Run {

    private static Logger logger = LoggerFactory.getLogger(Run.class);

    private static volatile Map<Integer, Data> globalMap = new ConcurrentHashMap<>();

    private static ExecutorService exec = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        //Проверка на ресурсы
        if (args.length == 0) {
            System.out.println("Нет ресурсов для загрузки в кеш");
            logger.warn("is't resources for download cache");
            return;
        }
        try {
            read(args);
        } catch (IOException e) {
            return;
        }
        exec.shutdown();
        write();
    }

    private static void read(String[] args) throws IOException {

        for (String src : args) {
            //Запуск потока под каждый ресурс
            ResourceThread thread = new ResourceThread(globalMap, src);
            try {
                System.out.println((exec.submit(thread::call)).get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                logger.warn("Чтение ресурса не выполненно", e);
                e.printStackTrace();
                exec.shutdown();
                throw new IOException("Чтение выполненно с ошибками.");
            }
        }
    }

    private static void write() {
        while (true) {
            if (exec.isTerminated()) {
                if (globalMap.isEmpty()) {
                    System.out.println("Кеш пуст");
                    logger.info("cache is empty");
                } else {
                    System.out.println("Данные успешно загружены в кеш");
                    logger.info("download cache complete");
                    new DataDAOFileImpl().write(globalMap);
                }
                break;
            }
        }
    }

}

