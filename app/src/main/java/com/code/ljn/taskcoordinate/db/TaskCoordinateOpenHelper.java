package com.code.ljn.taskcoordinate.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * TaskCoordinateOpenHelper
 *
 * @author JimmyU1
 * @time 2017-03-28
 */
public class TaskCoordinateOpenHelper extends SQLiteOpenHelper {

    //创建Coordinate数据库的SQL语句
    private static final String CREATE_COORDINATE = "create table Coordinate(" +
            "id integer primary key autoincrement," +
            "create_date text)";

    //创建Task数据库的SQL语句
    private static final String CREATE_TASK = "create table Task(" +
            "id integer primary key autoincrement," +
            "keyword text," +
            "content text" +
            "is_urgent integer" +
            "is_important integer" +
            "is_finish integer" +
            "coordinate_id integer)";


    public TaskCoordinateOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_COORDINATE);
        db.execSQL(CREATE_TASK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
