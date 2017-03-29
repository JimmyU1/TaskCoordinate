package com.code.ljn.taskcoordinate.model;


/**
 * 任务实体类
 *
 * @author JimmyU1
 * @time 2017-03-38
 */
public class Task {
    private int id;
    private String keyword;
    private String content;
    private int isUrgent;
    private int isImportant;
    private int isFinish;
    private int coordinateId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIsUrgent() {
        return isUrgent;
    }

    public void setIsUrgent(int isUrgent) {
        this.isUrgent = isUrgent;
    }

    public int getIsImportant() {
        return isImportant;
    }

    public void setIsImportant(int isImportant) {
        this.isImportant = isImportant;
    }

    public int getIsFinish() {
        return isFinish;
    }

    public void setIsFinish(int isFinish) {
        this.isFinish = isFinish;
    }

    public int getCoordinateId() {
        return coordinateId;
    }

    public void setCoordinateId(int coordinateId) {
        this.coordinateId = coordinateId;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", keyword='" + keyword + '\'' +
                ", content='" + content + '\'' +
                ", isUrgent=" + isUrgent +
                ", isImportant=" + isImportant +
                ", isFinish=" + isFinish +
                ", coordinateId=" + coordinateId +
                '}';
    }
}
