package ru.innopolis.task;

/**
 * Created by innopolis on 10.10.16.
 */
public class DublicatException extends Exception {


    public DublicatException() {

        System.err.println("Обнаружен дубликат при загрузке в кеш");
    }

    @Override
    public String getMessage() {
        return "Обнаружен дубликат при загрузке в кеш";
    }
}
