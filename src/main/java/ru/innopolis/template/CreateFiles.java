package ru.innopolis.template;

import ru.innopolis.task.DAO.DataDAO;
import ru.innopolis.task.DAO.impl.DataDAOFileImpl;
import ru.innopolis.task.entity.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by innopolis on 10.10.16.
 */
public class CreateFiles {

    Map<Integer, Data> dataMap = new HashMap<>();

    public static void main(String[] args) {
        new CreateFiles().createData();
    }

    /**
     * Метод формирования тестового файла
     */
    public void createData() {
        Data data1 = new Data(6, "six", 100l);
        Data data2 = new Data(7, "seven", 200l);
        Data data3 = new Data(8, "eight", 300l);
        Data data4 = new Data(9, "nine", 400l);
        Data data5 = new Data(10, "ten", 500l);

        dataMap.put(data1.getId(), data1);
        dataMap.put(data2.getId(), data2);
        dataMap.put(data3.getId(), data3);
        dataMap.put(data4.getId(), data4);
        dataMap.put(data5.getId(), data5);

        DataDAO dataDAO = new DataDAOFileImpl();
        dataDAO.write(dataMap);
    }


}
