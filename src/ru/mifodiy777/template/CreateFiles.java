package ru.mifodiy777.template;

import ru.mifodiy777.task.DAO.DataDAO;
import ru.mifodiy777.task.DAO.impl.DataDAOImpl;
import ru.mifodiy777.task.entity.Data;

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

        DataDAO dataDAO = new DataDAOImpl();
        dataDAO.writeToFile(dataMap);
    }


}
