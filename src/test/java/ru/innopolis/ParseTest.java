package ru.innopolis;

import static org.junit.Assert.*;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.*;
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

/**
 * Created by innopolis on 09.10.16.
 */
public class ParseTest {

    private static Logger log = LoggerFactory.getLogger(ParseTest.class);

    private Map<Integer, Data> globalMap = new HashMap<>();

    private Mockery context;

    @Before
    public void before() {
        globalMap.clear();
        this.context = new JUnit4Mockery();
        log.info("инициализация контекста jMock и кеша");
    }

    /**
     * Тест на не правельно введеный ресурс
     */
    @Test
    public void testEmptyFileRead() {
        assertNotNull("Тест не пройден - не существующий файл не выдал ошибок", new DataDAOFileImpl().read("qqq"));
    }

    /**
     * Тест работы исключения при загрузки дубликата
     *
     * @throws DublicatException
     */

    @Test(expected = DublicatException.class)
    public void testDublicatPut() throws DublicatException {
        DataService service = new DataServiceImpl(globalMap);
        List<Data> dataList = new ArrayList<>();
        dataList.add(new Data(1, "1", 100l));
        service.putAll(dataList);
        service.putAll(dataList);

    }

    /**
     * This test verify read is file
     */
    @Ignore
    @Test
    public void testRead() {
        Data data1 = new Data(1, "1", 100l);
        globalMap.put(data1.getId(), data1);
        new DataDAOFileImpl().write(globalMap);
        for (Data data : new DataDAOFileImpl().read("../download.bin")) {
            assertEquals("Тест не пройден - парсер работает не правильно", data1, data);
        }
    }

    /**
     * This test verify read is file using jMock
     */
    @Test
    public void testReadMock() {
        log.info("Тест чтения внешних ресурсов");
        final DataDAO dataDAO = context.mock(DataDAO.class);
        List<Data> dataList = new ArrayList<>();
        dataList.add(new Data(1, "1", 100l));
        context.checking(new Expectations() {{
            oneOf(dataDAO).read("../download.bin");
            will(returnValue(dataList));
        }});
        for (Data data : dataDAO.read("../download.bin")) {
            assertEquals("Тест не пройден - парсер работает не правильно", new Data(1, "1", 100l), data);
        }
    }

    /**
     * This test write to file and serialization instance Data
     */

    @Test
    public void testWrite() {
        Data data1 = new Data(1, "1", 100l);
        globalMap.put(data1.getId(), data1);
        assertTrue("Ошибка записи в файл", new DataDAOFileImpl().write(globalMap));
    }
}
