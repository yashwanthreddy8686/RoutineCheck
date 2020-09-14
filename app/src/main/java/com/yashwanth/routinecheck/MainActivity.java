package com.yashwanth.routinecheck;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.yashwanth.routinecheck.DB.AppDatabase;
import com.yashwanth.routinecheck.DB.DAO.TaskDao;
import com.yashwanth.routinecheck.DB.Entity.Task;
import com.yashwanth.routinecheck.recycler.TaskAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab;
    private RecyclerView recyclerView;
    private TaskAdapter adapter;
    private AppDatabase database;
    private TaskDao taskDAO;
    private List<Task> tasks;
    private int pos;
    private Button updateTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        //Recycler View
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TaskAdapter(this);
        recyclerView.setAdapter(adapter);

        tasks = new ArrayList<>();

        //Database
        database = AppDatabase.getDatabaseInstance(this);
        taskDAO = database.taskDao();

        taskDAO.getTasks().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> users) {
                adapter.setTasks(users);
            }
        });

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("MainActivity","pressed");
                startActivity(new Intent(MainActivity.this,CreateTask.class));
            }
        });

    }
}