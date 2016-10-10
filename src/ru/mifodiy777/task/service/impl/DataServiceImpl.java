package ru.mifodiy777.task.service.impl;

import ru.mifodiy777.task.DAO.DataDAO;
import ru.mifodiy777.task.DublicatException;
import ru.mifodiy777.task.entity.Data;
import ru.mifodiy777.task.service.DataService;

import java.util.List;
import java.util.Map;

/**
 * Created by innopolis on 04.10.16.
 */
public class DataServiceImpl implements DataService {

    private Map<Integer, Data> dataMap;

    private DataDAO dataDAO;

    public DataServiceImpl(Map<Integer, Data> dataMap, DataDAO dataDAO) {
        this.dataMap = dataMap;
        this.dataDAO = dataDAO;
    }

    @Override
    public void putAll(List<Data> dataList) throws DublicatException {
        for (Data data : dataList) {
            if (!dataMap.containsValue(data)) {
                dataMap.put(data.getId(), data);
            } else {
                throw new DublicatException();
            }

        }
    }

}
