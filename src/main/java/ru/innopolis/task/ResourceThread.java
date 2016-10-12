package ru.innopolis.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.innopolis.task.DAO.DataDAO;
import ru.innopolis.task.DAO.impl.DataDAOImpl;
import ru.innopolis.task.entity.Data;
import ru.innopolis.task.service.DataService;
import ru.innopolis.task.service.impl.DataServiceImpl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by innopolis on 10.10.16.
 */
public class ResourceThread extends Thread {

    private static Logger logger = LoggerFactory.getLogger(ResourceThread.class);

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
        DataService service = new DataServiceImpl(map);
        List<Data> dataList;
        if (src.startsWith("http")) {
            dataList = dataDAO.readUrl(src);
        } else {
            dataList = dataDAO.readFile(src);
        }
        if (dataList != null && dataList.size() > 0) {
            try {
                service.putAll(dataList);
                sum.addAndGet(1);
            } catch (DublicatException e) {
                logger.error("in cache exist object Data " + e.getMessage());
                Runtime process = Runtime.getRuntime();
                process.exit(1);
            }
        }
    }


}
