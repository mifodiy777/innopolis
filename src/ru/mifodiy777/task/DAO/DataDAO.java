package ru.mifodiy777.task.DAO;

import ru.mifodiy777.task.entity.Data;

import java.io.IOException;

/**
 * Created by innopolis on 04.10.16.
 */
public interface DataDAO {

    Data read(String src) throws IOException;

    void write(String data) throws IOException;
}
