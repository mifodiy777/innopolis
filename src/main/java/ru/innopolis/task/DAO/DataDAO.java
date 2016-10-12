package ru.innopolis.task.DAO;

import ru.innopolis.task.entity.Data;

import java.util.List;
import java.util.Map;

/**
 * Created by innopolis on 04.10.16.
 */
public interface DataDAO {

    List<Data> readFile(String src);

    boolean writeToFile(Map<Integer,Data> set);

    List<Data> readUrl(String src);

}
