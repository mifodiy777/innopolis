package ru.innopolis.task.DAO.impl;


import ru.innopolis.task.DAO.DataDAO;
import ru.innopolis.task.entity.Data;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by innopolis on 04.10.16.
 */
public class DataDAOImpl implements DataDAO {


    @Override
    public List<Data> readFile(String src) {
        List<Data> dataList = new ArrayList<>();
        try (FileInputStream fileInputStream = new FileInputStream(src);
             ObjectInputStream inputStream = new ObjectInputStream(fileInputStream)) {
            while (true) {
                dataList.add((Data) inputStream.readObject());
            }
        } catch (IOException e) {
            if(dataList.isEmpty()){
                System.out.println("Не найдено данных для оъекта Data");
            }
        } catch (ClassNotFoundException e1) {
            System.out.println("В файле не найден объект Data");
        }
        return dataList;
    }

    @Override
    public List<Data> readUrl(String src) {
        List<Data> dataList = new ArrayList<>();
        URL target = null;
        try {
            target = new URL(src);
            try (ObjectInputStream inputStream = new ObjectInputStream(target.openStream())) {
                while (true) {
                    dataList.add((Data) inputStream.readObject());
                }
            } catch (IOException e) {
                System.out.println("Ошибка ввода/вывода");

            } catch (ClassNotFoundException e) {
                if(dataList.isEmpty()){
                    System.out.println("Не найдено данных для оъекта Data");
                }
            }
        } catch (MalformedURLException e) {
            System.out.println("Ошибка подключение к URL");
        }
        return dataList;
    }

    @Override
    public void writeToFile(Map<Integer,Data> set) {
        try (FileOutputStream fileStream = new FileOutputStream("../download.bin");
             ObjectOutputStream out = new ObjectOutputStream(fileStream)) {
            for (Data data : set.values()) {
                out.writeObject(data);
            }
            System.out.println("Файл записан;");
        } catch (FileNotFoundException e) {
            System.out.println("Не найден файл!");
        } catch (IOException e) {
            System.out.println("Ошибка ввода вывода");
        }

    }
}
