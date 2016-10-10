package ru.mifodiy777.task;

import ru.mifodiy777.task.DAO.DataDAO;
import ru.mifodiy777.task.DAO.impl.DataDAOImpl;
import ru.mifodiy777.task.entity.Data;
import ru.mifodiy777.task.service.DataService;
import ru.mifodiy777.task.service.impl.DataServiceImpl;

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

    private volatile Map<Integer, Data> globalMap = new ConcurrentHashMap<>();

    private AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) {
        if(args.length==0){
            System.out.println("Нет ресурсов для загрузки в кеш");
            return;
        }
        Run run = new Run();
        for (String src : args) {
            if (src != null && !src.isEmpty()) {
                    new ResourceThread(run.globalMap,run.count, src);
            }
        }
        while (true) {
            if(args.length == run.count.get()) {
                System.out.println("Данные успешно загружены в кеш");
                System.out.println("Хотите выполнить выгрузку кеша в файл Y/N");
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                boolean startWrite = false;
                try {
                    startWrite = (reader.readLine().toUpperCase().startsWith("Y"));
                } catch (IOException e) {
                    System.out.println("Ошибка ввода/вывода");
                }
                if (startWrite) {
                    new DataDAOImpl().writeToFile(run.globalMap);
                }
                break;
            }
        }

    }
}
