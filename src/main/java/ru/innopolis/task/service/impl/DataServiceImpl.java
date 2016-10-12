package ru.innopolis.task.service.impl;

import ru.innopolis.task.DAO.DataDAO;
import ru.innopolis.task.DublicatException;
import ru.innopolis.task.entity.Data;
import ru.innopolis.task.service.DataService;

import java.util.List;
import java.util.Map;

/**
 * Created by innopolis on 04.10.16.
 */
public class DataServiceImpl implements DataService {

    private Map<Integer, Data> dataMap;

    public DataServiceImpl(Map<Integer, Data> dataMap) {
        this.dataMap = dataMap;
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
