package ru.mifodiy777.task;

/**
 * Created by innopolis on 09.10.16.
 */
public class Key {

    private final Object key;

    private long timelife;

    public Key(Object key, long timeout) {
        this.key = key;
        this.timelife = System.currentTimeMillis() + timeout;
    }

    public Key(Object key) {
        this.key = key;
    }

    public Object getKey(){
        return key;
    }

    public boolean isLive(long currentTimeMillis){
        return currentTimeMillis < timelife;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Key key1 = (Key) o;

        if (timelife != key1.timelife) return false;
        return key != null ? key.equals(key1.key) : key1.key == null;

    }

    @Override
    public int hashCode() {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + (int) (timelife ^ (timelife >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Key{" +
                "key=" + key +
                '}';
    }
}
