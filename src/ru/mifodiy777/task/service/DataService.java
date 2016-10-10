package ru.mifodiy777.task.service;

import ru.mifodiy777.task.DublicatException;
import ru.mifodiy777.task.entity.Data;
import java.util.List;
import java.util.Map;

/**
 * Created by innopolis on 04.10.16.
 */
public interface DataService {

    void putAll(List<Data> datas) throws DublicatException;

}
