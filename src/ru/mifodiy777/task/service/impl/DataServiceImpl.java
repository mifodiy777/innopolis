package ru.mifodiy777.task.service.impl;

import ru.mifodiy777.task.Config;
import ru.mifodiy777.task.entity.Data;
import ru.mifodiy777.task.service.DataService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by innopolis on 04.10.16.
 */
public class DataServiceImpl implements DataService {

   private Config config;

    @Override
    public void setConfig() {
        config = new Config(getParametr("Введите путь исходящих ресурсов"),
                            getParametr("Введите путь входящих ресурсов"),
                            getParametr("Введите имя исходящего ресурса"),
                            getParametr("Введите имя входящего ресурса"));


    }

    @Override
    public List<Data> readingObj() {
        return null;
    }

    @Override
    public void chaching() {

    }

    @Override
    public boolean writeObj(List<Data> list) {
        return false;
    }

    @Override
    public String getParametr(String msg){
        String parametr = "";
        System.out.println("Введите путь для сохранения файлов");
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
    }
}
