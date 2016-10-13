package ru.innopolis;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static Logger log = LoggerFactory.getLogger(ParseTest.class);

    /**
     * Тест на не правельно введеный ресурс
     */
    @BeforeClass
    public static void testEmptyFileRead() {
        DataDAO dataDAO = new DataDAOFileImpl();
        Assert.assertNotNull("Тест не пройден - не существующий файл не выдал ошибок",dataDAO.read("qqq"));
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
     * This test verify read is file
     */
    @Test
    public void testRead(){
        Map<Integer, Data> globalMap = new HashMap<>();
        Data data1 = new Data(1, "1", 100l);
        globalMap.put(data1.getId(), data1);
        new DataDAOFileImpl().write(globalMap);
        for (Data data :  new DataDAOFileImpl().read("../download.bin")){
            Assert.assertEquals("Тест не пройден - парсер работает не правильно",data1,data);
        }
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
