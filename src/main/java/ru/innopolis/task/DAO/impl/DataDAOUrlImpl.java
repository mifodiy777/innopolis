package ru.innopolis.task.DAO.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.innopolis.task.DAO.DataDAO;
import ru.innopolis.task.entity.Data;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by innopolis on 13.10.16.
 */
public class DataDAOUrlImpl implements DataDAO {

    private static Logger logger = LoggerFactory.getLogger(DataDAOUrlImpl.class);


    /**
     * Метод чтений из URL
     * @param src путь с обязательным префиксом "http"
     * @return Коллекция десериализованных объектов Data
     */

    @Override
    public List<Data> read(String src) {
        List<Data> dataList = new ArrayList<>();
        URL target = null;
        try {
            target = new URL(src);
            try (ObjectInputStream inputStream = new ObjectInputStream(target.openStream())) {
                while (true) {
                    dataList.add((Data) inputStream.readObject());
                }
            } catch (IOException e) {
                logger.error("error i/o in readUrl " + e.getMessage());
                System.out.println("Ошибка ввода/вывода");
                Runtime process = Runtime.getRuntime();
                process.exit(1);

            } catch (ClassNotFoundException e) {
                if(dataList.isEmpty()){
                    logger.error("in file not date for deserialization " + e.getMessage());
                    System.out.println("Не найдено данных для оъекта Data");
                    Runtime process = Runtime.getRuntime();
                    process.exit(1);
                }
            }
        } catch (MalformedURLException e) {
            logger.error("error conection url " + e.getMessage());
            System.out.println("Ошибка подключение к URL");
            Runtime process = Runtime.getRuntime();
            process.exit(1);
        }
        return dataList;
    }

    /**
     * Сериализация объектов Data  с последующей записью в файл download.bin в корень приложения
     * @param set Кеш объектов
     * @return true - запись успешно завершена
     */
    @Override
    public boolean write(Map<Integer,Data> set) {
        System.out.println("Метод для URL не реализован");
        return false;
    }
}
