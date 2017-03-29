package com.code.ljn.taskcoordinate.model;

/**
 * 坐标系实体类
 *
 * @author JimmyU1
 * @time 2017-03-28
 */
public class Coordinate {
    private int id;
    private String createDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "id=" + id +
                ", createDate='" + createDate + '\'' +
                '}';
    }
}
