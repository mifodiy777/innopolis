package ru.mifodiy777.task;

import ru.mifodiy777.task.DAO.DataDAO;
import ru.mifodiy777.task.DAO.impl.DataDAOImpl;
import ru.mifodiy777.task.entity.Data;
import ru.mifodiy777.task.service.DataService;
import ru.mifodiy777.task.service.impl.DataServiceImpl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by innopolis on 10.10.16.
 */
public class ResourceThread extends Thread {

    private Map<Integer, Data> map;

    private AtomicInteger sum;

    private String src;

    public ResourceThread(Map<Integer, Data> map, AtomicInteger sum, String src) {
        this.map = map;
        this.sum = sum;
        this.src = src;
        this.start();
    }

    @Override
    public void run() {
        DataDAO dataDAO = new DataDAOImpl();
        DataService service = new DataServiceImpl(map, dataDAO);
        List<Data> dataList;
        if (src.startsWith("http")) {
            dataList = dataDAO.readUrl(src);
        } else {
            dataList = dataDAO.readFile(src);
        }
        if (dataList != null) {
            try {
                service.putAll(dataList);
                sum.addAndGet(1);
            } catch (DublicatException e) {
                Runtime process = Runtime.getRuntime();
                process.exit(1);
            }
        }
    }


}
