package ru.innopolis.task.DAO.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.innopolis.task.DAO.DataDAO;
import ru.innopolis.task.entity.Data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by innopolis on 13.10.16.
 */
public class DataDAOFileImpl implements DataDAO{

    private static Logger logger = LoggerFactory.getLogger(DataDAOFileImpl.class);

    /**
     * Метод чтения из файла
     * @param src Путь к файлу
     * @return Коллекция десериализованных объектов Data
     */
    @Override
    public List<Data> read(String src) {
        List<Data> dataList = new ArrayList<>();
        try (FileInputStream fileInputStream = new FileInputStream(src);
             ObjectInputStream inputStream = new ObjectInputStream(fileInputStream)) {
            while (true) {
                dataList.add((Data) inputStream.readObject());
            }
        } catch (IOException e) {
            if(dataList.isEmpty()){
                logger.error("in file not date for deserialization " + e.getMessage());
                System.out.println("Не найдено данных для оъекта Data");
                Runtime process = Runtime.getRuntime();
                process.exit(1);
            }
        } catch (ClassNotFoundException e) {
            logger.warn("ClassNotFoundException in readFile " + e.getMessage());
            System.out.println("В файле не найден объект Data");
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
        try (FileOutputStream fileStream = new FileOutputStream("../download.bin");
             ObjectOutputStream out = new ObjectOutputStream(fileStream)) {
            for (Data data : set.values()) {
                out.writeObject(data);
            }
            System.out.println("Файл записан;");
            return true;
        } catch (FileNotFoundException e) {
            logger.error("file not found for writeFile " + e.getMessage());
            System.out.println("Не найден файл!");
            return false;
        } catch (IOException e) {
            logger.error("error i/o write file");
            System.out.println("Ошибка ввода вывода " + e.getMessage());
            return false;
        }

    }
}
