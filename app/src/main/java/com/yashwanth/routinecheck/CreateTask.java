package com.yashwanth.routinecheck;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.yashwanth.routinecheck.DB.AppDatabase;
import com.yashwanth.routinecheck.DB.DAO.TaskDao;
import com.yashwanth.routinecheck.DB.Entity.Task;
import com.yashwanth.routinecheck.recycler.TaskAdapter;

import java.util.List;

public class CreateTask extends AppCompatActivity {

    private TextInputEditText taskTitle;
    private TextInputEditText taskDescription;
    private TextInputEditText taskComments;
    private Button addTask;
    private boolean update;
    private AppDatabase database;
    private TaskDao taskDAO;
    private Task currentTask;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_task);

        taskTitle = findViewById(R.id.task_title);
        taskDescription = findViewById(R.id.task_description);
        taskComments = findViewById(R.id.task_comment);
        addTask = findViewById(R.id.add_task);

        final TaskAdapter adapter = new TaskAdapter(CreateTask.this);


//        //Database
        database = AppDatabase.getDatabaseInstance(this);
        taskDAO = database.taskDao();

//        if((currentTask = (Task) getIntent().getSerializableExtra("taskCreated")) != null){
//            getSupportActionBar().setTitle("update Task");
//            update = true;
//            addTask.setText("Update");
//            taskTitle.setText(currentTask.getTitle());
//            taskDescription.setText(currentTask.getDescription());
//            taskComments.setText(currentTask.getComments());
//        }

        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String taskT = taskTitle.getText().toString();
                final String taskD = taskDescription.getText().toString();
                final String taskC = taskComments.getText().toString();

                taskTitle.setText("");
                taskDescription.setText("");
                taskComments.setText("");

                AppDatabase.databaseWriteExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        Task task1 = new Task(taskT,taskD,taskC);
                        Intent intent = new Intent(CreateTask.this,MainActivity.class);
                        taskDAO.insert(task1);
                        startActivity(intent);
                    }
                });
            }
        });
    }
}
