package com.code.ljn.taskcoordinate.manager;

import com.code.ljn.taskcoordinate.db.TaskCoordinateDB;
import com.code.ljn.taskcoordinate.model.Coordinate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * CoordinateManager
 *
 * @author ljn19
 * @time 2017-03-29.
 */

public class CoordinateManager {
    public static List<Coordinate> coordinateList = new ArrayList<Coordinate>();

    public static void addCoordiante(Coordinate coordinate){
        coordinateList.add(coordinate);
    }

    public static void removeCoordinate(Coordinate coordinate){
        coordinateList.remove(coordinate);
    }

}
