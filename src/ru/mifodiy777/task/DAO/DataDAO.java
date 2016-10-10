package ru.mifodiy777.task.DAO;

import ru.mifodiy777.task.entity.Data;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by innopolis on 04.10.16.
 */
public interface DataDAO {

    List<Data> readFile(String src);

    void writeToFile(Map<Integer,Data> set);

    List<Data> readUrl(String src);

}
