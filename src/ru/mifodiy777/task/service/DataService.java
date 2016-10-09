package ru.mifodiy777.task.service;

import ru.mifodiy777.task.Cache;
import ru.mifodiy777.task.entity.Data;

import java.util.List;
import java.util.Set;

/**
 * Created by innopolis on 04.10.16.
 */
public interface DataService {

    void setConfig();

    List<Data> readingObj();

    void chaching(Cache cache, List<Data> list);

    Set<Data> getChachingObj(Cache cache);

    boolean writeObj(Set<Data> list);


}
