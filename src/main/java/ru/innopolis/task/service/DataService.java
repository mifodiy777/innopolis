package ru.innopolis.task.service;

import ru.innopolis.task.DublicatException;
import ru.innopolis.task.entity.Data;
import java.util.List;

/**
 * Created by innopolis on 04.10.16.
 */
public interface DataService {

    void putAll(List<Data> datas) throws DublicatException;

}
