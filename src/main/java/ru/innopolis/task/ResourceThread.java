package ru.innopolis.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.innopolis.task.DAO.DataDAO;
import ru.innopolis.task.DAO.impl.DataDAOFileImpl;
import ru.innopolis.task.DAO.impl.DataDAOUrlImpl;
import ru.innopolis.task.entity.Data;
import ru.innopolis.task.service.DataService;
import ru.innopolis.task.service.impl.DataServiceImpl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by innopolis on 10.10.16.
 */
public class ResourceThread {

    private static Logger logger = LoggerFactory.getLogger(ResourceThread.class);

    //Переданный объект кеша
    private Map<Integer, Data> map;
    //Путь к файлу
    private String src;

    public ResourceThread(Map<Integer, Data> map, String src) {
        this.map = map;
        this.src = src;
    }

    public String call() throws DublicatException {
        DataDAO dataDAO;
        //Проверка ресурса URL или файл на локальной машине
        if (src.toLowerCase().startsWith("http")) {
            dataDAO = new DataDAOUrlImpl();
        } else {
            dataDAO = new DataDAOFileImpl();
        }
        List<Data> dataList = dataDAO.read(src);
        //Если получилось сформировать коллекцию Data из ресурса
        if (dataList != null && dataList.size() > 0) {
            //Добавляем в кеш
            new DataServiceImpl(map).putAll(dataList);
            return "Данные из ресурса " + src + " загруженны";
        } else {
            return "Кеш пуст";
        }

    }


}
