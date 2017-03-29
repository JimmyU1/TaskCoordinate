package com.code.ljn.taskcoordinate.util;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * 活动收集器
 * @author  JimmyU1
 * @time 2017-03-29
 */
public class ActivityCollector {
    //活动列表
    public static List<Activity> activityList = new ArrayList<Activity>();

    /**
     * 添加活动
     * @param activity 活动实例
     */
    public static void addActivity(Activity activity){
        activityList.add(activity);
    }

    /**
     * 移除活动
     * @param activity 活动实例
     */
    public static void removeActivity(Activity activity){
        activityList.remove(activity);
    }

    /**
     * 销毁所有活动
     */
    public static void finishAll(){
        for(Activity activity : activityList){
            if(activity.isFinishing()){
                activity.finish();
            }
        }
    }


}
