package ru.innopolis.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.innopolis.task.DAO.impl.DataDAOImpl;
import ru.innopolis.task.entity.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * Created by innopolis on 06.10.16.
 */
public class Run {

    private static Logger logger = LoggerFactory.getLogger(Run.class);

    private volatile Map<Integer, Data> globalMap = new ConcurrentHashMap<>();

    private AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) {
        //Проверка на ресурсы
        if(args.length==0){
            System.out.println("Нет ресурсов для загрузки в кеш");
            logger.warn("is't resources for download cache");
            return;
        }
        Run run = new Run();
        for (String src : args) {
            if (src != null && !src.isEmpty()) {
                    //Запуск потока под каждый ресурс
                    new ResourceThread(run.globalMap,run.count, src);
            }
        }
        while (true) {
            //Сравнение загруженных ресурсов с их исходным числом
            if(args.length == run.count.get()) {
                System.out.println("Данные успешно загружены в кеш");
                logger.info("download cache complete");
                System.out.println("Хотите выполнить выгрузку кеша в файл Y/N");
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                boolean startWrite = false;
                try {
                    //Если первая буква Y то файл будет выгружен
                    startWrite = (reader.readLine().toUpperCase().startsWith("Y"));
                } catch (IOException e) {
                    logger.error("error i/o in write to file " + e.getMessage());
                    System.out.println("Ошибка ввода/вывода");
                }
                if (startWrite) {
                    new DataDAOImpl().writeToFile(run.globalMap);
                }
                //После чего программа будет выполненна
                break;
            }
        }

    }
}
