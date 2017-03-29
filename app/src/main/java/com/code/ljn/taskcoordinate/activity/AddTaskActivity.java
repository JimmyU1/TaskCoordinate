package com.code.ljn.taskcoordinate.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.code.ljn.taskcoordinate.R;
import com.code.ljn.taskcoordinate.db.TaskCoordinateDB;
import com.code.ljn.taskcoordinate.model.Coordinate;
import com.code.ljn.taskcoordinate.model.Task;

import java.util.Date;

/**
 * AddTaskActivity
 *
 * @author ljn19
 * @time 2017-03-29.
 */
public class AddTaskActivity extends BaseActivity implements View.OnClickListener{

    private EditText keywordText;
    private EditText contentText;
    private CheckBox isUrgentCheckBox;
    private CheckBox isImportantCheckBox;
    private Button addTaskButton;
    private Button cancelButton;

    private TaskCoordinateDB taskCoordinateDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task);
        this.setTitle("添加任务");

        //初始化控件
        keywordText = (EditText) findViewById(R.id.keyword_view);
        contentText = (EditText) findViewById(R.id.content_view);
        isUrgentCheckBox = (CheckBox) findViewById(R.id.is_urgent);
        isImportantCheckBox = (CheckBox) findViewById(R.id.is_important);
        addTaskButton = (Button) findViewById(R.id.add_task);
        cancelButton = (Button) findViewById(R.id.cancel_action);

        //初始化数据库
        taskCoordinateDB = TaskCoordinateDB.getIntance(this);

        addTaskButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_task:
                Task task = createTask();
                taskCoordinateDB.saveTask(task);
                break;
            case R.id.cancel_action:
                break;
            default:
                break;
        }

    }

    private Task createTask(){
        Task task = new Task();
        Coordinate coordinate = taskCoordinateDB.getCoordinateByDate(new Date()).get(0);
        int isUrgent = 0;
        int isImportant = 0;
        if(isUrgentCheckBox.isChecked()){
            isUrgent = 1;
        }
        if (isImportantCheckBox.isChecked()){
            isImportant = 1;
        }
        task.setKeyword(keywordText.getText().toString());
        task.setContent(contentText.getText().toString());
        task.setIsUrgent(isUrgent);
        task.setIsImportant(isImportant);
        task.setIsFinish(0);
        task.setCoordinateId(coordinate.getId());
        return task;
    }
}
