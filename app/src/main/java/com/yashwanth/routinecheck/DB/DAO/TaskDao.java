package com.yashwanth.routinecheck.DB.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.yashwanth.routinecheck.DB.Entity.Task;

import java.util.List;

@Dao
public interface TaskDao {
    @Query("SELECT * FROM Task")
    LiveData<List<Task>> getTasks();

    @Query("SELECT title_name from Task where tid = :id")
    String getTitleName(int id);

    @Query("SELECT title_description from Task where tid = :id")
    String getDescription(int id);

    @Query("SELECT title_comments from Task where tid = :id")
    String getComments(int id);

    @Insert
    void insert(Task... tasks);

    @Delete
    void delete(Task... tasks);

    @Update
    void update(Task task);
}
