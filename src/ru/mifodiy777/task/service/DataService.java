package ru.mifodiy777.task.service;

import ru.mifodiy777.task.entity.Data;

import java.util.List;

/**
 * Created by innopolis on 04.10.16.
 */
public interface DataService {

    void setConfig();

    List<Data> readingObj();

    void chaching();

    boolean writeObj(List<Data> list);

    String getParametr(String msg);


}
