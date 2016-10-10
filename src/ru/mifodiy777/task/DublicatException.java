package ru.mifodiy777.task;

/**
 * Created by innopolis on 10.10.16.
 */
public class DublicatException extends Exception {


    public DublicatException() {
        System.err.println("Обнаружен дубликат при загрузке в кеш");
    }
}
