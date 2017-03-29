package com.code.ljn.taskcoordinate.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.code.ljn.taskcoordinate.model.Coordinate;
import com.code.ljn.taskcoordinate.model.Task;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * TaskCoordianteDB
 *
 * @author JimmyU1
 * @time 2017-03-28
 */
public class TaskCoordinateDB {
    //数据库名
    public static final String DB_NAME = "task_coordinate";
    //数据库版本号
    public static final int VERSION = 1;
    //SQLite实例
    private SQLiteDatabase db;
    //TaskCoordinateDB实例
    private static TaskCoordinateDB taskCoordinateDB;

    /**
     * 私有构造函数
     *
     * @param context 应用上下文
     */
    private TaskCoordinateDB(Context context) {
        TaskCoordinateOpenHelper dbHelper = new TaskCoordinateOpenHelper(context, DB_NAME, null, VERSION);
        db = dbHelper.getWritableDatabase();
    }

    /**
     * 获取TaskCoordinateDB实例
     *
     * @param context 应用上下文
     * @return TaskCoordinateDB实例
     */
    public synchronized static TaskCoordinateDB getIntance(Context context) {
        if (taskCoordinateDB == null) {
            taskCoordinateDB = new TaskCoordinateDB(context);
        }
        return taskCoordinateDB;
    }

    /**
     * 存储坐标系实例到数据库中
     *
     * @param coordinate 坐标系实例
     */
    public void saveCoordinate(Coordinate coordinate) {
        if (coordinate != null) {
            ContentValues values = new ContentValues();
            values.put("create_date", coordinate.getCreateDate());
            db.insert("Coordinate", null, values);
        }
    }

    /**
     * 存储任务实例到数据库中
     *
     * @param task 任务实例
     */
    public void saveTask(Task task) {
        if (task != null) {
            ContentValues values = new ContentValues();
            values.put("keyword", task.getKeyword());
            values.put("content", task.getContent());
            values.put("is_urgent", task.getIsUrgent());
            values.put("is_important", task.getIsImportant());
            values.put("is_finish", task.getIsFinish());
            values.put("coordinate_id", task.getCoordinateId());
            db.insert("Task", null, values);
        }
    }

    /**
     * 加载所有坐标系实例
     *
     * @return 坐标系列表
     */
    public List<Coordinate> loadCoordinates() {
        List<Coordinate> coordinateList = new ArrayList<Coordinate>();
        Cursor cursor = db.query("Coordinates", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Coordinate coordinate = new Coordinate();
                coordinate.setId(cursor.getInt(cursor.getColumnIndex("id")));
                coordinate.setCreateDate(cursor.getString(cursor.getColumnIndex("create_date")));
                coordinateList.add(coordinate);
            } while (cursor.moveToNext());
        }
        return coordinateList;
    }

    /**
     * 通过创建日期获取坐标系
     * @param date 日期
     * @return 坐标系列表
     */
    public List<Coordinate> getCoordinateByDate(Date date) {
        List<Coordinate> coordinateList = new ArrayList<Coordinate>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        String dateString = sdf.format(date);
        Cursor cursor = db.query("Coordinate", null, "create_date = ?", new String[]{dateString}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Coordinate coordinate = new Coordinate();
                coordinate.setId(cursor.getInt(cursor.getColumnIndex("id")));
                coordinate.setCreateDate(cursor.getString(cursor.getColumnIndex("create_date")));
                coordinateList.add(coordinate);
            } while (cursor.moveToNext());
        }
        return coordinateList;
    }

    /**
     * 加载所有任务
     *
     * @param coordinateId 坐标系ID
     * @return 任务列表
     */
    public List<Task> loadAllTasks(int coordinateId) {
        List<Task> taskList = new ArrayList<Task>();
        Cursor cursor = db.query("Task", null, "coordinate_id = ?", new String[]{String.valueOf(coordinateId)}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Task task = new Task();
                task.setId(cursor.getInt(cursor.getColumnIndex("id")));
                task.setKeyword(cursor.getString(cursor.getColumnIndex("keyword")));
                task.setContent(cursor.getString(cursor.getColumnIndex("context")));
                task.setIsUrgent(cursor.getInt(cursor.getColumnIndex("is_urgent")));
                task.setIsImportant(cursor.getInt(cursor.getColumnIndex("is_important")));
                task.setIsFinish(cursor.getInt(cursor.getColumnIndex("is_finish")));
                task.setCoordinateId(cursor.getInt(cursor.getColumnIndex("coordinate_id")));
                taskList.add(task);
            } while (cursor.moveToNext());
        }
        return taskList;
    }

    /**
     * 加载紧急且重要的任务
     *
     * @param coordinateId 坐标系ID
     * @return 任务列表
     */
    public List<Task> loadUrgentAndImportantTasks(int coordinateId) {
        List<Task> taskList = new ArrayList<Task>();
        Cursor cursor = db.query("Task", null, "coordinate_id = ? && is_urgent = 1 && is_important = 1", new String[]{String.valueOf(coordinateId)}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Task task = new Task();
                task.setId(cursor.getInt(cursor.getColumnIndex("id")));
                task.setKeyword(cursor.getString(cursor.getColumnIndex("keyword")));
                task.setContent(cursor.getString(cursor.getColumnIndex("context")));
                task.setIsUrgent(cursor.getInt(cursor.getColumnIndex("is_urgent")));
                task.setIsImportant(cursor.getInt(cursor.getColumnIndex("is_important")));
                task.setIsFinish(cursor.getInt(cursor.getColumnIndex("is_finish")));
                task.setCoordinateId(cursor.getInt(cursor.getColumnIndex("coordinate_id")));
                taskList.add(task);
            } while (cursor.moveToNext());
        }
        return taskList;
    }

    /**
     * 加载紧急但不重要的任务
     *
     * @param coordinateId 坐标系ID
     * @return 任务列表
     */
    public List<Task> loadUrgentOnlyTasks(int coordinateId) {
        List<Task> taskList = new ArrayList<Task>();
        Cursor cursor = db.query("Task", null, "coordinate_id = ? && is_urgent = 1 && is_important = 0", new String[]{String.valueOf(coordinateId)}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Task task = new Task();
                task.setId(cursor.getInt(cursor.getColumnIndex("id")));
                task.setKeyword(cursor.getString(cursor.getColumnIndex("keyword")));
                task.setContent(cursor.getString(cursor.getColumnIndex("context")));
                task.setIsUrgent(cursor.getInt(cursor.getColumnIndex("is_urgent")));
                task.setIsImportant(cursor.getInt(cursor.getColumnIndex("is_important")));
                task.setIsFinish(cursor.getInt(cursor.getColumnIndex("is_finish")));
                task.setCoordinateId(cursor.getInt(cursor.getColumnIndex("coordinate_id")));
                taskList.add(task);
            } while (cursor.moveToNext());
        }
        return taskList;
    }

    /**
     * 加载重要但不紧急的任务
     *
     * @param coordinateId 坐标系ID
     * @return 任务列表
     */
    public List<Task> loadImportantOnlyTasks(int coordinateId) {
        List<Task> taskList = new ArrayList<Task>();
        Cursor cursor = db.query("Task", null, "coordinate_id = ? && is_urgent = 0 && is_important = 1", new String[]{String.valueOf(coordinateId)}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Task task = new Task();
                task.setId(cursor.getInt(cursor.getColumnIndex("id")));
                task.setKeyword(cursor.getString(cursor.getColumnIndex("keyword")));
                task.setContent(cursor.getString(cursor.getColumnIndex("context")));
                task.setIsUrgent(cursor.getInt(cursor.getColumnIndex("is_urgent")));
                task.setIsImportant(cursor.getInt(cursor.getColumnIndex("is_important")));
                task.setIsFinish(cursor.getInt(cursor.getColumnIndex("is_finish")));
                task.setCoordinateId(cursor.getInt(cursor.getColumnIndex("coordinate_id")));
                taskList.add(task);
            } while (cursor.moveToNext());
        }
        return taskList;
    }

    /**
     * 加载既不紧急也不重要的任务（普通任务）
     *
     * @param coordinateId 坐标系ID
     * @return 任务列表
     */
    public List<Task> loadNormalTasks(int coordinateId) {
        List<Task> taskList = new ArrayList<Task>();
        Cursor cursor = db.query("Task", null, "coordinate_id = ? && is_urgent = 0 && is_important = 0", new String[]{String.valueOf(coordinateId)}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Task task = new Task();
                task.setId(cursor.getInt(cursor.getColumnIndex("id")));
                task.setKeyword(cursor.getString(cursor.getColumnIndex("keyword")));
                task.setContent(cursor.getString(cursor.getColumnIndex("context")));
                task.setIsUrgent(cursor.getInt(cursor.getColumnIndex("is_urgent")));
                task.setIsImportant(cursor.getInt(cursor.getColumnIndex("is_important")));
                task.setIsFinish(cursor.getInt(cursor.getColumnIndex("is_finish")));
                task.setCoordinateId(cursor.getInt(cursor.getColumnIndex("coordinate_id")));
                taskList.add(task);
            } while (cursor.moveToNext());
        }
        return taskList;
    }

    /**
     * 将任务状态转换成已完成
     *
     * @param taskId 任务ID
     */
    public void finishTask(int taskId) {
        ContentValues values = new ContentValues();
        values.put("is_finish", 1);
        db.update("Task", values, "id = ? ", new String[]{String.valueOf(taskId)});
    }

    /**
     * 将任务状态转换成未完成
     *
     * @param taskId 任务ID
     */
    public void unFinishTask(int taskId) {
        ContentValues values = new ContentValues();
        values.put("is_finish", 0);
        db.update("Task", values, "id = ? ", new String[]{String.valueOf(taskId)});
    }

    /**
     * 更新任务数据
     *
     * @param task 任务实例
     */
    public void updateTask(Task task) {
        ContentValues values = new ContentValues();
        values.put("keyword", task.getKeyword());
        values.put("content", task.getContent());
        values.put("is_urgent", task.getIsUrgent());
        values.put("is_important", task.getIsImportant());
        values.put("is_finish", task.getIsFinish());
        //values.put("coordinate_id", task.getCoordinateId());
        db.update("Task", values, "id = ? ", new String[]{String.valueOf(task.getId())});
    }

    /**
     * 删除任务
     *
     * @param taskId 任务ID
     * @return 删除的任务数
     */
    public int deleteTask(int taskId) {
        return db.delete("Task", "id = ?", new String[]{String.valueOf(taskId)});
    }

}
