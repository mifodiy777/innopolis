package ru.mifodiy777.task.entity;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by innopolis on 04.10.16.
 */

public class Data implements Serializable {

    private Integer id;

    private String name;

    private Long data;

    public Data(Integer id, String name, Long data) {
        this.id = id;
        this.name = name;
        this.data = data;
    }

    public Data() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getData() {
        return data;
    }

    public void setData(Long data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Data data1 = (Data) o;

        if (id != null ? !id.equals(data1.id) : data1.id != null) return false;
        if (name != null ? !name.equals(data1.name) : data1.name != null) return false;
        return data != null ? data.equals(data1.data) : data1.data == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }
}
