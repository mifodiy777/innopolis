package ru.mifodiy777.task.service.impl;

import ru.mifodiy777.task.Cache;
import ru.mifodiy777.task.Config;
import ru.mifodiy777.task.DAO.DataDAO;
import ru.mifodiy777.task.DAO.impl.DataDAOImpl;
import ru.mifodiy777.task.entity.Data;
import ru.mifodiy777.task.service.DataService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by innopolis on 04.10.16.
 */
public class DataServiceImpl implements DataService {

    private Config config;

    private DataDAO dataDAO;

    @Override
    public void setConfig() {
        config = new Config(getParametr("Введите путь исходящих ресурсов"),
                getParametr("Введите путь входящих ресурсов"),
                getParametr("Введите имя исходящего ресурса"),
                "/" + getParametr("Введите имя входящего ресурса"));
        System.out.println("Конфигурация принята");


    }

    @Override
    public List<Data> readingObj() {
        dataDAO = new DataDAOImpl();
        if (config != null) {
            try {
                dataDAO.read(config.getSrcInput() + config.getNameInput());
            } catch (IOException e) {
                System.out.println("Ошибка ввода/вывода");
            }
        }

        return null;
    }

    @Override
    public void chaching(Cache cache, List<Data> list) {
        Set<Data> dataSet = new HashSet<>();
        for (Data data : list) {
            if (!dataSet.contains(data)) {
                dataSet.add(data);
            } else {
                System.out.println("Обнаружен дубликат");
                break;
            }
        }
        if(cache != null){
            cache.addAll();
        }

    }

    @Override
    public Set<Data> getChachingObj(Cache cache) {

        return null;
    }

    @Override
    public boolean writeObj(Set<Data> list) {
        return false;
    }

    private String getParametr(String msg) {
        String parametr = "";
        System.out.println(msg);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                parametr = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
            if (!parametr.isEmpty()) {
                break;
            }
        }
        return parametr;
    }
}
