package com.yashwanth.routinecheck.DB.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Task {
    @PrimaryKey(autoGenerate = true)
    private int tid;
    @ColumnInfo(name = "title_name")
    private String title;
    @ColumnInfo(name = "title_description")
    private String description;
    @ColumnInfo(name = "title_comments")
    private String comments;

    public Task(String title, String description, String comments) {
        this.title = title;
        this.description = description;
        this.comments = comments;

    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
