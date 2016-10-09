package ru.mifodiy777.task.DAO.impl;


import ru.mifodiy777.task.DAO.DataDAO;
import ru.mifodiy777.task.entity.Data;

import java.io.*;
import java.util.List;

/**
 * Created by innopolis on 04.10.16.
 */
public class DataDAOImpl implements DataDAO {

    public Data read(String src) {
        Data data = null;
        try (FileInputStream fileInputStream = new FileInputStream(src);
             ObjectInputStream inputStream = new ObjectInputStream(fileInputStream)) {
            data = (Data) inputStream.readObject();
        } catch (IOException e) {
            System.out.println("Не найден файл!");
        } catch (ClassNotFoundException e1) {
            System.out.println("В файле не найден объект Data");
        }
        return data;
    }


    public void write(Data data, String src) {
        try ( FileOutputStream fileStream = new FileOutputStream(src);
              ObjectOutputStream out = new ObjectOutputStream(fileStream)){
            out.writeObject(data);
            out.close();
        } catch (FileNotFoundException e) {
            System.out.println("Не найден файл!");
        } catch (IOException e) {
            System.out.println("Ошибка ввода вывода");
        }


    }
}
