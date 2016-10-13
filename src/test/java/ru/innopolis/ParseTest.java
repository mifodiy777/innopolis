package ru.innopolis;

import org.junit.Assert;
import org.junit.Test;
import ru.innopolis.task.DAO.DataDAO;
import ru.innopolis.task.DAO.impl.DataDAOFileImpl;
import ru.innopolis.task.DublicatException;
import ru.innopolis.task.entity.Data;
import ru.innopolis.task.service.DataService;
import ru.innopolis.task.service.impl.DataServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by innopolis on 09.10.16.
 */
public class ParseTest {

    /**
     * Тест на не правельно введеный ресурс
     */
    @Test
    public void testEmptyFileRead() {
        DataDAO dataDAO = new DataDAOFileImpl();
        Assert.assertNotNull(dataDAO.read("qqq"));
    }

    /**
     * Тест работы исключения при загрузки дубликата
     * @throws DublicatException
     */

    @Test(expected = DublicatException.class)
    public void testDublicatPut() throws DublicatException {
        Map<Integer,Data> integerDataMap = new ConcurrentHashMap<>();
        DataService service = new DataServiceImpl(integerDataMap);
        List<Data> dataList = new ArrayList<>();
        dataList.add(new Data(1, "1", 100l));
        service.putAll(dataList);
        service.putAll(dataList);

    }


    /**
     * This test write to file and serialization instance Data
     */
    @Test
    public void testWrite() {
        Map<Integer, Data> globalMap = new HashMap<>();
        Data data1 = new Data(1, "1", 100l);
        globalMap.put(data1.getId(), data1);
        Assert.assertTrue("Ошибка записи в файл", new DataDAOFileImpl().write(globalMap));
    }
}
