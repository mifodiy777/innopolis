package ru.innopolis;

import org.junit.Assert;
import org.junit.Test;
import org.junit.internal.runners.statements.Fail;
import ru.innopolis.task.DAO.DataDAO;
import ru.innopolis.task.DAO.impl.DataDAOImpl;
import ru.innopolis.task.entity.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.zip.DataFormatException;

/**
 * Created by innopolis on 09.10.16.
 */
public class ParseTest {

    @Test
    public void testRead() {
        //This test read file and deserialization
    }


    /**
     * This test write to file and serialization instance Data
     */
    @Test
    public void testWrite() {
        Map<Integer, Data> globalMap = new HashMap<>();

        Data data1 = new Data(1, "1", 100l);
        Data data2 = new Data(2, "2", 200l);
        Data data3 = new Data(3, "3", 300l);
        Data data4 = new Data(4, "4", 400l);
        Data data5 = new Data(5, "5", 500l);

        globalMap.put(data1.getId(), data1);
        globalMap.put(data2.getId(), data2);
        globalMap.put(data3.getId(), data3);
        globalMap.put(data4.getId(), data4);
        globalMap.put(data5.getId(), data5);

        DataDAO dataDAO = new DataDAOImpl();
        Assert.assertTrue("Ошибка записи в файл", dataDAO.writeToFile(globalMap));
    }
}
