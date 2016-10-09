package ru.mifodiy777.task;

import java.util.Map;
import java.util.concurrent.*;
import ru.mifodiy777.task.Key;

/**
 * Created by innopolis on 09.10.16.
 */
public class Cache<K, V> {

    private volatile ConcurrentHashMap<Key, V> globalMap = new ConcurrentHashMap<Key, V>();

    private long timeLive;

    public Cache(long timeLive) throws Exception {
        if (timeLive < 10) {
            throw new Exception("Время жизни слишком мало. Установите более 10 мс");
        }
        this.timeLive = timeLive;
    }

    /**
     * Метод для вставки обьекта в кеш
     * @param key ключ в кеше
     * @param data данные
     */
    public void put(K key, V data) {
        globalMap.put(new Key(key, timeLive), data);
    }

    /**
     * получение значения по ключу
     * @param key ключ для поиска с кеша
     * @return Обьект данных храняшийся в кеше
     */
    public V get(K key) {
        return globalMap.get(new Key(key));
    }

    /**
     * удаляет все значения по ключу из кеша
     * @param key - ключ
     */

    public void remove(K key) {
        globalMap.remove(new Key(key));
    }

    /**
     * Удаляет все значения из кеша
     */
    public void removeAll() {
        globalMap.clear();
    }

    /**
     * Полностью заменяет весь существующий кеш.
     * Время хранения по умолчанию.
     * @param map Карта с данными
     */
    public void setAll(Map<K, V> map) {
        ConcurrentHashMap tempmap = new ConcurrentHashMap<Key, V>();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            tempmap.put(new Key(entry.getKey(), timeLive), entry.getValue());
        }
        globalMap = tempmap;
    }

    /**
     * Добавляет к сущесвуещему кешу переданую карту
     * Время хранения по умолчанию.
     * @param map Карта с данными
     */
    public void addAll(Map<K, V> map) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }
}

